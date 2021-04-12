
package pl.kursant.oskoffice.form;


public class NewAccountForm {
    
//    Atrybuty z user
    private String login;
    private String password;
    private String email;
    
//    Atrybuty z student
    private String firstName;
    private String lastName;
    private String pkkNumber;
    private String street;
    private String city;
    private String zipCode;
    
//    Atrybuty z employee
    private String position;

    public NewAccountForm(String login, String password, String email, String firstName, String lastName, String pkkNumber, String street, String city, String zipCode, String position) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pkkNumber = pkkNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.position = position;
    }
      
    public NewAccountForm() {}

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
