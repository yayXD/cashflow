package com.example.sweater;

//import com.example.sweater.domain.Message;
//import com.example.sweater.repos.MessageRepo;
import com.example.sweater.domain.Registration;
import com.example.sweater.repos.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationRepo registrationRepo;

//    @GetMapping("/greeting")
//    public String greeting(
//            @RequestParam(name="name", required=false, defaultValue="World") String name,
//            Map<String, Object> model
//    ) {
//        model.put("name", name);
//        return "greeting";
//    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Registration> registration = registrationRepo.findAll();

        model.put("messages", registration);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String login, @RequestParam String password, @RequestParam String email, Map<String, Object> model) {
        Registration registration = new Registration(login, password, email);

        registrationRepo.save(registration);

        Iterable<Registration> messages = registrationRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Registration> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = registrationRepo.findByLogin(filter);
        } else {
            messages = registrationRepo.findAll();
        }

        model.put("messages", messages);

        return "main";
    }
}
