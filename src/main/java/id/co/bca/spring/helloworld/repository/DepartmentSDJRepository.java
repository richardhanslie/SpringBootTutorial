package id.co.bca.spring.helloworld.repository;

import id.co.bca.spring.helloworld.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentSDJRepository extends JpaRepository<Department, Integer> {
    Department findDepartmentById(Integer aInteger);
}
