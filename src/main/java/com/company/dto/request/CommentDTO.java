package com.company.dto.request;

import com.company.dto.BaseDTO;
import com.company.enums.ReviewType;
import com.company.dto.response.GuideResponse;
import com.company.dto.response.ProfileResponseDTO;
import com.company.dto.response.TripResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentDTO extends BaseDTO {

    private Integer rate;

    private String reviewTime;

    private String reviewContent;

    private ProfileResponseDTO from;

    private ReviewType commentType;

    private TripResponseDTO trip;

    private GuideResponse guide;

    private String answerTime;

    private String answerContent;
}
