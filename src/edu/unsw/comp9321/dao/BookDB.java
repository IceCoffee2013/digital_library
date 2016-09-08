package edu.unsw.comp9321.dao;

import edu.unsw.comp9321.Constant;
import edu.unsw.comp9321.controller.SearchController;
import edu.unsw.comp9321.entity.Article;
import edu.unsw.comp9321.util.DBLPSaxHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Access Book DB.
 * Created by Langley on 8/26/16.
 */
public class BookDB {

    private DBLPSaxHandler dblpSaxHandler;
    private List<Article> articles = new ArrayList<>();

    public BookDB(String path) {
        init(path);
    }

    private void init(String path) {
        dblpSaxHandler = new DBLPSaxHandler(0);

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(path), dblpSaxHandler);
        } catch (Exception e) {
            System.err.println("DB init error: " + e);
        }

        this.articles = dblpSaxHandler.getArticles();
        System.out.println("BookDB articles counts: " + articles.size());
    }

    public List<Article> getIndexArticles() {
        List<Article> result = new ArrayList<>();
        int count = 0;
        if (this.articles.size() < Constant.INDEX_ARTICLE_COUNT) {
            count = this.articles.size();
        } else {
            count = Constant.INDEX_ARTICLE_COUNT;
        }
        List<Integer> indexs = randomIndex(count, Constant.INDEX_ARTICLE_COUNT);
        for (int i : indexs) {
            result.add(this.articles.get(i));
        }
        return result;
    }

    /**
     *
     * @param count db size
     * @param times index article size
     * @return
     */
    private List<Integer> randomIndex(int count, int times) {
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        while (times > 0) {
            int index = random.nextInt(count);
            if (!result.contains(index)) {
                result.add(index);
                times--;
            }
        }
        return result;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Article getItemByID(int id) {
        for (Article a : this.articles) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public static void main(String args[]) {
        BookDB bookDB = new BookDB(Constant.XML_FULL_PATH);
        SearchController searchController = new SearchController(bookDB);
        List<Article> result = new ArrayList<>();
        result = searchController.simpleSearchByAuthor("Joachim");
        System.out.println("Search result: " + result);
    }

}
