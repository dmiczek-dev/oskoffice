
package pl.kursant.oskoffice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.kursant.oskoffice.model.Firm;
import pl.kursant.oskoffice.model.Student;
import pl.kursant.oskoffice.model.StudentProgress;
import pl.kursant.oskoffice.model.TermView;
import pl.kursant.oskoffice.model.User;
import pl.kursant.oskoffice.model.Vehicle;
import pl.kursant.oskoffice.repository.EmployeeRepository;
import pl.kursant.oskoffice.repository.FirmRepository;
import pl.kursant.oskoffice.repository.StudentProgressRepository;
import pl.kursant.oskoffice.repository.StudentRepository;
import pl.kursant.oskoffice.repository.TermViewRepository;
import pl.kursant.oskoffice.repository.UserRepository;
import pl.kursant.oskoffice.repository.VehicleRepository;

@Controller
@RequestMapping("/kursant")
public class StudentController {
    
    @Autowired
    StudentProgressRepository studentProgressRepo;
    
    @Autowired
    FirmRepository firmRepo;
    
    @Autowired
    VehicleRepository vehicleRepo;
    
    @Autowired
    StudentRepository studentRepo;
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    TermViewRepository termViewRepo;
    
    @Autowired
    EmployeeRepository employeeRepo;
    
    @GetMapping(value = "/main")
    public ModelAndView getMainView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("student/main");
        
//        Pobieranie zalogowanego użytkownika
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        
//        Pobranie danych kursanta
        Student student = studentRepo.getStudentByUserId(user.getUserId());
        model.addObject("student", student);
        
//        Pobieranie danych
        List<Student> allStudents = studentRepo.findAll();
        model.addObject("allStudents", allStudents.size());
        
        List<Vehicle> allVehicles = vehicleRepo.findAll();
        model.addObject("allVehicles", allVehicles.size());
        
        List<Firm> allFirm = firmRepo.findAll();
        model.addObject("allFirm", allFirm.size());
        
        return model;
    }
    
    @GetMapping(value = "/profil")
    public ModelAndView getProfileView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("student/profile");
        
//        Pobieranie zalogowanego użytkownika
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addObject("user", user);
        
//        Pobranie danych kursanta
        Student student = studentRepo.getStudentByUserId(user.getUserId());
        model.addObject("student", student);
        
        
        
        
        return model;
    }
    
    @GetMapping(value = "/postep")
    public ModelAndView getProgressView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("student/progress");
        
        //        Pobieranie zalogowanego użytkownika
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addObject("user", user);
        
//        Pobranie danych kursanta
        Student student = studentRepo.getStudentByUserId(user.getUserId());
        StudentProgress studentProgress = studentProgressRepo.findOne(student.getStudentId());
        System.out.println(studentProgress.getTheory());
        model.addObject("student", student);
        model.addObject("studentProgress", studentProgress);
        
        return model;
    }
    
    @GetMapping(value = "/terminarz")
    public ModelAndView getCalendarView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("student/calendar");
        
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Student student = studentRepo.getStudentByUserId(user.getUserId());
        model.addObject("student", student);
        
        StudentProgress studentProgress = studentProgressRepo.findOne(student.getStudentId());
        
        //        Pobranie terminow
        List<TermView> terms = termViewRepo.getTermsByCurrentDate();
        
        List<Map<String, String>> calendarEvents;
        calendarEvents = new ArrayList();
        
        for (int i = 0; i < terms.size(); i++){
            
            Map<String, String> map = new HashMap();
            
//            Pobiera terminy teoretyczne dla studentow ktorzy nie ukonczyli teorii
            if(studentProgress.getTheory() < 30 && terms.get(i).getStudentId() == null) {
                map.put("termId", String.valueOf(terms.get(i).getTermId()));
                map.put("date", String.valueOf(terms.get(i).getDate()));
                map.put("startTime", String.valueOf(terms.get(i).getStartTime()));
                map.put("endTime", String.valueOf(terms.get(i).getEndTime()));
                map.put("description", String.valueOf(terms.get(i).getDescription()));
                map.put("employee", String.valueOf(employeeRepo.findOne(terms.get(i).getEmployeeId()).getFirstName() + ' ' + employeeRepo.findOne(terms.get(i).getEmployeeId()).getLastName()));
                map.put("firm", String.valueOf(firmRepo.findOne(terms.get(i).getFirmId()).getName()));
                map.put("color", "#9966ff");
                map.put("textColor", "white");
            }

            //Pobiera terminy praktyczne dla zalogowanego studenta
            if(terms.get(i).getStudentId() != null && terms.get(i).getStudentId() == student.getStudentId()) {
                map.put("termId", String.valueOf(terms.get(i).getTermId()));
                map.put("date", String.valueOf(terms.get(i).getDate()));
                map.put("startTime", String.valueOf(terms.get(i).getStartTime()));
                map.put("endTime", String.valueOf(terms.get(i).getEndTime()));
                map.put("description", String.valueOf(terms.get(i).getDescription()));
                map.put("employee", String.valueOf(employeeRepo.findOne(terms.get(i).getEmployeeId()).getFirstName() + ' ' + employeeRepo.findOne(terms.get(i).getEmployeeId()).getLastName()));
                map.put("firm", String.valueOf(firmRepo.findOne(terms.get(i).getFirmId()).getName()));
                map.put("vehicle", String.valueOf(vehicleRepo.findOne(terms.get(i).getVehicleId()).getModel() + " [" + vehicleRepo.findOne(terms.get(i).getVehicleId()).getVehicleCode() + ']'));
                map.put("student", String.valueOf(studentRepo.findOne(terms.get(i).getStudentId()).getFirstName() + ' ' + studentRepo.findOne(terms.get(i).getStudentId()).getLastName()));
                map.put("color", "#ff6666");
                map.put("textColor", "white");
            }
            
            calendarEvents.add(map);
            
        }
        model.addObject("terms", calendarEvents);
        return model;
    }
    
}
