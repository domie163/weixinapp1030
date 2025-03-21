<template name="glanceShopcart">
	<view class="glance-shop-cart" style="background-color: #F5F5F5;" @touchmove="scrollhoming" @touchend="scrollhomed">
		<!-- 顶部广告图 -->
		<!-- 我是分割 -->
		<view style="height: 10px;background-color: #F5F5F5;"></view>
		<!-- 空购物车背景 -->
		<view v-if="shownullcart" style="width: 100%;height: 250px;">
			<view class="glance-shop-cart-nullcart"></view>
			<view style="height: 30px;width: 100%;font-size: 15px;line-height: 30px;text-align: center;">您的购物车为空，快去添加心爱的商品吧！</view>
		</view>
		<!-- 购物车商品 -->
		<view v-for="(item,index) in cart" :key="index" style="background-color: #FFFFFF;">
			<scroll-view style="width: 100%;white-space: nowrap;" scroll-x="true" :scroll-left='scrollposition'
			 scroll-with-animation="true" v-if="item.id > -99">
				<view class="glance-shop-cart-scrollx-items" style="display: inline-block;width: 100%;">
					<view class="glance-shop-cart-scrollx-items-item">
						<!-- 勾选 -->
						<view style="width: 10%;height: 100%;background-color: #FFFFFF;">
							<!-- 勾选 -->
							<view @tap="clickitemselected(item.id)" class="glance-shop-cart-scrollx-items-item-sel" :class="[ item.id > 0 ? 'glance-shop-cart-itemselected-img':'glance-shop-cart-itemunselected-img']"></view>
						</view>
						<!-- 图片 -->
						<view style="width: 30%;height: 100%;text-align:center;">
							<image :src="baseUrl+item.shangpinPhoto" mode="scaleToFill" style="height: 75px;width: 75px;line-height: 80px;padding-top: 5px;"></image>
						</view>
						<!-- 描述 -->
						<view class="glance-shop-cart-items-item-des">
							<!-- 名称 -->
							<view class="sigle-line-text" style="font-size: 16px;height: 33.33%;text-align: left;">{{ item.shangpinName }}</view>
							<!-- 属性 -->
							<!-- 价格 & 数量-->
							<view class="glance-shop-cart-items-item-pq">
								<view class="sigle-line-text" style="font-size: 15px;text-align: left;width: 50%;">￥{{item.shangpinNewMoney}}</view>
								<!-- 数量操作 -->
								<view class="glance-shop-cart-items-item-opt">
									<!-- 减数量 -->
									<view class="glance-shop-cart-items-item-opt-quantity-minus" @click="minusitem(item.id)">-</view>
									<!-- 数量 -->
									<view class="glance-shop-cart-items-item-opt-quantity">{{ item.buyNumber }}</view>
									<!-- 加数量 -->
									<view class="glance-shop-cart-items-item-opt-quantity-plus" @click="plusitem(item.id)">+</view>
								</view>
							</view>
						</view>
					</view>
				</view>
			</scroll-view>
			<view style="height: 10px;background-color: #F5F5F5;"></view>
		</view>
		<!-- 金额合计 -->
		<view class="glance-shop-cart-order">
			<!-- 全选 -->
			<view style="width: 12%;">
				<!-- 勾选 -->
				<view class="glance-shop-cart-scrollx-items-item-sel" :class="[ isselectedall ? 'glance-shop-cart-itemselected-img':'glance-shop-cart-itemunselected-img']"
				 @click="clickitemselectedall"></view>
			</view>
			<!-- 总数量 -->
			<view class="glance-shop-cart-total-cnt">已选：( {{ cntitems }} )</view>
			<!-- 总金额 -->
			<view class="glance-shop-cart-total-amt">￥{{ totalamount }}</view>
			<!-- 立即下单 -->
			<view class="glance-shop-cart-create-order" @click="createorder">立即下单</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 全选 默认全选
				isselectedall: true,
				// scroll position
				scrollposition: 0,
				// 实际项目中 购物车数据可从本地缓存中获取或从服务端获取
				cart: [],
				// 合计金额
				totalamount: 0,
				// 购物车商品数量 
				cntitems: 0,
				// 显示空购物车背景
				shownullcart: false,
				user: {}
			};
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		onShow() {
			this.init();
		},
		methods: {
			async init() {
				this.totalamount = 0;
				this.cntitems = 0;
				let table = uni.getStorageSync("nowTable");
				let res = await this.$api.session(table);
				this.user = res.data;
				res = await this.$api.page('cart', {
					yonghuId: this.user.id
				});
				this.cart = res.data.list;
				// 默认勾选购物车所有商品 合计金额 合计数量
				for (let i = 0; i < this.cart.length; i++) {
					// 总金额 
					this.totalamount = parseFloat(this.totalamount) + parseFloat(this.cart[i].shangpinNewMoney * this.cart[i].buyNumber)
					// 总数量
					this.cntitems = parseInt(this.cntitems) + parseInt(this.cart[i].buyNumber)
				}
				this.totalamount = this.fmamount(this.totalamount)
			},
			// scroll x 归位
			scrollhoming() {
				this.scrollposition = this.scrollposition - 1
			},
			// scroll x 归位后重置 scrollposition
			scrollhomed() {
				this.scrollposition = 0
			},
			// 点击全选
			clickitemselectedall() {
				this.isselectedall = !this.isselectedall
				// 全选
				if (this.isselectedall) {
					if (this.cart) {
						for (var i = 0; i < this.cart.length; i++) {
							// 未选的则选
							if (this.cart[i].id < 0) {
								this.cart[i].id = -this.cart[i].id
								// 更新总数量
								this.cntitems = parseInt(this.cntitems) + parseInt(this.cart[i].buyNumber)
								// 更新总金额
								this.totalamount = parseFloat(this.totalamount) + parseFloat(this.cart[i].shangpinNewMoney * this.cart[i].buyNumber)
								this.totalamount = this.fmamount(this.totalamount)
							}
						}
					}
				} else {
					// 全不选
					// this.cntitems = 0
					if (this.cart) {
						for (var i = 0; i < this.cart.length; i++) {
							// 累计总金额和总数量 勾选时加
							if (this.isselected(this.cart[i].id)) {
								this.cart[i].id = -this.cart[i].id
								// 更新总数量
								this.cntitems = this.cntitems - this.cart[i].buyNumber
								// 更新总金额
								this.totalamount = this.totalamount - this.cart[i].shangpinNewMoney * this.cart[i].buyNumber
								this.totalamount = this.fmamount(this.totalamount)
							}
						}
					}
				}
			},
			// 点击勾选
			clickitemselected(id) {
				if (this.cart) {
					for (var i = 0; i < this.cart.length; i++) {
						if (this.cart[i].id == id) {
							this.cart[i].id = -this.cart[i].id
							// 累计总金额和总数量 勾选时加
							if (this.isselected(this.cart[i].id)) {
								// 更新总数量
								this.cntitems = parseInt(this.cntitems) + parseInt(this.cart[i].buyNumber)
								// 更新总金额
								this.totalamount = parseFloat(this.totalamount) + parseFloat(this.cart[i].shangpinNewMoney * this.cart[i].buyNumber)
								this.totalamount = this.fmamount(this.totalamount)
								// 最后已勾选则 全选
								if (this._isselectedall()) {
									this.isselectedall = true
								}
							} else {
								// 取消勾选时减
								// 更新总数量
								this.cntitems = this.cntitems - this.cart[i].buyNumber
								// 更新总金额
								this.totalamount = this.totalamount - this.cart[i].shangpinNewMoney * this.cart[i].buyNumber
								this.totalamount = this.fmamount(this.totalamount)
								this.isselectedall = false
							}
							return
						}
					}
				}
			},
			// 减数量
			async minusitem(itemid) {
				for (let i = 0; i < this.cart.length; i++) {
					if ((this.cart[i].id == itemid) && (this.cart[i].buyNumber > 0)) {
						// 更新item数量
						this.cart[i].buyNumber = this.cart[i].buyNumber - 1
						// 勾选状态下更新数量和金额
						if (this.isselected(this.cart[i].id)) {
							// 更新总数量
							this.updatecntitems(-1)
							// 更新总金额
							this.updatetotalamt(-this.cart[i].shangpinNewMoney)
							// 执行update接口
							await this.$api.update('cart', this.cart[i]);
						}
						// 数量减为0时 不勾选
						if (this.cart[i].buyNumber == 0) {
							this.cart[i].id = this._unselected(this.cart[i].id)
							// 执行删除接口
							await this.$api.del('cart', JSON.stringify([itemid]));
							uni.removeStorageSync(`cart${this.cart[i].shangpinId}`)
							this.init();
						}
						return
					}
				}
			},
			// 加数量
			async plusitem(itemid) {
				for (let i = 0; i < this.cart.length; i++) {
					// 这里需要进行超卖控制 商品可售卖的数量 这里面示例可售卖100
					if ((this.cart[i].id == itemid) && (this.cart[i].buyNumber < 100)) {
						// 更新item数量
						this.cart[i].buyNumber = parseInt(this.cart[i].buyNumber) + 1
						// 勾选状态下更新数量和金额
						if (this.isselected(this.cart[i].id)) {
							// 更新总数量
							this.updatecntitems(1)
							// 更新总金额
							this.updatetotalamt(this.cart[i].shangpinNewMoney)
						} else {
							// 加数量时未勾选则 勾选
							this.cart[i].id = this._selected(this.cart[i].id)
							// 更新总数量、
							this.cntitems = parseInt(this.cntitems) + parseInt(this.cart[i].buyNumber)
							// 更新总金额
							this.totalamount = parseFloat(this.totalamount) + parseFloat(this.cart[i].shangpinNewMoney * this.cart[i].buyNumber)
							this.totalamount = this.fmamount(this.totalamount)
						}
						// 执行update接口
						await this.$api.update('cart',this.cart[i]);
						// 最后已勾选则 全选
						if (this._isselectedall()) {
							this.isselectedall = true
						}
						return
					}
				}
			},
			// 更新合计金额
			updatetotalamt(amt) {
				this.totalamount = parseFloat(this.totalamount) + parseFloat(amt)
				this.totalamount = this.fmamount(this.totalamount)

			},
			// 更新合计数量
			updatecntitems(cnt) {
				this.cntitems = parseInt(this.cntitems) + parseInt(cnt)
			},
			// 格式化金额
			fmamount(amt) {
				return Math.round(amt * 100) / 100
			},
			// 不勾选
			_unselected(id) {
				return -Math.abs(id)
			},
			// 勾选
			_selected(id) {
				return Math.abs(id)
			},
			// 是否勾选
			isselected(id) {
				return id > 0
			},
			// 是否全部已勾选
			_isselectedall() {
				for (let i = 0; i < this.cart.length; i++) {
					// 存在一个未勾选 则未全选
					if (this.cart[i].id < 0) {
						return false
					}
				}
				return true
			},
			// 是否全部删除
			_isdeledall() {
				for (let i = 0; i < this.cart.length; i++) {
					// 存在 未删除
					if (this.cart[i].id > -99) {
						return false
					}
				}
				return true
			},
			// 生成订单
			createorder() {
				// 合计金额大于0 创建订单
				if (this.totalamount == 0) {
					uni.showModal({
						content: '请选择下单的商品！'
					})
				} else {
					let orderGoods = [];
					// 1、处理购物车内已选择的订单生成商品
					for (let i = 0; i < this.cart.length; i++) {
						// item id 大于0 的是勾选的
						if (this.cart[i].id > 0) {
							//这里的item 是下单的
							orderGoods.push(this.cart[i])
						}
					}
					// 跳转到下单页面,需要购买的数据保存在缓存
					uni.setStorageSync('orderGoods', orderGoods)
					this.$utils.jump('../shangpinOrder/confirm');
				}
			}
		}
	}
</script>

<style lang="scss">
	page {
		background: rgb(245, 245, 245);
	}

	// 单行文本样式
	.sigle-line-text {
		width: 100%;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		text-align: left;
	}

	.glance-shop-cart {
		width: 100%;
		// font-family: Arial, Helvetica, sans-serif;
	}

	// 滑动item 样式
	.glance-shop-cart-scrollx-items {
		width: 100%;
		height: 90px;
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: flex-start;
		justify-content: space-between;
		// padding: 10px 0 10px 0;
		margin-top: 10px;
	}

	// 滑动区域内item 样式
	.glance-shop-cart-scrollx-items-item {
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: flex-start;
		justify-content: flex-start;
		width: 100%;
		height: 100%;
	}

	// item 勾选样式
	.glance-shop-cart-scrollx-items-item-sel {
		position: relative;
		top: 30%;
		left: 60%;
		;
	}

	// item 描述信息样式
	.glance-shop-cart-items-item-des {
		width: 60%;
		height: 100%;
		display: -webkit-flex;
		flex-flow: column nowrap;
		align-items: flex-start;
		justify-content: flex-start;
	}

	// 价格和数量样式
	.glance-shop-cart-items-item-pq {
		width: 100%;
		height: 33.33%;
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: flex-start;
		justify-content: space-between;
	}

	// 数量操作
	.glance-shop-cart-items-item-opt {
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: center;
		justify-content: space-between;
		width: 50%;
		height: 100%;
		font-size: 20px;
		margin-right: 10px;
		text-align: center;
		color: #6C6C6C;
		line-height: 100%;

		// 减数量
		&-quantity-minus {
			border-style: solid;
			border-width: 1px 0px 1px 1px;
			border-color: #E0E0E0;
			width: 33.33%;
			height: 80%;
			position: relative;
			top: 25%;
			transform: translateY(-50%)
		}

		// 数量
		&-quantity {
			border-style: solid;
			border-width: 1px 1px 1px 1px;
			border-color: #E0E0E0;
			width: 33.33%;
			height: 80%;
			font-size: 13px;
			position: relative;
			top: 25%;
			transform: translateY(-50%);
		}

		// 加数量
		&-quantity-plus {
			border-style: solid;
			border-width: 1px 1px 1px 0;
			border-color: #E0E0E0;
			width: 33.33%;
			height: 80%;
			position: relative;
			top: 25%;
			transform: translateY(-50%);
		}
	}

	// 底部汇总和订单样式
	.glance-shop-cart-order {
		width: 100%;
		height: 60px;
		background-color: #ffffff;
		display: flex;
		display: -webkit-flex;
		flex-flow: row nowrap;
		align-items: center;
		justify-content: space-between;
		position: fixed;
		bottom: 0;
	}

	// 合计金额样式
	.glance-shop-cart-total-cnt {
		width: 30%;
		text-align: left;
		line-height: 40px;
		font-size: 13px;
		margin-left: 15px;
	}

	// 合计金额样式
	.glance-shop-cart-total-amt {
		width: 35%;
		text-align: right;
		margin-right: 15px;
		line-height: 40px;
		font-size: 16px;
		margin-left: 10px;
		color: #f40;
	}

	// 生成订单样式
	.glance-shop-cart-create-order {
		width: 30%;
		background-color: #DC143C;
		text-align: center;
		line-height: 40px;
		font-size: 0.8rem;
		color: #ffffff;
		margin-right: 10px;
	}

	// item 选择样式
	// 不勾选
	.glance-shop-cart-itemunselected-img {
		width: 16px;
		height: 16px;
		border: solid 1px lightgray;
		border-radius: 50%;
	}

	// 勾选
	.glance-shop-cart-itemselected-img {
		width: 20px;
		height: 20px;
		background-size: 20px 20px;
		background-repeat: no-repeat;
	}

	.glance-shop-cart-del {
		display: inline-block;
		width: 18%;
		height: 90px;
		background-color: red;
	}

	// 删除商品背景样式
	.glance-shop-cart-del-img {
		width: 30px;
		height: 30px;
		background-repeat: no-repeat;
		position: relative;
		top: 25%;
		transform: translateY(-50%);
		left: 50%;
		transform: translateX(-50%);
	}

	// 空购物车背景样式
	.glance-shop-cart-nullcart {
		width: 120px;
		height: 120px;
		position: relative;
		transform: translateY(-50%);
		left: 50%;
		transform: translateX(-50%);
		background-repeat: no-repeat;
	}
</style>
