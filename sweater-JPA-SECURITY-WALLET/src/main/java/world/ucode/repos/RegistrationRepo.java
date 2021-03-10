package world.ucode.repos;

//import com.example.sweater.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.ucode.domain.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
@Repository
public interface RegistrationRepo extends CrudRepository<Registration, Long> {
    Registration findByUsername(String username);

}
