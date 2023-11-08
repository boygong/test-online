<template>
    <div>
    <h2>您所有的考试成绩分布如下:</h2><br>
    <div style="width: 400px;height: 400px;">
      <canvas ref="pieChart"></canvas>
    </div>
   </div>
  </template>
  
  <script>
  import Chart from 'chart.js/auto';
  
  export default {
    name:"TestAbout",
    data() {
      return {
        chartData: {
          labels: ['>90', '90~60', '<60'],
          datasets: [{
            data: [60, 50, 100],
            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
            hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
          }]
        }
      };
    },
    mounted() {
      this.renderChart();
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
  