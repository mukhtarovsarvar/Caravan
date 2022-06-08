package com.company.mapper;

import com.company.enums.DeviceType;

public interface ProfileDeviceInfoMapper {

    String getD_id();
    String getD_token();
    DeviceType getD_type();

}
