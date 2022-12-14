package com.springboot.blog.controller.helper;

import com.springboot.blog.common.dto.GeneratePostContentDto;
import com.springboot.blog.controller.payload.GeneratePostContentRequest;
import com.springboot.blog.controller.payload.GeneratePostContentResponse;
import com.springboot.blog.controller.payload.TextCortexResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
/** The PostControllerHelper is the helper class for post controller */
public class PostControllerHelper {

    /** ModelMapper to map object properties */
    @Autowired
    private ModelMapper modelMapper;

    /** This method used for convert GeneratePostContentRequest to GeneratePostContentDto */
    public GeneratePostContentDto convertRequestToGenerateContentDto(GeneratePostContentRequest request){

        GeneratePostContentDto dto = GeneratePostContentDto.builder().build();
        // use modelMapper to convert.
        modelMapper.map(request, dto);
        return dto;
    }

    /** This method used for convert TextCortexResponse to GeneratePostContentResponse */
    public GeneratePostContentResponse convertTextCortexResponseToPostContentResponse(TextCortexResponse response){

        return GeneratePostContentResponse.builder()
                .content(response.getGenerated_text().get(0).getText())
                .status(response.getStatus())
                .build();
    }
}
