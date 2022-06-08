package com.company.controller;

import com.company.dto.response.ActionDTO;
import com.company.dto.request.AnswerDTO;
import com.company.dto.request.ReviewDTO;
import com.company.service.AnswerService;
import com.company.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
@Slf4j
@Api(tags = "Review")
public class ReviewController {

    private final ReviewService reviewService;

    private final AnswerService answerService;

    @PostMapping("")
    @ApiOperation(value = "Create", notes = "Method used for to create review")
    public ResponseEntity<ActionDTO> create(@RequestBody @Valid ReviewDTO dto) {
        log.info("create comment: {}", dto);
        return ResponseEntity.ok(reviewService.create(dto));
    }


    @PostMapping("/answer")
    @PreAuthorize("hasRole('GUIDE')")
    @ApiOperation(value = "Answer", notes = "Method used for to answer review")
    public ResponseEntity<ActionDTO> answer(@RequestBody @Valid AnswerDTO dto) {
        log.info("answer comment: {}", dto);
        return ResponseEntity.ok(answerService.create(dto));
    }


}
