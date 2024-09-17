package ru.alexbat.manager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alexbat.manager.entity.ManagerUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ManagerUser, Long> {

    Optional<ManagerUser> findByUsername(String username);
}
