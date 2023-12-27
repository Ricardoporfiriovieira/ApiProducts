package ricardo.porfirio.ApiProducts.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class HealthCheck {
    @GetMapping("/")
    public String healthCheck(){
        return "Hello World!";
    }
}
