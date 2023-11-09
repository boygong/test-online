<template>
    <el-table
      :data="tableData"
      style="width: 100%"
      max-height="800">
      <el-table-column
        fixed
        type="index"
        label="序号"
        width="150">
      </el-table-column>
      <el-table-column
        prop="name"
        label="考试名称"
        width="150">
      </el-table-column>
      <el-table-column
        prop="start_time"
        label="考试开始时间"
        width="200"
        sortable
        :formatter="formatDateTime">
        </el-table-column>
        <el-table-column
        prop="end_time"
        label="考试结束时间"
        width="180"
        sortable
        :formatter="formatDateTime1">
        </el-table-column>
      <el-table-column
        prop="teacher_name"
        label="发布教师"
        width="150">
      </el-table-column>
          <el-table-column
          prop="state"
          label="状态"
          width="150">
          <template slot-scope="scope">
           <el-button :type="getButtonType(scope.row.state)"> {{ scope.row.state }}
          </el-button>
          </template>
        </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="180">
        <template slot-scope="scope">
          <el-button :disabled="scope.row.state === '已完成' || scope.row.state === '已过期' || scope.row.state === '待发布'"
          @click.native.prevent="turnto(scope.$index, tableData)"
          type="text"
          size="small">
          进入考试
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </template>
  
  <script>
  import axios from 'axios';
    export default {
        name:'TestList',
      data() {
        return {
          tableData: [{
           
          }]
        }
      },
      methods: {
            getButtonType(state) {
              switch (state) {
                case '已过期':
                  return 'danger';
                case '即将过期':
                  return 'warning';
                case '进行中':
                  return 'success';
                case '待发布':
                  return '';
                default:
                  return 'primary';
              }
            },
        turnto(index, rows) {
          const name = rows[index].name;
          const user = JSON.parse(localStorage.getItem('user'));
          this.$router.push({name:'ReadTest',params:{ param1: name, param2: parseInt(user.student_id) }});
        },
        formatDateTime(row){
            const isoDate = row.start_time; 
            const date = new Date(isoDate);
            return date.toLocaleString(); 
            },
        formatDateTime1(row){
            const isoDate = row.end_time; 
            const date = new Date(isoDate);
            return date.toLocaleString(); 
            }
      },mounted() {
        const user = JSON.parse(localStorage.getItem('user'));
        axios.get('http://localhost:8080/find', { params: { student_id: parseInt(user.student_id) } })
            .then(response => {
            this.tableData = response.data;
            console.log(response.data);
            const currentTime = new Date(); 

            this.tableData.forEach(row => {
              const endTime = new Date(row.end_time); 
              const startTime = new Date(row.start_time); 
              const timeDiff = endTime - currentTime; 
              const timeDiff1 = startTime - currentTime; 
              if(row.state != '已完成')
              {
                if (timeDiff < 0) {
                row.state = '已过期'; 
              } else if (timeDiff < 2 * 60 * 60 * 1000) {
                row.state = '即将过期'; 
              } else if(timeDiff1 > 0 && timeDiff >0){
                row.state = '待发布'; 
              }else{
                row.state = '进行中'; 
              }
              }
            });
            })
            .catch(error => {
            console.error(error);
            });
        },
        
    }
  </script>