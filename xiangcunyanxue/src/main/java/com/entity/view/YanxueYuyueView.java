package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.YanxueYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 研学报名
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("yanxue_yuyue")
public class YanxueYuyueView extends YanxueYuyueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 报名状态的值
	*/
	@ColumnInfo(comment="报名状态的字典表值",type="varchar(200)")
	private String yanxueYuyueYesnoValue;

	//级联表 研学
		/**
		* 研学标题
		*/

		@ColumnInfo(comment="研学标题",type="varchar(200)")
		private String yanxueName;
		/**
		* 研学编号
		*/

		@ColumnInfo(comment="研学编号",type="varchar(200)")
		private String yanxueUuidNumber;
		/**
		* 研学照片
		*/

		@ColumnInfo(comment="研学照片",type="varchar(200)")
		private String yanxuePhoto;
		/**
		* 负责人
		*/

		@ColumnInfo(comment="负责人",type="varchar(200)")
		private String yanxueFuzeren;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String yanxuePhone;
		/**
		* 研学路线
		*/

		@ColumnInfo(comment="研学路线",type="varchar(200)")
		private String yanxueAddress;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 研学类型
		*/
		@ColumnInfo(comment="研学类型",type="int(11)")
		private Integer yanxueTypes;
			/**
			* 研学类型的值
			*/
			@ColumnInfo(comment="研学类型的字典表值",type="varchar(200)")
			private String yanxueValue;
		/**
		* 剩余可参与人数
		*/

		@ColumnInfo(comment="剩余可参与人数",type="int(11)")
		private Integer yanxueKucunNumber;
		/**
		* 出发时间
		*/
		@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat
		@ColumnInfo(comment="出发时间",type="timestamp")
		private Date yanxueTime;
		/**
		* 研学热度
		*/

		@ColumnInfo(comment="研学热度",type="int(11)")
		private Integer yanxueClicknum;
		/**
		* 研学内容
		*/

		@ColumnInfo(comment="研学内容",type="longtext")
		private String yanxueContent;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer yanxueDelete;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 账户状态
		*/
		@ColumnInfo(comment="账户状态",type="int(11)")
		private Integer jinyongTypes;
			/**
			* 账户状态的值
			*/
			@ColumnInfo(comment="账户状态的字典表值",type="varchar(200)")
			private String jinyongValue;



	public YanxueYuyueView() {

	}

	public YanxueYuyueView(YanxueYuyueEntity yanxueYuyueEntity) {
		try {
			BeanUtils.copyProperties(this, yanxueYuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 报名状态的值
	*/
	public String getYanxueYuyueYesnoValue() {
		return yanxueYuyueYesnoValue;
	}
	/**
	* 设置： 报名状态的值
	*/
	public void setYanxueYuyueYesnoValue(String yanxueYuyueYesnoValue) {
		this.yanxueYuyueYesnoValue = yanxueYuyueYesnoValue;
	}


	//级联表的get和set 研学

		/**
		* 获取： 研学标题
		*/
		public String getYanxueName() {
			return yanxueName;
		}
		/**
		* 设置： 研学标题
		*/
		public void setYanxueName(String yanxueName) {
			this.yanxueName = yanxueName;
		}

		/**
		* 获取： 研学编号
		*/
		public String getYanxueUuidNumber() {
			return yanxueUuidNumber;
		}
		/**
		* 设置： 研学编号
		*/
		public void setYanxueUuidNumber(String yanxueUuidNumber) {
			this.yanxueUuidNumber = yanxueUuidNumber;
		}

		/**
		* 获取： 研学照片
		*/
		public String getYanxuePhoto() {
			return yanxuePhoto;
		}
		/**
		* 设置： 研学照片
		*/
		public void setYanxuePhoto(String yanxuePhoto) {
			this.yanxuePhoto = yanxuePhoto;
		}

		/**
		* 获取： 负责人
		*/
		public String getYanxueFuzeren() {
			return yanxueFuzeren;
		}
		/**
		* 设置： 负责人
		*/
		public void setYanxueFuzeren(String yanxueFuzeren) {
			this.yanxueFuzeren = yanxueFuzeren;
		}

		/**
		* 获取： 联系方式
		*/
		public String getYanxuePhone() {
			return yanxuePhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setYanxuePhone(String yanxuePhone) {
			this.yanxuePhone = yanxuePhone;
		}

		/**
		* 获取： 研学路线
		*/
		public String getYanxueAddress() {
			return yanxueAddress;
		}
		/**
		* 设置： 研学路线
		*/
		public void setYanxueAddress(String yanxueAddress) {
			this.yanxueAddress = yanxueAddress;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 研学类型
		*/
		public Integer getYanxueTypes() {
			return yanxueTypes;
		}
		/**
		* 设置： 研学类型
		*/
		public void setYanxueTypes(Integer yanxueTypes) {
			this.yanxueTypes = yanxueTypes;
		}


			/**
			* 获取： 研学类型的值
			*/
			public String getYanxueValue() {
				return yanxueValue;
			}
			/**
			* 设置： 研学类型的值
			*/
			public void setYanxueValue(String yanxueValue) {
				this.yanxueValue = yanxueValue;
			}

		/**
		* 获取： 剩余可参与人数
		*/
		public Integer getYanxueKucunNumber() {
			return yanxueKucunNumber;
		}
		/**
		* 设置： 剩余可参与人数
		*/
		public void setYanxueKucunNumber(Integer yanxueKucunNumber) {
			this.yanxueKucunNumber = yanxueKucunNumber;
		}

		/**
		* 获取： 出发时间
		*/
		public Date getYanxueTime() {
			return yanxueTime;
		}
		/**
		* 设置： 出发时间
		*/
		public void setYanxueTime(Date yanxueTime) {
			this.yanxueTime = yanxueTime;
		}

		/**
		* 获取： 研学热度
		*/
		public Integer getYanxueClicknum() {
			return yanxueClicknum;
		}
		/**
		* 设置： 研学热度
		*/
		public void setYanxueClicknum(Integer yanxueClicknum) {
			this.yanxueClicknum = yanxueClicknum;
		}

		/**
		* 获取： 研学内容
		*/
		public String getYanxueContent() {
			return yanxueContent;
		}
		/**
		* 设置： 研学内容
		*/
		public void setYanxueContent(String yanxueContent) {
			this.yanxueContent = yanxueContent;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getYanxueDelete() {
			return yanxueDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setYanxueDelete(Integer yanxueDelete) {
			this.yanxueDelete = yanxueDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}
		/**
		* 获取： 账户状态
		*/
		public Integer getJinyongTypes() {
			return jinyongTypes;
		}
		/**
		* 设置： 账户状态
		*/
		public void setJinyongTypes(Integer jinyongTypes) {
			this.jinyongTypes = jinyongTypes;
		}


			/**
			* 获取： 账户状态的值
			*/
			public String getJinyongValue() {
				return jinyongValue;
			}
			/**
			* 设置： 账户状态的值
			*/
			public void setJinyongValue(String jinyongValue) {
				this.jinyongValue = jinyongValue;
			}


	@Override
	public String toString() {
		return "YanxueYuyueView{" +
			", yanxueYuyueYesnoValue=" + yanxueYuyueYesnoValue +
			", yanxueName=" + yanxueName +
			", yanxueUuidNumber=" + yanxueUuidNumber +
			", yanxuePhoto=" + yanxuePhoto +
			", yanxueFuzeren=" + yanxueFuzeren +
			", yanxuePhone=" + yanxuePhone +
			", yanxueAddress=" + yanxueAddress +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", yanxueKucunNumber=" + yanxueKucunNumber +
			", yanxueTime=" + DateUtil.convertString(yanxueTime,"yyyy-MM-dd") +
			", yanxueClicknum=" + yanxueClicknum +
			", yanxueContent=" + yanxueContent +
			", yanxueDelete=" + yanxueDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			"} " + super.toString();
	}
}
