package id.co.bca.spring.helloworld.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @JsonBackReference(value = "department")
    @OneToMany(mappedBy = "department")
    private List<EmployeeModel> employeeModels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeModel> getEmployeeModels() {
        return employeeModels;
    }

    public void setEmployeeModels(List<EmployeeModel> employeeModels) {
        this.employeeModels = employeeModels;
    }
}
