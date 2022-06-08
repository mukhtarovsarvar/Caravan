package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.request.AnswerDTO;
import com.company.dto.response.ActionDTO;
import com.company.entity.AnswerEntity;
import com.company.entity.GuideEntity;
import com.company.entity.ReviewEntity;
import com.company.exception.AppForbiddenException;
import com.company.repository.AnswerRepository;
import com.company.repository.GuideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final ReviewService reviewService;

    private final GuideRepository guideRepository;

    public ActionDTO create(AnswerDTO dto) {

        ReviewEntity reviewEntity = reviewService.get(dto.getReviewId());

        GuideEntity guideEntity = guideRepository.findByProfile_PhoneNumber(EntityDetails.getProfile().getPhoneNumber())
                .orElse(null);

        if (guideEntity == null) throw new AppForbiddenException("Not access!");

        switch (reviewEntity.getType()) {

            case TRIP -> {
                if (!reviewEntity.getTrip().getGuideId().equals(guideEntity.getId()))
                    throw new AppForbiddenException("Not access");
            }

            case GUIDE -> {
                if (!reviewEntity.getGuideId().equals(guideEntity.getId()))
                    throw new AppForbiddenException("Not access");
            }
        }

        AnswerEntity answerEntity = new AnswerEntity();

        answerEntity.setContent(dto.getContent());

        answerEntity.setGuideId(guideEntity.getId());

        answerEntity.setReviewId(dto.getReviewId());

        answerRepository.save(answerEntity);

        return new ActionDTO(true);
    }


//    public ActionDTO update()
}
