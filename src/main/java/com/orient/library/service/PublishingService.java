package com.orient.library.service;

import com.orient.library.dto.request.PublishingRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.PublishingResponseDto;

import java.util.List;

public interface PublishingService {
    List<PublishingResponseDto> getAllPublishings();
    PublishingResponseDto getPublishingById(Long id);
    String createPublishing(PublishingRequestDto publishingRequestDto);
    String updatePublishing(PublishingRequestDto publishingRequestDto);
    String deletePublishing(Long id);
    String changeStatus(StatusRequestDto statusRequestDto);
}
