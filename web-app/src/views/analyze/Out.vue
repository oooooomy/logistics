<template>
  <div class="main" id="main">

  </div>
</template>

<script>
import * as echarts from 'echarts/core';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent
} from 'echarts/components';
import {
  PieChart
} from 'echarts/charts';
import {
  CanvasRenderer
} from 'echarts/renderers';
import {AnalyzeCommodity} from "../../api/inventory";

echarts.use(
    [TitleComponent, TooltipComponent, LegendComponent, PieChart, CanvasRenderer]
);

export default {

  data() {
    return {
      commodityList: []
    }
  },

  mounted() {
    AnalyzeCommodity(-1).then((res) => {
      if (res.status) {
        let commodityList = res.data
        let chartDom = document.getElementById('main');
        let myChart = echarts.init(chartDom);
        let option;

        option = {
          title: {
            text: '商品出库排行分析',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left',
          },
          series: [
            {
              name: '访问来源',
              type: 'pie',
              radius: '50%',
              data: commodityList,
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };

        option && myChart.setOption(option);
      }
    })

  },

  methods: {},

}
</script>

<style scoped>
.main {
  width: 100%;
  height: 500px;
  background: #ffffff;
  padding: 50px;
}
</style>