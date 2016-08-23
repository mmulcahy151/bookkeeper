package com.example.mmulcahy.mybooks;

import java.sql.Date;

/**
 * Created by mmulcahy on 8/15/2016.
 */

public class Book {
    private int id;
    private int owner;
    private String title;
    private String authorFirst;
    private String authorMiddle;
    private String authorLast;
    private int publicationDate;
    private int pages;
    private java.sql.Date dateFinished;
    private int timesRead;
    private int glad;
    private int readAgain;
    private String comments;
    private int deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirst() {
        return authorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    public String getAuthorMiddle() {
        return authorMiddle;
    }

    public void setAuthorMiddle(String authorMiddle) {
        this.authorMiddle = authorMiddle;
    }

    public String getAuthorLast() {
        return authorLast;
    }

    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public int getTimesRead() {
        return timesRead;
    }

    public void setTimesRead(int timesRead) {
        this.timesRead = timesRead;
    }

    public int getGlad() {
        return glad;
    }

    public void setGlad(int glad) {
        this.glad = glad;
    }

    public int getReadAgain() {
        return readAgain;
    }

    public void setReadAgain(int readAgain) {
        this.readAgain = readAgain;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
