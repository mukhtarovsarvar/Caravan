package com.company.controller;

import com.company.enums.Region;
import com.company.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/district")
@Slf4j
@Api(tags = "District")
public class DistrictController {

    private final DistrictService service;

    @GetMapping("/{region}")
    @ApiOperation(value = "Get district", notes = "Method used for get district by region name")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    public ResponseEntity<?> getByRegion(@PathVariable("region") Region region){
        log.info("Get District by RegionName: {}",region);
        return ResponseEntity.ok(service.getByRegion(region));
    }


}
