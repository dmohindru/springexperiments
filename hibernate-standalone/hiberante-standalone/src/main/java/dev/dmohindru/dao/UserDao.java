package dev.dmohindru.dao;

import dev.dmohindru.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class UserDao {
    private EntityManagerFactory entityManagerFactory;
    public UserDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public User saveUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(user);
        entityTransaction.commit();
        entityManager.close();

        return user;
    }
}
