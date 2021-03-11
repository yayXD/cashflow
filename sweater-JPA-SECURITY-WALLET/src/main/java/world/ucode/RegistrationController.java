package world.ucode;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import world.ucode.domain.Currency;
import world.ucode.domain.Registration;
import world.ucode.repos.CurrencyRepo;
import world.ucode.repos.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import world.ucode.domain.Role;
import world.ucode.service.RegistrationService;

import javax.validation.Valid;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

//    @Autowired
//    private CurrencyRepo currencyRepo;
//    @GetMapping("/greeting")
//    public String greeting(
//            @RequestParam(name="name", required=false, defaultValue="World") String name,
//            Map<String, Object> model
//    ) {
//        model.put("name", name);
//        return "greeting";
//    }

   @GetMapping("/registration")
    public String main() {
       // Iterable<Registration> registration = registrationRepo.findAll();

        //model.put("messages", registration);

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Registration registration, Model model) {
      //  Registration reg = registrationRepo.findByUsername(registration.getUsername());
//        if(bindingResult.hasErrors()) {
//            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errors);
//            return  "registration";
//        }
////        if (reg == null) {
////            Registration registration = new Registration(login, password);
//            registration.setActive(true);
//            registration.setRoles(Collections.singleton(Role.USER));
//            registrationRepo.save(registration);
//
//
//            //Iterable<Registration> registr = registrationRepo.findAll();
        if(registration.getUsername() != null && registration.getPassword() != null) {
            if (registrationService.addUser(registration)) {
                // model.addAttribute("message", "Этот логин уже существует");
                model.addAttribute("message", "Вы зарегистрированы");
            } else
                model.addAttribute("message", "Этот логин уже существует");
        } else
            model.addAttribute("message", "Заполните все поля формы");

        return"registration";
    }


//    @GetMapping("/login")
//    public String mains(Registration registration, Map<String, Object> model) {
////        Registration registr = registrationRepo.findByLogin(registration.getLogin());
////        if (registr != null) {
////            if (registration.getPassword().equals(registr.getPassword()) == true) {
////                registr.setActive(true);
////                model.put("message", "Вы вошли");
////                return "main";
////            } else {
////                model.put("message", "Вы ввели не правильный пароль");
////                return "login";
////            }
////        } else {
////            model.put("message", "Такого пользователя не существует");
////            return "login";
////        }
//
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String add(Registration registration, Map<String, Object> model) {
//        //Registration registration = new Registration();
//
//        //registrationRepo.save(registration);
//
//        Registration registr = registrationRepo.findByLogin(registration.getLogin());
//        if (registr != null) {
//            if (registration.getPassword().equals(registr.getPassword()) == true) {
//                registr.setActive(true);
//                model.put("message", "Вы вошли");
//                return "login";
//            } else {
//                model.put("message", "Вы ввели не правильный пароль");
//                return "login";
//            }
//        } else {
//            model.put("message", "Такого пользователя не существует");
//            return "login";
//        }
//    }
}

//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model) {
//        Iterable<Registration> messages;
//
//        if (filter != null && !filter.isEmpty()) {
//            messages = registrationRepo.findByLogin(filter);
//        } else {
//            messages = registrationRepo.findAll();
//        }
//
//        model.put("messages", messages);
//
//        return "main";
//    }
//}