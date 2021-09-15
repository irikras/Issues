package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.IssueComparator;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.Status;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueRepository repo = new IssueRepository();
    private IssueManager manager = new IssueManager(repo);
    private IssueComparator comparator = new IssueComparator();

    private Issue issue1 = new Issue(1, "name1", Status.OPEN, "author1", Label.BUG, "project1", "5.7 M2", new Assignee(4, "Name4", "Surname4"), "11.01.2021", 3, 4);
    private Issue issue2 = new Issue(2, "name2", Status.CLOSED, "author2", Label.INVALID, "project3", "5.7 Backlog", new Assignee(3, "Name3", "Surname3"), "14.02.2020", 11, 1);
    private Issue issue3 = new Issue(3, "name3", Status.OPEN, "author3", Label.QUESTION, "project1", null, new Assignee(2, "name2", "Surname2"), "23.02.2020", 15, 0);
    private Issue issue4 = new Issue(4, "name4", Status.CLOSED, "author4", Label.BUG, "project2", null, new Assignee(3, "Name3", "Surname3"), "02.04.2021", 3, 2);
    private Issue issue5 = new Issue(5, "name5", Status.OPEN, "author5", Label.QUESTION, "project2", "5.7 M2", new Assignee(4, "Name4", "Surname4"), "26.04.2020", 0, 0);
    private Issue issue6 = new Issue(6, "name6", Status.CLOSED, "author6", Label.NEW, "project4", null, new Assignee(1, "Name1", "Surname1"), "11.03.2021", 70, 7);

    @BeforeEach
    public void setUp() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        manager.add(issue6);
    }

    @Test
    void shouldFindAllOpen() {
        List<Issue> actual = manager.findAllOpen();
        List<Issue> expected = Arrays.asList(issue1, issue3, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindAllClosed() {
        List<Issue> actual = manager.findAllClosed();
        List<Issue> expected = Arrays.asList(issue2, issue4, issue6);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthor() {
        List<Issue> actual = manager.findByAuthor(issue -> issue.getAuthor().equals("author2"));
        List<Issue> expected = Arrays.asList(issue2);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthorIfNotExist() {
        List<Issue> actual = manager.findByAuthor(issue -> issue.getAuthor().equals("author7"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabel() {
        List<Issue> actual = manager.findByLabel(issue -> issue.getLabel().equals(Label.QUESTION));
        List<Issue> expected = Arrays.asList(issue3, issue5);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabelIfNotExist() {
        List<Issue> actual = manager.findByLabel(issue -> issue.getLabel().equals(Label.DOCUMENTATION));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssignee() {
        List<Issue> actual = manager.findByAssignee(new Assignee(3, "Name3", "Surname3"));
        List<Issue> expected = Arrays.asList(issue2, issue4);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssigneeIfNotExist() {
        List<Issue> actual = manager.findByAssignee(new Assignee(5, "Name5", "Surname5"));
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}