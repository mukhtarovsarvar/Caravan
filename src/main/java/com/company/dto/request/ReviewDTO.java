package com.company.dto.request;

import com.company.enums.ReviewType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class ReviewDTO {


    @Size(max = 5, message = "rate must be between 0 and 5")
    private Integer rate;

    @NotBlank(message = "content required")
    private String content;

    @NotNull(message = "type required")
    private ReviewType type;

    private String tripId;

    private String guideId;
}


// reponse comment   profile dto
// if(trip)  trip dto
// if(guide)  guide dto
