package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();


    void saveUser(User user);
    void updateUser(User user);
    User getUserById(long id);
    void delete(long id);
}
