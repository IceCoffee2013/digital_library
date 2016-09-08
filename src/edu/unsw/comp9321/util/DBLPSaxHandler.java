package edu.unsw.comp9321.util;

import edu.unsw.comp9321.Constant;
import edu.unsw.comp9321.entity.Article;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Langley on 8/26/16.
 */
public class DBLPSaxHandler extends DefaultHandler {

    // save lab.
    private Stack<String> stack = new Stack<String>();

    private static int uniqueID = 0;

    // data
    private String mdate;
    private String key;

    private String author;
    private String title;
    private String journal;
    private String volume;
    private String year;
    private String pages;
    private String url;
    private String ee;

    private boolean fullRecordMode = false;
    private int targetCount = -1;
    private int currentCount = 0;
    private List<Article> articles = new ArrayList<>();

    /**
     * Specify record count if count is not zero.
     * @param targetCount Records Count
     */
    public DBLPSaxHandler(int targetCount) {
        if (targetCount == 0) {
            fullRecordMode = true;
        } else {
            fullRecordMode = false;
            this.targetCount = targetCount;
        }
    }

    @Override
    public void startDocument() throws SAXException {
//        System.out.println("start document -> parse begin");
    }

    @Override
    public void endDocument() throws SAXException {

//        System.out.println("end document -> parse finished");
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        // Add lab in stack.
        stack.push(qName);

        // Deal with attributes.
        for (int i = 0; i < attributes.getLength(); ++i) {
            String attrName = attributes.getQName(i);
            String attrValue = attributes.getValue(i);

            if ("mdate".equals(attrName)) {
                mdate = attrValue;
            } else if ("key".equals(attrName)) {
                key = attrValue;
            }

//            System.out.println("属性： " + attrName + "=" + attrValue);

        }

    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        // Get lab name
        String tag = stack.peek();

        if ("author".equals(tag)) {
            author = new String(ch, start, length);
        } else if ("title".equals(tag)) {
            title = new String(ch, start, length);
        } else if ("journal".equals(tag)) {
            journal = new String(ch, start, length);
        } else if ("volume".equals(tag)) {
            volume = new String(ch, start, length);
        } else if ("year".equals(tag)) {
            year = new String(ch, start, length);
        } else if ("pages".equals(tag)) {
            pages = new String(ch, start, length);
        } else if ("url".equals(tag)) {
            url = new String(ch, start, length);
        } else if ("ee".equals(tag)) {
            ee = new String(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        // these lab has finished parse, pop lab.
        stack.pop();

        if ("article".equals(qName)) {

            if (Constant.DEBUG_MODE) {
                System.out.println("Book info: -------");
                System.out.println("    mdate: " + mdate + "  key: " + key + " ID: " + uniqueID);
                System.out.println("    author: " + author);
                System.out.println("    title: " + title);
                System.out.println("    journal: " + journal);
                System.out.println("    volume: " + volume);
                System.out.println("    year: " + year);
                System.out.println("    pages: " + pages);
                System.out.println("    url: " + url);
                System.out.println("    ee: " + ee);
                System.out.println();
            }

            // save article
            Article article = new Article();
            article.setId(uniqueID);
            article.setMdate(mdate);
            article.setKey(key);
            article.setAuthor(author);
            article.setTitle(title);
            article.setJournel(journal);
            article.setVolume(volume);
            article.setYear(year);
            article.setPages(pages);
            article.setUrl(url);
            article.setEe(ee);
            articles.add(article);

            // Increase Count.
            if (fullRecordMode == false) {
                currentCount++;
            }
            uniqueID++;
        }

        if (fullRecordMode == false && currentCount >= targetCount) {
            System.err.println("Reach Max Count..." + currentCount);
            throw new SAXExitException("Reach Max Count...");
        }

    }

    /**
     *
     * @return Article Entity List
     */
    public List<Article> getArticles() {
        return articles;
    }
}
