package pl.kursant.oskoffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kursant.oskoffice.model.PracticeTerm;
import pl.kursant.oskoffice.repository.PracticeTermRepository;

@Service
public class PracticeTermServiceImpl implements PracticeTermService{

    @Autowired
    PracticeTermRepository practiceTermRepo;
    
    @Override
    public List<PracticeTerm> getPracticeTerm() {
        return practiceTermRepo.findAll();
    }

    @Override
    public PracticeTerm addPracticeTerm(PracticeTerm practiceTerm) {
        return practiceTermRepo.saveAndFlush(practiceTerm);
    }

    @Override
    public PracticeTerm getPracticeTermById(Integer practiceTermId) {
        return practiceTermRepo.findOne(practiceTermId);
    }

    @Override
    public PracticeTerm updatePracticeTerm(PracticeTerm practiceTerm) {
        return practiceTermRepo.saveAndFlush(practiceTerm);
    }

    @Override
    public void deletePracticeTerm(Integer practiceTermId) {
        practiceTermRepo.delete(practiceTermId);
    }
    
}
