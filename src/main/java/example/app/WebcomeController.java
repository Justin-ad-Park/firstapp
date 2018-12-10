package example.app;

import example.aspect.annotation.HeaderCacheControlNoCache;
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


    @HeaderCacheControlNoCache
    @RequestMapping("/aoptest")
    public ModelAndView home(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView("echo/aop");
        mav.addObject("aopData", "AOP Test");
        return mav;
    }
}
