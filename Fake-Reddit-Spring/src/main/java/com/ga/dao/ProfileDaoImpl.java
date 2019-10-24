package com.ga.dao;

import com.ga.entity.Profile;
import com.ga.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDaoImpl implements ProfileDao {
    @Autowired
    UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Profile createUserProfile(String email, Profile profile) {
        User user = userDao.getUserByEmail(email);

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            session.save(profile);
            user.setProfile(profile);
            session.update(user);

            session.getTransaction().commit();
        } finally {
            session.close();
        }

        return profile;
    }

    @Override
    public Profile getUserProfile(String email) {
        User user = userDao.getUserByEmail(email);
        return user.getProfile();
    }

}
