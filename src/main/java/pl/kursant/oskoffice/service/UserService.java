package pl.kursant.oskoffice.service;

import java.util.List;
import pl.kursant.oskoffice.model.User;

public interface UserService {
    
    List<User> getUser();
    
    User addUser(User user);
    
    User getUserById(Integer userId);
    
    User updateUser(User user);
    
    void deleteUser(Integer userId);
    
}
