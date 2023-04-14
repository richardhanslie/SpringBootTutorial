package id.co.bca.spring.helloworld.controller;

import id.co.bca.spring.helloworld.dto.EmployeeInput;
import id.co.bca.spring.helloworld.model.Department;
import id.co.bca.spring.helloworld.model.EmployeeModel;
import id.co.bca.spring.helloworld.repository.DepartmentSDJRepository;
import id.co.bca.spring.helloworld.repository.EmployeeSDJRepository;
import id.co.bca.spring.helloworld.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api")
public class RestEmployeeController {
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    EmployeeSDJRepository employeeSDJRepository;
    @Autowired
    DepartmentSDJRepository departmentSDJRepository;

//    @GetMapping("/allpage")
//    public @ResponseBody List<EmployeeModel> findAllPage(@RequestParam("page") int page,
//                                                         @RequestParam("size") int size) {
//        return employeeService.allEmployeesPage(page, size);
//    }

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<EmployeeModel> findAll() {
        return employeeService.allEmployees();
    }

    @GetMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody EmployeeModel getEmployee(@PathVariable("id") int id) {
        return employeeSDJRepository.findEmployeeById(id);
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody EmployeeModel addEmployee(@RequestBody EmployeeInput employeeInput) throws Exception {

        return employeeService.insert(employeeInput);
    }

    @PutMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody EmployeeModel updateEmployee(@RequestBody EmployeeInput employeeInput) throws Exception {
        Department department = departmentSDJRepository.findDepartmentById(employeeInput.getDepartmentId());
        if (department == null) {
            throw new Exception("Department Not Found");
        }
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(employeeInput.getId());
        employeeModel.setFirstName(employeeInput.getFirstName());
        employeeModel.setLastName(employeeInput.getLastName());
        employeeModel.setEmail(employeeInput.getEmail());
        employeeModel.setDepartment(department);
        return employeeSDJRepository.save(employeeModel);
    }

    @DeleteMapping("/employee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeSDJRepository.deleteById(id);
    }
}
