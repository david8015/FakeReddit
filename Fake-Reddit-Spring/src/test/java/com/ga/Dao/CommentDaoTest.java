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

;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
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

        post.addComment(comment);
        comment.setPost(post);

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void createComment_Comment_Success(){
        String username = "username";
        Long postid = 1L;

        when(session.createQuery(anyString())).thenReturn(query);
        when(session.get(Post.class, 1L)).thenReturn(post);

        Comment commentTest = commentDao.createComment(comment, username, postid);

        assertNotNull("Test returned null object, expected non-null", commentTest);
        assertEquals(commentTest, comment);

        }

    }
