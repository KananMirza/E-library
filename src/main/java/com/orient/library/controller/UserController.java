package com.orient.library.controller;

import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.enums.Message;
import com.orient.library.response.ResponseApi;
import com.orient.library.service.UserService;
import com.orient.library.util.Utility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Utility utility;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseApi> getAllUserList() {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value()
                , userService.getAllUserList()));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<ResponseApi> findUserById(@PathVariable @Valid @NotNull(message = "Id is required!") Long userId) {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), Message.SUCCESS.value()
                , userService.findUserById(userId)));
    }

    @PostMapping("/change-status")
    public ResponseEntity<ResponseApi> changeStatus(@RequestBody @Valid StatusRequestDto statusRequestDto) {
        return ResponseEntity.ok(utility.response(HttpStatus.OK.value(), userService.changeStatus(statusRequestDto)
                , null));
    }
}
