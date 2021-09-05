package com.gigalima.personapi.utils;

import com.gigalima.personapi.dto.request.PhoneDTO;
import com.gigalima.personapi.entity.Phone;
import com.gigalima.personapi.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "1199999-9999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .num(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .num(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
