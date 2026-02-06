package com.mydiarie.dAIrie.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Validated
public class DailyEntryController {

    private final DailyEntryService dailyService;

    @PostMapping
    public ResponseEntity<DailyEntryResponseDTO> createEntry(
            @Valid @RequestBody CreateDailyEntryRequestDTO dto,
            Principal principal) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dailyService.createEntry(principal.getName(), dto));
    }

    @GetMapping
    public ResponseEntity<List<DailyEntryResponseDTO>> getUserEntries(
            Principal principal) {

        return ResponseEntity.ok(
                dailyService.getEntriesByUser(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyEntryResponseDTO> getEntry(
            @PathVariable Long id,
            Principal principal) {

        return ResponseEntity.ok(
                dailyService.getEntryById(principal.getName(), id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DailyEntryResponseDTO> updateEntry(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDailyEntryRequestDTO dto,
            Principal principal) {

        return ResponseEntity.ok(
                dailyService.updateEntry(principal.getName(), id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(
            @PathVariable Long id,
            Principal principal) {

        dailyService.deleteEntry(principal.getName(), id);
        return ResponseEntity.noContent().build();
    }
}
