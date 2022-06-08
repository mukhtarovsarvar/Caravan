package com.company.controller;


import com.company.dto.AttachDTO;
import com.company.dto.request.TripFilterDTO;
import com.company.dto.request.TripFirstDTO;
import com.company.dto.request.TripSecondDTO;
import com.company.dto.request.TripUploadPhotoDTO;
import com.company.dto.response.ActionDTO;
import com.company.dto.trip.TripDTO;
import com.company.service.TripLocationService;
import com.company.service.TripPhotoService;
import com.company.service.TripService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/trip")
@RequiredArgsConstructor
@Api(tags = "Trip")
public class TripController {

    private final TripService tripService;

    private final TripPhotoService tripPhotoService;

    private final TripLocationService tripLocationService;


    @ApiOperation(value = "Create Trip",
            notes = "Method used for create trip")
    @PostMapping("")
    public ResponseEntity<ActionDTO> create(@RequestBody @Valid TripFirstDTO dto) {
        log.info("Create Trip {}", dto);
        return ResponseEntity.ok(tripService.create(dto));
    }

    @PostMapping("/photo/{tripId}")
    @PreAuthorize("hasAnyRole('GUIDE')")

    @ApiOperation(value = "update Trip",
            notes = "Method used for update trip detail")
    public ResponseEntity<ActionDTO> secondSend(@PathVariable("tripId") String tripId,
                                                @RequestBody TripSecondDTO dto) {
        log.info("Update Trip {}", dto);
        return ResponseEntity.ok(tripPhotoService.create(tripId, dto));
    }


    @ApiOperation(value = "upload trip photo", notes = "Method used for upload trip photo")
    @PreAuthorize("hasAnyRole('GUIDE')")
    @PostMapping("/trip-upload")
    public ResponseEntity<TripUploadPhotoDTO> tripUpload(@RequestBody TripUploadPhotoDTO dto) {
        log.info("trip upload dto");
        return ResponseEntity.ok(tripLocationService.tripUploadPhoto(dto));
    }

    @GetMapping("")
    @ApiOperation(value = "get Trip", notes = "Method used for update trip detail")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    public ResponseEntity<PageImpl<TripDTO>> getByGuideId(@RequestParam(value = "page", defaultValue = "0") int page,
                                                          @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok(tripService.getTrip(page, size));
    }


    @ApiOperation(value = "List By Filter", notes = "Method used for get list by filter")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @GetMapping("/filter")
    public ResponseEntity<List<TripDTO>> filter(@RequestBody TripFilterDTO dto) {
        return ResponseEntity.ok(tripService.filter(dto));
    }

}
