package com.orient.library.controller;

import com.orient.library.dto.request.PenaltyTypRequestDto;
import com.orient.library.dto.response.PenaltyTypeResponseDto;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.PenaltyTypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/penalty-type")
public class PenaltyTypeController {
    private final PenaltyTypeService penaltyTypeService;
    private final ResponseApi responseApi;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllPenaltyTypes(){
        List<PenaltyTypeResponseDto> responseDtos = penaltyTypeService.getAllPenaltyTypes();
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), Message.SUCCESS.value(), responseDtos));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseApi> findPenaltTypeById(@PathVariable @Valid @NotNull(message = "Id is required") Long id){
        PenaltyTypeResponseDto responseDto = penaltyTypeService.getPenaltyTypeById(id);
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),Message.SUCCESS.value(), responseDto));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createPenaltyType(@RequestBody @Valid PenaltyTypRequestDto penaltyTypRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseApi(HttpStatus.CREATED.value()
        ,penaltyTypeService.createPenaltyType(penaltyTypRequestDto),null));
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updatePenaltyType(@RequestBody @Valid PenaltyTypRequestDto penaltyTypRequestDto){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), penaltyTypeService.updatePenaltyType(penaltyTypRequestDto),null));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseApi> deletePenaltyType(@PathVariable @Valid @NotNull(message = "Id is required") Long id){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),penaltyTypeService.deletePenaltyType(id),null));
    }


    private ResponseApi responseApi(Integer status, String message, Object object){
        responseApi.setStatus(status);
        responseApi.setMessage(message);
        responseApi.setBody(object);
        return responseApi;
    }
}
