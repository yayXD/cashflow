package world.ucode;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import world.ucode.domain.Registration;
import world.ucode.domain.Transaction;
import world.ucode.domain.Wallet;
import world.ucode.repos.CurrencyRepo;
import world.ucode.repos.TransactionRepo;
import world.ucode.repos.WalletRepo;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private TransactionRepo transactionRepo;

//    @PersistenceContext
//    EntityManager entityManager;

    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world.ucode");
        return emf.createEntityManager();
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal Registration registration, Model model) {
//       Iterable<Wallet> wal = walletRepo.findAll();
        Iterable<Wallet> wal = walletRepo.findByOwnerLogin(registration);
        model.addAttribute("wallets", wal);
        return "main";
    }

//    @PostMapping("/main")
//    public String add(@AuthenticationPrincipal Registration registration, @RequestParam int walletNumber, ModelMap model) {
//        Iterable<Wallet> wal = walletRepo.findByOwnerLogin(registration);
//        model.addAttribute("wallets", wal);
//        Wallet w = walletRepo.findByItemNumber(walletNumber);
//        List<Transaction> tr = transactionRepo.findByWalletName(w);
//        model.addAttribute("trans", tr);
//        return "main";
//    }
    @GetMapping("/main/{itemNumber}")
    public String add(@AuthenticationPrincipal Registration registration, @PathVariable int itemNumber, ModelMap model) {
        Iterable<Wallet> wal = walletRepo.findByOwnerLogin(registration);
        model.addAttribute("wallets", wal);
        Wallet w = walletRepo.findByItemNumber(itemNumber);
        List<Transaction> tr = transactionRepo.findByWalletName(w);
        model.addAttribute("trans", tr);
        model.addAttribute("walletId", itemNumber);
        return "/main";
    }

    @GetMapping ("/main/export/{walletId}")
    public String exportToCSV(@AuthenticationPrincipal Registration registration, HttpServletResponse response, @PathVariable int walletId, ModelMap model) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=wallet_" + walletId + "_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        //List<User> listUsers = service.listAll();

        Iterable<Wallet> wal = walletRepo.findByOwnerLogin(registration);
        model.addAttribute("wallets", wal);
        Wallet w = walletRepo.findByItemNumber(walletId);
        List<Transaction> tr = transactionRepo.findByWalletName(w);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Sum transaction","Receiver card number","Transaction","Date", "Description"};
//        String[] nameMapping = {"walletName.name","walletName.currencyNames.curName","spending","receiverNumber","type","walletName.category.categoryName","walletName.tag.tagName","date","description"};
        String[] nameMapping = {"spending","receiverNumber","type","date","description"};

        csvWriter.writeHeader(csvHeader);

        for (Transaction tr2 : tr) {
            csvWriter.write(tr2, nameMapping);
        }

        csvWriter.close();
        model.addAttribute("trans", tr);
        return "/main";
    }

    @GetMapping("/main/deleteAllTransactions/{walletId}")
    public String deleteAllTransactions(@AuthenticationPrincipal Registration registration, @PathVariable int walletId, ModelMap model) {
        Iterable<Wallet> wal = walletRepo.findByOwnerLogin(registration);
        model.addAttribute("wallets", wal);
        Wallet w = walletRepo.findByItemNumber(walletId);
        List<Transaction> tr = transactionRepo.findByWalletName(w);
        if(tr.size() != 0) {
            Transaction tr2 = tr.get(tr.size() - 1);
//            EntityManager entityManager = getEntityManager();
//            entityManager.getTransaction().begin();
//            entityManager.persist(tr2);
//            entityManager.flush();
//            entityManager.clear();
            transactionRepo.delete(tr2);
//            entityManager.createQuery("delete from Transaction where walletName=:name")
//            .setParameter("name", w)
//            .executeUpdate();
//            entityManager.getTransaction().commit();

        }

        tr = transactionRepo.findByWalletName(w);
        model.addAttribute("trans", tr);
        model.addAttribute("walletId", walletId);
        return "/main";
    }

}
