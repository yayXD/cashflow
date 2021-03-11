package world.ucode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import world.ucode.domain.*;
import world.ucode.repos.*;

import java.util.Date;
//import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

@Controller
public class TransactionController {

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private CurrencyRepo currencyRepo;

    @Autowired
    private TransactionRepo transactionRepo;

//    @Autowired
//    private CategoryRepo categoryRepo;
//
//    @Autowired
//    private TagRepo tagRepo;

    @GetMapping("/transaction")
    public String main(@AuthenticationPrincipal Registration registration, Model model) {
        Iterable<Wallet> wall = walletRepo.findByOwnerLogin(registration);
        model.addAttribute("wall", wall);
        return "transaction";
    }

    @PostMapping("/transaction")
    public String add(@AuthenticationPrincipal Registration registration, @RequestParam("tag") String wallName, //@RequestParam("type") String type,
                      //@RequestParam("categoryEx") String categoryEx,
                     // @RequestParam String tag,
                      @RequestParam Double spending,
                      @RequestParam String description, @RequestParam int receiverNumber, Model model) {
       // makeCategory();
       // makeTag();
       // Wallet w = walletRepo.findByName(walletName);
       // if (w != null) {
        Wallet wal = walletRepo.findByName(wallName);
        if(spending != null) {
            if (wal.getBalance() > spending) {
                Wallet w = walletRepo.findByItemNumber(receiverNumber);
                if(w != null && receiverNumber != wal.getItemNumber()) {
                   // if(spending != null) {
                        Date data = new Date();
                        Transaction tr = new Transaction(wal, "expense", spending, data.toString(), description, receiverNumber);
                        transactionRepo.save(tr);
                        if(wal.getCurrencyNames().getCurName().equals("dollar") == true)
                            wal.setBalance(wal.getBalance() - spending / 30);
                         else
                            wal.setBalance(wal.getBalance() - spending);
                        walletRepo.save(wal);
                        Transaction tr2 = new Transaction(w, "income", spending, data.toString(), description, wal.getItemNumber());
                        transactionRepo.save(tr2);
                        if(w.getCurrencyNames().getCurName().equals("dollar") == true)
                            w.setBalance(w.getBalance() + spending / 30);
                        else
                            w.setBalance(w.getBalance() + spending);
                        walletRepo.save(w);
                        model.addAttribute("message", "Вы создали трансакцию");

                } else
                    model.addAttribute("message", "Не правильно указан номер карты получателя");

            } else
                model.addAttribute("message", "Недостаточно средств на счету для данной операции");
        } else
            model.addAttribute("message", "Не заполнили поле сумма перевода");
        Iterable<Wallet> wall = walletRepo.findByOwnerLogin(registration);
        model.addAttribute("wall", wall);
        return "transaction";
    }


}
