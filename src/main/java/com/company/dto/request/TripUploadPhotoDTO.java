package com.company.dto.request;

import com.company.dto.LocationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class TripUploadPhotoDTO {

    private String id;

    private String photoId;

    private LocationDTO location;



}
