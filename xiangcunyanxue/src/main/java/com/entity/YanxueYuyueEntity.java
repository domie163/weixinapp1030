package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 研学报名
 *
 * @author 
 * @email
 */
@TableName("yanxue_yuyue")
public class YanxueYuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YanxueYuyueEntity() {

	}

	public YanxueYuyueEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 报名编号
     */
    @ColumnInfo(comment="报名编号",type="varchar(200)")
    @TableField(value = "yanxue_yuyue_uuid_number")

    private String yanxueYuyueUuidNumber;


    /**
     * 研学
     */
    @ColumnInfo(comment="研学",type="int(11)")
    @TableField(value = "yanxue_id")

    private Integer yanxueId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 报名理由
     */
    @ColumnInfo(comment="报名理由",type="longtext")
    @TableField(value = "yanxue_yuyue_text")

    private String yanxueYuyueText;


    /**
     * 研学报名时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="研学报名时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 报名状态
     */
    @ColumnInfo(comment="报名状态",type="int(11)")
    @TableField(value = "yanxue_yuyue_yesno_types")

    private Integer yanxueYuyueYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "yanxue_yuyue_yesno_text")

    private String yanxueYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "yanxue_yuyue_shenhe_time")

    private Date yanxueYuyueShenheTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "YanxueYuyue{" +
            ", id=" + id +
            ", yanxueYuyueUuidNumber=" + yanxueYuyueUuidNumber +
            ", yanxueId=" + yanxueId +
            ", yonghuId=" + yonghuId +
            ", yanxueYuyueText=" + yanxueYuyueText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", yanxueYuyueYesnoTypes=" + yanxueYuyueYesnoTypes +
            ", yanxueYuyueYesnoText=" + yanxueYuyueYesnoText +
            ", yanxueYuyueShenheTime=" + DateUtil.convertString(yanxueYuyueShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
