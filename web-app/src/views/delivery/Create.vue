<template>
  <div class="main">
    <a-steps :current="current">
      <a-step title="填写申请信息"></a-step>
      <a-step title="确认配送信息"/>
      <a-step title="完成申请"/>
    </a-steps>
    <div class="steps-content">
      <div v-if="current === 0">
        <a-form-model :model="form" :label-col="labelCol" :wrapper-col="wrapperCol">
          <a-form-model-item label="选择司机" required>
            <a-select v-model="selectDriverIndex" placeholder="请选择配送司机">
              <a-select-option :value="index" v-for="(item, index) in drivers" :key="index" :disabled="item.driving">
                {{ item.name }}
                <i class="dis" v-if="item.driving">
                  <a-icon type="close-circle"/>
                  正在途中</i>
              </a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item label="选择运输车辆" required>
            <a-select v-model="selectVehicleIndex" placeholder="请选择配送车辆">
              <a-select-option :value="index" v-for="(item, index) in vehicles" :key="index" :disabled="item.driving">
                {{ item.type }} : {{ item.number }}
                <i class="dis" v-if="item.driving">
                  <a-icon type="close-circle"/>
                  正在途中</i>
              </a-select-option>
            </a-select>
          </a-form-model-item>
          <a-form-model-item label="预计交货时间" required>
            <a-date-picker
                v-model="form.time"
                show-time
                type="date"
                placeholder="选择日期"
                style="width: 100%;"
            />
          </a-form-model-item>
          <a-form-model-item label="加急处理">
            <a-switch v-model="form.urgent"/>
          </a-form-model-item>
          <a-form-model-item label="注意事项">
            <a-checkbox-group v-model="form.cares">
              <a-checkbox value="冰柜冷藏" name="type">
                冰柜冷藏
              </a-checkbox>
              <a-checkbox value="注意易碎" name="type">
                注意易碎
              </a-checkbox>
              <a-checkbox value="防止高温" name="type">
                防止高温
              </a-checkbox>
            </a-checkbox-group>
          </a-form-model-item>
          <a-form-model-item label="客户电话" required>
            <a-input v-model="form.phone"/>
          </a-form-model-item>
          <a-form-model-item label="客户地址" required>
            <a-input v-model="form.address" type="textarea" :rows="4"/>
          </a-form-model-item>
          <a-form-model-item :wrapper-col="{ span: 14, offset: 6 }">
            <a-button type="primary" @click="next">
              下一步
            </a-button>
          </a-form-model-item>
        </a-form-model>
      </div>
      <div v-if="current === 1" class="check">
        <p>送货司机： {{ form.driver }}</p>
        <p>车牌号码： {{ form.number }}</p>
        <p>加急处理： {{ form.urgent }}</p>
        <p>注意事项： {{ form.care }}</p>
        <p>客户电话： {{ form.phone }}</p>
        <p>客户地址： {{ form.address }}</p>
        <p>预计送达： {{ form.time }}</p>
        <a-button type="danger" style="margin-right: 20px" :loading="loading" @click="submit">提交</a-button>
        <a-button @click="current = 0">上一步</a-button>
      </div>
      <div v-if="current === 2">
        <a-result
            status="success"
            title="Submitted Successfully"
            sub-title="Please wait for the administrator to review the delivery request."
        >
          <template #extra>
            <router-link to="/delivery/list">
              <a-button key="console" type="primary">
                Go Console
              </a-button>
            </router-link>
            <a-button key="buy" @click="current = 0">
              Submit Again
            </a-button>
          </template>
        </a-result>
      </div>
    </div>
  </div>
</template>

<script>
import {FindAllCanUse, SaveDistribution} from "../../api/distribution";

export default {

  data() {
    return {
      loading: false,
      labelCol: {span: 6},
      wrapperCol: {span: 12},
      current: 0,
      selectDriverIndex: 0,
      selectVehicleIndex: 0,
      drivers: [],
      vehicles: [],
      form: {
        did: '',
        vid: '',
        driver: '',
        number: '',
        phone: '',
        address: '',
        urgent: false,
        cares: [],
        care: '',
        time: '',
        status: 0,
      },
    }
  },

  mounted() {
    FindAllCanUse().then((res) => {
      if (res.status) {
        this.drivers = res.data.drivers
        this.vehicles = res.data.vehicles
      }
      console.log(this.drivers)
      console.log(this.vehicles)
    })
  },

  methods: {
    next() {
      let care = ''
      for (let i = 0; i < this.form.cares.length; i++) {
        care += this.form.cares[i] + ", "
      }
      this.form.driver = this.drivers[this.selectDriverIndex].name
      this.form.did = this.drivers[this.selectDriverIndex].id
      this.form.number = this.vehicles[this.selectVehicleIndex].number
      this.form.vid = this.vehicles[this.selectVehicleIndex].id
      this.form.care = care
      this.current = 1
    },
    submit() {
      this.loading = true
      SaveDistribution(this.form).then((res) => {
        if (res.status) {
          setTimeout(() => {
            this.loading = false
            this.current = 2
            this.$message.success("提交成功，请等待审核")
          }, 800)
        } else {
          setTimeout(() => {
            this.loading = false
            this.$message.error("提交失败")
          }, 800)
        }
      })
    },
  },

}
</script>

<style scoped>
.main {
  padding: 50px 180px;
  background: #ffffff;
}

.steps-content {
  margin-top: 40px;
}

.check {
  padding-left: 200px;
}

.check p {
  padding-bottom: 10px;
}

.dis {
  padding-left: 10px;
  font-size: 10px;
  letter-spacing: 1px;
  color: red;
}

</style>