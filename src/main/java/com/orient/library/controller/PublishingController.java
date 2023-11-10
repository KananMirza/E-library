package com.orient.library.controller;

import com.orient.library.dto.request.PublishingRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.PublishingResponseDto;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.PublishingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/publisher")
public class PublishingController {
    private final PublishingService publishingService;
    private final ResponseApi responseApi;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllPublishing() {
        List<PublishingResponseDto> publishers = publishingService.getAllPublishings();
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), Message.SUCCESS.value(), publishers));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseApi> getPublishingById(@PathVariable @Valid @NotNull(message = "Id is required") Long id) {
        PublishingResponseDto publisher = publishingService.getPublishingById(id);
        if (publisher == null) {
            throw new DataNotFoundException(Message.PUBLISHING_NOT_FOUND.value());
        }
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), Message.SUCCESS.value(), publisher));
    }
    @PostMapping("/create")
    public ResponseEntity<ResponseApi> createPublishing(@RequestBody @Valid PublishingRequestDto publishingRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseApi(HttpStatus.CREATED.value()
                , publishingService.createPublishing(publishingRequestDto), null));
    }
    @PostMapping("/update")
    public ResponseEntity<ResponseApi> updatePublishing(@RequestBody @Valid PublishingRequestDto publishingRequestDto) {
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(), publishingService.updatePublishing(publishingRequestDto), null));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseApi> deletePublishing(@PathVariable @Valid @NotNull(message = "Id is required") Long id){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),publishingService.deletePublishing(id),null));
    }
    @PostMapping("/change-status")
    public ResponseEntity<ResponseApi> changeStatus(@RequestBody @Valid StatusRequestDto statusRequestDto){
        return ResponseEntity.ok(responseApi(HttpStatus.OK.value(),publishingService.changeStatus(statusRequestDto),null));
    }

    private ResponseApi responseApi(Integer status, String message, Object object) {
        responseApi.setStatus(status);
        responseApi.setMessage(message);
        responseApi.setBody(object);
        return responseApi;
    }
}
