package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
//        UserServiceImpl userService = new UserServiceImpl();
//        userService.createUsersTable();
//
//        userService.saveUser("ivan", "ivanov", (byte) 32);
//        userService.saveUser("igor", "igorevich", (byte) 15);
//        userService.saveUser("anton", "petrov", (byte) 25);
//        userService.saveUser("kirill", "nikolaev", (byte) 14);
//        for (User user: userService.getAllUsers()) System.out.println(user);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Vasilii", "vasiliev", (byte) 12);
        userDaoHibernate.saveUser("ivan", "ivanov", (byte) 15);
        userDaoHibernate.saveUser("petr", "petrov", (byte) 16);
        userDaoHibernate.saveUser("nikita", "nikitov", (byte) 20);
        for(User user: userDaoHibernate.getAllUsers()) System.out.println(user);
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
