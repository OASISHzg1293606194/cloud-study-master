package com.hzg.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzg.mybatisplus.constants.BaseConstants;
import com.hzg.mybatisplus.constants.enums.DeletedEnum;
import com.hzg.mybatisplus.pojo.enums.SexEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Package: com.hzg.mybatisplus.pojo
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-23 13:05
 */
@Data
@TableName("yh_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 业务ID
     */
    @TableField("biz_id")
    private Long bizId;

    /**
     * 用户名
     */
    @TableField("name")
    private String name;

    /**
     * 性别: [0:女; 1:男; 2:未知]
     */
    @TableField("sex")
    private SexEnum sex;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除: [0:否; 1:是]
     */
    // @TableLogic
    @TableField("deleted")
    private Byte deleted;


    @TableField(value = "ageBegin", exist = false)
    private Integer ageBegin;

    @TableField(value = "ageEnd", exist = false)
    private Integer ageEnd;


    public void creatorData() {
        this.createdBy = BaseConstants.SYS_USERNAME;
        this.createTime = LocalDateTime.now();
        this.updatedBy = BaseConstants.SYS_USERNAME;
        this.updateTime = LocalDateTime.now();
        this.deleted = DeletedEnum.UN_DELETED.getCode();
    }

    public void updatorData() {
        this.updatedBy = BaseConstants.SYS_USERNAME;
        this.updateTime = LocalDateTime.now();
    }

}
