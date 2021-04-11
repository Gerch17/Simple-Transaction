package ru.gerch.transaction.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gerch.transaction.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findUsersByUserName(String str);
}
