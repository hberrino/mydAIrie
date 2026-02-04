package com.mydiarie.dAIrie.dto.DailyEntryDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyEntryResponseDTO {

    private Long id;
    private LocalDate date;
    private Integer productivityRating;
    private Integer moodRating;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
