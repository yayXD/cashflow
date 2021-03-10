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
    public String add(@RequestParam("tag") String wallName, //@RequestParam("type") String type,
                      //@RequestParam("categoryEx") String categoryEx,
                     // @RequestParam String tag,
                      @RequestParam Double spending,
                      @RequestParam String description, @RequestParam int receiverNumber, Map<String, Object> model) {
       // makeCategory();
       // makeTag();
       // Wallet w = walletRepo.findByName(walletName);
       // if (w != null) {
        Wallet wal = walletRepo.findByName(wallName);
            if (wal.getBalance() > spending) {
                Wallet w = walletRepo.findByItemNumber(receiverNumber);
                if(w != null) {
               // if (type.equals("expense") == true || type.equals("income") == true) {
//                    model2.addAttribute("gender", user.getGender());
                   // Category c = categoryRepo.findByCategoryName(categoryEx);
                  //  Tag t = tagRepo.findByTagName(tag);
                   // if (c != null && t != null) {
                        Date data = new Date();
                        Transaction tr = new Transaction(wal, "expense", spending, data.toString(), description, receiverNumber);
                        transactionRepo.save(tr);
                    wal.setBalance(wal.getBalance() - spending);
                        walletRepo.save(wal);
                        Transaction tr2 = new Transaction(w, "income", spending, data.toString(), description, wal.getItemNumber());
                        transactionRepo.save(tr2);
                        w.setBalance(w.getBalance() + spending);
                        walletRepo.save(w);
                        model.put("message", "Вы создали трансакцию");
//                        if (type.equals("expense") == true) {
//                            w.setBalance(w.getBalance() - balance);
//                            walletRepo.save(w);
//                        } else {
//                            w.setBalance(w.getBalance() + balance);
//                            walletRepo.save(w);
//                        }
//                    }
//                    model.put("message", "Не правильно указана категория или тэг");
//                }
//                model.put("message", "Не правильно указан тип");
            } else
                model.put("message", "Не правильно указан номер карты получателя");
        } else
            model.put("message", "Недостаточно средств на счету для данной операции");
        return "transaction";
    }

//    public void makeCategory() {
//        String[] cat = new String[]{"salary", "cashback", "interest", "purchase", "communal", "services", "connection",
//            "taxi", "tickets"};
//        for( int i = 0; i < 9; i++) {
//            Category c = categoryRepo.findByCategoryName(cat[i]);
//            if(c == null) {
//                Category ca = new Category(cat[i], cat[i]);
//                categoryRepo.save(ca);
//            }
//        }
//    }

//    public void makeTag() {
//        String[] t = new String[]{"products", "equipment", "medicine", "flights", "clothes", "tools", "delivery",
//                "internet"};
//        for( int i = 0; i < 8; i++) {
//            Tag ta = tagRepo.findByTagName(t[i]);
//            if(ta == null) {
//                Tag tag = new Tag(t[i], t[i]);
//                tagRepo.save(tag);
//            }
//        }
//    }

}
