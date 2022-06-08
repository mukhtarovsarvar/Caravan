package com.company.dto;

import com.company.enums.DeviceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DeviceDTO {

    private String deviceId;

    private String deviceToken;

    private DeviceType deviceType;


}
