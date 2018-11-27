package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebcomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}
