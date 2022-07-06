package com.hzg.mybatisplus.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @Package: com.hzg.mybatisplus.pojo.enums
 * @Description: 性别枚举
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-28 23:12
 */
@Getter
public enum SexEnum {

    /**
     * 性别: [0:女; 1:男; 2:未知]
     */
    FEMALE(new Byte("0"), "女"),
    MALE(new Byte("1"), "男"),
    UNKNOWN(new Byte("3"), "未知");

    @EnumValue
    private Byte code;
    private String desc;

    SexEnum(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
