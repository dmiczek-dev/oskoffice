package pl.kursant.oskoffice.service;

import java.util.List;
import pl.kursant.oskoffice.model.Employee;

public interface EmployeeService {
    
    List<Employee> getEmployee();
    
    Employee addEmployee(Employee employee);
    
    Employee getEmployeeById(Integer employeeId);
    
    Employee updateEmployee(Employee employee);
    
    void deleteEmployee(Integer employeeId);
    
}
