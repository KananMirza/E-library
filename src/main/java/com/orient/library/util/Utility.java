package com.orient.library.util;

import com.orient.library.response.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {
    private final ResponseApi responseApi;
    public ResponseApi response(Integer status, String message, Object object){
        responseApi.setMessage(message);
        responseApi.setStatus(status);
        responseApi.setBody(object);
        return responseApi;
    }
}
