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
        prop="startTime"
        label="考试开始时间"
        width="200"
        sortable
        :formatter="formatDateTime">
        </el-table-column>
        <el-table-column
        prop="endTime"
        label="考试结束时间"
        width="180"
        sortable
        :formatter="formatDateTime1">
        </el-table-column>
      <el-table-column
        prop="teacherName"
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
          <el-button icon="el-icon-delete" type="text" @click="deleteRow(scope.$index, tableData)" v-show="scope.row.state === '已完成' ||
           scope.row.state === '已过期' ? true :false"></el-button>
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
           
          }],
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
          this.$router.push({name:'ReadTest',params:{ param1: name, param2: parseInt(user.studentId) }});
        },
        deleteRow(index, rows) {
          this.$confirm('确定提交吗？, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            rows.splice(index, 1);
            this.$message({
              type: 'success',
              message: '提交成功!'
            });
          })
        },
        formatDateTime(row){
            const isoDate = row.startTime; 
            const date = new Date(isoDate);
            return date.toLocaleString(); 
            },
        formatDateTime1(row){
            const isoDate = row.endTime; 
            const date = new Date(isoDate);
            return date.toLocaleString(); 
            }
      },mounted() {
        const user = JSON.parse(localStorage.getItem('user'));
        axios.get('http://localhost:4399/find', { params: { student_id: parseInt(user.studentId) } })
            .then(response => {
            this.tableData = response.data;
            console.log(response.data);
            const currentTime = new Date(); 

            this.tableData.forEach(row => {
              const endTime = new Date(row.endTime); 
              const startTime = new Date(row.startTime); 
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
            this.$message.error('出错啦~');
            });
        },
        
    }
  </script>