package com.springboot.blog.service.helper;

import com.springboot.blog.common.dto.CommentDto;
import com.springboot.blog.dao.entity.CommentEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommentServiceHelper {

    private ModelMapper modelMapper;


    public CommentDto convertCommentEntityToDto(CommentEntity entity){
        return modelMapper.map(entity, CommentDto.class);
    }

    public CommentEntity convertDtoToCommentEntity(CommentDto dto){
        return modelMapper.map(dto, CommentEntity.class);
    }

}
