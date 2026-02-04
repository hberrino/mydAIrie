package com.mydiarie.dAIrie.dto.DailyEntryDTO;
import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDailyEntryRequestDTO {

    @NotNull
    private LocalDate date;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer productivityRating;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer moodRating;

    @NotNull
    @Size(min = 1, max = 3000)
    private String content;
}