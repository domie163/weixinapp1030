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
 * 研学
 *
 * @author 
 * @email
 */
@TableName("yanxue")
public class YanxueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YanxueEntity() {

	}

	public YanxueEntity(T t) {
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
     * 研学标题
     */
    @ColumnInfo(comment="研学标题",type="varchar(200)")
    @TableField(value = "yanxue_name")

    private String yanxueName;


    /**
     * 研学编号
     */
    @ColumnInfo(comment="研学编号",type="varchar(200)")
    @TableField(value = "yanxue_uuid_number")

    private String yanxueUuidNumber;


    /**
     * 研学照片
     */
    @ColumnInfo(comment="研学照片",type="varchar(200)")
    @TableField(value = "yanxue_photo")

    private String yanxuePhoto;


    /**
     * 负责人
     */
    @ColumnInfo(comment="负责人",type="varchar(200)")
    @TableField(value = "yanxue_fuzeren")

    private String yanxueFuzeren;


    /**
     * 联系方式
     */
    @ColumnInfo(comment="联系方式",type="varchar(200)")
    @TableField(value = "yanxue_phone")

    private String yanxuePhone;


    /**
     * 研学路线
     */
    @ColumnInfo(comment="研学路线",type="varchar(200)")
    @TableField(value = "yanxue_address")

    private String yanxueAddress;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 研学类型
     */
    @ColumnInfo(comment="研学类型",type="int(11)")
    @TableField(value = "yanxue_types")

    private Integer yanxueTypes;


    /**
     * 剩余可参与人数
     */
    @ColumnInfo(comment="剩余可参与人数",type="int(11)")
    @TableField(value = "yanxue_kucun_number")

    private Integer yanxueKucunNumber;


    /**
     * 出发时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="出发时间",type="timestamp")
    @TableField(value = "yanxue_time")

    private Date yanxueTime;


    /**
     * 研学热度
     */
    @ColumnInfo(comment="研学热度",type="int(11)")
    @TableField(value = "yanxue_clicknum")

    private Integer yanxueClicknum;


    /**
     * 研学内容
     */
    @ColumnInfo(comment="研学内容",type="longtext")
    @TableField(value = "yanxue_content")

    private String yanxueContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "yanxue_delete")

    private Integer yanxueDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：研学标题
	 */
    public String getYanxueName() {
        return yanxueName;
    }
    /**
	 * 设置：研学标题
	 */

    public void setYanxueName(String yanxueName) {
        this.yanxueName = yanxueName;
    }
    /**
	 * 获取：研学编号
	 */
    public String getYanxueUuidNumber() {
        return yanxueUuidNumber;
    }
    /**
	 * 设置：研学编号
	 */

    public void setYanxueUuidNumber(String yanxueUuidNumber) {
        this.yanxueUuidNumber = yanxueUuidNumber;
    }
    /**
	 * 获取：研学照片
	 */
    public String getYanxuePhoto() {
        return yanxuePhoto;
    }
    /**
	 * 设置：研学照片
	 */

    public void setYanxuePhoto(String yanxuePhoto) {
        this.yanxuePhoto = yanxuePhoto;
    }
    /**
	 * 获取：负责人
	 */
    public String getYanxueFuzeren() {
        return yanxueFuzeren;
    }
    /**
	 * 设置：负责人
	 */

    public void setYanxueFuzeren(String yanxueFuzeren) {
        this.yanxueFuzeren = yanxueFuzeren;
    }
    /**
	 * 获取：联系方式
	 */
    public String getYanxuePhone() {
        return yanxuePhone;
    }
    /**
	 * 设置：联系方式
	 */

    public void setYanxuePhone(String yanxuePhone) {
        this.yanxuePhone = yanxuePhone;
    }
    /**
	 * 获取：研学路线
	 */
    public String getYanxueAddress() {
        return yanxueAddress;
    }
    /**
	 * 设置：研学路线
	 */

    public void setYanxueAddress(String yanxueAddress) {
        this.yanxueAddress = yanxueAddress;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：研学类型
	 */
    public Integer getYanxueTypes() {
        return yanxueTypes;
    }
    /**
	 * 设置：研学类型
	 */

    public void setYanxueTypes(Integer yanxueTypes) {
        this.yanxueTypes = yanxueTypes;
    }
    /**
	 * 获取：剩余可参与人数
	 */
    public Integer getYanxueKucunNumber() {
        return yanxueKucunNumber;
    }
    /**
	 * 设置：剩余可参与人数
	 */

    public void setYanxueKucunNumber(Integer yanxueKucunNumber) {
        this.yanxueKucunNumber = yanxueKucunNumber;
    }
    /**
	 * 获取：出发时间
	 */
    public Date getYanxueTime() {
        return yanxueTime;
    }
    /**
	 * 设置：出发时间
	 */

    public void setYanxueTime(Date yanxueTime) {
        this.yanxueTime = yanxueTime;
    }
    /**
	 * 获取：研学热度
	 */
    public Integer getYanxueClicknum() {
        return yanxueClicknum;
    }
    /**
	 * 设置：研学热度
	 */

    public void setYanxueClicknum(Integer yanxueClicknum) {
        this.yanxueClicknum = yanxueClicknum;
    }
    /**
	 * 获取：研学内容
	 */
    public String getYanxueContent() {
        return yanxueContent;
    }
    /**
	 * 设置：研学内容
	 */

    public void setYanxueContent(String yanxueContent) {
        this.yanxueContent = yanxueContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 设置：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getYanxueDelete() {
        return yanxueDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setYanxueDelete(Integer yanxueDelete) {
        this.yanxueDelete = yanxueDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Yanxue{" +
            ", id=" + id +
            ", yanxueName=" + yanxueName +
            ", yanxueUuidNumber=" + yanxueUuidNumber +
            ", yanxuePhoto=" + yanxuePhoto +
            ", yanxueFuzeren=" + yanxueFuzeren +
            ", yanxuePhone=" + yanxuePhone +
            ", yanxueAddress=" + yanxueAddress +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", yanxueTypes=" + yanxueTypes +
            ", yanxueKucunNumber=" + yanxueKucunNumber +
            ", yanxueTime=" + DateUtil.convertString(yanxueTime,"yyyy-MM-dd") +
            ", yanxueClicknum=" + yanxueClicknum +
            ", yanxueContent=" + yanxueContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", yanxueDelete=" + yanxueDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
