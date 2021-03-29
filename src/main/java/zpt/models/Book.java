package zpt.models;

import javax.servlet.ServletContext;
import java.util.ArrayList;

public class Book implements Comparable<Book>{
    protected int id;
    protected String title;
    protected String author;
    protected int year;

    public Book(String title, String author, int year) {
        //this.id =
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book() {
        this.title = "temp";
        this.author = "temp";
        this.year = 2000;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Book o) {
        if (this.getId() > o.getId()) {
            return 1;
        } else if (this.getId() < o.getId()) {
            return -1;
        }
        return 0;
    }
}
