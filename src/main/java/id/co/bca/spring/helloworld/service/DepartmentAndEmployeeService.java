package id.co.bca.spring.helloworld.service;

import id.co.bca.spring.helloworld.model.Department;
import id.co.bca.spring.helloworld.model.EmployeeModel;
import id.co.bca.spring.helloworld.repository.DepartmentSDJRepository;
import id.co.bca.spring.helloworld.repository.EmployeeSDJRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentAndEmployeeService {

    @Autowired
    DepartmentSDJRepository departmentSDJRepository;

    @Autowired
    EmployeeSDJRepository employeeSDJRepository;

    public void addEmployeeToDepartment(EmployeeModel employeeModel, int did){
        employeeSDJRepository.save(employeeModel);
        Department department = departmentSDJRepository.findDepartmentById(Integer.valueOf(did));
        departmentSDJRepository.save(department);
        employeeModel.setDepartment(department);
        employeeSDJRepository.save(employeeModel);
    }

    @Transactional
    public void addEmployeeToDepartmentWithTransactional(EmployeeModel employeeModel, int did){
        //employeeSDJRepository.save(employeeModel);
        Department department = departmentSDJRepository.findDepartmentById(Integer.valueOf(did));
        //departmentSDJRepository.save(department);
        employeeModel.setDepartment(department);
        employeeSDJRepository.save(employeeModel);
    }
}
