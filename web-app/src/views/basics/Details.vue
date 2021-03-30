<template>
  <div class="main">
    <div class="header">
      <router-link to="/warehouse">
        <a-icon type="arrow-left" style="padding-right: 5px"/>
        返回上一页
      </router-link>
    </div>
    <div style="display: flex">
      <a-button class="editable-btn" @click="handleSubmit('in')">
        入库商品
      </a-button>
      <a-button class="editable-btn" @click="handleSubmit('out')">
        出库商品
      </a-button>
      <download-excel
          class="export-excel-wrapper"
          :data="data"
          :fields="json_fields"
          name="库存报表.xls">
        <a-button class="editable-btn">
          <a-icon type="cloud-download"/>
          下载库存报表 Excel
        </a-button>
      </download-excel>
      <a-button class="editable-btn" @click="recordVisible = true">
        <a-icon type="retweet"/>
        出入库记录 Excel
      </a-button>
    </div>

    <a-table :loading="loading" :columns="columns" :data-source="data" rowKey="id">
      <a slot="name" slot-scope="text">{{ text }}</a>
      <span slot="customTitle"><a-icon type="smile-o"/> 商品名</span>
      <span slot="count" slot-scope="count">
        <a-tag color="#108ee9">{{ count }}</a-tag>
      </span>
    </a-table>

    <a-modal
        title="入库 ｜ 出库"
        :closable="false"
        :visible="visible"
        @ok="submit"
        @cancel="visible = false"
    >
      <a-form-model :model="form">
        <a-form-model-item label="请选商品">
          <a-select v-model="selectIndex" placeholder="请选择入库的商品">
            <a-select-option :value="index" v-for="(item, index) in commodityList" :key="index">
              {{ item.name }}
            </a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item label="商品数量">
          <a-input-number id="inputNumber" v-model="form.count" :min="1"/>
        </a-form-model-item>
        <a-form-model-item label="备注">
          <a-input :rows="4" v-model="form.description" type="textarea"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-modal
        title="出入库记录"
        width="80%"
        :visible="recordVisible"
        :footer="null"
        @cancel="recordVisible = false"
    >
      <InventoryRecords :warehouse-id="id"/>
    </a-modal>

  </div>
</template>

<script>
import {FindAllCommodity} from "../../api/commodity";
import {FindAllInventory, InAndOut} from "../../api/inventory";
import InventoryRecords from "../../components/InventoryRecords";

const columns = [
  {
    title: '商品ID',
    dataIndex: 'id',
    key: 'id',
    width: '50%'
  },
  {
    dataIndex: 'name',
    key: 'name',
    slots: {title: 'customTitle'},
    scopedSlots: {customRender: 'name'},
  },
  {
    title: '库存数量',
    dataIndex: 'count',
    key: 'count',
    scopedSlots: {customRender: 'count'},
  },
];

export default {

  components: {InventoryRecords},

  data() {
    return {
      json_fields: {
        "商品ID": "cid",
        "商品名称": "name",
        "库存盘点数量": "count",
      },
      id: this.$route.params.id,
      loading: false,
      visible: false,
      recordVisible: false,
      selectIndex: 0,
      commodityVisible: false,
      data: [],
      columns,
      commodityList: [],
      submitType: '',
      form: {
        wid: this.$route.params.id,
        cid: '',
        name: '',
        description: '',
        count: 50,
      },
    }
  },

  mounted() {
    this.loadData()
    FindAllCommodity().then((res) => {
      if (res.status) this.commodityList = res.data
    })
  },

  methods: {

    loadData() {
      this.loading = true
      FindAllInventory(this.$route.params.id).then((res) => {
        setTimeout(() => {
          this.data = res.data
          this.loading = false
        }, 500)
      })
    },

    handleSubmit(type) {
      this.submitType = type
      this.visible = true
    },

    submit() {
      this.form.cid = this.commodityList[this.selectIndex].id
      this.form.name = this.commodityList[this.selectIndex].name
      InAndOut(this.submitType, this.form).then((res) => {
        if (res.status) this.$message.success("操作成功")
        this.visible = false
        this.loadData()
      })
    },

  }

}
</script>


<style scoped>
.main {
  background: #ffffff;
  padding: 30px;
}

.header {
  font-size: 18px;
  margin-bottom: 40px;
}

a {
  color: #000000;
}

.header a:hover {
  color: #5a84fd;
}

.editable-btn {
  margin-bottom: 20px;
  margin-right: 10px;
}

.in-icon {
  transform: rotate(270deg);
}
</style>