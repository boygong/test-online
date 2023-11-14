<template>
  <div class="main">
    <div style="width: 400px;height: 400px;">
      <h2>您所有的考试成绩分布如下:</h2><br>
      <canvas ref="pieChart"></canvas>
    </div>
    <div class="right">
      <h1>您一共经历了 {{ total }} 场考试</h1>
      <h1>其中：</h1>
      <h1>最好的成绩是 {{ maxcore }} 分</h1>
      <h1>最差的成绩是 {{ mincore }} 分</h1>
      <h1>平均成绩是 {{ averageScore }} 分</h1>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Chart from 'chart.js/auto';

export default {
  name: "TestAbout",
  data() {
    return {
      averageScore:null,
      total:null,
      maxcore:null,
      mincore:null,
      bestCore: null,
      middleCore: null,
      badCore: null,
      chartData: null
    };
  },
  mounted() {
    const user = JSON.parse(localStorage.getItem('user'));
    axios.get('http://localhost:4399/findAllCore', { params: { student_id: parseInt(user.student_id) } })
      .then(response => {
        this.bestCore = response.data.bestCore;
        this.middleCore = response.data.middleCore;
        this.badCore = response.data.badCore;
        this.total = response.data.total;
        this.averageScore =response.data.averageCore;
        this.maxcore = response.data.maxCore;
        this.mincore = response.data.minCore

        // 初始化 chartData 对象
        this.chartData = {
          labels: ['>90', '90~60', '<60'],
          datasets: [{
            data: [this.bestCore, this.middleCore, this.badCore],
            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
            hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
          }]
        };

        // 重新渲染图表
        this.renderChart();
      })
      .catch(error => {
        console.error(error);
        this.$message.error('出错啦~');
      });
  },
  methods: {
    renderChart() {
      const ctx = this.$refs.pieChart.getContext('2d');
      new Chart(ctx, {
        type: 'pie',
        data: this.chartData
      });
    }
  }
};
</script>
<style>
.main{
  display: flex;
  flex-direction: row;
}
.right{
  position: relative;
  top: 80px;
  left: 50px;
}
</style>
