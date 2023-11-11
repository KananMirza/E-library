package com.orient.library.service;

import com.orient.library.dto.request.ShelfRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.ShelfResponseDto;

import java.util.List;

public interface ShelfService {
    List<ShelfResponseDto> getAllShelves();
    ShelfResponseDto getShelfById(Long id);
    String createShelf(ShelfRequestDto shelfRequestDto);
    String updateShelf(ShelfRequestDto shelfRequestDto);
    String deleteShelf(Long id);
    String changeStatus(StatusRequestDto statusRequestDto);
}
