package id.co.bca.spring.helloworld;

import id.co.bca.spring.helloworld.config.Api;
import id.co.bca.spring.helloworld.controller.EmployeeController;
import id.co.bca.spring.helloworld.controller.HelloController;
import id.co.bca.spring.helloworld.model.Employee;
import id.co.bca.spring.helloworld.model.EmployeeModel;
import id.co.bca.spring.helloworld.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
public class WebMockTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    EmployeeService service;
    @MockBean
    Employee employee;
    @MockBean
    Api api;

    @Test
    public void employeeShouldReturnMessageFromService() throws Exception{
        //when(service.getAllEmployee()).thenReturn()
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World!")));
    }
}
