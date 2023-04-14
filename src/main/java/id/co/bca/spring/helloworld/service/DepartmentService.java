package id.co.bca.spring.helloworld.service;

import id.co.bca.spring.helloworld.model.Department;
import id.co.bca.spring.helloworld.repository.DepartmentSDJRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentService {
    @Autowired
    DepartmentSDJRepository departmentSDJRepository;

    public void insert(Department department) {
        departmentSDJRepository.save(department);
    }

    public void update(Department department) {
        departmentSDJRepository.save(department);
    }

    public void delete(Department department) {
        departmentSDJRepository.deleteById(department.getId());
    }

    public List<Department> all() {
        return departmentSDJRepository.findAll();
    }


}
