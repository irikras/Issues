package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Assignee;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.domain.Status;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    private IssueRepository repo = new IssueRepository();

    private Issue issue1 = new Issue(1, "name1", Status.OPEN, "author1", Label.BUG, "project1", "5.7 M2", new Assignee(4, "Name4", "Surname4"), "11.01.2021", 3, 4);
    private Issue issue2 = new Issue(2, "name2", Status.CLOSED, "author2", Label.INVALID, "project3", "5.7 Backlog", new Assignee(3, "Name3", "Surname3"), "14.02.2020", 11, 1);
    private Issue issue3 = new Issue(3, "name3", Status.OPEN, "author3", Label.QUESTION, "project1", null, new Assignee(2, "name2", "Surname2"), "23.02.2020", 15, 0);
    private Issue issue4 = new Issue(4, "name4", Status.CLOSED, "author4", Label.BUG, "project2", null, new Assignee(3, "Name3", "Surname3"), "02.04.2021", 3, 2);
    private Issue issue5 = new Issue(5, "name5", Status.OPEN, "author5", Label.QUESTION, "project2", "5.7 M2", new Assignee(4, "Name4", "Surname4"), "26.04.2020", 0, 0);
    private Issue issue6 = new Issue(6, "name6", Status.CLOSED, "author6", Label.NEW, "project4", null, new Assignee(1, "Name1", "Surname1"), "11.03.2021", 70, 7);

    @BeforeEach
    public void setUp() {
        repo.save(issue1);
        repo.save(issue2);
        repo.save(issue3);
        repo.save(issue4);
        repo.save(issue5);
        repo.save(issue6);
    }

    @Test
    public void shouldFindAll() {
        Collection<Issue> actual = repo.findAll();
        Collection<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue2);
        expected.add(issue3);
        expected.add(issue4);
        expected.add(issue5);
        expected.add(issue6);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Issue actual = repo.findById(3);
        assertEquals(issue3, actual);
    }

    @Test
    void shouldFindByIdIfNotExist() {
        Issue actual = repo.findById(7);
        assertEquals(null, actual);
    }

    @Test
    void shouldOpenById() {
        repo.openById(1);
        Issue byId = repo.findById(1);
        assertSame(byId.getStatus(), Status.OPEN);
    }

    @Test
    void shouldCloseById() {
        repo.closeById(2);
        Issue byId = repo.findById(2);
        assertSame(byId.getStatus(), Status.CLOSED);
    }
}