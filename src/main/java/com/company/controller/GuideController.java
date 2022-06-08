package com.company.controller;

import com.company.dto.response.ActionDTO;
import com.company.dto.GuideDTO;
import com.company.entity.GuideEntity;
import com.company.service.GuideService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/guide")
@Api(tags = "Guide")
public class GuideController {

    private final GuideService guideService;


    @PostMapping("")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @ApiOperation(value = "Create Guide", notes = "Method used for create guide")
    public ResponseEntity<ActionDTO> create(@RequestBody @Valid GuideDTO dto) {
        log.info("create guide: {}",dto);
        return ResponseEntity.ok(guideService.create(dto));
    }



    @PutMapping("")
    @PreAuthorize("hasAnyRole('GUIDE')")
    @ApiOperation(value = "Update Guide", notes = "Method used for update guide")
    public ResponseEntity<ActionDTO> update(@RequestBody @Valid GuideDTO dto) {
        log.info("update guide {}", dto);
        return ResponseEntity.ok(guideService.update(dto));
    }



    @ApiOperation(value = "Get Guide Hiring", notes = "Method used for get guide hiring (true/false)")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @GetMapping("/status")
    public ResponseEntity<Boolean> getGuideHiring() {
        log.info("/status");
        return ResponseEntity.ok(guideService.getGuideHiring());
    }

    @ApiOperation(value = "Update Guide Hiring", notes = "Method used for update guide hiring (true/false)")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @PutMapping("/status")
    public ResponseEntity<Boolean> updateIsHiring() {
        log.info("update  status  ");
        return ResponseEntity.ok(guideService.updateIsHiring());
    }


    @DeleteMapping("")
    @PreAuthorize("hasAnyRole('GUIDE')")
    @ApiOperation(value = "Delete Guide", notes = "Method used for delete guide")
    public ResponseEntity<ActionDTO> delete() {
        log.info("delete guide");
        return ResponseEntity.ok(guideService.delete());
    }

    @GetMapping("/{guideId}")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @ApiOperation(value = "Get Guide", notes = "Method used for getting guide")
    public ResponseEntity<GuideDTO> get(@PathVariable("guideId") String guideId) {
        return ResponseEntity.ok(guideService.getGuide(guideId));
    }


}
