package com.ga.dao;

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

public class ProfileDaoTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Transaction transaction;

    @InjectMocks
    private ProfileDaoImpl profileDao;

    @InjectMocks
    private User user;

    @Mock
    Query<User> query;

    
}