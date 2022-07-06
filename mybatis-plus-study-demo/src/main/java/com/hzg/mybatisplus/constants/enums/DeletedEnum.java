package com.hzg.mybatisplus.constants.enums;

import lombok.Getter;

/**
 * @Package: com.hzg.mybatisplus.constants.enums
 * @Description: 删除状态枚举类
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-23 13:17
 */
@Getter
public enum DeletedEnum {

    /**
     * 业务逻辑删除-是否删除: [1:是; 0:否]
     */
    IS_DELETED(new Byte("1"), "已删除"),
    UN_DELETED(new Byte("0"), "未删除");

    private Byte code;
    private String desc;

    DeletedEnum(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据枚举编码获取枚举描述
     * <p/>
     *
     * @param code 枚举编码
     * @return java.lang.String
     * @author HuangZhiGao
     * @date 2022/3/23/023 13:18
     */
    public static String getDescByCode(Byte code) {
        for (DeletedEnum codeEnum : DeletedEnum.values()) {
            if (codeEnum.getCode().equals(code)) {
                return codeEnum.getDesc();
            }
        }
        return null;
    }

}
