package com.springboot.blog.controller.payload;

import com.springboot.blog.common.dto.GeneratedText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/** This payload class is to map response from TextCortex API */
public class TextCortexResponse {

    private String status;

    private List<GeneratedText> generated_text;

    private String remaining_credits;

    private String error;
}
