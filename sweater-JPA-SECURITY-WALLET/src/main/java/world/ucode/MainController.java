package world.ucode;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import world.ucode.domain.Registration;

import java.util.Map;

@Controller
public class MainController {
    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main() {
        // Iterable<Registration> registration = registrationRepo.findAll();

        //model.put("messages", registration);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal Registration registration,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ) {

        return "main";
    }


}
