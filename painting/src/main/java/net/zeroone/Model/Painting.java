package net.zeroone.Model;

public class Painting {

    private int id;
    private String author;
    private String title;
    private int year;
    
    /**
     * we need this constructor or else jackson databind will give us a weird error ... "unable to deserialize"
     * jackson databind, when it's converting json to java object, actually tries to always use the no-args constructor.
     */
    public Painting() {
    }
    public Painting(int id, String author, String title, int year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    
    @Override
    public String toString() {
        return "Painting [id=" + id + ", author=" + author + ", title=" + title + ", year=" + year + "]";
    }

    
}