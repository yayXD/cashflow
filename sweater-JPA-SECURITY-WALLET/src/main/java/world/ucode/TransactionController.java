package world.ucode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import world.ucode.domain.Transaction;
import world.ucode.domain.Wallet;
import world.ucode.repos.TransactionRepo;
import world.ucode.repos.WalletRepo;

import java.util.Map;

@Controller
public class TransactionController {

    @Autowired
    private TransactionRepo transactionRepo;

    @GetMapping("/transaction")
    public String main(Map<String, Object> model) {
        //Iterable<Registration> registration = registrationRepo.findAll();

        //model.put("messages", registration);

        return "transaction";
    }

    @PostMapping("/transaction")
    public String add(@RequestParam String walletName, @RequestParam String type, @RequestParam String category,
                      @RequestParam String tag, @RequestParam String date, @RequestParam String description,
                      Map<String, Object> model) {
//        WalletRepo wal;
//        Iterable<Wallet> wallet = wal.findByWalletName(walletName);
//        if(wallet.spliterator().getExactSizeIfKnown() != 0) { // && wallet.getBalance()) {
//            Transaction transaction = new Transaction(walletName, type, category, tag, date, description);
//           transactionRepo.save(transaction);
//            model.put("message", "Вы cоздали трансакцию");
//        } else
//            model.put("message", "Этого кошелька не существует");

        return "transaction";
    }
}