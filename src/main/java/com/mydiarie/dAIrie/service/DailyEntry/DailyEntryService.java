package com.mydiarie.dAIrie.service.DailyEntry;

import java.util.List;

import com.mydiarie.dAIrie.dto.DailyEntryDTO.CreateDailyEntryRequestDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.DailyEntryResponseDTO;
import com.mydiarie.dAIrie.dto.DailyEntryDTO.UpdateDailyEntryRequestDTO;

public interface DailyEntryService {
   
    DailyEntryResponseDTO createEntry(String email, CreateDailyEntryRequestDTO dto);
    List<DailyEntryResponseDTO> getEntriesByUser(String email);
    DailyEntryResponseDTO getEntryById (String email, Long entryId);
    DailyEntryResponseDTO updateEntry (String email, Long entryId, UpdateDailyEntryRequestDTO dto);
    void deleteEntry (String email, Long entryId);
    
    
}
