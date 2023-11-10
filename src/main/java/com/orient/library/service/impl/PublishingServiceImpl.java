package com.orient.library.service.impl;

import com.orient.library.dto.request.PublishingRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.PublishingResponseDto;
import com.orient.library.entity.Publishing;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.PublishingMapper;
import com.orient.library.repository.PublishingRepository;
import com.orient.library.service.PublishingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublishingServiceImpl implements PublishingService {
    private final PublishingRepository publishingRepository;
    private final LocalDateTime CURRENT_TIME = LocalDateTime.now();
    @Override
    public List<PublishingResponseDto> getAllPublishings() {
        return publishingRepository.getPublishingsByIsDeleted(DeleteType.NONDELETE.value()).stream()
                .map(PublishingMapper.INSTANCE::entityToDto).collect(Collectors.toList());
    }
    @Override
    public PublishingResponseDto getPublishingById(Long id) {
        return PublishingMapper.INSTANCE.entityToDto(publishingRepository.findPublishingByIdAndIsDeleted(id
                ,DeleteType.NONDELETE.value()));
    }
    @Override
    public String createPublishing(PublishingRequestDto publishingRequestDto) {
        Publishing publishing = PublishingMapper.INSTANCE.dtoToEntity(publishingRequestDto);
        publishingRepository.save(publishing);
        return "Publisher has been successfully created!";
    }

    @Override
    public String updatePublishing(PublishingRequestDto publishingRequestDto) {
        Publishing publishing = findPublishing(publishingRequestDto.getId());
        publishing.setName(publishingRequestDto.getName());
        publishing.setDescription(publishingRequestDto.getDescription());
        publishing.setAddress(publishingRequestDto.getAddress());
        publishing.setUpdatedAt(CURRENT_TIME);
        publishingRepository.save(publishing);
        return "Publisher has been successfully updated";
    }


    @Override
    public String deletePublishing(Long id) {
        Publishing publishing = findPublishing(id);
        publishing.setIsDeleted(DeleteType.DELETE.value());
        publishing.setUpdatedAt(CURRENT_TIME);
        publishingRepository.save(publishing);
        return "Publisher has been successfully deleted!";
    }

    @Override
    public String changeStatus(StatusRequestDto statusRequestDto) {
        Publishing publishing = findPublishing(statusRequestDto.getId());
        publishing.setStatus(statusRequestDto.getStatus());
        publishing.setUpdatedAt(CURRENT_TIME);
        publishingRepository.save(publishing);
        return "Publisher status has been successfully updated!";
    }
    private Publishing findPublishing(Long id){
        Publishing publishing = publishingRepository.findPublishingByIdAndIsDeleted(id,DeleteType.NONDELETE.value());
        if(publishing == null){
            throw new DataNotFoundException(Message.PUBLISHING_NOT_FOUND.value());
        }
        return publishing;
    }
}
