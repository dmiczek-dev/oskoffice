package pl.kursant.oskoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Query(value = "SELECT * FROM public.users WHERE login = ?1", nativeQuery = true)
    public User findByLogin(String login);
    
    @Modifying
    @Query(value = "INSERT INTO public.user_role(user_id, role_id) VALUES (?1, ?2)", nativeQuery = true)
    public void createNewUserRole(Integer userId, Integer roleId);    
    
}
