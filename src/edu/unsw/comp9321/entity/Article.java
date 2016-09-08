package edu.unsw.comp9321.entity;

/**
 * Created by Langley on 8/26/16.
 */
public class Article {

    /**
     * Unique Identification.
     */
    private int id = -1;
    private String mdate = "";
    private String key = "";

    private String author = "";
    private String title = "";
    private String journel = "";
    private String volume = "";
    private String year = "";
    private String pages = "";
    private String url = "";
    private String ee = "";

    public Article() {

    }

    public Article(int id, String mdate, String key, String author, String title, String journel, String volume, String year, String pages, String url, String ee) {
        this.id = id;
        this.mdate = mdate;
        this.key = key;
        this.author = author;
        this.title = title;
        this.journel = journel;
        this.volume = volume;
        this.year = year;
        this.pages = pages;
        this.url = url;
        this.ee = ee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJournel() {
        return journel;
    }

    public void setJournel(String journel) {
        this.journel = journel;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEe() {
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (author != null ? !author.equals(article.author) : article.author != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        return year != null ? year.equals(article.year) : article.year == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", mdate='" + mdate + '\'' +
                ", key='" + key + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", journel='" + journel + '\'' +
                ", volume='" + volume + '\'' +
                ", year='" + year + '\'' +
                ", pages='" + pages + '\'' +
                ", url='" + url + '\'' +
                ", ee='" + ee + '\'' +
                '}';
    }
}

