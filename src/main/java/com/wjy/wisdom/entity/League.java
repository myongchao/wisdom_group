package com.wjy.wisdom.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wjy.wisdom.mian.entity.FullAuditedEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Juci123
 * @since 2019-02-02
 */
@Data
@Accessors(chain = true)
@TableName("league")
public class League extends FullAuditedEntity<League,Long> implements Serializable {

    /**
     * 团员姓名
     */
    private String name;
    /**
     * 身份证号
     */
    @TableField("idNumber")
    private String idNumber;
    /**
     * 民族
     */
    private String national;
    /**
     * 政治面貌
     */
    @TableField("politicalLandscape")
    private String politicalLandscape;
    /**
     * 文化程度
     */
    private String education;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 入团时间
     */
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    @TableField("leagueTime")
    private Date leagueTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    @TableField("applyDate")
    private Date applyDate;

    private String qq;
    /**
     * 团干
     */
    @TableField("tuanGan")
    private String tuanGan;
    /**
     * 团干性质
     */
    @TableField("tuanGanXZ")
    private String tuanGanXZ;
    private String status;
    /**
     * 现任职务
     */
    private String position;
    /**
     * 是否为党员
     */
    @TableField("partyMember")
    private String partyMember;

    @TableField("is_admin")
    private String isAdmin;

}
