package pl.kursant.oskoffice.service;

import java.util.List;
import pl.kursant.oskoffice.model.PracticeTerm;

public interface PracticeTermService {
    
    List<PracticeTerm> getPracticeTerm();
    
    PracticeTerm addPracticeTerm(PracticeTerm practiceTerm);
    
    PracticeTerm getPracticeTermById(Integer practiceTermId);
    
    PracticeTerm updatePracticeTerm(PracticeTerm practiceTerm);
    
    void deletePracticeTerm(Integer practiceTermId);
    
}
