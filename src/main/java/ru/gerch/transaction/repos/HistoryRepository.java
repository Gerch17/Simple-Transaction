package ru.gerch.transaction.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gerch.transaction.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
}
