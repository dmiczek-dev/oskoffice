package pl.kursant.oskoffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kursant.oskoffice.model.Employee;
import pl.kursant.oskoffice.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;
    
    @Override
    public List<Employee> getEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepo.saveAndFlush(employee);
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepo.findOne(employeeId);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployee(Integer employeeId) {
        employeeRepo.delete(employeeId);
    }
    
}
