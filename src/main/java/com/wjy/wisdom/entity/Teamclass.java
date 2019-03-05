package com.wjy.wisdom.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import com.wjy.wisdom.mian.entity.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Juci123
 * @since 2019-03-05
 */
@Data
@TableName("teamclass")
@Accessors(chain = true)
public class Teamclass extends FullAuditedEntity<Teamclass,Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 团课学时
     */
    @TableField("classHour")
    private String classHour;
    /**
     * 团课地点
     */
    private String place;
    /**
     * 团课主题
     */
    private String theme;
    /**
     * 团课 内容
     */
    private String content;


}
