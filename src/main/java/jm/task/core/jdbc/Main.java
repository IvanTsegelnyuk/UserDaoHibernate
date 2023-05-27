package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("ivan", "ivanov", (byte) 32);
        userService.saveUser("igor", "igorevich", (byte) 15);
        userService.saveUser("anton", "petrov", (byte) 25);
        userService.saveUser("kirill", "nikolaev", (byte) 14);
        for (User user: userService.getAllUsers()) System.out.println(user);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
