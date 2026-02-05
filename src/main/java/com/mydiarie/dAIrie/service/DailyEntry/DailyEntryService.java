package com.mydiarie.dAIrie.service.DailyEntry;

import java.util.List;

import com.mydiarie.dAIrie.dto.DailyEntryDTO.CreateDailyEntryRequestDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.DailyEntryResponseDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.UpdateDailyEntryRequestDTO;

public interface DailyEntryService {
   
    DailyEntryResponseDTO createEntry (Long userId, CreateDailyEntryRequestDTO dto);
    List<DailyEntryResponseDTO> getEntriesByUser (Long userId);
    DailyEntryResponseDTO getEntryById (Long userId, long entryId);
    DailyEntryResponseDTO updateEntry (Long userId, Long entryId, UpdateDailyEntryRequestDTO dto);
    void deleteEntry (Long userId, Long entryId);
    
    
}
