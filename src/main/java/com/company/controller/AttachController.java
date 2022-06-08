package com.company.controller;

import com.company.dto.AttachDTO;
import com.company.service.AttachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v1/attach")
@RequiredArgsConstructor
@Api(tags = "Attach")
public class AttachController {

    private final AttachService attachService;
    @Value("${trip_folder}")
    private String folder;


    /**
     * PUBLIC
     */
    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @ApiOperation(value = "Upload file", notes = "Method used for upload file")
    public ResponseEntity<AttachDTO> upload(@RequestParam MultipartFile file) {
        log.info("/upload fileName={}, fileSize={}", file.getOriginalFilename(), file.getSize());
        return ResponseEntity.ok(attachService.upload(file,folder));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @ApiOperation(value = " Delete file", notes = "Method used for delete file")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.info("/delete fileId={}", id);
        return ResponseEntity.ok(attachService.delete(id));
    }

}
