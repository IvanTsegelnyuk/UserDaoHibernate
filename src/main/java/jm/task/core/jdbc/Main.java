package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasilii", "vasiliev", (byte) 12);
        userService.saveUser("ivan", "ivanov", (byte) 15);
        userService.saveUser("petr", "petrov", (byte) 16);
        userService.saveUser("nikita", "nikitov", (byte) 20);
        for(User user: userService.getAllUsers()) System.out.println(user);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
