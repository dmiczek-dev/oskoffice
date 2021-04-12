
package pl.kursant.oskoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @SequenceGenerator(sequenceName="user_user_id_seq", name="user_user_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_user_id_seq")
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
    
    @Column(name = "enabled")
    private Boolean enabled;

    public User(Integer userId, String login, String password, String email, Boolean enabled) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }
    
    public User() {};

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
   
    
}
