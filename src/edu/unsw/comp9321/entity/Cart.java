package edu.unsw.comp9321.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Article> bookList;

    public Cart() {
        this.bookList = new ArrayList<>();
    }

    public void addItem(Article article) {
        if (article != null) {
            this.bookList.add(article);
        }
    }

    public void removeItem(Article article) {
        if (article == null) {
            return;
        }
        Iterator<Article> it = this.bookList.iterator();
        while (it.hasNext()) {
            Article a = it.next();
            if (a.getTitle().equals(article.getTitle()) && a.getAuthor().equals(article.getAuthor())) {
                it.remove();
            }
        }
    }

    public boolean isCartNotEmpty() {
        if (this.bookList != null && this.bookList.size() != 0) {
            System.out.println("cart size: " + this.bookList.size());
            return true;
        }
        return false;
    }

    public List<Article> getBookList() {
        return bookList;
    }

    public void setBookList(List<Article> bookList) {
        this.bookList = bookList;
    }
}
