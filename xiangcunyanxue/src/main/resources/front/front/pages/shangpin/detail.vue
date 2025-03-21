<template>
<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback"
			  :bottom="100">
	<view class="container">
		<view>
			<swiper class="swiper" :indicator-dots='".swiper-pagination"==null?false:true'
					:autoplay='autoplaySwiper' :circular='true' indicator-active-color='#000000'
					indicator-color='rgba(0, 0, 0, .3)' :duration='1000' :interval='intervalSwiper'
					:vertical='"horizontal"=="horizontal"?false:true'>
				<swiper-item v-for="(swiper,index) in swiperList" :key="index">
					<image mode="aspectFit" :src="baseUrl+swiper"></image>
				</swiper-item>
			</swiper>
		</view>

		<!-- 二维码模态框 -->
		<uni-popup ref="erweimaModal" type="center">
			<view style="width: 500rpx;background-color: #FFFFFF;height: auto;border-radius: 20rpx;padding-top: 20rpx;">
				<view style="text-align: center;">
					二维码
				</view>
				<view style="width: 90%;margin: 0 auto;padding-top: 10rpx;">
					<view style="width: 400rpx ;height: 400rpx;margin: 0 auto;">
						<u-qrcode ref="qr" canvas-id="qr" :value="erweimaText" size="200"></u-qrcode>
					</view>
				</view>
				<view style="display: flex;width: 100%;margin: 20rpx auto;border-top: 1rpx solid #dddddd;">
					<view style="width: 50%;text-align: center;height: 75rpx;line-height: 75rpx;" @tap="closeErweima">
						关闭
					</view>
					<view style="width: 50%;text-align: center;height: 75rpx;line-height: 75rpx;background-color: blue;color: #FFFFFF;" @tap="saveErweima">
						保存二维码
					</view>
				</view>
			</view>
		</uni-popup>

		<view class="">
			<view class="price ">
				<!-- 金额-->
				<view class="pricejiner"
					  :style="detail.shangpinNewMoney>1000? 'width: 450rpx;':detail.shangpinNewMoney>10000? 'width: 550rpx;':'width: 400rpx;'">
					<text style='font-size:20rpx'>¥</text>
					<text style="font-weight:bold;font-size:50rpx">{{detail.shangpinNewMoney}}</text>
						<text style="font-size: 20rpx;color: #fff;margin-left: 10rpx;">
							<text v-if="detail.shangpinOldMoney!=null">原价￥
								<text style="text-decoration:line-through">{{detail.shangpinOldMoney}}</text>
							</text>
						</text>
				</view>
			</view>

					<!-- 商品内容 -->
			<view class="yinyuewai">
				<view class="yinyuenei"><!-- @tap="onErweima()" -->
						<!-- 商品名称 -->
						<view class="shangpinfei">
							{{detail.shangpinName}}<!-- <uni-icons type="scan" size="30"></uni-icons> -->
						</view>
				<view class="xiangqing">
					<view class="shangpinxiangqing">
						<view class="box">
							商品编号：
						</view>
						<view class="app">
							{{detail.shangpinUuidNumber}}
						</view>
					</view>
				</view>
					<view class="xiangqing">
						<view class="shangpinxiangqing">
							<view class="box">
								商品类型：
							</view>
							<view class="app">
								{{detail.shangpinValue}}
							</view>
						</view>
					</view>
					<view class="xiangqing">
						<view class="shangpinxiangqing">
							<view class="box">
								商品库存：
							</view>
							<view class="app">
								{{detail.shangpinKucunNumber}}
							</view>
						</view>
					</view>
					<view class="xiangqing">
						<view class="shangpinxiangqing">
							<view class="box">
								商品热度：
							</view>
							<view class="app">
								{{detail.shangpinClicknum}}
							</view>
						</view>
					</view>

				<view class="xiangqing"v-if="(thumbsupFlag&&!crazilyFlag) || (!thumbsupFlag&&!crazilyFlag)">
                    <view class="shangpinxiangqing">
                        <view class="box" v-if="!thumbsupFlag">
                            赞：
                        </view>
                        <view class="box" v-if="thumbsupFlag">
                            已赞：
                        </view>
                        <view class="app">
                            <image @click="zan"
                                   style="width: 40rpx;height: 40rpx;vertical-align: middle;margin-right: 10rpx;"
                                   class="icon" src="../../static/gen/zan.png" mode=""></image>
                            {{detail.zanNumber}}
                        </view>
                    </view>
                </view>
				<view class="xiangqing" v-if="(!thumbsupFlag&&crazilyFlag) || (!thumbsupFlag&&!crazilyFlag)">
					<view class="shangpinxiangqing">
						<view class="box" v-if="!crazilyFlag">
							踩：
						</view>
						<view class="box" v-if="crazilyFlag">
							已踩：
						</view>
						<view class="app">
							<image @click="cai"
								   style="width: 40rpx;height: 40rpx;vertical-align: middle;margin-right: 10rpx;"
								   class="icon" src="../../static/gen/cai.png" mode=""></image>
							{{detail.caiNumber}}
						</view>
					</view>
				</view>
			</view>
		</view>
				<!-- 简介 -->
				<view class="yinyuewai">
					<view class="yinyuenei">
						<!-- 简介 -->
						<view class="jianjiexiangqing">
							简介/详情
						</view>
						<!-- 简介内容 -->
						<view class="" style="margin-top: -20rpx;
					margin-bottom: 25rpx;
					text-indent:24px">
							<rich-text :nodes="detail.shangpinContent"></rich-text>
						</view>
					</view>
				</view>
            <!-- 评价 -->
				<view class="yinyuewai">
					<view class="yinyuenei">
						<!-- 评价 -->
						<view v-if="commentList.length>0" class="jianjiexiangqing">
							评价
						</view>
						<!-- 内容 -->
						<view v-if="commentList.length>0" v-for="(item,index) in commentList" class=""
							  style="margin-top: -20rpx;margin-bottom: 50rpx;">
							<view v-bind:key="index" class="" style="align-items: center;">
								<view class=""
									  style="width: 100%;display: flex;flex-direction: row;align-items: center;margin-left: 30rpx;margin-bottom: 10rpx;">
									<!-- 头像 -->
									<image v-if="item.yonghuPhoto"
										   style="width: 100rpx;height: 100rpx;border-radius: 50%;margin-right: 20rpx;"
										   :src="baseUrl+item.yonghuPhoto" mode="scaleToFill"></image>
									<!-- 用户 -->
									<view class="" style="font-size: 14px;display: flex;flex-direction: column;color: #8799A3;"
										  v-if="item.yonghuName != null && item.yonghuName != ''">
										{{item.yonghuName}}
										<!-- 时间 -->
										<view class="" style="font-size: 20rpx;color: #c1c1c1;">
											{{item.createTime}}
										</view>
									</view>
								</view>
								<view style="width: 80%;margin-left: 100rpx;">
									<!-- 评价 -->
									{{item.shangpinCommentbackText}}
								</view>
							</view>
							<!-- 回复 -->
							<view class="" style="margin-left: 100rpx;width: 82%;height: auto;margin-top: 15rpx;">
								<view class="" style="font-size: 12px;" v-if="item.replyText">
									<text style="color: rgba(68,119,197,1.00);">回复</text> :{{item.replyText}}
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
				<!-- 商品导航栏 -->
				<view class="bottom-content cu-bar bg-white tabbar border shop">
					<view style="width: 100%;margin: 0 auto;display: flex;align-items: center;justify-content: space-around;">
						<view style="width: 30%;display: flex;align-items: center;flex-wrap: wrap;justify-content: space-evenly;">
								<!-- 收藏 -->
								<uni-icons style="color: var(--publicMainColor);" :type="collectionFlag==0?'star':'star-filled'" size="30" @tap="shoucang"></uni-icons>
								<!-- 购物车 -->
								<uni-icons type="cart" size="30" @tap="goCart"></uni-icons>
						</view>
						<view class="" style="display: flex; width: 70%;color: #FFFFFF;">
							<view @tap="onCartTap()" :style="[{backgroundImage:'linear-gradient(40deg,'+ btnColor[0] +','+ btnColor[1] +')'}]" style="width:47%;height: 80rpx;line-height: 80rpx;text-align: center;border-bottom-left-radius: 40rpx;border-top-left-radius: 40rpx;">
								购物车
							</view>
							<view @tap="onSubmit()" :style="[{backgroundImage:'linear-gradient(130deg,'+ btnColor[1] +','+ btnColor[0] +')'}]" style="width: 47%;height: 80rpx;line-height: 80rpx;text-align: center;border-bottom-right-radius: 40rpx;border-top-right-radius: 40rpx;">
								立即购买
							</view>
						</view>
                        <!--两个按钮
						<view class="" style="display: flex; width: 70%;color: #FFFFFF;">
							<view @tap="onCartTap()" :style="[{backgroundImage:'linear-gradient(40deg,'+ btnColor[0] +','+ btnColor[1] +')'}]" style="width:47%;height: 80rpx;line-height: 80rpx;text-align: center;border-bottom-left-radius: 40rpx;border-top-left-radius: 40rpx;">
								第一个
							</view>
							<view @tap="onSubmit()" :style="[{backgroundImage:'linear-gradient(130deg,'+ btnColor[1] +','+ btnColor[0] +')'}]" style="width: 47%;height: 80rpx;line-height: 80rpx;text-align: center;border-bottom-right-radius: 40rpx;border-top-right-radius: 40rpx;">
								第二个
							</view>
						</view>

						一个按钮
						<view @tap="fanhui" style="display: flex; width: 80%;color: #FFFFFF;">
							<view :style="[{backgroundImage:'linear-gradient(120deg,'+ btnColor[0] +','+ btnColor[1] +')'}]" style="width: 95%;height: 80rpx;line-height: 80rpx;text-align: center;border-radius: 40rpx;">
								返回上一页
							</view>
						</view>
                        -->

						<!-- 没理由
						<view @tap="baoming" style="display: flex; width: 80%;color: #FFFFFF;">
							<view :style="[{backgroundImage:'linear-gradient(120deg,'+ btnColor[0] +','+ btnColor[1] +')'}]" style="width: 95%;height: 80rpx;line-height: 80rpx;text-align: center;border-radius: 40rpx;">
								我要报名
							</view>
						</view>
						-->

				</view>
			</view>


			</mescroll-uni>


	</template>

	<script>
		export default {
			data() {
				return {
					user:{},//当前登录表的所有参数
					sessionTable : uni.getStorageSync("nowTable"),//登录账户所在表名
					role : uni.getStorageSync("role"),//权限
					userId:uni.getStorageSync("userid"),//当前登录人的id
				    /*其他     开始*/
					autoplaySwiper: false ? true : false,
					intervalSwiper: false ? $template2.front.base.swiper.autoplay.delay : 5000,
                    btnColor: ['#67c23a', '#909399', '#e6a23c', '#f56c6c', '#356c6c', '#3498db', '#f093a9',
                        '#a7c23a', '#104eff', '#90f794', '#a21233', '#8e44ad'
                    ],
                    mescroll: null, //mescroll实例对象
                    downOption: {
                        auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
                    },
                    upOption: {
                        noMoreSize: 3, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
                        textNoMore: '~ 没有更多了 ~',
                    },
                    hasNext: true,
                    /*其他     结束*/
					shangpinId: '',
					detail: {},//本页面数据对象
					swiperList: [],//主图
					collectionFlag: 0,//收藏
					/*赞踩     开始*/
					thumbsupFlag: 0,
					crazilyFlag: 0,
                    /*赞踩     结束*/
					/*留言评论评分相关     开始*/
                    totalScore: 0, //总评分
                    scoreData: 0, //评分
                    commentData: null, //留言
                    commentList: [], //留言数据集合
                    /*留言评论评分相关     结束*/
					erweimaText: "",
				}
			},
			computed: {
				baseUrl() {
					return this.$base.url;
				},
			},
			async onLoad(options) {
				this.shangpinId = options.id;
			},
			async onShow(options) {
				// 渲染数据
				this.init();
				//获取收藏信息
				this.getshangpinCollection()
				let table = uni.getStorageSync("nowTable");
				// 获取用户信息
				let res = await this.$api.session(table);
				this.user = res.data;
				this.btnColor = this.btnColor.sort(() => {
					return (0.5 - Math.random());
				});
				this.getThumbsup();
				let cleanType = uni.getStorageSync('pingluenStateState')
				if (cleanType) {
					uni.removeStorageSync('pingluenStateState')
					this.mescroll.num = 1
					this.upCallback(this.mescroll)
				}
			},
			destroyed: function() {
				//window.clearInterval(this.inter);
			},
			methods: {
				//返回
				async fanhui(){
					this.$utils.tab("list")
				},
				//下载
				download(url) {
					let _this = this;
					uni.downloadFile({
						url: url,
						success: (res) => {
							if (res.statusCode === 200) {
								_this.$utils.msg('下载成功');
								// window.open(url);
							}
						}
					});
				},
				// 获取详情
				async init() {
					let res = await this.$api.detail('shangpin', this.shangpinId);
                    this.detail = res.data
					this.detail.shangpinContent = this.detail.shangpinContent.replace("<img","<img  class=\"daxiao\"").replace("src=\"upload/","src=\""+this.$base.url+"upload/");
					// 主图
					this.swiperList = this.detail.shangpinPhoto ? this.detail.shangpinPhoto.split(",") : [];
				},
				/* && !nameList?seq_contains('zuowei_number')*/
				//跳转购物车
				goCart(){
                    this.$utils.jump("../cart/list")
                },
				//添加购物车
				async onCartTap() {
					let cartRes = await this.$api.page('cart', {
						page: 1,
						limit: 9999,
						shangpinId: this.shangpinId
					})
					if(cartRes.data.list.length>0){
						this.$utils.msg('商品已添加到购物车');
						return
					}
					await this.$api.save('cart', {
						shangpinId: this.detail.id,
						buyNumber: 1,
						yonghuId: this.user.id,
					});
					this.$utils.msg('添加到购物车成功')
				},
				//立即购买
				onSubmit(){
					uni.setStorageSync('orderGoods', [{
						shangpinId: this.detail.id,
						shangpinName: this.detail.shangpinName,
						shangpinPhoto: this.swiperList[0],
						buyNumber: 1,
						shangpinNewMoney: this.detail.shangpinNewMoney,
					}])
					this.$utils.jump('../shangpinOrder/confirm');
				},
				// 收藏
				async getshangpinCollection() {
					let params = {
						page: 1,
						limit: 1,
						shangpinId: this.shangpinId,
						shangpinCollectionTypes:'1'
					}
					let res = await this.$api.page(`shangpinCollection`, params);
					if(res.data.list.length > 0){
						this.collectionFlag = res.data.list.length;
					}else{
						this.collectionFlag = 0
					}
				},
				async shoucang() {
					let _this = this;
					let params = {
						page: 1,
						limit: 1,
						shangpinId: _this.detail.id,
						yonghuId: _this.user.id,
						shangpinCollectionTypes:'1'
					}
					let res = await _this.$api.list(`shangpinCollection`, params);
					if (res.data.list.length == 1) {
						let shangpinCollectionId = res.data.list[0].id;
						uni.showModal({
							title: '提示',
							content: '是否取消',
							success: async function(res) {
								if (res.confirm) {
									await _this.$api.del('shangpinCollection', JSON.stringify([shangpinCollectionId]));
									_this.$utils.msg('取消成功');
									_this.getshangpinCollection();
								}
							}
						});
						return;
					}
					uni.showModal({
						title: '提示',
						content: '是否收藏',
						success: async function(res) {
							if (res.confirm) {
								await _this.$api.add('shangpinCollection', {
									yonghuId: _this.user.id,
									shangpinId: _this.detail.id,
									shangpinCollectionTypes:1
								});
								_this.$utils.msg('收藏成功');
								_this.getshangpinCollection();
							}
						}
					});
				},
				// mescroll组件初始化的回调,可获取到mescroll对象
				mescrollInit(mescroll) {
					this.mescroll = mescroll;
				},

				/*下拉刷新的回调 */
				downCallback(mescroll) {
					this.hasNext = true
					mescroll.resetUpScroll()
				},

				/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
				async upCallback(mescroll) {
					let res = await this.$api.list('shangpinCommentback', {
						page: mescroll.num,
						limit: mescroll.size,
						shangpinId: this.shangpinId
					});
					// 如果是第一页数据置空
					if (mescroll.num == 1) this.commentList = [];
					this.commentList = this.commentList.concat(res.data.list);
					if (res.data.list.length == 0) this.hasNext = false;
					mescroll.endSuccess(mescroll.size, this.hasNext);
				},
				// 获取赞踩
				async getThumbsup() {
					let _this = this;
					let params = {
						page: 1,
						limit: 1,
						shangpinId: _this.detail.id,
						yonghuId: _this.user.id,
					}
					let res = await _this.$api.list(`shangpinCollection`, params);
					if (res.data.list.length > 0) {
						if (res.data.list[0].shangpinCollectionTypes == '2') {
							_this.thumbsupFlag = true;
						}
						if (res.data.list[0].shangpinCollectionTypes == '3') {
							_this.crazilyFlag = true;
						}
					}

				},
				// 点赞
				async zan() {
					let _this = this;
					let params = {
						page: 1,
						limit: 1,
						shangpinId: _this.detail.id,
						yonghuId: _this.user.id,
						shangpinCollectionTypes: '2',
					}
					let res = await _this.$api.list(`shangpinCollection`, params);
					if (res.data.list.length > 0) {
						let shangpinCollectionId = res.data.list[0].id;
						uni.showModal({
							title: '提示',
							content: '是否取消点赞',
							success: async function(res) {
								if (res.confirm) {
									await _this.$api.del('shangpinCollection', JSON.stringify([shangpinCollectionId]));
									_this.detail.zanNumber = parseInt(_this.detail.zanNumber) - 1;
									await _this.$api.update('shangpin', _this.detail);
									_this.$utils.msg('取消成功');
									_this.thumbsupFlag = false;
								}
							}
						});
						return;
					}
					uni.showModal({
						title: '提示',
						content: '是否点赞',
						success: async function(res) {
							if (res.confirm) {
								await _this.$api.add('shangpinCollection', {
									yonghuId: _this.user.id,
									shangpinId: _this.detail.id,
									shangpinCollectionTypes: '2'
								});
								_this.detail.zanNumber = parseInt(_this.detail.zanNumber) + 1;
								await _this.$api.update('shangpin', _this.detail);
								_this.$utils.msg('点赞成功');
								_this.thumbsupFlag = true;
							}
						}
					});
				},
				// 踩
				async cai() {
					let _this = this;
					let params = {
						page: 1,
						limit: 1,
						shangpinId: _this.detail.id,
						yonghuId: _this.user.id,
						shangpinCollectionTypes: '3',
					}
					let res = await _this.$api.list(`shangpinCollection`, params);
					if (res.data.list.length > 0) {
						let shangpinCollectionId = res.data.list[0].id;
						uni.showModal({
							title: '提示',
							content: '是否取消点踩',
							success: async function(res) {
								if (res.confirm) {
									await _this.$api.del('shangpinCollection', JSON.stringify([shangpinCollectionId]));
									_this.detail.caiNumber = parseInt(_this.detail.caiNumber) - 1;
									await _this.$api.update('shangpin', _this.detail);
									_this.$utils.msg('取消成功');
									_this.crazilyFlag = false;
								}
							}
						});
						return;
					}
					uni.showModal({
						title: '提示',
						content: '是否点踩',
						success: async function(res) {
							if (res.confirm) {
								await _this.$api.add('shangpinCollection', {
									yonghuId: _this.user.id,
									shangpinId: _this.detail.id,
									shangpinCollectionTypes: '3'
								});
								_this.detail.caiNumber = parseInt(_this.detail.caiNumber) + 1;
								await _this.$api.update('shangpin', _this.detail);
								_this.$utils.msg('点踩成功');
								_this.crazilyFlag = true;
							}
						}
					});
				},
				async baoming() {
					let _this = this;
					uni.showModal({
						title: '提示',
						content: '确定要报名么',
						success: async function(res) {
							if (res.confirm) {
								await _this.$api.add('shangpinYuyue', {
									yonghuId: _this.user.id,
									shangpinId: _this.detail.id,
								});
								_this.$utils.msg('报名成功');
								_this.$utils.jump('/pages/shangpinYuyue/list');
							}
						}
					});
				},
				async onErweima() {//打开模态框
					// this.erweimaText = "画师名称:["+this.detail.huashiName+"]所属画院:["+this.detail.huayuanName+"]画师称号:["+this.detail.huashiChenghao+"]画师性别:["+this.detail.sexValue+"]"
					this.erweimaText = "二维码内容";
					this.$refs.erweimaModal.open()
				},
				async closeErweima() {//关闭模态框
					this.$refs.erweimaModal.close()
				},
				async saveErweima() {//保存二维码
					uni.showLoading({title: '保存中',mask: true});
					const ref = this.$refs['qr'];
					ref.save({
						success: res => {
							uni.hideLoading();
							uni.showToast({icon: 'success',title: '保存成功'});
						},
						fail: err => {
							uni.showToast({icon: 'none',title: JSON.stringify(err)});
						}
					});
				},
			}
		}
	</script>

	<style lang="scss">
        /* 新闻样式==================================开始 */
        .daxiao{
            width: 600rpx;
        }
        .title {
            text-align: center;
            font-size: 40upx;
            font-weight: bold;
            margin: 20upx;
        }

        .content {
            margin: 20upx 40upx;
            font-size: 30upx;
            line-height: 50upx;
            letter-spacing: 5upx;
        }
        /* 新闻样式==================================结束 */
		// 商品名称
		.box {
			width: 40%;
			line-height: 55rpx;
			display: inline-block;
			text-align: center;
			border-bottom-left-radius: 15rpx;
			border-top-left-radius: 15rpx;
			position: relative;
			background-color: var(--publicMainColor);
		}

		.box::after {
			content: '';
			position: absolute;
			bottom: 0;
			right: 0;
			width: 0;
			height: 0;
			border-style: solid;
			border-width: 0 20px 27px 0px;
			border-color: transparent transparent var(--publicMainColor) transparent;
		}

		//商品类型
		  .app {
			  width: 60%;
			  line-height: 55rpx;
			  display: inline-block;
			  text-align: center;
			  border-bottom-right-radius: 15rpx;
			  border-top-right-radius: 15rpx;
			  position: relative;
			  background-color: var(--publicSubColor);

		  }

		.app::after {
			content: '';
			position: absolute;
			bottom: 0;
			right: 360rpx;
			width: 0;
			height: 0;
			border-style: solid;
			border-width: 0px 20px 27px 0px;
			border-color: transparent var(--publicSubColor) transparent transparent;
		}

		// 轮播图
		   .swiper {
			   width: 100%;
			   height: 800upx;
			   margin: 0 auto;

		image {
			width: 100%;
			height: 100%;
			text-align:center;
		}
		}

		// 商品导航栏
		   .bottom-content {
			   position: fixed;
			   bottom: 0;
			   left: 0;

			   width: 100%;
		   }

		//订座样式
		  .seat-list {
			  display: flex;
			  align-items: center;
			  flex-wrap: wrap;
			  margin: 20upx;
			  border-radius: 20upx;
			  padding: 20upx;
			  font-size: 30upx;

		.seat-item {
			width: 33.33%;
			display: flex;
			align-items: center;
			flex-direction: column;
			margin-bottom: 20upx;

		.seat-icon {
			width: 50upx;
			height: 50upx;
			margin-bottom: 10upx;
		}
		}
		}

		// 金额
		   .price {
			   width: 700rpx;
			   margin: 0 auto;
			   border-bottom: 1rpx solid var(--publicMainColor);
		   }

		.pricejiner {
			width: 400rpx;
			padding: 10rpx;
			height: 0rpx;
			color: #fff;
			line-height: 120rpx;
			border-width: 0rpx 40rpx 80rpx 0rpx;
			border-style: none solid solid;
			border-color: transparent transparent var(--publicMainColor);
		}

		// 音乐
		   .yinyuewai {
			   width: 700rpx;
			   height: auto;
			   margin: 0 auto;
			   margin-top: 15rpx;
			   margin-top: 50rpx;
		   }

		.yinyuenei {
			width: 650rpx;
			height: auto;
			margin: 0 auto;
			border: 5rpx dashed #8799A3;
			box-shadow: 0 0 10px rgba(0, 0, 0, 1) inset;
			border-radius: 20rpx;
		}

		// 商品
		   .shangpinfei {
			   width: 500rpx;
			   text-align: center;
			   padding: 10rpx;
			   height: 100rpx;
			   color: #000;
			   font-weight: 600;
			   margin: 0 auto;
			   background-color: #fff;
			   position: relative;
			   left: 0rpx;
			   top: -40rpx;
			   border: 5rpx dashed #8799A3;
			   border-radius: 30rpx;
		   }

		.shangpinxiangqing {
			width: 600rpx;
			height: 55rpx;
			margin: 0 auto;
			color: #fff;
			margin-top: -20rpx;
			margin-bottom: 40rpx;
		}

		// 订做
		   .yy-list {
			   width: 700rpx;
			   height: auto;
			   margin: 15rpx auto;
		   }

		.ding-list {
			width: 650rpx;
			height: auto;
			margin: 0 auto;
			border: 5rpx dashed #8799A3;
			box-shadow: 0 0 10px rgba(0, 0, 0, 1) inset;
			border-radius: 20rpx;
		}

		.yuxuan-list {
			width: 500rpx;
			text-align: center;
			padding: 10rpx;
			height: 80rpx;
			color: #000;
			font-weight: 600;
			margin: 0 auto;
			background-color: #fff;
			position: relative;
			left: 0rpx;
			top: -50rpx;
			border: 5rpx dashed #8799A3;
			border-radius: 20rpx;
		}

		//视频
		  .shipin-list {
			  width: 700rpx;
			  height: auto;
			  margin: 15rpx auto;
		  }

		// 简介详情
		   .jianjiexiangqing {
			   width: 200rpx;
			   text-align: center;
			   padding: 10rpx;
			   height: 80rpx;
			   color: #000;
			   font-weight: 600;
			   margin: 0 auto;
			   background-color: #fff;
			   position: relative;
			   left: 0rpx;
			   top: -40rpx;
			   border: 5rpx dashed #8799A3;
			   border-radius: 20rpx;
		   }
	</style>
