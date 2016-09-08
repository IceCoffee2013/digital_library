package edu.unsw.comp9321.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Generate Page slice.
 * Created by Langley on 9/1/16.
 */
public class Paginator {

    private List<Article> articles;
    private int perPage = 10;

    public Paginator(List<Article> articles, int perPage) {
        this.articles = articles;
        this.perPage = perPage;
    }

    public boolean validateNumber(int number) {
        if (number < 1) {
            System.err.println("number is invalid: " + number);
            return false;
        }
        if (number > this.articles.size()) {
            System.err.println("number is oversize: " + number);
            return false;
        }
        return true;
    }

    public Page getPage(int number) {
        if (!validateNumber(number)) {
            return null;
        }
        int bottom = (number - 1) * this.perPage;
        int top = bottom + this.perPage;
        if (top > this.articles.size()) {
            top = this.articles.size();
        }
        List<Article> tmpList = new ArrayList<>();
        for (int i = bottom; i < top; i++) {
            tmpList.add(this.articles.get(i));
        }
        Page page = new Page(tmpList, number, this);
        return page;
    }

    public int getCount() {
        return this.articles.size();
    }

    public int getNumPages() {
        if (this.articles == null || this.articles.isEmpty()) {
            return 0;
        }
        int numPages = (int) Math.ceil(this.getCount() / this.perPage);
        return numPages;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
