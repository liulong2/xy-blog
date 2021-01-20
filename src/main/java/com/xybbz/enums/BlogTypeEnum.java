package com.xybbz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlogTypeEnum {

    BULLETIN(1,"通知公告"),
    LEARN(2,"学习资料");

    int code;
    String message;
}
