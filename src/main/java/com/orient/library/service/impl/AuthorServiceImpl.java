package com.orient.library.service.impl;

import com.orient.library.dto.request.AuthorRequestDto;
import com.orient.library.dto.request.StatusRequestDto;
import com.orient.library.dto.response.AuthorResponseDto;
import com.orient.library.entity.Author;
import com.orient.library.enums.DeleteType;
import com.orient.library.enums.Message;
import com.orient.library.exception.DataNotFoundException;
import com.orient.library.mapper.AuthorMapper;
import com.orient.library.repository.AuthorRepository;
import com.orient.library.service.AuthorService;
import com.orient.library.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final Utility utility;
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now();

    @Override
    public List<AuthorResponseDto> getAllAuthor() {
        return authorRepository.getAuthorsByIsDeleted(DeleteType.NONDELETE.value()).stream()
                .map(author -> {
                            if (author.getImage() != null) {
                                String image = utility.imageDownload(author.getImage());
                                author.setImage(image);
                            }
                            return AuthorMapper.INSTANCE.entityToDto(author);
                        }
                ).collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        Author author = findAuthor(id);
        if (author.getImage() != null) {
            String image = utility.imageDownload(author.getImage());
            author.setImage(image);
        }
        return AuthorMapper.INSTANCE.entityToDto(author);
    }

    @Override
    public String createAuthor(AuthorRequestDto authorRequestDto) {
        Author author = AuthorMapper.INSTANCE.dtoToEntity(authorRequestDto);
        String image = utility.imageUpload(authorRequestDto.getImageFile());
        author.setImage(image);
        authorRepository.save(author);
        return "Author has been successfully created!";
    }

    @Override
    public String updateAuthor(AuthorRequestDto authorRequestDto) {
        Author author = findAuthor(authorRequestDto.getId());
        author.setName(authorRequestDto.getName());
        author.setSurname(authorRequestDto.getSurname());
        author.setDescription(authorRequestDto.getDescription());
        author.setUpdatedAt(CURRENT_DATE);
        if (authorRequestDto.getImageFile() != null) {
            String image = utility.imageUpload(authorRequestDto.getImageFile());
            author.setImage(image);
        }
        authorRepository.save(author);
        return "Author has been successfully updated!";
    }

    @Override
    public String deleteAuthor(Long id) {
        Author author = findAuthor(id);
        author.setIsDeleted(DeleteType.DELETE.value());
        author.setUpdatedAt(CURRENT_DATE);
        authorRepository.save(author);
        return "Author has been successfully deleted!";
    }

    @Override
    public String changeStatus(StatusRequestDto requestStatus) {
        Author author = findAuthor(requestStatus.getId());
        author.setStatus(requestStatus.getStatus());
        author.setUpdatedAt(CURRENT_DATE);
        authorRepository.save(author);
        return "Author status has been successfully changed!";
    }

    private Author findAuthor(Long id) {
        Author author = authorRepository.findAuthorByIdAndIsDeleted(id, DeleteType.NONDELETE.value());
        if (author == null) {
            throw new DataNotFoundException(Message.AUTHOR_NOT_FOUND.value());
        }
        return author;
    }
}
