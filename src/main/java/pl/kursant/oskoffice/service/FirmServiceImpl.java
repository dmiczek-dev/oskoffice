package pl.kursant.oskoffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kursant.oskoffice.model.Firm;
import pl.kursant.oskoffice.repository.FirmRepository;

@Service
public class FirmServiceImpl implements FirmService {

    @Autowired
    FirmRepository firmRepo;
    
    @Override
    public List<Firm> getFirm() {
        return firmRepo.findAll();
    }

    @Override
    public Firm addFirm(Firm firm) {
        return firmRepo.saveAndFlush(firm);
    }

    @Override
    public Firm getFirmById(Integer firmId) {
        return firmRepo.findOne(firmId);
    }

    @Override
    public Firm updateFirm(Firm firm) {
        return firmRepo.saveAndFlush(firm);
    }

    @Override
    public void deleteFirm(Integer firmId) {
        firmRepo.delete(firmId);
    }
    
}
