package com.orient.library.service.impl;

import com.orient.library.dto.request.ShelfRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.ShelfResponseDto;
import com.orient.library.entity.Shelf;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.ShelfMapper;
import com.orient.library.repository.ShelfRepository;
import com.orient.library.service.ShelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShelfServiceImpl implements ShelfService {
    private final ShelfRepository shelfRepository;
    private final LocalDateTime CURRENT_TIME = LocalDateTime.now();
    @Override
    public List<ShelfResponseDto> getAllShelves() {
        return shelfRepository.getShelvesByIsDeleted(DeleteType.NONDELETE.value()).stream().map(
                ShelfMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ShelfResponseDto getShelfById(Long id) {
        return ShelfMapper.INSTANCE.entityToDto(findShelf(id));
    }

    @Override
    public String createShelf(ShelfRequestDto shelfRequestDto) {
        Shelf shelf = ShelfMapper.INSTANCE.dtoToEntity(shelfRequestDto);
        shelfRepository.save(shelf);
        return "Shelf has been successfully updated!";
    }

    @Override
    public String updateShelf(ShelfRequestDto shelfRequestDto) {
        Shelf shelf = findShelf(shelfRequestDto.getId());
        shelf.setShelfNo(shelfRequestDto.getShelfNo());
        shelf.setUpdatedAt(CURRENT_TIME);
        shelfRepository.save(shelf);
        return "Shelf has been successfully updated!";
    }

    @Override
    public String deleteShelf(Long id) {
        Shelf shelf = findShelf(id);
        shelf.setIsDeleted(DeleteType.DELETE.value());
        shelf.setUpdatedAt(CURRENT_TIME);
        shelfRepository.save(shelf);
        return "Shelf has been successfully deleted!";
    }

    @Override
    public String changeStatus(StatusRequestDto statusRequestDto) {
        Shelf shelf = findShelf(statusRequestDto.getId());
        shelf.setStatus(statusRequestDto.getStatus());
        shelf.setUpdatedAt(CURRENT_TIME);
        shelfRepository.save(shelf);
        return "Shelf status has been successfully changed!";
    }

    public Shelf findShelf(Long id){
        Shelf shelf = shelfRepository.findShelfByIdAndIsDeleted(id, DeleteType.NONDELETE.value());
        if(shelf == null){
            throw new DataNotFoundException(Message.SHELF_NOT_FOUND.value());
        }
        return shelf;
    }
}
