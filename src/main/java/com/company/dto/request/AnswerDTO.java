package com.company.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnswerDTO {

    @NotBlank(message = "content required")
    private String content;


    @NotBlank(message = "reviewId required")
    private String reviewId;


}
