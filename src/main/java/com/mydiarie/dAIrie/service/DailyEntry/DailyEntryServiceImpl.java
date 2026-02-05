package com.mydiarie.dAIrie.service.DailyEntry;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mydiarie.dAIrie.dto.DailyEntryDTO.CreateDailyEntryRequestDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.DailyEntryResponseDTO;
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
    public DailyEntryResponseDTO createEntry(Long userId, CreateDailyEntryRequestDTO dto) {

    DiarieUser user = userRepository.findById(userId)
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
    public List<DailyEntryResponseDTO> getEntriesByUser (Long userId){
        List<DailyEntry> entries = dailyEntryRepository.findByUserIdOrderByDateDesc(userId);
        return entries.stream().map(this::toDTO).toList();
    }

}
