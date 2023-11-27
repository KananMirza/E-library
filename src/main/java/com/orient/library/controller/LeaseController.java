package com.orient.library.controller;

import com.orient.library.dto.request.LeaseRequestDto;
import com.orient.library.enums.Message;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.LeaseService;
import com.orient.library.util.Utility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lease")
@RequiredArgsConstructor
public class LeaseController {
    private final LeaseService leaseService;
    private final Utility utility;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllLease() {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value()
                , leaseService.getAllLease()));
    }

    @GetMapping("/get/{leaseId}")
    public ResponseEntity<ResponseApi> getLeaseById(@PathVariable @Valid @NotNull(message = "Lease id is required!") Long leaseId) {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value()
                , leaseService.getLeaseById(leaseId)));
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createLease(@RequestBody @Valid LeaseRequestDto leaseRequestDto){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),leaseService.createLease(leaseRequestDto),null));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updateLease(@RequestBody @Valid LeaseRequestDto leaseRequestDto){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),leaseService.updateLease(leaseRequestDto),null));
    }
    @PostMapping("/update-status")
    public ResponseEntity<ResponseApi> updateLeaseStatus(){
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),leaseService.updateLeaseStatus(),null));
    }
}
