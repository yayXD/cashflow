package world.ucode.repos;

import org.springframework.data.repository.CrudRepository;
import world.ucode.domain.Currency;

import java.util.List;
import java.util.stream.Stream;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {
    List<Currency> findByName(String name);
}
