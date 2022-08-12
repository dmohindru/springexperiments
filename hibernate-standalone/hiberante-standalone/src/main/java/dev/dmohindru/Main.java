package dev.dmohindru;

import dev.dmohindru.dao.UserDao;
import dev.dmohindru.entity.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("hibernatePlayground");

        UserDao userDao = new UserDao(entityManagerFactory);

        User newUser = new User();
        newUser.setFirstName("Poonam");
        newUser.setLastName("Mohindru");
        newUser.setAge(16);

        newUser = userDao.saveUser(newUser);
        System.out.println("----DONE----");

    }
}
