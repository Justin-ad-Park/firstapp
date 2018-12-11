package example.app;

import example.aspect.annotation.NoCache;
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


    @NoCache
    @RequestMapping("/AOP4NoCache")
    public ModelAndView testAOP4NoCache(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView("echo/aop");
        mav.addObject("aopData", "AOP Test");
        return mav;
    }

    @RequestMapping("/withoutAOP")
    public ModelAndView testAOP(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        ModelAndView mav = new ModelAndView("echo/aop");
        mav.addObject("aopData", "AOP Test");
        return mav;
    }

}
