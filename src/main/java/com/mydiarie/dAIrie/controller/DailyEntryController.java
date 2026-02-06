package com.mydiarie.dAIrie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mydiarie.dAIrie.dto.DailyEntryDTO.CreateDailyEntryRequestDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.DailyEntryResponseDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.UpdateDailyEntryRequestDTO;
import com.mydiarie.dAIrie.service.DailyEntry.DailyEntryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/entries")
@RequiredArgsConstructor
public class DailyEntryController {

    private final DailyEntryService dailyService;

    @PostMapping
    public ResponseEntity<DailyEntryResponseDTO> createEntry(
            @RequestParam Long userId,
            @Valid @RequestBody CreateDailyEntryRequestDTO dto) {

        DailyEntryResponseDTO response = dailyService.createEntry(userId, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DailyEntryResponseDTO>> getEntriesByUser(
            @RequestParam Long userId) {

        return ResponseEntity.ok(dailyService.getEntriesByUser(userId));
    }

    @GetMapping("/{entryId}")
    public ResponseEntity<DailyEntryResponseDTO> getEntryById(
            @RequestParam Long userId,
            @PathVariable Long entryId) {

        return ResponseEntity.ok(
                dailyService.getEntryById(userId, entryId)
        );
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<DailyEntryResponseDTO> updateEntry(
            @RequestParam Long userId,
            @PathVariable Long entryId,
            @Valid @RequestBody UpdateDailyEntryRequestDTO dto) {

        return ResponseEntity.ok(
                dailyService.updateEntry(userId, entryId, dto)
        );
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<Void> deleteEntry(
            @RequestParam Long userId,
            @PathVariable Long entryId) {

        dailyService.deleteEntry(userId, entryId);

        return ResponseEntity.noContent().build();
    }
}
