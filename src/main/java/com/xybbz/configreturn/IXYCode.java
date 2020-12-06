package com.xybbz.configreturn;

import java.io.Serializable;

public interface IXYCode extends Serializable {
    //获取状态码
    int getCode();

    //获取消息
    String getMessage();
}
