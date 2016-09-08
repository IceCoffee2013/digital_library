package edu.unsw.comp9321.controller;

import edu.unsw.comp9321.dao.BookDB;
import edu.unsw.comp9321.entity.Article;
import edu.unsw.comp9321.entity.Query;
import edu.unsw.comp9321.entity.QueryType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Langley on 8/26/16.
 */
public class SearchController {

    private BookDB bookDB;

    public SearchController() {
        bookDB = (BookDB) System.getProperties().get("bookDb");
        if (bookDB == null) {
            System.err.println("bookdb is null");
        }
    }

    public SearchController(BookDB bookDB) {
        this.bookDB = bookDB;
    }

    /**
     * Search both title & author
     * @param target
     * @return
     */
    public List<Article> simpleSearch(String target) {
        List<Article> result = new ArrayList<>();
        result.addAll(simpleSearchByTitle(target));
        for (Article a : simpleSearchByAuthor(target)) {
            if (!result.contains(a)) {
                result.add(a);
            }
        }
        System.out.println("generic search result: " + result.size());
        return result;
    }

    public List<Article> simpleSearchByTitle(String title) {
        List<Article> resource = bookDB.getArticles();
        List<Article> result = new ArrayList<>();
        for (Article article : resource) {
            if (article.getTitle().contains(title)) {
                result.add(article);
            }
        }
        System.out.println("title search result: " + result.size());
        return result;
    }

    public List<Article> simpleSearchByAuthor(String author) {
        List<Article> resource = bookDB.getArticles();
        List<Article> result = new ArrayList<>();
        for (Article article : resource) {
            if (article.getAuthor().contains(author)) {
                result.add(article);
            }
        }
        System.out.println("author search result: " + result.size());
        return result;
    }

    public List<Article> advanceSearch(List<Query> queryList) {
        List<Article> result = new ArrayList<>();
        if (queryList == null || queryList.isEmpty()) {
            return result;
        }

        result = resultFilter(bookDB.getArticles(), queryList.remove(0));
        for (Query q : queryList) {
            result = resultFilter(result, q);
        }

        return result;
    }

    private List<Article> resultFilter(List<Article> target, Query query) {
        List<Article> result = new ArrayList<>();
        if (query == null || query.getQuery() == null || query.getSearchIn() == null) {
            System.err.println("query == null || query.getQuery() == null || query.getQueryType() == null");
            return result;
        }
        QueryType queryType = query.getQueryType();
        if (query.getQueryType() == QueryType.NONE) {
            queryType = QueryType.AND;
        }

        if (queryType == QueryType.AND) {
            result = searchByType(target, query.getQuery(), query.getSearchIn());
            return result;
        }

        if (queryType == QueryType.OR) {
            List<Article> queryResult = searchByType(query.getQuery(), query.getSearchIn());
            result.addAll(target);
            for (Article a : queryResult) {
                if (!result.contains(a)) {
                    result.add(a);
                }
            }
            return result;
        }

        if (queryType == QueryType.NOT) {
            List<Article> queryResult = searchByType(query.getQuery(), query.getSearchIn());
            result.addAll(target);
            for (Article a : queryResult) {
                if (result.contains(a)) {
                    result.remove(a);
                }
            }
            return result;
        }

        return result;
    }

    /**
     *
     * @param query
     * @param type searchIn
     * @return
     */
    public List<Article> searchByType(String query, QueryType type) {
        return this.searchByType(bookDB.getArticles(), query, type);
    }

    private List<Article> searchByType(List<Article> target, String query, QueryType type) {
        List<Article> result = new ArrayList<>();
        if (target == null || target.isEmpty()) {
            return result;
        }
        for (Article article : target) {
            if (type == QueryType.TITLE && article.getTitle().contains(query)) {
                result.add(article);
                continue;
            }
            if (type == QueryType.AUTHOR && article.getAuthor().contains(query)) {
                result.add(article);
                continue;
            }
            if (type == QueryType.YEAR && article.getYear().contains(query)) {
                result.add(article);
                continue;
            }
            if (type == QueryType.JOURNAL && article.getJournel().contains(query)) {
                result.add(article);
                continue;
            }
            if (type == QueryType.VOLUME && article.getVolume().contains(query)) {
                result.add(article);
                continue;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        List<Article> list = new ArrayList<>();
        list.add(new Article());
        System.getProperties().put("list", list);
        System.out.println("[]"+System.getProperties().get("list"));
    }
}
