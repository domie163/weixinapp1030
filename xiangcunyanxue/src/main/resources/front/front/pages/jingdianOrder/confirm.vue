<template>
    <view class="content">
        <form>

			<view class="cu-form-group">
                <view class="title">购买清单</view>
            </view>
			<view class="cu-form-group">
                <view class="title">购买清单</view>
            </view>
            <view v-for="(item,index) in orderGoods " v-bind:key="index" class="cu-form-group">
                <image class="avator" :src="baseUrl+item.jingdianPhoto" mode=""></image>
                <view class="title" style="width: 75%;">
                    <view style="margin-top: -50rpx;">{{item.jingdianName}}</view>
                    <view >
                        x{{item.buyNumber}}
                        <text style="margin-left: 30upx;color: red;">￥{{item.jingdianNewMoney}}</text>
                    </view>
                </view>
            </view>
            <view @tap="onAddressTap" class="cu-form-group">
                <view class="title">总价</view>
                <view>
                    <text v-if="jingdianOrderPaymentTypes == 1">原价：￥{{(maxNewMouey).toFixed(2)}}</text>
                    <view v-if="jingdianOrderPaymentTypes == 1"></view>
                    <text v-if="jingdianOrderPaymentTypes == 1">折扣价：￥{{(maxNewMouey * zhekou).toFixed(2)}}</text>
                    <text v-if="jingdianOrderPaymentTypes == 2">{{(maxNewMouey).toFixed(2)}}积分</text>
                </view>
            </view>
        </form>
        <view class="padding" style="text-align: center;">
            <button @tap="onSubmitTap()" class="bg-red lg">确认支付</button>
        </view>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                user: {},//登录用户
                orderGoods: [],//展示数据
                maxNewMouey: 0,//总价格
                jingdianOrderPaymentTypes:1,//是什么支付类型
                zhi:[
                    {
                        id:1,
                        val:"余额"
                    },
                    {
                        id:2,
                        val:"积分"
                    },
                ],
                zhekou:1,//折扣
            }
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
        async onLoad(options) {
            // 获取订单信息，座位信息
            this.orderGoods = uni.getStorageSync('orderGoods');
            if(this.orderGoods.length>0){
                for (let i = 0; i < this.orderGoods.length; i++) {
                    this.maxNewMouey = this.maxNewMouey + parseFloat(this.orderGoods[i].jingdianNewMoney) * this.orderGoods[i].buyNumber
                }
            }
            uni.removeStorageSync("orderGoods")
        },
        async onShow() {
            let _this = this
            let table = uni.getStorageSync("nowTable");
            let userRes = await _this.$api.session(table)
            _this.user = userRes.data

            let huiyuandengjiTypesRes = await _this.$api.page("dictionary",{
                dicCode: "huiyuandengji_types",
                dicName: "会员等级类型",
                codeIndexStart: _this.user.huiyuandengjiTypes,
                codeIndexEnd: _this.user.huiyuandengjiTypes,
            })
            if(huiyuandengjiTypesRes.data.list.length >0){
                _this.zhekou = Number(huiyuandengjiTypesRes.data.list[0].beizhu);
            }


        },
        methods: {
            async onSubmitTap() {
                let _this = this;
                let table = uni.getStorageSync("nowTable");
                uni.showModal({
                    title: '提示',
                    content: '是否确认支付',
                    success: async function(res) {
                        if (res.confirm) {
							 // let data = {
								//  jingdians: JSON.stringify(_this.orderGoods),
								//  yonghuId: _this.user.id,
								//  jingdianOrderPaymentTypes:  _this.jingdianOrderPaymentTypes,
							 // }
							 
							let order = {
								jingdianOrderUuidNumber:_this.$utils.genTradeNo(),
								yonghuId: _this.user.id,
								jingdianId:  _this.orderGoods[0].jingdianId,
							}
							 
							 await _this.$api.add('jingdianOrder', order);
							 _this.$utils.msg('支付成功');
							// await _this.$api.requestConditionDataGet('jingdianOrder','order',null,data);
                            _this.$utils.jump('/pages/jingdianOrder/list');
                        }
                    }
                });
            },
        }
    }
</script>

<style lang="scss">
    .avator {
        width: 150upx;
        height: 150upx;
        margin: 20upx 0;
    }
</style>
