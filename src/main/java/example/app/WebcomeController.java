package example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WebcomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }


    @RequestMapping("/aoptest")
    public ModelAndView home(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        response.setHeader("Cache-Control", "no-cache");

        ModelAndView mav = new ModelAndView("echo/aop");
        mav.addObject("aopData", "AOP Test");
        return mav;
    }
}
