package com.company.controller;

import com.company.config.details.EntityDetails;
import com.company.dto.profile.ProfileDTO;
import com.company.dto.response.ActionDTO;
import com.company.enums.AppLang;
import com.company.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@Api(tags = "Profile")
public class ProfileController {

    private final ProfileService profileService;
    
    

    @ApiOperation(value = "Update Profile", notes = "Method used for update profile information",
            authorizations = @Authorization(value = "JWT Token"))
    @PutMapping("")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    public ResponseEntity<ProfileDTO> update(@RequestBody @Valid ProfileDTO dto) {
        log.info("Update Profile {}", dto);
        return ResponseEntity.ok(profileService.update(dto));
    }


    @ApiOperation(value = "Upload Main Photo To Profile", notes = "Method used for upload profile main photo and set it to profile",
            authorizations = @Authorization(value = "JWT Token"))
    @PutMapping("/photo")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    public ResponseEntity<ActionDTO> uploadPhoto(@RequestParam MultipartFile file) {
        log.info("Upload Main Photo To Profile");
        return ResponseEntity.ok(profileService.uploadPhoto(file));
    }

    @ApiOperation(value = "Change Profile Status",
            notes = "Method used for change profile status (ACTIVE or BLOCK)")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @PutMapping("/status")
    public ResponseEntity<Boolean> changeStatus() {
        log.info("Change Profile Status ");
        return ResponseEntity.ok(profileService.changeStatus());
    }

    @ApiOperation(value = "Profile Pagination List",
            notes = "Method used for get profile pagination list order by created date desc")
    @PreAuthorize("hasAnyRole('GUIDE','TOURIST')")
    @GetMapping("")
    public ResponseEntity<PageImpl<ProfileDTO>>
    profilePaginationList(@RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("Profile Pagination List page={} size={}", page, size);
        return ResponseEntity.ok(profileService.profilePaginationList(page, size));
    }

    @ApiOperation(value = "Get Profile ",
            notes = "Method used for get profile ")
    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable("profileId") String profileId) {
        log.info("Get Profile by id {}", profileId);
        return ResponseEntity.ok(profileService.get(profileId));
    }

    @ApiOperation(value = "Get appLanguage",
            notes = "Method used for get appLanguage")
    @GetMapping("/language")
    public ResponseEntity<AppLang> getLanguageById() {
        log.info("Get Profile by id ");

        return ResponseEntity.ok(profileService.getByPhoneNumber(EntityDetails.getProfile().getPhoneNumber()).getAppLanguage());
    }

    @ApiOperation(value = "Put Profile Language",
            notes = "Method used for get profile ")
    @PutMapping("/language")
    public ResponseEntity<ActionDTO> updateLanguageById(@RequestParam("lang") AppLang lang) {

        log.info("Get Profile by id ");
        return ResponseEntity.ok(profileService.updateLanguage( lang));
    }

    @ApiOperation(value = "Delete Profile ",
            notes = "Method used for delete profile ")
    @DeleteMapping("")
    public ResponseEntity<ActionDTO> delete() {
        log.info("delete Profile ");
        return ResponseEntity.ok(profileService.delete());
    }

}
