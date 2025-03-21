package com.entity.vo;

import com.entity.YanxueYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 研学报名
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yanxue_yuyue")
public class YanxueYuyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "yanxue_yuyue_uuid_number")
    private String yanxueYuyueUuidNumber;


    /**
     * 研学
     */

    @TableField(value = "yanxue_id")
    private Integer yanxueId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 报名理由
     */

    @TableField(value = "yanxue_yuyue_text")
    private String yanxueYuyueText;


    /**
     * 研学报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 报名状态
     */

    @TableField(value = "yanxue_yuyue_yesno_types")
    private Integer yanxueYuyueYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "yanxue_yuyue_yesno_text")
    private String yanxueYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "yanxue_yuyue_shenhe_time")
    private Date yanxueYuyueShenheTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：报名编号
	 */
    public String getYanxueYuyueUuidNumber() {
        return yanxueYuyueUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setYanxueYuyueUuidNumber(String yanxueYuyueUuidNumber) {
        this.yanxueYuyueUuidNumber = yanxueYuyueUuidNumber;
    }
    /**
	 * 设置：研学
	 */
    public Integer getYanxueId() {
        return yanxueId;
    }


    /**
	 * 获取：研学
	 */

    public void setYanxueId(Integer yanxueId) {
        this.yanxueId = yanxueId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：报名理由
	 */
    public String getYanxueYuyueText() {
        return yanxueYuyueText;
    }


    /**
	 * 获取：报名理由
	 */

    public void setYanxueYuyueText(String yanxueYuyueText) {
        this.yanxueYuyueText = yanxueYuyueText;
    }
    /**
	 * 设置：研学报名时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：研学报名时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：报名状态
	 */
    public Integer getYanxueYuyueYesnoTypes() {
        return yanxueYuyueYesnoTypes;
    }


    /**
	 * 获取：报名状态
	 */

    public void setYanxueYuyueYesnoTypes(Integer yanxueYuyueYesnoTypes) {
        this.yanxueYuyueYesnoTypes = yanxueYuyueYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getYanxueYuyueYesnoText() {
        return yanxueYuyueYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setYanxueYuyueYesnoText(String yanxueYuyueYesnoText) {
        this.yanxueYuyueYesnoText = yanxueYuyueYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getYanxueYuyueShenheTime() {
        return yanxueYuyueShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setYanxueYuyueShenheTime(Date yanxueYuyueShenheTime) {
        this.yanxueYuyueShenheTime = yanxueYuyueShenheTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
