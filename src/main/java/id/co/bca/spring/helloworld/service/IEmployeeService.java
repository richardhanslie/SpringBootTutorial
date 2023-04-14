package id.co.bca.spring.helloworld.service;

import id.co.bca.spring.helloworld.dto.EmployeeInput;
import id.co.bca.spring.helloworld.model.EmployeeModel;

import java.util.List;

public interface IEmployeeService {
    EmployeeModel insert(EmployeeInput employeeInput) throws Exception;
    void update(EmployeeModel employee);
    void delete(EmployeeModel employee);
    EmployeeModel findTheEmployee(EmployeeModel employee);
    List<EmployeeModel> allEmployees();
    List<EmployeeModel> allEmployeesPage(int page, int size);
}
