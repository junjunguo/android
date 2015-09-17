package com.junjunguo.resumedownload;

/**
 * This file is part of ResumeDownload
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on September 17, 2015.
 */
public class Message {
    private Integer progress;
    private String message;

    public Message(Integer progress, String message) {
        this.progress = progress;
        this.message = message;
    }


    public Message(Integer progress) {
        this.progress = progress;
        this.message = "";
    }


    public Message(String message) {
        this.progress = 0;
        this.message = message;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "Message{" +
                "progress=" + progress +
                ", message='" + message + '\'' +
                '}';
    }
}
