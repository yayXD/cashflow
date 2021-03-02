package world.ucode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import world.ucode.domain.Currency;
import world.ucode.domain.Registration;
import world.ucode.domain.Wallet;
import world.ucode.repos.CurrencyRepo;
import world.ucode.repos.WalletRepo;

import java.util.Map;

@Controller
public class WalletController {

    @Autowired
    private WalletRepo walletRepo;

   // @Autowired
   // private CurrencyRepo currencyRepo;

    @GetMapping("/wallet")
    public String main(Map<String, Object> model) {
        //Iterable<Registration> registration = registrationRepo.findAll();

        //model.put("messages", registration);

        return "wallet";
    }

    @PostMapping("/wallet")
     public String add(@RequestParam String name, @RequestParam String currency,
                       //@RequestParam String ownerLogin,
                       @AuthenticationPrincipal Registration registration,
                      @RequestParam Long balance, Map<String, Object> model) {
         Wallet reg = walletRepo.findByName(name);
        if (reg == null) {
            Wallet wallet = new Wallet(name, balance, currency, registration);
            walletRepo.save(wallet);
            //Iterable<Wallet> wal = walletRepo.findByOwnerLogin(ownerLogin);
            //model.put("message", wal);
            model.put("message", "Вы создали новый кошелек");
        } else
            model.put("message", "Не возможно создать такой кошелек. Это имя кошелька уже занято");
        return "wallet";
    }
}