<template>
    <view class="content">
        <form class="app-update-pv">
                <!-- <view
                         :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                         class="cu-form-group">
                     <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                           class="title">景点名称</view>
                     <input   disabled
                              :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                              :disabled="ro.jingdianName" v-model="jingdian.jingdianName" placeholder="景点名称"></input>
                 </view>
                 <view
                         :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                         class="cu-form-group">
                     <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                           class="title">景点编号</view>
                     <input   disabled
                              :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                              :disabled="ro.jingdianUuidNumber" v-model="jingdian.jingdianUuidNumber" placeholder="景点编号"></input>
                 </view>
                <view
                        :style='{"boxShadow":"0 0 0px rgba(0,0,0,.3)","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(218, 220, 219, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"108rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">景点照片</view>
                    <view class="right-input" style="padding:0;textAlign:left">
                        <image
                                :style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
                                class="avator" v-if="jingdian.jingdianPhoto" :src="baseUrl+jingdian.jingdianPhoto" mode="aspectFill">
                        </image>
                        <image
                                :style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
                                class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
                    </view>
                </view>
							                 <view
                         :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                         class="cu-form-group">
                     <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                           class="title">景点位置</view>
                     <input   disabled
                              :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                              :disabled="ro.jingdianAddress" v-model="jingdian.jingdianAddress" placeholder="景点位置"></input>
                 </view>
                <view
                        :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">景点星级</view>
                    <input disabled
                           :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                           :disabled="ro.jingdianXingjiValue"
                           v-model="jingdian.jingdianXingjiValue"
                           placeholder="景点星级"></input>
                </view>
                <view
                        :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">景点类型</view>
                    <input disabled
                           :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                           :disabled="ro.jingdianValue"
                           v-model="jingdian.jingdianValue"
                           placeholder="景点类型"></input>
                </view>
                 <view
                         :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                         class="cu-form-group">
                     <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                           class="title">剩余门票数</view>
                     <input   disabled
                              :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                              :disabled="ro.jingdianKucunNumber" v-model="jingdian.jingdianKucunNumber" placeholder="剩余门票数"></input>
                 </view> -->
                <!--<view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">景点</view>
                    <picker @change="jingdianChange" :value="jingdianIndex" :range="jingdianOptions" range-key="jingdianName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 16rpx var(--publicSubColor) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"20rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.jingdianId?ruleForm.jingdianName:"请选择景点"}}</view>
                    </picker>
                </view>-->
                <!--<view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">用户</view>
                    <picker @change="yonghuChange" :value="yonghuIndex" :range="yonghuOptions" range-key="yonghuName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 16rpx var(--publicSubColor) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"20rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.yonghuId?ruleForm.yonghuName:"请选择用户"}}</view>
                    </picker>
                </view>-->
            <view
                    :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                    class="cu-form-group">
                <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                      class="title">订单编号</view>
                <input   disabled
                         :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                         :disabled="ro.jingdianOrderUuidNumber" type="text" v-model="ruleForm.jingdianOrderUuidNumber" placeholder="订单编号"></input>
            </view>
                <!-- 级联表的表id -->
                <input type="hidden" :value="jingdian.id" id="jingdianId" name="jingdianId"/>
                <!-- <view
                        :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">购买数量</view>
                    <input   disabled
                             :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                             :disabled="ro.buyNumber" type="number" v-model="ruleForm.buyNumber" placeholder="购买数量"></input>
                </view> -->
            <view
                    :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                    class="cu-form-group">
                <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                      class="title">预约日期</view>
                <input
                        :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                        v-model="ruleForm.jingdianOrderTime" placeholder="预约日期" :readonly="ro.jingdianOrderTime"
                        @tap="toggleTab('jingdianOrderTime')"></input>
            </view>
                <!-- <view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">订单类型</view>
                    <picker @change="jingdianOrderTypesChange" :value="jingdianOrderTypesIndex" :range="jingdianOrderTypesOptions" range-key="indexName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.jingdianOrderTypes?ruleForm.jingdianOrderValue:"请选择订单类型"}}</view>
                    </picker>
                </view> -->

            <view class="btn">
                <button
                        :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"var(--publicMainColor)","borderColor":"#409EFF","borderRadius":"8rpx","color":"rgba(255, 255, 255, 1)","borderWidth":"0","width":"70%","fontSize":"32rpx","borderStyle":"solid","height":"80rpx"}'
                        @tap="onSubmitTap" class="bg-red">提交</button>
            </view>
        </form>

					<w-picker mode="date" step="1" :current="false" :hasSecond="false" @confirm="jingdianOrderTimeConfirm"
                              ref="jingdianOrderTime" themeColor="#333333"></w-picker>
					<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="insertTimeConfirm"
                              ref="insertTime" themeColor="#333333"></w-picker>
					<w-picker mode="dateTime" step="1" :current="false" :hasSecond="false" @confirm="createTimeConfirm"
                              ref="createTime" themeColor="#333333"></w-picker>


    </view>
</template>

<script>
    import wPicker from "@/components/w-picker/w-picker.vue";

    export default {
        data() {
            return {
                cross: '',
                ro:{
					jingdianOrderUuidNumber: true,
					jingdianId: false,
					yonghuId: false,
					buyNumber: true,
					jingdianOrderTime: false,
					jingdianOrderTruePrice: false,
					jingdianOrderTypes: false,
					jingdianOrderPaymentTypes: false,
					insertTime: false,
					createTime: false,
        },
            ruleForm: {
					jingdianOrderUuidNumber: this.getUUID(),//数字
					jingdianId: '',
					yonghuId: '',
					buyNumber: '',
					jingdianOrderTime: '',
					jingdianOrderTruePrice: '',
					jingdianOrderTypes: '',//数字
					jingdianOrderValue: '',//数字对应的值
					jingdianOrderPaymentTypes: '',//数字
					jingdianOrderPaymentValue: '',//数字对应的值
					insertTime: '',
					createTime: '',
            },
            // 登陆用户信息
            user: {},
            jingdianOrderTypesOptions: [],
            jingdianOrderTypesIndex : 0,
            jingdianOrderPaymentTypesOptions: [],
            jingdianOrderPaymentTypesIndex : 0,
            jingdianOptions: [],//景点
            jingdianIndex : 0,//景点下标
            yonghuOptions: [],//用户
            yonghuIndex : 0,//用户下标

        }
        },
        components: {
            wPicker
        },
        computed: {
            baseUrl() {
                return this.$base.url;
            },
        },
        async onLoad(options) {
            let jingdianParams = {
                page: 1,
                limit: 100,
            }
            let jingdianData = await this.$api.page(`jingdian`, jingdianParams);
            this.jingdianOptions = jingdianData.data.list;
            let yonghuParams = {
                page: 1,
                limit: 100,
            }
            let yonghuData = await this.$api.page(`yonghu`, yonghuParams);
            this.yonghuOptions = yonghuData.data.list;
		/*下拉框*/
			let jingdianOrderTypesParams = {
                page: 1,
                limit: 100,
                dicCode: 'jingdian_order_types',
            }
			let jingdianOrderTypes = await this.$api.page(`dictionary`, jingdianOrderTypesParams);
			this.jingdianOrderTypesOptions = jingdianOrderTypes.data.list
		/*下拉框*/
			let jingdianOrderPaymentTypesParams = {
                page: 1,
                limit: 100,
                dicCode: 'jingdian_order_payment_types',
            }
			let jingdianOrderPaymentTypes = await this.$api.page(`dictionary`, jingdianOrderPaymentTypesParams);
			this.jingdianOrderPaymentTypesOptions = jingdianOrderPaymentTypes.data.list
                this.ruleForm.jingdianId = options.jingdianId;
            // 如果是更新操作
            if (options.id) {
                this.ruleForm.id = options.id;
                // 获取信息
                let res = await this.$api.info(`jingdianOrder`, this.ruleForm.id);
                this.ruleForm = res.data;
            }
            if(options.jingdianOrderId){
                this.ruleForm.jingdianOrderId = options.jingdianOrderId;
            }
            // 跨表
            // this.styleChange()
        },
        methods: {
            jingdianChange(e) {
                this.jingdianIndex = e.target.value
                this.ruleForm.jingdianName = this.jingdianOptions[this.jingdianIndex].jingdianName
                this.ruleForm.jingdianId = this.jingdianOptions[this.jingdianIndex].id
            },
            yonghuChange(e) {
                this.yonghuIndex = e.target.value
                this.ruleForm.yonghuName = this.yonghuOptions[this.yonghuIndex].yonghuName
                this.ruleForm.yonghuId = this.yonghuOptions[this.yonghuIndex].id
            },
            // 下拉变化
            jingdianOrderTypesChange(e) {
                this.jingdianOrderTypesIndex = e.target.value
                this.ruleForm.jingdianOrderValue = this.jingdianOrderTypesOptions[this.jingdianOrderTypesIndex].indexName
                this.ruleForm.jingdianOrderTypes = this.jingdianOrderTypesOptions[this.jingdianOrderTypesIndex].codeIndex
            },
            jingdianOrderPaymentTypesChange(e) {
                this.jingdianOrderPaymentTypesIndex = e.target.value
                this.ruleForm.jingdianOrderPaymentValue = this.jingdianOrderPaymentTypesOptions[this.jingdianOrderPaymentTypesIndex].indexName
                this.ruleForm.jingdianOrderPaymentTypes = this.jingdianOrderPaymentTypesOptions[this.jingdianOrderPaymentTypesIndex].codeIndex
            },
            // styleChange() {
            // 	this.$nextTick(() => {
            // 		// document.querySelectorAll('.app-update-pv .cu-form-group .uni-yaoxianStyle-yaoxianStyle').forEach(el=>{
            // 		//   el.style.backgroundColor = this.addUpdateForm.yaoxianStyle.content.backgroundColor
            // 		// })
            // 	})
            // },
			// 日期控件
			jingdianOrderTimeConfirm(val) {
                this.ruleForm.jingdianOrderTime = val.result;
                this.$forceUpdate();
            },
			// 日期控件
			insertTimeConfirm(val) {
                this.ruleForm.insertTime = val.result;
                this.$forceUpdate();
            },
			// 日期控件
			createTimeConfirm(val) {
                this.ruleForm.createTime = val.result;
                this.$forceUpdate();
            },

            getUUID() {
                return new Date().getTime();
            },
            async onSubmitTap() {
				if ((!this.ruleForm.jingdianOrderUuidNumber)) {
                    this.$utils.msg(`订单编号不能为空`);
                    return
                }
				if ((!this.ruleForm.buyNumber) && (!this.$validate.isIntNumer(this.ruleForm.buyNumber)) && this.ruleForm.buyNumber >0) {
                    this.$utils.msg(`购买数量不能为空，不能小于0 格式为数字`);
                    return
                }
				if ((!this.ruleForm.jingdianOrderTime)) {
                    this.$utils.msg(`预约日期不能为空`);
                    return
                }
                if (this.ruleForm.id) {
                    await this.$api.update(`jingdianOrder`, this.ruleForm);
                } else {
                    await this.$api.save(`jingdianOrder`, this.ruleForm);
                }
                uni.setStorageSync('pingluenStateState', true);
                this.$utils.msgBack('提交成功');
            },
            getDate(type) {
                const date = new Date();
                let year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();
                if (type === 'start') {
                    year = year - 60;
                } else if (type === 'end') {
                    year = year + 2;
                }
                month = month > 9 ? month : '0' + month;;
                day = day > 9 ? day : '0' + day;
                return `${year}-${month}-${day}`;
            },
            toggleTab(str) {
                this.$refs[str].show();
            }
        }
    }
</script>
<style lang="scss" scoped>
    .container {
        padding: 20upx;
    }

    .content:after {
        position: fixed;
        top: 0;
        right: 0;
        left: 0;
        bottom: 0;
        content: '';
        background-attachment: fixed;
        background-size: cover;
        background-position: center;
    }

    textarea {
        border: 1upx solid #EEEEEE;
        border-radius: 20upx;
        padding: 20upx;
    }

    .title {
        width: 180upx;
    }

    .avator {
        width: 150upx;
        height: 60upx;
    }

    .right-input {
        flex: 1;
        text-align: left;
        padding: 0 24upx;
    }

    .cu-form-group.active {
        justify-content: space-between;
    }

    .cu-form-group .title {
        height: auto;
        line-height:30rpx
    }

    .btn {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-wrap: wrap;
        padding: 20upx 0;
    }

    .cu-form-group {
        padding: 0 24upx;
        background-color: transparent;
        min-height: inherit;
    }

    .cu-form-group+.cu-form-group {
        border: 0;
    }

    .cu-form-group uni-input {
        padding: 0 30upx;
    }

    .uni-input {
        padding: 0 30upx;
    }

    .cu-form-group uni-textarea {
        padding: 30upx;
        margin: 0;
    }

    .cu-form-group uni-picker::after {
        line-height: 80rpx;
    }

    .select .uni-input {
        line-height: 80rpx;
    }

    .input .right-input {
        line-height: 88rpx;
    }
</style>