package com.ai.prompt_response.model;

public class Tutorial {
    private String title;
    private String content;
    private String createdYear;
    private String createdBy;

    public Tutorial(String title, String content, String createdYear, String createdBy) {
        this.title = title;
        this.content = content;
        this.createdYear = createdYear;
        this.createdBy = createdBy;
    }

    public Tutorial() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedYear() {
        return createdYear;
    }

    public void setCreatedYear(String createdYear) {
        this.createdYear = createdYear;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
