package com.xybbz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum YesNoEnum {
    YES(1,"是"),
    NO(2,"否");
    int code;
    String message;
}
