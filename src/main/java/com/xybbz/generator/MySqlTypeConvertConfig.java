package com.xybbz.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MySqlTypeConvertConfig extends MySqlTypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {

        if (fieldType.toLowerCase().contains("datetime")) {
            return DbColumnType.LOCAL_TIME;
        }

        return super.processTypeConvert(globalConfig, fieldType);
    }
}
