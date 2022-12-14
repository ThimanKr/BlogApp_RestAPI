package com.springboot.blog.service.helper;

import com.springboot.blog.common.dto.CommentDto;
import com.springboot.blog.dao.entity.CommentEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** CommentServiceHelper is the helper class for comment service */
@Component
public class CommentServiceHelper {

    @Autowired
    private ModelMapper modelMapper;


    /** method to convert Comment Entity to CommentDto */
    public CommentDto convertCommentEntityToDto(CommentEntity entity){
        return modelMapper.map(entity, CommentDto.class);
    }

    /** method to convert Comment Dto to Comment Entity */
    public CommentEntity convertDtoToCommentEntity(CommentDto dto){
        return modelMapper.map(dto, CommentEntity.class);
    }

}
