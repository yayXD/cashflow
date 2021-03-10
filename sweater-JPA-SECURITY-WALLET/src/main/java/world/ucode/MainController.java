package world.ucode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import world.ucode.domain.Registration;
import world.ucode.domain.Wallet;
import world.ucode.repos.CurrencyRepo;
import world.ucode.repos.TransactionRepo;
import world.ucode.repos.WalletRepo;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal Registration registration, Model model) {
//       Iterable<Wallet> wal = walletRepo.findAll();
        Iterable<Wallet> wal = walletRepo.findByOwnerLogin(registration);
        model.addAttribute("wallets", wal);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam int walletNumber, ModelMap model) {
        Iterable<Wallet> tr = walletRepo.findByOwnerLogin(walletNumber);

        return "main";
    }


}
