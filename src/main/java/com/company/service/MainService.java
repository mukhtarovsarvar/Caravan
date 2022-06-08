package com.company.service;

import com.company.dto.response.MainResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainService {

    private final GuideService guideService;

    private final TripService tripService;


    public MainResponseDTO getMainPage(int page, int size) {
        MainResponseDTO dto = new MainResponseDTO();

        dto.setTopGuides(guideService.getTop10(page, size));

       dto.setTopTrips(tripService.top10(page,size));

        dto.setStatus(true);

        return dto;

    }
}
