package com.project.helpdesk.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Lob
    private String summary;

    @Column(length = 1000)
    private String description;

    private String category;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    void preSave(){
        if(this.createdOn == null){
            this.createdOn = LocalDateTime.now();
        }
        this.updatedOn = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate(){
        this.updatedOn = LocalDateTime.now();
    }

    public Ticket() {
    }

    public Ticket(long id, String summary, Priority priority, Status status, LocalDateTime createdOn,
                  LocalDateTime updatedOn, String description, String category, String email) {
        this.id = id;
        this.summary = summary;
        this.priority = priority;
        this.status = status;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.description = description;
        this.category = category;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
