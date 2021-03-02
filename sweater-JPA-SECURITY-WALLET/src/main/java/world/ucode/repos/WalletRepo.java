package world.ucode.repos;

import world.ucode.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WalletRepo extends CrudRepository<Wallet, Long> {
    Wallet findByName(String name);
    List<Wallet> findByOwnerLogin(String ownerLogin);

}
