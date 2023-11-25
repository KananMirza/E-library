package com.orient.library.controller;

import com.orient.library.dto.request.LeaseStatusRequestDto;
import com.orient.library.dto.response.LeaseStatusResponseDto;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.LeaseStatusService;
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
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/lease-status")
public class LeaseStatusController {
    private final LeaseStatusService leaseStatusService;
    private final Utility utility;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllLeaseStatus() {
        List<LeaseStatusResponseDto> leaseStatuses = leaseStatusService.getAllLeaseStatus();
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value(), leaseStatuses));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseApi> getLeaseStatusById(@PathVariable @Valid @NotNull(message = "Id is required!") Long id) {
        LeaseStatusResponseDto leaseStatuse = leaseStatusService.getLeaseStatusById(id);
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value(), leaseStatuse));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createLeaseStatus(@RequestBody @Valid LeaseStatusRequestDto leaseStatusRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(utility.response(HttpStatus.CREATED.value(),
                leaseStatusService.createLeaseStatus(leaseStatusRequestDto), null));
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updateLeaseStatus(@RequestBody @Valid LeaseStatusRequestDto leaseStatusRequestDto) {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),
                leaseStatusService.updateLeaseStatus(leaseStatusRequestDto), null));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseApi> deleteLeaseStatus(@PathVariable @Valid @NotNull(message = "Id is required!") Long id) {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(),
                leaseStatusService.deleteLeaseStatus(id), null));
    }
}
