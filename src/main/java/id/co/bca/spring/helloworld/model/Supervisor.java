package id.co.bca.spring.helloworld.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Supervisor implements Employee {
    @Override
    public String salary() {
        return "Rp. 20.000.000";
    }
}
