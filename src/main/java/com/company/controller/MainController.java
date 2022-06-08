package com.company.controller;

import com.company.dto.response.MainResponseDTO;
import com.company.service.MainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/main")
@Slf4j
@Api(tags = "Main")
public class MainController {

    private final MainService mainService;


    @GetMapping("")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @ApiOperation(value = "Get Main page", notes = "Method used for Get Main page")
    public ResponseEntity<MainResponseDTO> getMainPage(@RequestParam(value = "page",defaultValue = "0") int page,
                                                       @RequestParam(value = "size",defaultValue = "10") int size){
        log.info("get main page");
        return ResponseEntity.ok(mainService.getMainPage(page, size));
    }
}
