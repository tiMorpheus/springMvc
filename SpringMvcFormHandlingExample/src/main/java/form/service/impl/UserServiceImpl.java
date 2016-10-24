package form.service.impl;

import form.dao.UserDao;
import form.model.User;
import form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void saveOrUpdate(User user) {

        if (findById(user.getId())==null) {
            userDao.save(user);
        } else {
            userDao.update(user);
        }

    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

}
