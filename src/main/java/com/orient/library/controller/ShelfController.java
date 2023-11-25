package com.orient.library.controller;

import com.orient.library.dto.request.ShelfRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.ShelfResponseDto;
import com.orient.library.enums.Message;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.ShelfService;
import com.orient.library.util.Utility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/shelf")
public class ShelfController {
    private final ShelfService shelfService;
    private final Utility utility;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllShelfs(){
        List<ShelfResponseDto> shelves = shelfService.getAllShelves();
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value(), shelves));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseApi> findShelfById(@PathVariable @Valid @NotNull(message = "Id is required!") Long id){
        ShelfResponseDto shelf = shelfService.getShelfById(id);
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value(), shelf));
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createShelf(@RequestBody @Valid ShelfRequestDto shelfRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(utility.response(HttpStatus.OK.value()
        ,shelfService.createShelf(shelfRequestDto),null));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updateShelf(@RequestBody @Valid ShelfRequestDto shelfRequestDto){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),shelfService.updateShelf(shelfRequestDto),null));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseApi> deleteShelf(@PathVariable @Valid @NotNull(message = "Id is required") Long id){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),shelfService.deleteShelf(id),null));
    }
    @PostMapping("change-status")
    public ResponseEntity<ResponseApi> changeStatus(@RequestBody @Valid StatusRequestDto statusRequestDto){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), shelfService.changeStatus(statusRequestDto),null));
    }
}
