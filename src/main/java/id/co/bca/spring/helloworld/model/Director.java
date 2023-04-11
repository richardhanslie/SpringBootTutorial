package id.co.bca.spring.helloworld.model;

import org.springframework.stereotype.Component;

@Component
public class Director implements Employee {
    @Override
    public String salary() {
        return "Rp. 100.000.000";
    }
}
