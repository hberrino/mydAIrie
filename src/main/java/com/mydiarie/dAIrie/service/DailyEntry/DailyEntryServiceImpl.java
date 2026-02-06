package com.mydiarie.dAIrie.service.DailyEntry;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mydiarie.dAIrie.dto.DailyEntryDTO.CreateDailyEntryRequestDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.DailyEntryResponseDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.UpdateDailyEntryRequestDTO;
import com.mydiarie.dAIrie.models.DailyEntry;
import com.mydiarie.dAIrie.models.DiarieUser;
import com.mydiarie.dAIrie.repository.DailyEntryRepository;
import com.mydiarie.dAIrie.repository.DiarieUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DailyEntryServiceImpl implements DailyEntryService {

    private final DailyEntryRepository dailyEntryRepository;
    private final DiarieUserRepository userRepository;

    private DailyEntryResponseDTO toDTO(DailyEntry entry) {
        return new DailyEntryResponseDTO(
                entry.getId(),
                entry.getDate(),
                entry.getProductivityRating(),
                entry.getMoodRating(),
                entry.getContent(),
                entry.getCreatedAt(),
                entry.getUpdatedAt()
        );
    }

    @Override
    public DailyEntryResponseDTO createEntry(String email, CreateDailyEntryRequestDTO dto) {

        DiarieUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DailyEntry entry = new DailyEntry();
        entry.setDate(dto.getDate());
        entry.setProductivityRating(dto.getProductivityRating());
        entry.setMoodRating(dto.getMoodRating());
        entry.setContent(dto.getContent());
        entry.setUser(user);

        DailyEntry savedEntry = dailyEntryRepository.save(entry);

        return toDTO(savedEntry);
    }

    @Override
    public List<DailyEntryResponseDTO> getEntriesByUser(String email) {

        List<DailyEntry> entries =
                dailyEntryRepository.findByUserEmailOrderByDateDesc(email);

        return entries.stream().map(this::toDTO).toList();
    }

    @Override
    public DailyEntryResponseDTO getEntryById(String email, Long entryId) {

        DailyEntry entry = dailyEntryRepository
                .findByIdAndUserEmail(entryId, email)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        return toDTO(entry);
    }

    @Override
    public DailyEntryResponseDTO updateEntry(
            String email,
            Long entryId,
            UpdateDailyEntryRequestDTO dto) {

        DailyEntry entry = dailyEntryRepository
                .findByIdAndUserEmail(entryId, email)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        if (dto.getDate() != null) {
            entry.setDate(dto.getDate());
        }

        if (dto.getProductivityRating() != null) {
            entry.setProductivityRating(dto.getProductivityRating());
        }

        if (dto.getMoodRating() != null) {
            entry.setMoodRating(dto.getMoodRating());
        }

        if (dto.getContent() != null) {
            entry.setContent(dto.getContent());
        }

        DailyEntry updated = dailyEntryRepository.save(entry);

        return toDTO(updated);
    }

    @Override
    public void deleteEntry(String email, Long entryId) {

        DailyEntry entry = dailyEntryRepository
                .findByIdAndUserEmail(entryId, email)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        dailyEntryRepository.delete(entry);
    }
}
