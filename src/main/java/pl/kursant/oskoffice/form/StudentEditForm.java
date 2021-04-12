
package pl.kursant.oskoffice.form;


public class StudentEditForm {
    
//    Atrybuty z user
    private Integer userId;
    private String login;
    private String password;
    private String email;
    private boolean enabled;
    
//    Atrybuty z student
    private String firstName;
    private String lastName;
    private String pkkNumber;
    private String street;
    private String city;
    private String zipCode;

    public StudentEditForm(Integer userId, String login, String password, String email, boolean enabled, String firstName, String lastName, String pkkNumber, String street, String city, String zipCode) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pkkNumber = pkkNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public StudentEditForm() {}
    
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPkkNumber() {
        return pkkNumber;
    }

    public void setPkkNumber(String pkkNumber) {
        this.pkkNumber = pkkNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    
    
}