package com.ga.Dao;

import com.ga.dao.CommentDaoImpl;
import com.ga.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.junit.Before;

import static org.mockito.Mockito.when;

public class CommentDaoTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Transaction transaction;

    @InjectMocks
    Comment comment;

    @InjectMocks
    private CommentDaoImpl commentDao;

    @Before
    public void init() {
        comment.setId(1L);
        comment.setDescription("description");

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void createComment_Comment_Success(){
        String username = "username";
        Long postid = 1l;


    }

    }
