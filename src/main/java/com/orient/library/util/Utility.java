package com.orient.library.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orient.library.config.ConfigReader;
import com.orient.library.dto.request.ImageRequestDto;
import com.orient.library.dto.response.FileResponseDto;
import com.orient.library.exception.ImageServiceException;
import com.orient.library.response.ResponseApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class Utility {
    private final ResponseApi responseApi;
    private final ConfigReader configReader;
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
        ResponseEntity<ResponseApi> response = restTemplate.exchange(configReader.getImageServicePath() + "/upload"
                , HttpMethod.POST, requestEntity, ResponseApi.class);

        HttpStatusCode code = response.getStatusCode();
        if(!code.is2xxSuccessful()){
            throw new ImageServiceException("Image upload Service failed!");
        }
        return getString(response,true);
    }

    public String imageDownload(String key){
        RestTemplate  restTemplate = new RestTemplate();
        String url = configReader.getImageServicePath() + "/download/"+key;
        ResponseEntity<ResponseApi> response = restTemplate.getForEntity(url,ResponseApi.class);
        return getString(response,false);
    }

    private String getString(ResponseEntity<ResponseApi> response,boolean isKey) {
        ResponseApi responseBody = response.getBody();
        log.info(responseBody.toString());
        Object body = responseBody.getBody();

        if (body instanceof LinkedHashMap) {
            LinkedHashMap<String, Object> responseMap = (LinkedHashMap<String, Object>) body;

            FileResponseDto fileResponseDto = new FileResponseDto();
            if(isKey){
                fileResponseDto.setKey((String) responseMap.get("key"));

                return fileResponseDto.getKey();
            }else{
                fileResponseDto.setFilePath((String) responseMap.get("filePath"));

                return fileResponseDto.getFilePath();
            }
        } else {
            throw new ImageServiceException("Unexpected response body type: " + body.getClass());
        }
    }
}
