package world.ucode.repos;

//import com.example.sweater.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import world.ucode.domain.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepo extends JpaRepository<Registration, Long> {
    Registration findByUsername(String username);

}
