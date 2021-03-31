<template>
  <div>
    <a-table :loading="loading" :columns="columns" :data-source="data" bordered rowKey="id">
      <span slot="status" slot-scope="status">
        <a-tag v-if="status===0" color="#f50">等待审核</a-tag>
        <a-tag v-if="status===1" color="#87d068">正在运输</a-tag>
        <a-tag v-if="status===2" color="#2db7f5">配送完成</a-tag>
      </span>
      <template
          v-for="col in ['phone','address']"
          :slot="col"
          slot-scope="text, record, index"
      >
        <div :key="col">
          <a-input
              v-if="record.editable"
              style="margin: -5px 0"
              :value="text"
              @change="e => handleChange(e.target.value, record.id, col)"
          />
          <template v-else>
            {{ text }}
          </template>
        </div>
      </template>
      <template slot="operation" slot-scope="text, record, index">
        <div class="editable-row-operations">
          <span v-if="record.editable">
          <a @click="() => save(record.id, index)">保存</a>
          <a-popconfirm title="Sure to cancel?" @confirm="() => cancel(record.id)">
            <a>取消</a>
          </a-popconfirm>
        </span>
          <span v-else>
          <a :disabled="editingKey !== ''" @click="() => edit(record.id)">编辑</a>
        </span>
          <a-button @click="review(index)" type="link" v-if="record.status===0">审核</a-button>
          <a-button @click="review(index)" type="link" v-if="record.status===1">配送</a-button>
          <a-button @click="review(index)" type="link" v-if="record.status===2">查看</a-button>
        </div>
      </template>
    </a-table>

    <a-modal
        title="Title"
        :visible="visible"
        @ok="submitForm"
        @cancel="visible = false"
    >
      <a-form-model :model="form">
        <a-form-model-item label="姓名">
          <a-input v-model="form.name" placeholder="请输入司机姓名"/>
        </a-form-model-item>
        <a-form-model-item label="身份证号">
          <a-input v-model="form.idCard" placeholder="请输入司机身份证信息"/>
        </a-form-model-item>
        <a-form-model-item label="联系方式">
          <a-input v-model="form.phone" placeholder="请输入手机号码"/>
        </a-form-model-item>
        <a-form-item label="驾照信息">
          <a-row :gutter="20">
            <a-col :span="12">
              <a-input v-model="form.license" addon-before="驾驶证" default-value="0571"/>
            </a-col>
            <a-col :span="7">
              <a-input-number v-model="form.score" addon-before="分数" default-value="12" :min="0" :max="12"/>
            </a-col>
          </a-row>
        </a-form-item>
        <a-form-model-item label="性别">
          <a-radio-group v-model="form.gender">
            <a-radio value="男性">男性</a-radio>
            <a-radio value="女性">女性</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item label="家庭住址">
          <a-input v-model="form.address" type="textarea"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-modal
        title="Title"
        :visible="visible2"
        width="60%"
        :footer="null"
        @cancel="visible2 = false"
    >
      <a-steps :current="select.status" style="padding: 50px">
        <a-step title="确认信息无误"/>
        <a-step title="开始配送"/>
        <a-step title="配送完成"/>
      </a-steps>
      <div class="content">
        <div v-if="select.status === 0" class="check">
          <p>送货司机： {{ select.driver }}</p>
          <p>车牌号码： {{ select.number }}</p>
          <p>加急处理： {{ select.urgent }}</p>
          <p>注意事项： {{ select.care }}</p>
          <p>客户电话： {{ select.phone }}</p>
          <p>客户地址： {{ select.address }}</p>
          <p>预计送达： {{ select.time }}</p>
          <a-button type="danger" style="margin-right: 20px" :loading="loading" @click="agree">通过</a-button>
          <a-button @click="visible2 = false">不通过</a-button>
        </div>
        <div v-if="select.status === 1">
          <a-result
              status="success"
              title="Successfully passed the audit!"
              >
            <template #extra>
              <a-button @click="service" key="console" type="primary">
                已送达目的地
              </a-button>
            </template>
          </a-result>
        </div>
        <div v-if="select.status === 2">
          <a-result
              status="success"
              title="运输订单已成功送达"
          >
            <template #extra>
              <a-button @click="visible2 = false" key="console" type="primary">
                确定
              </a-button>
            </template>
          </a-result>
        </div>
      </div>

    </a-modal>

  </div>
</template>

<script>
import {FindAllDistribution, SaveDistribution} from "../../api/distribution";

const columns = [
  {
    title: '司机',
    dataIndex: 'driver',
    scopedSlots: {customRender: 'driver'},
  },
  {
    title: '车牌号',
    dataIndex: 'number',
    scopedSlots: {customRender: 'number'},
  },
  {
    title: '客户电话',
    dataIndex: 'phone',
    scopedSlots: {customRender: 'phone'},
  },
  {
    title: '客户地址',
    dataIndex: 'address',
    scopedSlots: {customRender: 'address'},
  },
  {
    title: '注意事项',
    dataIndex: 'care',
    scopedSlots: {customRender: 'care'},
  },
  {
    title: '预计送达',
    dataIndex: 'time',
    scopedSlots: {customRender: 'time'},
  },
  {
    title: '当前状态',
    dataIndex: 'status',
    scopedSlots: {customRender: 'status'},
  },
  {
    title: '操作',
    dataIndex: 'operation',
    scopedSlots: {customRender: 'operation'},
  },
];

export default {
  data() {
    return {
      select: {},
      loading: false,
      form: {
        cacheData: [],
        name: '',
        gender: '男性',
        phone: '',
        address: '',
        idCard: '',
        license: '',
        score: 12,
      },
      visible: false,
      visible2: false,
      data: [],
      columns,
      editingKey: '',
    };
  },
  mounted() {
    this.loadTableData()
  },
  methods: {
    loadTableData() {
      this.loading = true
      FindAllDistribution().then((res) => {
        if (res.status) {
          this.data = res.data
          this.cacheData = res.data.map(item => ({...item}))
        }
        setTimeout(() => {
          this.loading = false
        }, 600)
      })
    },
    submitForm() {
      SaveDistribution(this.form).then((res) => {
        if (res.status) this.$message.success('司机信息提交成功');
        this.visible = false
        this.loadTableData()
      })
    },
    handleChange(value, id, column) {
      const newData = [...this.data];
      const target = newData.filter(item => id === item.id)[0];
      if (target) {
        target[column] = value;
        this.data = newData;
      }
    },
    edit(id) {
      const newData = [...this.data];
      const target = newData.filter(item => id === item.id)[0];
      this.editingKey = id;
      if (target) {
        target.editable = true;
        this.data = newData;
      }
    },

    save(id, index) {
      const newData = [...this.data];
      const newCacheData = [...this.cacheData];
      const target = newData.filter(item => id === item.id)[0];
      const targetCache = newCacheData.filter(item => id === item.id)[0];
      if (target && targetCache) {
        delete target.editable;
        this.data = newData;
        Object.assign(targetCache, target);
        this.cacheData = newCacheData;
      }
      this.editingKey = '';
      SaveDistribution(newData[index]).then((res) => {
        if (res.status) this.$message.success("信息保存成功")
      })
    },

    cancel(id) {
      const newData = [...this.data];
      const target = newData.filter(item => id === item.id)[0];
      this.editingKey = '';
      if (target) {
        Object.assign(target, this.cacheData.filter(item => id === item.id)[0]);
        delete target.editable;
        this.data = newData;
      }
    },

    review(index) {
      this.select = this.data[index]
      this.visible2 = true
    },

    agree() {
      this.select.status = 1
      SaveDistribution(this.select)
    },

    service(){
      this.select.status = 2
      SaveDistribution(this.select)
    },

  },
};
</script>
<style scoped>

.editable-add-btn {
  margin-bottom: 15px;
}

.editable-row-operations a {
  margin-right: 8px;
}

.content {
  padding: 50px 0;
}


.check {
  padding-left: 200px;
}

.check p {
  padding-bottom: 20px;
}
</style>
