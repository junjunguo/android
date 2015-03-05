package com.junjunguo.simplesqlitedb;

/**
 * This file is part of Simple SQLite DB
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 05/03/15.
 * <p/>
 */
public class TextReport {
    private long id;
    private String textreport;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTextreport() {
        return textreport;
    }

    public void setTextreport(String textreport) {
        this.textreport = textreport;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return textreport;
    }

}
