package com.entity.model;

import com.entity.YanxueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 研学
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YanxueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 研学标题
     */
    private String yanxueName;


    /**
     * 研学编号
     */
    private String yanxueUuidNumber;


    /**
     * 研学照片
     */
    private String yanxuePhoto;


    /**
     * 负责人
     */
    private String yanxueFuzeren;


    /**
     * 联系方式
     */
    private String yanxuePhone;


    /**
     * 研学路线
     */
    private String yanxueAddress;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 研学类型
     */
    private Integer yanxueTypes;


    /**
     * 剩余可参与人数
     */
    private Integer yanxueKucunNumber;


    /**
     * 出发时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date yanxueTime;


    /**
     * 研学热度
     */
    private Integer yanxueClicknum;


    /**
     * 研学内容
     */
    private String yanxueContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer yanxueDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
