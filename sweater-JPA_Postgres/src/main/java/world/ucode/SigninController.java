//package world.ucode;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import world.ucode.domain.Registration;
//import world.ucode.repos.RegistrationRepo;
//
//import java.util.Map;
//@Controller
//public class SigninController {
//       // @Autowired
//        private RegistrationRepo registrationRepo;
//
////    @GetMapping("/greeting")
////    public String greeting(
////            @RequestParam(name="name", required=false, defaultValue="World") String name,
////            Map<String, Object> model
////    ) {
////        model.put("name", name);
////        return "greeting";
////    }
//
//        @GetMapping("/signin")
//        public String main(Map<String, Object> model) {
//           // Iterable<Registration> registration = registrationRepo.findAll();
//
//           // model.put("messages", registration);
//
//            return "signin";
//        }
//
//        @PostMapping
//        public String add(@RequestParam String login, @RequestParam String password, Map<String, Object> model) {
//            //Registration registration = new Registration();
//
//            //registrationRepo.save(registration);
//
//            Iterable<Registration> registr = registrationRepo.findByLogin(login);
//            if(password.equals(registr.iterator().next().getPassword()) == true)
//                model.put("message", "Вы вошли");
//            else
//                model.put("message", "Вы ввели не правильный пароль");
//
//            return "signin";
//        }
//
//    }
