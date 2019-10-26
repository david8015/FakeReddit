package com.ga.service;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class CommentServiceTest {
    @Mock
    CommentDao commentDao;

    @Mock
    UserService userService;

    @InjectMocks
    CommentServiceImpl commentService;

//    @InjectMocks
//    private Comment comment;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    Comment comment = new Comment();

    @Before
    public void initiliazeComment(){
        comment.setId(1L);
        comment.setDescription("description");
    }

    @Test
    public void createComment_comment_Success(){
        Long postId = 2l;
        String username = "username";

        Comment expectedComment = null;

        when(userService.returnUsername()).thenReturn(username);

        when(commentDao.createComment(any(), anyString(), anyLong())).thenReturn(comment);

        expectedComment = commentService.createComment(comment, 1L);

        System.out.println(expectedComment);

        assertNotNull(expectedComment);

    }

    @Test
    public void deleteByCommentId_Comment_Success(){
        Long commentId = 1L;

        when(commentDao.deleteCommentById(commentId)).thenReturn(commentId);

        Long expectedId = commentService.deleteCommentById(commentId);

        assertNotNull(expectedId);

        assertEquals(expectedId, commentId);

    }

    @Test
    public void createComment_Comment_Success(){
        Long commentId = 1l;

        when(commentDao.updateComment(any(), anyLong())).thenReturn(comment);

        Comment expectedComment = commentService.updateComment(comment, commentId);

        assertNotNull(expectedComment);

        assertEquals(comment, expectedComment);


    }

}
