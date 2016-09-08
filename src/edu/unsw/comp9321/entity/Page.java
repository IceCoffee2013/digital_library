package edu.unsw.comp9321.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Langley on 9/1/16.
 */
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Article> articles = new ArrayList<>();
    private int number = -1;
    private Paginator paginator;

    public Page(List<Article> articles, int number, Paginator paginator) {
        this.articles = articles;
        this.number = number;
        this.paginator = paginator;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean hasNext() {
        return this.number < this.paginator.getNumPages();
    }

    public boolean hasPrevious() {
        return this.number > 1;
    }

    public int nextPageNumber() {
        if (paginator.validateNumber(this.number + 1)) {
            return number + 1;
        }
        return -1;
    }

    public int previousPageNumber() {
        if (paginator.validateNumber(this.number - 1)) {
            return number - 1;
        }
        return -1;
    }

    public int startIndex() {
        if (paginator.getCount() == 0) {
            return 0;
        }
        return paginator.getPerPage() * (number - 1) + 1;
    }

    public int end() {
        if (number == paginator.getNumPages()) {
            return paginator.getCount();
        }
        return number * paginator.getPerPage();
    }


}
