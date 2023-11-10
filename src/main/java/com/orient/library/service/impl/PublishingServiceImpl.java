package com.orient.library.service.impl;

import com.orient.library.dto.request.PublishingRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.PublishingResponseDto;
import com.orient.library.service.PublishingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PublishingServiceImpl implements PublishingService {
    @Override
    public List<PublishingResponseDto> getAllPublishings() {
        return null;
    }

    @Override
    public PublishingResponseDto getPublishingById(Long id) {
        return null;
    }

    @Override
    public String createPublishing(PublishingRequestDto publishingRequestDto) {
        return null;
    }

    @Override
    public String updatePublishing(PublishingRequestDto publishingRequestDto) {
        return null;
    }

    @Override
    public String deletePublishing(Long id) {
        return null;
    }

    @Override
    public String changeStatus(StatusRequestDto statusRequestDto) {
        return null;
    }
}
