<template>
  <div>
    <a-table :loading="loading" :columns="columns" :data-source="data" rowKey="id">
      <a slot="company" slot-scope="company">{{ company }}</a>
      <span slot="customTitle"><a-icon type="bank" /> 公司名称</span>
    </a-table>

  </div>
</template>

<script>
import {FindAllSale, SaveSale} from "../../api/sale";

const columns = [
  {
    dataIndex: 'company',
    key: 'company',
    slots: {title: 'customTitle'},
    scopedSlots: {customRender: 'company'},
  },
  {
    title: '预留电话',
    key: 'phone',
    dataIndex: 'phone',
  },
  {
    title: '时间',
    dataIndex: 'createAt',
    key: 'createAt',
  },
];

const data = [];

export default {

  data() {
    return {
      loading: false,
      data: [],
      columns,
    };
  },

  mounted() {
    this.loadTableData()
  },

  methods: {

    loadTableData() {
      this.loading = true
      FindAllSale().then((res) => {
        setTimeout(() => {
          this.loading = false
          this.data = res.data
        }, 600)
      })
    }

  },

};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
