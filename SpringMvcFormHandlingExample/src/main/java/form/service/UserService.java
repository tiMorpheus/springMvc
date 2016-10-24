package form.service;

import form.model.User;

import java.util.List;


public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    void saveOrUpdate(User user);

    void delete(int id);

}