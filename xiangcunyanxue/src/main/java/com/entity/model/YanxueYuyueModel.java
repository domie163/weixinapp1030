package com.entity.model;

import com.entity.YanxueYuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 研学报名
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YanxueYuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String yanxueYuyueUuidNumber;


    /**
     * 研学
     */
    private Integer yanxueId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 报名理由
     */
    private String yanxueYuyueText;


    /**
     * 研学报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 报名状态
     */
    private Integer yanxueYuyueYesnoTypes;


    /**
     * 审核回复
     */
    private String yanxueYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yanxueYuyueShenheTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：报名编号
	 */
    public String getYanxueYuyueUuidNumber() {
        return yanxueYuyueUuidNumber;
    }


    /**
	 * 设置：报名编号
	 */
    public void setYanxueYuyueUuidNumber(String yanxueYuyueUuidNumber) {
        this.yanxueYuyueUuidNumber = yanxueYuyueUuidNumber;
    }
    /**
	 * 获取：研学
	 */
    public Integer getYanxueId() {
        return yanxueId;
    }


    /**
	 * 设置：研学
	 */
    public void setYanxueId(Integer yanxueId) {
        this.yanxueId = yanxueId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：报名理由
	 */
    public String getYanxueYuyueText() {
        return yanxueYuyueText;
    }


    /**
	 * 设置：报名理由
	 */
    public void setYanxueYuyueText(String yanxueYuyueText) {
        this.yanxueYuyueText = yanxueYuyueText;
    }
    /**
	 * 获取：研学报名时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：研学报名时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：报名状态
	 */
    public Integer getYanxueYuyueYesnoTypes() {
        return yanxueYuyueYesnoTypes;
    }


    /**
	 * 设置：报名状态
	 */
    public void setYanxueYuyueYesnoTypes(Integer yanxueYuyueYesnoTypes) {
        this.yanxueYuyueYesnoTypes = yanxueYuyueYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getYanxueYuyueYesnoText() {
        return yanxueYuyueYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setYanxueYuyueYesnoText(String yanxueYuyueYesnoText) {
        this.yanxueYuyueYesnoText = yanxueYuyueYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getYanxueYuyueShenheTime() {
        return yanxueYuyueShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setYanxueYuyueShenheTime(Date yanxueYuyueShenheTime) {
        this.yanxueYuyueShenheTime = yanxueYuyueShenheTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
