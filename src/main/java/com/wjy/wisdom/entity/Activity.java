package com.wjy.wisdom.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
@TableName("activity")
@Accessors(chain = true)
public class Activity extends FullAuditedEntity<Activity,Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动类型
     */
    private String name;
    /**
     * 活动日期
     */
    @TableField("activityDate")
    private Date activityDate;
    /**
     * 活动地点
     */
    private String place;
    /**
     * 活动主题
     */
    private String theme;
    /**
     * 活动内容
     */
    private String content;


}
