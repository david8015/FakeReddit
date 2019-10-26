package com.ga.Dao;

import com.ga.dao.CommentDaoImpl;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.junit.Before;

import static org.mockito.ArgumentMatchers.*;
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
    private Comment comment;

    @Mock
    User user;


    @InjectMocks
    private CommentDaoImpl commentDao;

    @InjectMocks
    Post post;

    @Mock
    Query<User> query;

    @Mock
    Query<Comment> commentQuery;

    @Before
    public void init() {
        post.setId(1L);
        post.setTitle("test");
        post.setDescription("description");

        comment.setId(1L);
        comment.setDescription("description");

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void createComment_Comment_Success(){
        String username = "username";
        Long postid = 1l;

        when(session.createQuery(anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(user);
        when(session.get(anyLong()).thenReturn(post);

        }

    }
