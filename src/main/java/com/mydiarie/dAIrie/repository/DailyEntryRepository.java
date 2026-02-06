package com.mydiarie.dAIrie.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mydiarie.dAIrie.models.DailyEntry;

public interface DailyEntryRepository extends JpaRepository<DailyEntry, Long> {

    List<DailyEntry> findByUserEmailOrderByDateDesc(String email);

    List<DailyEntry> findByUserEmailAndDateBetween(
            String email,
            LocalDate startDate,
            LocalDate endDate
    );

    Optional<DailyEntry> findByIdAndUserEmail(Long id, String email);

}
