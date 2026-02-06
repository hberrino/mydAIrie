package com.mydiarie.dAIrie.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mydiarie.dAIrie.models.DailyEntry;
import com.mydiarie.dAIrie.models.DiarieUser;

public interface DailyEntryRepository extends JpaRepository <DailyEntry, Long> {

    List<DailyEntry> findByUser(DiarieUser user);

    List<DailyEntry> findByUserId(Long userId);

    List<DailyEntry> findByUserIdAndDateBetween(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );

    List<DailyEntry> findByUserIdOrderByDateDesc(Long userId);

}
