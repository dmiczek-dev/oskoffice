package pl.kursant.oskoffice.controller;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.kursant.oskoffice.form.NewTermForm;
import pl.kursant.oskoffice.model.Employee;
import pl.kursant.oskoffice.model.Firm;
import pl.kursant.oskoffice.model.PracticeTerm;
import pl.kursant.oskoffice.model.Student;
import pl.kursant.oskoffice.model.StudentProgress;
import pl.kursant.oskoffice.model.Term;
import pl.kursant.oskoffice.model.TermView;
import pl.kursant.oskoffice.model.User;
import pl.kursant.oskoffice.model.Vehicle;
import pl.kursant.oskoffice.repository.EmployeeRepository;
import pl.kursant.oskoffice.repository.FirmRepository;
import pl.kursant.oskoffice.repository.PracticeTermRepository;
import pl.kursant.oskoffice.repository.StudentProgressRepository;
import pl.kursant.oskoffice.repository.StudentRepository;
import pl.kursant.oskoffice.repository.TermRepository;
import pl.kursant.oskoffice.repository.TermViewRepository;
import pl.kursant.oskoffice.repository.UserRepository;
import pl.kursant.oskoffice.repository.VehicleRepository;

@Controller
@RequestMapping("/instruktor")
public class InstructorController {
    
    @Autowired
    StudentProgressRepository studentProgressRepo;
    
    @Autowired
    FirmRepository firmRepo;
    
    @Autowired
    VehicleRepository vehicleRepo;
    
    @Autowired
    EmployeeRepository employeeRepo;
    
    @Autowired
    StudentRepository studentRepo;
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    TermRepository termRepo;
    
    @Autowired
    PracticeTermRepository practiceTermRepo;
    
    @Autowired
    TermViewRepository termViewRepo;
    
    @GetMapping(value = "/main")
    public ModelAndView getIndexView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("instructor/main");
        
//        Pobieranie zalogowanego użytkownika
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        
//        Pobranie danych pracownika
        Employee employee = employeeRepo.getEmployeeByUserId(user.getUserId());
        model.addObject("employee", employee);
        
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
        ModelAndView model = new ModelAndView("instructor/profile");
        
//        Pobieranie zalogowanego użytkownika
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addObject("user", user);
        
//        Pobranie danych pracownika
        Employee employee = employeeRepo.getEmployeeByUserId(user.getUserId());
        model.addObject("employee", employee);
        
        return model;
    }
    
    @GetMapping(value = "/sprawdz-postep")
    public ModelAndView getCheckProgressView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("instructor/check-progress");
        
//        Pobieranie zalogowanego użytkownika
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addObject("user", user);
        
//        Pobranie danych pracownika
        Employee employee = employeeRepo.getEmployeeByUserId(user.getUserId());
        model.addObject("employee", employee);
        
//        Pobieranie danych
        List<Student> allStudents = studentRepo.findAll();
        model.addObject("allStudents", allStudents);
        
        return model;
    }
    
    @GetMapping("/postep-kursanta")
    public ModelAndView postCheckProgressView(HttpServletRequest request,
        @RequestParam("id") Integer studentId) {
        ModelAndView model = new ModelAndView("instructor/check-progress");
        
//        Pobieranie zalogowanego użytkownika
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addObject("user", user);
        
//        Pobranie danych pracownika
        Employee employee = employeeRepo.getEmployeeByUserId(user.getUserId());
        model.addObject("employee", employee);
        
//        Pobieranie danych
        List<Student> allStudents = studentRepo.findAll();
        model.addObject("allStudents", allStudents);
        
        Student student = studentRepo.findOne(studentId);
        model.addObject("student", student);
        
        StudentProgress studentProgress = studentProgressRepo.findOne(studentId);
        model.addObject("studentProgress", studentProgress);
        
        
        return model;
    }
    
    @PostMapping("/postep-kursanta/aktualizuj")
    public String updateStudentProgress(@ModelAttribute("StudentProgress") StudentProgress form, HttpServletRequest request) {

        try {
            studentProgressRepo.saveAndFlush(form);
        } catch(Exception e) {
            return "redirect:/instruktor/sprawdz-postep?=invalidType";
        } 
        return "redirect:/instruktor/postep-kursanta?id="+form.getStudentId();
        
    }
    
    @GetMapping(value = "/terminarz")
    public ModelAndView getCalendarView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("instructor/calendar");
        
//        Pobieranie zalogowanego użytkownika
        User user = userRepo.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addObject("user", user);
        
//        Pobranie danych pracownika
        Employee employee = employeeRepo.getEmployeeByUserId(user.getUserId());
        model.addObject("employee", employee);
        
//        Pobieranie dat miesiac do przodu
        List<String> monthTerms = new ArrayList<>();
        for(Timestamp oldDate : termRepo.getMonthTerms()) {
            LocalDateTime datetime = LocalDateTime.parse(oldDate.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
            String newDateTime = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            monthTerms.add(newDateTime);
        }
        model.addObject("monthTerms", monthTerms);
        
//        Pobieranie kursantow
        List<Student> allStudents = studentRepo.findAll();
        model.addObject("allStudents", allStudents);
        
//        Pobieranie pojazdow
        List<Vehicle> allVehicles = vehicleRepo.findAll();
        model.addObject("allVehicles", allVehicles);
        
//        Pobieranie firmy
        Firm firm = firmRepo.findOne(1);
        model.addObject("firm", firm);
        
//        Pobranie terminow
        List<TermView> terms = termViewRepo.getTermsByCurrentDate();
        
        List<Map<String, String>> calendarEvents;
        calendarEvents = new ArrayList();
        
        for (int i = 0; i < terms.size(); i++){
            
            Map<String, String> map = new HashMap();
            
//            Informacje ogolne
            map.put("termId", String.valueOf(terms.get(i).getTermId()));
            map.put("date", String.valueOf(terms.get(i).getDate()));
            map.put("startTime", String.valueOf(terms.get(i).getStartTime()));
            map.put("endTime", String.valueOf(terms.get(i).getEndTime()));
            map.put("description", String.valueOf(terms.get(i).getDescription()));
            map.put("employee", String.valueOf(employeeRepo.findOne(terms.get(i).getEmployeeId()).getFirstName() + ' ' + employeeRepo.findOne(terms.get(i).getEmployeeId()).getLastName()));
            map.put("employeeId", String.valueOf(terms.get(i).getEmployeeId()));
            map.put("firm", String.valueOf(firmRepo.findOne(terms.get(i).getFirmId()).getName()));
            map.put("textColor", "white");
//            Sprawdza czy termin jest praktyczny
            if(terms.get(i).getVehicleId() != null && terms.get(i).getStudentId() != null) {
                map.put("vehicle", String.valueOf(vehicleRepo.findOne(terms.get(i).getVehicleId()).getModel() + " [" + vehicleRepo.findOne(terms.get(i).getVehicleId()).getVehicleCode() + ']'));
                map.put("vehicleId", String.valueOf(terms.get(i).getVehicleId()));
                map.put("student", String.valueOf(studentRepo.findOne(terms.get(i).getStudentId()).getFirstName() + ' ' + studentRepo.findOne(terms.get(i).getStudentId()).getLastName()));
                map.put("studentId", String.valueOf(terms.get(i).getStudentId()));
                map.put("color", "#ff6666");
                map.put("activity", "practice");
            } else {
                map.put("color", "#9966ff");
                map.put("activity", "theory");
            }
//            Sprawdza czy termin jest przypisany do zalogowanego instruktora
            if(terms.get(i).getEmployeeId() != employee.getEmployeeId()) {
                map.put("rendering", "background");
                map.replace("student", null);
                if(terms.get(i).getVehicleId() != null && terms.get(i).getStudentId() != null) {
                    map.replace("color", "rgb(255, 102, 102, 0.2)");
                    map.replace("textColor", "#404040");
                } else {
                    map.replace("color", "rgb(153, 102, 255, 0.2)");
                    map.replace("textColor", "#404040");
                }
                
            }
            
            calendarEvents.add(map);
            
        }
        model.addObject("terms", calendarEvents);
        return model;
    }
    
    @PostMapping("/terminarz/nowy-termin")
    public String createNewTerm(@ModelAttribute("NewTermForm") NewTermForm form, HttpServletRequest request) {

        try {
//            Nowy obiekt Term

            
            Term term = new Term();
            
            term.setDate(form.getDate());
            term.setDescription(form.getDescription());
            term.setEmployeeId(form.getEmployeeId());
            term.setEndTime(Time.valueOf(form.getEndTime()+":00"));
            term.setStartTime(Time.valueOf(form.getStartTime()+":00"));
            term.setFirmId(form.getFirmId());          
            
            termRepo.saveAndFlush(term);
            
//            Nowy obiekt PracticeTerm
            if(form.getType().equals("practice")) {
                
                PracticeTerm practiceTerm = new PracticeTerm();
                practiceTerm.setTermId(term.getTermId());
                practiceTerm.setStudentId(form.getStudentId());
                practiceTerm.setVehicleId(form.getVehicleId());
                practiceTermRepo.saveAndFlush(practiceTerm);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/instruktor/terminarz?blad";
        }
        
        return "redirect:/instruktor/terminarz";
    }
}
