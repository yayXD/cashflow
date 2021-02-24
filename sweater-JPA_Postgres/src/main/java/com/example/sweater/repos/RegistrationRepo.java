package com.example.sweater.repos;

//import com.example.sweater.domain.Message;
import com.example.sweater.domain.Registration;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegistrationRepo extends CrudRepository<Registration, Long> {

    List<Registration> findByLogin(String login);

}
