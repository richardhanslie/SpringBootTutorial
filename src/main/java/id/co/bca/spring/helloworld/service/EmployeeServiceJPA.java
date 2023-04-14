package id.co.bca.spring.helloworld.service;

import id.co.bca.spring.helloworld.dto.EmployeeInput;
import id.co.bca.spring.helloworld.model.Department;
import id.co.bca.spring.helloworld.model.EmployeeModel;
import id.co.bca.spring.helloworld.repository.DepartmentSDJRepository;
import id.co.bca.spring.helloworld.repository.EmployeeSDJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceJPA implements IEmployeeService {

    @Autowired
    EmployeeSDJRepository sdjRepository;
    @Autowired
    DepartmentSDJRepository departmentSDJRepository;

    @Override
    public EmployeeModel insert(EmployeeInput employeeInput) throws Exception {
        Department department = departmentSDJRepository.findDepartmentById(employeeInput.getDepartmentId());
        if (department == null) {
            throw new Exception("Department Not Found");
        }
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setFirstName(employeeInput.getFirstName());
        employeeModel.setLastName(employeeInput.getLastName());
        employeeModel.setEmail(employeeInput.getEmail());
        employeeModel.setDepartment(department);
        return sdjRepository.save(employeeModel);
    }

    @Override
    public void update(EmployeeModel employee) {
        sdjRepository.save(employee);
    }

    @Override
    public void delete(EmployeeModel employee) {
        sdjRepository.deleteById(employee.getId());
    }

    @Override
    public EmployeeModel findTheEmployee(EmployeeModel employee) {
        return sdjRepository.findEmployeeById(employee.getId());
    }

    @Override
    public List<EmployeeModel> allEmployees() {
        return sdjRepository.findAllByOrderByLastName();
    }

    @Override
    public List<EmployeeModel> allEmployeesPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sdjRepository.findAll(pageable).getContent();
    }
}
