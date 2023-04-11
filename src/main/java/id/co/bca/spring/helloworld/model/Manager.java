package id.co.bca.spring.helloworld.model;

import org.springframework.stereotype.Component;

@Component
public class Manager implements Employee {
    @Override
    public String salary() {
        return "Rp. 50.000.000";
    }
}
