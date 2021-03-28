<template>
  <div>
    <a-spin size="large" :spinning="spinning">
      <a-row :gutter="20">
        <a-col :span="8" class="item">
          <a-card hoverable class="add-item" @click="visible = true">
            <a-icon type="plus"/>
            添加车辆
          </a-card>
        </a-col>
        <a-col :span="8" v-for="(item, index) in this.data" :key="index" class="item">
          <a-card hoverable>
            <template slot="actions" class="ant-card-actions">
              <a-tag :color=" item.driving ? 'orange': 'green'">{{ item.driving ? '正在途中' : '正在休息' }}</a-tag>
              <span>使用记录</span>
            </template>
            <a-card-meta
                :title="'车牌号：' + item.number"
                :description="'ID: ' + item.id">
              <a-badge :number-style="{ backgroundColor: '#52c41a' }"
                       slot="avatar"
                       :count="item.type"
                       :offset="[-80,10]">
                <img class="image" :src="require('../../assets/' +item.type+'.svg')" alt=""/>
              </a-badge>
            </a-card-meta>
          </a-card>
        </a-col>
      </a-row>
    </a-spin>
    <a-modal
        title="新增仓库"
        :visible="visible"
        @ok="submit"
        @cancel="visible = false"
    >
      <a-form-model :model="form">
        <a-form-model-item label="车牌号码">
          <a-input v-model="form.number"/>
        </a-form-model-item>
        <a-form-model-item label="车辆类型">
          <a-select v-model="form.type">
            <a-select-option value="小型汽车">小型汽车</a-select-option>
            <a-select-option value="货车">货车</a-select-option>
            <a-select-option value="卡车">卡车</a-select-option>
          </a-select>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </div>
</template>

<script>

import {FindAllVehicle, SaveVehicle} from "@/api/vehicle";

export default {
  name: "WareHouse",
  data() {
    return {
      visible: false,
      spinning: false,
      form: {
        number: '京A0000',
        type: '货车',
        driving: false,
      },
      data: [],
      imgList: [
        require('../../assets/warehouse0.svg'),
        require('../../assets/warehouse1.svg'),
        require('../../assets/warehouse2.svg'),]
    }
  },

  mounted() {
    this.loadData()
  },

  methods: {

    loadData() {
      this.spinning = true
      FindAllVehicle().then((res) => {
        if (res.status) this.data = res.data
        setTimeout(() => {
          this.spinning = false
        }, 600)
      })
    },

    submit() {
      SaveVehicle(this.form).then((res) => {
        if (res.status) this.$message.success("添加成功")
        this.visible = false
        this.loadData()
      })
    }

  },

}
</script>

<style scoped>
.add-item {
  text-align: center;
  line-height: 120px;
  min-height: 120px;
  border: 1px dashed #91949c;
}

.add-item:hover {
  border: 1px dashed #5a84fd;
  color: #5a84fd;
}

.item {
  margin-bottom: 20px;
}

.image {
  width: 80px;
  height: 80px;
  margin-left: 20px;
}
</style>