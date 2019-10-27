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
    public Profile getUserProfile(Long userId) {
        User user;
        Profile profile = null;
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();

            user = session.get(User.class, userId);

            profile = user.getProfile();

        }finally {
            session.close();


        return profile;
        }
    }

    @Override
    public Long deleteProfileById(Long profileId) {
        Session session = sessionFactory.getCurrentSession();

        Profile profileToDelete = null;

        try {
            session.beginTransaction();

            profileToDelete = session.get(Profile.class, profileId);

            session.delete(profileToDelete);

            session.getTransaction().commit();


        } finally {
            session.close();
        }
        return profileToDelete.getProfileId();
    }

    @Override
    public Profile updateProfile(Profile profile, Long profileId) {
        Profile profileToUpdate = null;
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            profileToUpdate = session.get(Profile.class, profileId);
            profileToUpdate.setAddress(profile.getAddress());
            profileToUpdate.setMobile(profile.getAddress());


            session.update(profileToUpdate);

            session.getTransaction().commit();

        } finally {
            session.close();
        }
        return profileToUpdate;
    }

}
