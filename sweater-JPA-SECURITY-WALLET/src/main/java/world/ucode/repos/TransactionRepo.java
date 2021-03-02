package world.ucode.repos;

//import com.example.sweater.domain.Message;
import world.ucode.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
//
//import javax.transaction.Transaction;
import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
    List<Transaction> findByType(String type);
    List<Transaction> findByWalletName(String walletName);

}