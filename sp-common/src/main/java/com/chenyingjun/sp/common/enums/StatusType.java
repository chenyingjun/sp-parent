package com.chenyingjun.sp.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum StatusType {

    STATUS_1("1", "可用"),
    STATUS_0("0", "禁用");
    private String key;
    private String value;

    public static String getName(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        for (StatusType type : StatusType.values()) {
            if (type.key.equals(key)) {
                return type.value;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    StatusType(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
