package com.wjy.wisdom.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("meetting")
@Accessors(chain = true)
public class Meetting extends FullAuditedEntity<Meetting,Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会议主题
     */
    private String title;
    /**
     * 会议时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @TableField("meet_date")
    private Date meetDate;
    /**
     * 会议地点
     */
    private String place;
    /**
     * 会议主题
     */
    private String theme;
    /**
     * 会议内容
     */
    private String content;

    /**
     * 会议类型
     */
    private Integer type;


}
