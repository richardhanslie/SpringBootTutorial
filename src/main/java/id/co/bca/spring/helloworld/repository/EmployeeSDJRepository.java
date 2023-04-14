package id.co.bca.spring.helloworld.repository;

import id.co.bca.spring.helloworld.model.EmployeeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSDJRepository extends JpaRepository<EmployeeModel, Integer> {
    EmployeeModel findEmployeeById(Integer integer);
    List<EmployeeModel> findAllByOrderByLastName();
    Page<EmployeeModel> findAllByOrderByEmail(Pageable pageable);
}
