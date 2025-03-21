<template>
    <view class="content">
        <form class="app-update-pv">
            <view
                    :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                    class="cu-form-group">
                <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                      class="title">景点名称</view>
                <input   disabled
                         :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                         :disabled="ro.jingdianName" type="text" v-model="ruleForm.jingdianName" placeholder="景点名称"></input>
            </view>
            <view
                    :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                    class="cu-form-group">
                <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                      class="title">景点编号</view>
                <input   disabled
                         :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                         :disabled="ro.jingdianUuidNumber" type="text" v-model="ruleForm.jingdianUuidNumber" placeholder="景点编号"></input>
            </view>
                <view
                        :style='{"boxShadow":"0 0 0px rgba(0,0,0,.3)","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(218, 220, 219, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"108rpx"}'
                        class="cu-form-group" @tap="jingdianPhotoTap">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">景点照片</view>
                    <view class="right-input" style="padding:0;textAlign:left">
                        <image
                                :style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
                                class="avator" v-if="ruleForm.jingdianPhoto" :src="baseUrl+ruleForm.jingdianPhoto" mode="aspectFill">
                        </image>
                        <image  :style='{"width":"68rpx","boxShadow":"0 0 0px rgba(0,0,0,.3)","borderRadius":"100%","textAlign":"left","height":"68rpx"}'
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
                         :disabled="ro.jingdianAddress" type="text" v-model="ruleForm.jingdianAddress" placeholder="景点位置"></input>
            </view>
                <view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">景点星级</view>
                    <picker @change="jingdianXingjiTypesChange" :value="jingdianXingjiTypesIndex" :range="jingdianXingjiTypesOptions" range-key="indexName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.jingdianXingjiTypes?ruleForm.jingdianXingjiValue:"请选择景点星级"}}</view>
                    </picker>
                </view>
                <view :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                      class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">景点类型</view>
                    <picker @change="jingdianTypesChange" :value="jingdianTypesIndex" :range="jingdianTypesOptions" range-key="indexName">
                        <view
                                :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                                class="uni-input">{{ruleForm.jingdianTypes?ruleForm.jingdianValue:"请选择景点类型"}}</view>
                    </picker>
                </view>
                <view
                        :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">剩余门票数</view>
                    <input   disabled
                             :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                             :disabled="ro.jingdianKucunNumber" type="number" v-model="ruleForm.jingdianKucunNumber" placeholder="剩余门票数"></input>
                </view>
                <view
                        :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">景点原价</view>
                    <input   disabled
                             :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                             :disabled="ro.jingdianOldMoney" type="number" v-model="ruleForm.jingdianOldMoney" placeholder="景点原价"></input>
                </view>
                <view
                        :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"100rpx"}'
                        class="cu-form-group">
                    <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                          class="title">现价/张</view>
                    <input   disabled
                             :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx"}'
                             :disabled="ro.jingdianNewMoney" type="number" v-model="ruleForm.jingdianNewMoney" placeholder="现价/张"></input>
                </view>
            <view
                    :style='{"boxShadow":"0","backgroundColor":"rgba(255, 255, 255, 0.25)","borderColor":"rgba(228, 232, 230, 1)","margin":"0 0 0px 0","borderWidth":"2rpx","borderStyle":"none none solid none ","height":"210rpx"}'
                    class="cu-form-group">
                <view :style='{"width":"160rpx","fontSize":"28rpx","color":"var(--publicMainColor)","textAlign":"left"}'
                      class="title">景点介绍</view>
                <textarea :style='{"padding":"0 30rpx","boxShadow":"0 0 0px rgba(0,0,0,.6) inset","backgroundColor":"rgba(255, 255, 255, 1)","borderColor":"var(--publicMainColor)","borderRadius":"16rpx","color":"var(--publicMainColor)","textAlign":"left","borderWidth":"2rpx","fontSize":"28rpx","borderStyle":"solid","height":"88rpx","marginTop":"7rpx","height":"200rpx"}'
                          :disabled="ro.jingdianContent" v-model="ruleForm.jingdianContent" placeholder="景点介绍"/>
            </view>

            <view class="btn">
                <button
                        :style='{"boxShadow":"0 0 16rpx rgba(0,0,0,0) inset","backgroundColor":"var(--publicMainColor)","borderColor":"#409EFF","borderRadius":"8rpx","color":"rgba(255, 255, 255, 1)","borderWidth":"0","width":"70%","fontSize":"32rpx","borderStyle":"solid","height":"80rpx"}'
                        @tap="onSubmitTap" class="bg-red">提交</button>
            </view>
        </form>

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
					jingdianName: false,
					jingdianUuidNumber: true,
					jingdianPhoto: false,
					jingdianAddress: false,
					zanNumber: false,
					caiNumber: false,
					jingdianXingjiTypes: false,
					jingdianTypes: false,
					jingdianKucunNumber: false,
					jingdianOldMoney: false,
					jingdianNewMoney: false,
					jingdianClicknum: false,
					jingdianContent: false,
					shangxiaTypes: false,
					jingdianDelete: false,
					insertTime: false,
					createTime: false,
        },
            ruleForm: {
					jingdianName: '',
					jingdianUuidNumber: this.getUUID(),//数字
					jingdianPhoto: '',
					jingdianAddress: '',
					zanNumber: '',
					caiNumber: '',
					jingdianXingjiTypes: '',//数字
					jingdianXingjiValue: '',//数字对应的值
					jingdianTypes: '',//数字
					jingdianValue: '',//数字对应的值
					jingdianKucunNumber: '',
					jingdianOldMoney: '',
					jingdianNewMoney: '',
					jingdianClicknum: '',
					jingdianContent: '',
					shangxiaTypes: '',//数字
					shangxiaValue: '',//数字对应的值
					jingdianDelete: '',
					insertTime: '',
					createTime: '',
            },
            // 登陆用户信息
            user: {},
            jingdianXingjiTypesOptions: [],
            jingdianXingjiTypesIndex : 0,
            jingdianTypesOptions: [],
            jingdianTypesIndex : 0,
            shangxiaTypesOptions: [],
            shangxiaTypesIndex : 0,

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
		/*下拉框*/
			let jingdianXingjiTypesParams = {
                page: 1,
                limit: 100,
                dicCode: 'jingdian_xingji_types',
            }
			let jingdianXingjiTypes = await this.$api.page(`dictionary`, jingdianXingjiTypesParams);
			this.jingdianXingjiTypesOptions = jingdianXingjiTypes.data.list
		/*下拉框*/
			let jingdianTypesParams = {
                page: 1,
                limit: 100,
                dicCode: 'jingdian_types',
            }
			let jingdianTypes = await this.$api.page(`dictionary`, jingdianTypesParams);
			this.jingdianTypesOptions = jingdianTypes.data.list
		/*下拉框*/
			let shangxiaTypesParams = {
                page: 1,
                limit: 100,
                dicCode: 'shangxia_types',
            }
			let shangxiaTypes = await this.$api.page(`dictionary`, shangxiaTypesParams);
			this.shangxiaTypesOptions = shangxiaTypes.data.list


            // 如果是更新操作
            if (options.id) {
                this.ruleForm.id = options.id;
                // 获取信息
                let res = await this.$api.info(`jingdian`, this.ruleForm.id);
                this.ruleForm = res.data;
            }
            if(options.jingdianId){
                this.ruleForm.jingdianId = options.jingdianId;
            }
            // 跨表
            // this.styleChange()
        },
        methods: {
            // 下拉变化
            jingdianXingjiTypesChange(e) {
                this.jingdianXingjiTypesIndex = e.target.value
                this.ruleForm.jingdianXingjiValue = this.jingdianXingjiTypesOptions[this.jingdianXingjiTypesIndex].indexName
                this.ruleForm.jingdianXingjiTypes = this.jingdianXingjiTypesOptions[this.jingdianXingjiTypesIndex].codeIndex
            },
            jingdianTypesChange(e) {
                this.jingdianTypesIndex = e.target.value
                this.ruleForm.jingdianValue = this.jingdianTypesOptions[this.jingdianTypesIndex].indexName
                this.ruleForm.jingdianTypes = this.jingdianTypesOptions[this.jingdianTypesIndex].codeIndex
            },
            shangxiaTypesChange(e) {
                this.shangxiaTypesIndex = e.target.value
                this.ruleForm.shangxiaValue = this.shangxiaTypesOptions[this.shangxiaTypesIndex].indexName
                this.ruleForm.shangxiaTypes = this.shangxiaTypesOptions[this.shangxiaTypesIndex].codeIndex
            },
            // styleChange() {
            // 	this.$nextTick(() => {
            // 		// document.querySelectorAll('.app-update-pv .cu-form-group .uni-yaoxianStyle-yaoxianStyle').forEach(el=>{
            // 		//   el.style.backgroundColor = this.addUpdateForm.yaoxianStyle.content.backgroundColor
            // 		// })
            // 	})
            // },
			jingdianPhotoTap() {
                let _this = this;
                this.$api.upload(function(res) {
                    _this.ruleForm.jingdianPhoto = 'upload/' + res.file;
                    _this.$forceUpdate();
                    _this.$nextTick(() => {
                        // _this.styleChange()
                    })
                });
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
				if ((!this.ruleForm.jingdianName)) {
                    this.$utils.msg(`景点名称不能为空`);
                    return
                }
				if ((!this.ruleForm.jingdianUuidNumber)) {
                    this.$utils.msg(`景点编号不能为空`);
                    return
                }
				if ((!this.ruleForm.jingdianPhoto)) {
                    this.$utils.msg(`景点照片不能为空`);
                    return
                }
				if ((!this.ruleForm.jingdianAddress)) {
                    this.$utils.msg(`景点位置不能为空`);
                    return
                }
				if ((!this.ruleForm.zanNumber) && (!this.$validate.isIntNumer(this.ruleForm.zanNumber)) && this.ruleForm.zanNumber >0) {
                    this.$utils.msg(`赞不能为空，不能小于0 格式为数字`);
                    return
                }
				if ((!this.ruleForm.caiNumber) && (!this.$validate.isIntNumer(this.ruleForm.caiNumber)) && this.ruleForm.caiNumber >0) {
                    this.$utils.msg(`踩不能为空，不能小于0 格式为数字`);
                    return
                }
				if ((!this.ruleForm.jingdianXingjiTypes)) {
                    this.$utils.msg(`景点星级不能为空`);
                    return
                }
				if ((!this.ruleForm.jingdianKucunNumber) && (!this.$validate.isIntNumer(this.ruleForm.jingdianKucunNumber)) && this.ruleForm.jingdianKucunNumber >0) {
                    this.$utils.msg(`剩余门票数不能为空，不能小于0 格式为数字`);
                    return
                }
				if ((!this.ruleForm.jingdianClicknum) && (!this.$validate.isIntNumer(this.ruleForm.jingdianClicknum)) && this.ruleForm.jingdianClicknum >0) {
                    this.$utils.msg(`景点热度不能为空，不能小于0 格式为数字`);
                    return
                }
                if (this.ruleForm.id) {
                    await this.$api.update(`jingdian`, this.ruleForm);
                } else {
                    await this.$api.save(`jingdian`, this.ruleForm);
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