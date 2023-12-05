package com.orient.library.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orient.library.dto.request.ImageRequestDto;
import com.orient.library.exception.ImageServiceException;
import com.orient.library.response.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Utility {
    private final ResponseApi responseApi;
    private final ObjectMapper objectMapper;
    private final static String IMAGE_SERVICE_PATH="http://127.0.0.1:8085/api/v1/files";
    public ResponseApi response(Integer status, String message, Object object){
        responseApi.setMessage(message);
        responseApi.setStatus(status);
        responseApi.setBody(object);
        return responseApi;
    }
    public String imageUpload(ImageRequestDto imageRequestDto) throws ImageServiceException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ImageRequestDto> requestEntity = new HttpEntity<>(imageRequestDto, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseApi> response = restTemplate.exchange(IMAGE_SERVICE_PATH + "/upload", HttpMethod.POST, requestEntity, ResponseApi.class);

        ResponseApi responseBody = response.getBody();
        if(responseBody.getStatus() != 201){
            throw new ImageServiceException(responseBody.getMessage());
        }
        return responseBody.getMessage();
    }
}
