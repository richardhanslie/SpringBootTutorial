package id.co.bca.spring.helloworld.controller;

import id.co.bca.spring.helloworld.model.EmployeeModel;
import id.co.bca.spring.helloworld.service.DepartmentAndEmployeeService;
import id.co.bca.spring.helloworld.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    private DepartmentAndEmployeeService departmentAndEmployeeService;

    @GetMapping("/allpage")
    public @ResponseBody List<EmployeeModel> findAllPage(@RequestParam("page") int page,
                                                         @RequestParam("size") int size) {
        return employeeService.allEmployeesPage(page, size);
    }

    @GetMapping("/all")
    public @ResponseBody List<EmployeeModel> getAll() {
        return employeeService.allEmployees();
    }

    @GetMapping("/id")
    public @ResponseBody EmployeeModel getEmployee(@RequestParam("id") int id) {
        EmployeeModel model = new EmployeeModel();
        model.setId(id);
        return employeeService.findTheEmployee(model);
    }

    @GetMapping("/add-ed")
    public String addEmployee(@RequestParam("firstname") String firstname,
                              @RequestParam("lastname") String lastname,
                              @RequestParam("email") String email,
                              @RequestParam("did") int did) {
        EmployeeModel model = new EmployeeModel();
        model.setId(0);
        model.setFirstName(firstname);
        model.setLastName(lastname);
        model.setEmail(email);
        model.setDepartment(null);
        //employeeService.insert(model);
        departmentAndEmployeeService.addEmployeeToDepartmentWithTransactional(model, did);

        return "redirect:/employee/all";
    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("id") int id,
                                 @RequestParam("firstname") String firstname,
                                 @RequestParam("lastname") String lastname,
                                 @RequestParam("email") String email) {
        EmployeeModel model = new EmployeeModel();
        model.setId(id);
        model.setFirstName(firstname);
        model.setLastName(lastname);
        model.setEmail(email);
        employeeService.update(model);

        return "redirect:/employee/all";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") int id) {
        EmployeeModel model = new EmployeeModel();
        model.setId(id);
        employeeService.delete(model);

        return "redirect:/employee/all";
    }

    @GetMapping("/greet")
    public String greet(){
        return "Hello, World!";
    }
}
