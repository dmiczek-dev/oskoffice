
package pl.kursant.oskoffice.controller;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.kursant.oskoffice.form.EmployeeEditForm;
import pl.kursant.oskoffice.form.NewAccountForm;
import pl.kursant.oskoffice.form.StudentEditForm;
import pl.kursant.oskoffice.model.Employee;
import pl.kursant.oskoffice.model.EmployeeDetailsView;
import pl.kursant.oskoffice.model.Student;
import pl.kursant.oskoffice.model.StudentProgress;
import pl.kursant.oskoffice.model.User;
import pl.kursant.oskoffice.model.StudentDetailsView;
import pl.kursant.oskoffice.repository.EmployeeDetailsViewRepository;
import pl.kursant.oskoffice.repository.EmployeeRepository;
import pl.kursant.oskoffice.repository.StudentProgressRepository;
import pl.kursant.oskoffice.repository.StudentRepository;
import pl.kursant.oskoffice.repository.UserRepository;
import pl.kursant.oskoffice.repository.StudentDetailsViewRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    StudentRepository studentRepo;
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    StudentProgressRepository studentProgressRepo;
    
    @Autowired
    EmployeeRepository employeeRepo;
   
    @Autowired
    StudentDetailsViewRepository studentDetailsViewRepo;
    
    @Autowired
    EmployeeDetailsViewRepository employeeDetailsViewRepo;
    
    @GetMapping(value = "/main")
    public ModelAndView getIndexView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("admin/main");        
        
        return model;
    }
    
    @GetMapping(value = "/zakladanie-konta")
    public ModelAndView getCreatAccountView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("admin/create-account");        
        
        return model;
    }
    
    @PostMapping(value = "/zakladanie-konta/stworz")
    public String postCreateAccountView(@ModelAttribute("NewAccountForm") NewAccountForm form, HttpServletRequest request) {
                
        try {
            String passwordEncoded = new BCryptPasswordEncoder().encode(form.getPassword());
            
//            Tworzenie nowego usera
            User newUser = new User();
            List<User> allUsers = userRepo.findAll();
            for(User oneUser : allUsers) {
                if(form.getLogin().equals(oneUser.getLogin())) {
                    return "redirect:/admin/zakladanie-konta?=loginNotAllowed";
                } else {
                    newUser.setLogin(form.getLogin());
                }
            }
            newUser.setPassword(passwordEncoded);
            newUser.setEmail(form.getEmail());
            newUser.setEnabled(true);
            userRepo.saveAndFlush(newUser);
            
            if(form.getPosition().equals("instructor")) {
//                Tworzenie konta instruktora
                Employee newEmployee = new Employee();
                newEmployee.setFirstName(form.getFirstName());
                newEmployee.setLastName(form.getLastName());
                newEmployee.setPosition("Instruktor");
                newEmployee.setStreet(form.getStreet());
                newEmployee.setCity(form.getCity());
                newEmployee.setZipCode(form.getZipCode());
                newEmployee.setUserId(newUser.getUserId());
                employeeRepo.saveAndFlush(newEmployee);
//                Dodawanie uprawnien
                userRepo.createNewUserRole(newUser.getUserId(), 2); // roleId(2) = ROLE_INSTRUCTOR
            } else {
//                Tworzenie nowego studenta
                Student newStudent = new Student();
                newStudent.setFirstName(form.getFirstName());
                newStudent.setLastName(form.getLastName());
                newStudent.setPkkNumber(form.getPkkNumber());
                newStudent.setCity(form.getCity());
                newStudent.setStreet(form.getStreet());
                newStudent.setZipCode(form.getZipCode());
                newStudent.setUserId(newUser.getUserId());
                studentRepo.saveAndFlush(newStudent);
//                Tworzenie postepu studenta
                StudentProgress newStudentProgress = new StudentProgress();
                newStudentProgress.setPractice(0);
                newStudentProgress.setPracticeExam(false);
                newStudentProgress.setTheory(0);
                newStudentProgress.setTheoryExam(0);
                newStudentProgress.setStudentId(newStudent.getStudentId());
                studentProgressRepo.saveAndFlush(newStudentProgress);
//                Dodawanie uprawnien
                userRepo.createNewUserRole(newUser.getUserId(), 3); // roleId(3) = ROLE_USER
            }
        } catch(Exception e) {
            e.printStackTrace();
            return "redirect:/admin/zakladanie-konta?=failed";
        }
        return "redirect:/admin/zakladanie-konta?=success";
    }
    
    @GetMapping(value = "/edytuj-kursanta")
    public ModelAndView getStudentEditView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("admin/edit-student");
        
        List<StudentDetailsView> studentDetails = new ArrayList<>();
        studentDetails = studentDetailsViewRepo.getAllStudentsWithDetails();
        
        model.addObject("studentDetails", studentDetails);
        
        return model;
    }
    
    @GetMapping("/edycja-kursanta")
    public ModelAndView getStudentView(HttpServletRequest request,
        @RequestParam("login") String login) {
        ModelAndView model = new ModelAndView("admin/edit-student");
        
        List<StudentDetailsView> studentDetails = new ArrayList<>();
        studentDetails = studentDetailsViewRepo.getAllStudentsWithDetails();
        
        model.addObject("studentDetails", studentDetails);
        
        User user = new User();
        user = userRepo.findByLogin(login);
        
        Student student = new Student();
        student = studentRepo.getStudentByUserId(user.getUserId());

        model.addObject("user", user);
        model.addObject("studentToEdit", student);

        return model;
    }
    
    @PostMapping("/edycja-kursanta/aktualizuj")
    public String updateStudentDetails(@ModelAttribute("StudentEditForm") StudentEditForm form, HttpServletRequest request) {

        try {
            User oldUser = new User();
            oldUser = userRepo.findOne(form.getUserId());
            User user = new User();
            user.setUserId(form.getUserId());
            List<User> allUsers = userRepo.findAll();
            for(User oneUser : allUsers) {
                if(!(form.getLogin().equals(oldUser.getLogin())) && (form.getLogin().equals(oneUser.getLogin()))) {
                    return "redirect:/admin/edytuj-kursanta?=loginNotAllowed";
                } else {
                    user.setLogin(form.getLogin());
                }
            }
            if(userRepo.findOne(form.getUserId()).getPassword() == form.getPassword()) {
                user.setPassword(form.getPassword());
            } else {
                String passwordEncoded = new BCryptPasswordEncoder().encode(form.getPassword());
                user.setPassword(passwordEncoded);
            }
            user.setEmail(form.getEmail());
            user.setEnabled(form.isEnabled());
            userRepo.saveAndFlush(user);
            

            Student oldStudent = studentRepo.getStudentByUserId(form.getUserId());
            Student student = new Student();
            student.setStudentId(oldStudent.getStudentId());
            student.setFirstName(form.getFirstName());
            student.setLastName(form.getLastName());
            student.setPkkNumber(form.getPkkNumber());
            student.setStreet(form.getStreet());
            student.setCity(form.getCity());
            student.setZipCode(form.getZipCode());
            student.setUserId(form.getUserId());
            studentRepo.saveAndFlush(student);
                  
        } catch(Exception e) {
            return "redirect:/admin/edytuj-kursanta?=failed";
        } 
        return "redirect:/admin/edycja-kursanta?login=" + form.getLogin();
        
    }
    
    @GetMapping(value = "/edytuj-pracownika")
    public ModelAndView getEmployeeEditView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("admin/edit-employee");
        
        List<EmployeeDetailsView> employeeDetails = new ArrayList<>();
        employeeDetails = employeeDetailsViewRepo.getAllEmployeesWithDetails();
        
        model.addObject("employeeDetails", employeeDetails);
        
        return model;
    }
    
    @GetMapping("/edycja-pracownika")
    public ModelAndView getEmployeeView(HttpServletRequest request,
        @RequestParam("login") String login) {
        ModelAndView model = new ModelAndView("admin/edit-employee");
        
        List<EmployeeDetailsView> employeeDetails = new ArrayList<>();
        employeeDetails = employeeDetailsViewRepo.getAllEmployeesWithDetails();
        
        model.addObject("employeeDetails", employeeDetails);
        
        User user = new User();
        user = userRepo.findByLogin(login);
        
        Employee employee = new Employee();
        employee = employeeRepo.getEmployeeByUserId(user.getUserId());

        model.addObject("user", user);
        model.addObject("employeeToEdit", employee);

        return model;
    }
    
    @PostMapping("/edycja-pracownika/aktualizuj")
    public String updateEmployeeDetails(@ModelAttribute("EmployeeEditForm") EmployeeEditForm form, HttpServletRequest request) {

        try {
            User oldUser = new User();
            oldUser = userRepo.findOne(form.getUserId());
            User user = new User();
            user.setUserId(form.getUserId());
            List<User> allUsers = userRepo.findAll();
            for(User oneUser : allUsers) {
                if(!(form.getLogin().equals(oldUser.getLogin())) && (form.getLogin().equals(oneUser.getLogin()))) {
                    return "redirect:/admin/edytuj-pracownika?=loginNotAllowed";
                } else {
                    user.setLogin(form.getLogin());
                }
            }
            if(userRepo.findOne(form.getUserId()).getPassword() == form.getPassword()) {
                user.setPassword(form.getPassword());
            } else {
                String passwordEncoded = new BCryptPasswordEncoder().encode(form.getPassword());
                user.setPassword(passwordEncoded);
            }
            user.setEmail(form.getEmail());
            user.setEnabled(form.isEnabled());
            userRepo.saveAndFlush(user);           

            Employee oldEmployee = employeeRepo.getEmployeeByUserId(form.getUserId());
            Employee employee = new Employee();
            employee.setEmployeeId(oldEmployee.getEmployeeId());
            employee.setFirstName(form.getFirstName());
            employee.setLastName(form.getLastName());
            employee.setPosition(form.getPosition());
            employee.setStreet(form.getStreet());
            employee.setCity(form.getCity());
            employee.setZipCode(form.getZipCode());
            employee.setUserId(form.getUserId());
            employeeRepo.saveAndFlush(employee);
                  
        } catch(Exception e) {
            return "redirect:/admin/edytuj-pracownika?=failed";
        } 
        return "redirect:/admin/edycja-pracownika?login=" + form.getLogin();
        
    }
    
}
