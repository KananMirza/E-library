package com.orient.library.controller;

import com.orient.library.dto.request.PenaltyRequestDto;
import com.orient.library.enums.Message;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.PenaltyService;
import com.orient.library.util.Utility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/penalty")
@RequiredArgsConstructor
public class PenaltyController {
    private final PenaltyService penaltyService;
    private final Utility utility;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllPenalty(){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value()
                ,penaltyService.getAllPenalty() ));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseApi> getPenaltyById(@PathVariable @Valid @NotNull(message = "Id is required") Long id){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value()
                ,penaltyService.getPenaltyById(id) ));
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createPenalty(@RequestBody @Valid PenaltyRequestDto penaltyRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(utility.response(HttpStatus.CREATED.value()
                ,penaltyService.createPenalty(penaltyRequestDto),null ));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updatePenalty(@RequestBody @Valid PenaltyRequestDto penaltyRequestDto){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value()
                ,penaltyService.updatePenalty(penaltyRequestDto),null ));
    }
}
