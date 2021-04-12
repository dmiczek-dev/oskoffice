package pl.kursant.oskoffice.service;

import java.util.List;
import pl.kursant.oskoffice.model.Term;

public interface TermService {
    
    List<Term> getTerm();
    
    Term addTerm(Term term);
    
    Term getTermById(Integer termId);
    
    Term updateTerm(Term term);
    
    void deleteTerm(Integer termId);
    
}
