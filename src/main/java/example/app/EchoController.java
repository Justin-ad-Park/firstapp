package example.app;

import example.aspect.annotation.NoCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("echo")
public class EchoController {

    @NoCache
    @RequestMapping(method = RequestMethod.GET)
    public String viewInput(Model model) {
        log.debug("EchoController viewInput");

        EchoForm form = new EchoForm();
        model.addAttribute(form);

        return "echo/input";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String echo(@Valid EchoForm form, BindingResult result) {
        log.debug("EchoController echo");

        if (result.hasErrors()) {
            return "echo/input";
        }
        return "echo/output";
    }
}
