package pl.kursant.oskoffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kursant.oskoffice.model.Term;
import pl.kursant.oskoffice.repository.TermRepository;

@Service
public class TermServiceImpl implements TermService {

    @Autowired
    TermRepository termRepo;
    
    @Override
    public List<Term> getTerm() {
        return termRepo.findAll();
    }

    @Override
    public Term addTerm(Term term) {
        return termRepo.saveAndFlush(term);
    }

    @Override
    public Term getTermById(Integer termId) {
        return termRepo.findOne(termId);
    }

    @Override
    public Term updateTerm(Term term) {
        return termRepo.saveAndFlush(term);
    }

    @Override
    public void deleteTerm(Integer termId) {
        termRepo.delete(termId);
    }
    
}
