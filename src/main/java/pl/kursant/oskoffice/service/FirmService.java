package pl.kursant.oskoffice.service;

import java.util.List;
import pl.kursant.oskoffice.model.Firm;

public interface FirmService {
    
    List<Firm> getFirm();
    
    Firm addFirm(Firm firm);
    
    Firm getFirmById(Integer firmId);
    
    Firm updateFirm(Firm firm);
    
    void deleteFirm(Integer firmId);
    
}
