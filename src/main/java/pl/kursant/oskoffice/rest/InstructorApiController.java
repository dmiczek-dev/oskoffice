package pl.kursant.oskoffice.rest;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kursant.oskoffice.model.PracticeTerm;
import pl.kursant.oskoffice.model.Term;
import pl.kursant.oskoffice.repository.PracticeTermRepository;
import pl.kursant.oskoffice.repository.TermRepository;

@RestController
@RequestMapping(value = "/api/instructor")
public class InstructorApiController {
    
    @Autowired
    TermRepository termRepo;
    
    @Autowired
    PracticeTermRepository practiceTermRepo;
    
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @PostMapping("/termDateUpdate")
    @RequestMapping(value = "/termDateUpdate")
    public boolean updateTermDate(@RequestParam Map<String, String> termUpdated) {
        
        try {
        Integer termId = Integer.parseInt(termUpdated.get("id"));
        String startTime = termUpdated.get("start");
        String endTime = termUpdated.get("end");
        
        
        String[] newStartTime = startTime.split(" ");
        String[] newEndTime = endTime.split(" ");
        
            System.out.println(newStartTime[0]);
            System.out.println(newStartTime[1]);

        Term oldTerm = termRepo.findOne(termId);
        
        Term newTerm = new Term (termId, Date.valueOf(newStartTime[0]), 
                Time.valueOf(newStartTime[1]), Time.valueOf(newEndTime[1]), 
                oldTerm.getDescription(), oldTerm.getEmployeeId(), oldTerm.getFirmId());
        
        termRepo.saveAndFlush(newTerm);
        
        return true;
        
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @PostMapping("/termEdit")
    @RequestMapping(value = "/termEdit")
    public boolean editTerm(@RequestParam Map<String, String> termEdited) {
    
        try {
            
            String activity = termEdited.get("activity");
            Integer termId = Integer.parseInt(termEdited.get("termId"));
            String description = termEdited.get("description");
            
            Term oldTerm = termRepo.findOne(termId);
            PracticeTerm oldPracticeTerm = practiceTermRepo.findOne(termId);
            Term newTerm = new Term(termId, oldTerm.getDate(), oldTerm.getStartTime(), oldTerm.getEndTime(), description, oldTerm.getEmployeeId(), oldTerm.getFirmId());
            termRepo.saveAndFlush(newTerm);
            
            if(activity.equals("practice")) {
                Integer studentId = Integer.parseInt(termEdited.get("student"));
                Integer vehicleId = Integer.parseInt(termEdited.get("vehicle"));
                PracticeTerm newPracticeTerm = new PracticeTerm(termId, vehicleId, studentId);
                practiceTermRepo.saveAndFlush(newPracticeTerm);
            }
            

            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    @PostMapping("/termDelete")
    @RequestMapping(value = "/termDelete")
    public boolean deleteTerm(@RequestParam Map<String, String> termDeleted) {
        
        try {
            Integer termId = Integer.parseInt(termDeleted.get("term_id"));
            String activity = termDeleted.get("activity");
            
            if(activity.equals("practice")) {
                practiceTermRepo.delete(termId);
            }
            
            termRepo.delete(termId);
            
            return true;
            
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
}
