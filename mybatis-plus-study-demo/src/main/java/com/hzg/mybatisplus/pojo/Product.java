package com.hzg.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.hzg.mybatisplus.constants.BaseConstants;
import com.hzg.mybatisplus.constants.enums.DeletedEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Package: com.hzg.mybatisplus.pojo
 * @Description:
 * @Author: HuangZhiGao
 * @CreateDate: 2022-03-28 16:19
 */
@Data
@TableName("t_product")
public class Product implements Serializable {

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
     * 产品名称
     */
    @TableField("name")
    private String name;

    /**
     * 产品价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 版本
     */
    @Version
    @TableField("version")
    private Integer version;

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
    @TableField("deleted")
    private Byte deleted;


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
