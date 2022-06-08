package com.company.service;

import com.company.config.details.EntityDetails;
import com.company.dto.request.CommentDTO;
import com.company.dto.request.ReviewDTO;
import com.company.dto.response.ActionDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.ReviewEntity;
import com.company.enums.ReviewType;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProfileService profileService;
    private final TripService tripService;
    private final GuideService guideService;

    public ActionDTO create(ReviewDTO dto) {

       ProfileEntity profile = EntityDetails.getProfile();

        ReviewEntity entity = new ReviewEntity();

        if (dto.getType().equals(ReviewType.TRIP)) {

            if (Optional.ofNullable(tripService.get(dto.getTripId())).isEmpty())
                return new ActionDTO("Trip", "trip not found!", false);

            entity.setTripId(dto.getTripId());

        } else {

            if (Optional.ofNullable(guideService.get(dto.getGuideId())).isEmpty())
                return new ActionDTO("Guide", "guide not found!", false);

            entity.setGuideId(dto.getGuideId());
        }

        entity.setProfileId(profile.getId());
        entity.setRate(dto.getRate());
        entity.setText(dto.getContent());
        entity.setType(dto.getType());

        reviewRepository.save(entity);

        return new ActionDTO(true);
    }

    public CommentDTO getTripComment(String tripId)   {

        return null;
    }

    public ReviewEntity get(String id) {

        return reviewRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Review not found!");
        });

    }


}
