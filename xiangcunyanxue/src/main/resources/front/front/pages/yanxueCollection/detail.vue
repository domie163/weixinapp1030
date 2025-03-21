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

					<!-- 商品内容 -->
			<view class="yinyuewai">
				<view class="yinyuenei"><!-- @tap="onErweima()" -->
					<view class="xiangqing">
						<view class="shangpinxiangqing">
							<view class="box">
								类型：
							</view>
							<view class="app">
								{{detail.yanxueCollectionValue}}
							</view>
						</view>
					</view>

			</view>
		</view>
            <!-- 留言 -->
			</view>
		</view>
				<!-- 商品导航栏 -->
				<view class="bottom-content cu-bar bg-white tabbar border shop">
					<view style="width: 100%;margin: 0 auto;display: flex;align-items: center;justify-content: space-around;">
						<view style="width: 30%;display: flex;align-items: center;flex-wrap: wrap;justify-content: space-evenly;">
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
					yanxueCollectionId: '',
					detail: {},//本页面数据对象
					swiperList: [],//主图
					collectionFlag: 0,//收藏
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
				this.yanxueCollectionId = options.id;
			},
			async onShow(options) {
				// 渲染数据
				this.init();
				let table = uni.getStorageSync("nowTable");
				// 获取用户信息
				let res = await this.$api.session(table);
				this.user = res.data;
				this.btnColor = this.btnColor.sort(() => {
					return (0.5 - Math.random());
				});
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
					let res = await this.$api.detail('yanxueCollection', this.yanxueCollectionId);
                    this.detail = res.data
					// 主图
					this.swiperList = this.detail.yanxueCollectionPhoto ? this.detail.yanxueCollectionPhoto.split(",") : [];
				},
				/* && !nameList?seq_contains('zuowei_number')*/
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
					this.mescroll.endByPage(this.mescroll.num, 10);
				},
				async baoming() {
					let _this = this;
					uni.showModal({
						title: '提示',
						content: '确定要报名么',
						success: async function(res) {
							if (res.confirm) {
								await _this.$api.add('yanxueCollectionYuyue', {
									yonghuId: _this.user.id,
									yanxueCollectionId: _this.detail.id,
								});
								_this.$utils.msg('报名成功');
								_this.$utils.jump('/pages/yanxueCollectionYuyue/list');
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
