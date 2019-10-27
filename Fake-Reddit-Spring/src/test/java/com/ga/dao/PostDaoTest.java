package com.ga.dao;

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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PostDaoTest {
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

    @InjectMocks
    private PostDaoImpl postDao;

    @InjectMocks
    private Post post;

    @InjectMocks
    private User user;

    @Mock
    private List<Comment> comments;

    @Mock
    private List<Post> posts;

    @Mock
    Query<User> query;

    @Before
    public void init() {
        post.setId(1L);
        post.setTitle("test");
        post.setDescription("description");

        comment.setId(1L);
        comment.setDescription("description");
        comment.setPost(post);

        post.addComment(comment);

        comments.add(comment);
        user.setUserId(1L);
        user.setUsername("username");
        post.setUser(user);
        posts.add(post);

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void createPost_Post_success(){
        when(session.createQuery(anyString())).thenReturn(query);
        Post postTest = postDao.createPost(post, user.getUsername());

        assertNotNull("Test returned null object, expected non-null", postTest);
        assertEquals(postTest, post);

    }
    @Test
    public void UpdatePost_Post_Success(){
        when(session.get(Post.class, 1L)).thenReturn(post);
        Post postTest = postDao.updatePost(post, 1l);
        assertNotNull("Test returned null object, expected non-null", postTest);
        assertEquals(postTest, post);
    }
    @Test
    public void deletePost_Post_Success(){
        when(session.get(Post.class, 1L)).thenReturn(post);
        Long postTestId = postDao.deletePostByPostId(1L);
        assertNotNull("Test returned null object, expected non-null", postTestId);
        assertEquals(postTestId, post.getId());
    }
    @Test
    public void getCommentsByPostId_Post_Success(){
        when(session.get(Post.class, 1L)).thenReturn(post);
        List<Comment> commentList = postDao.getCommentsByPostId(1L);
        assertNotNull("Test returned null object, expected non-null", commentList);
        assertEquals(commentList, comments);
    }
    @Test
    public void getPostList_Post_Success(){
        when(session.createQuery(anyString())).thenReturn(query);
        List<Post> listPost = postDao.listPosts();
        assertNotNull("Test returned null object, expected non-null", listPost);
    }
}