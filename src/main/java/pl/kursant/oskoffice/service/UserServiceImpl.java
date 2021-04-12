package pl.kursant.oskoffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kursant.oskoffice.model.User;
import pl.kursant.oskoffice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepo;

    @Override
    public List<User> getUser() {
        return userRepo.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepo.findOne(userId);
    }

    @Override
    public User updateUser(User user) {
        return userRepo.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepo.delete(userId);
    }
    
}
