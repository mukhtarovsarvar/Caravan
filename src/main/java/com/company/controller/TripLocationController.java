package com.company.controller;

import com.company.service.TripLocationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/trip-location")
@RequiredArgsConstructor
@Api(tags = "Trip Location")
public class TripLocationController {

    private final TripLocationService tripLocationService;


}
