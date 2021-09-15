package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class Issue {
    private int id;
    private String name;
    private Status status;
    private String author;
    private Label label;
    private String projects;
    private String milestones;
    private Set<Assignee> assigneeSet = new HashSet<>();
    private String date;
    private int commentsCount;
    private int pullRequestCount;

    public Issue(int id, String name, Status status, String author, Label label, String projects,
                 String milestones, Assignee assignee, String date, int commentsCount, int pullRequestCount) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.author = author;
        this.label = label;
        this.milestones = milestones;
        this.assigneeSet.add(assignee);
        this.date = date;
        this.commentsCount = commentsCount;
        this.pullRequestCount = pullRequestCount;
        this.projects = projects;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return label;
    }
}

