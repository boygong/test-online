<template>
    <el-table
      :data="tableData"
      style="width: 100%"
      :default-sort = "{prop: 'time', order: 'descending'}"
      >
      <el-table-column
        type="index"
        label="序号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="time"
        label="日期"
        sortable
        :formatter="formatDate"
        width="200">
      </el-table-column>
      <el-table-column
        prop="name"
        label="考试科目"
        width="200">
      </el-table-column>
      <el-table-column
        prop="core"
        label="成绩"
        sortable>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
        <el-button type="text" @click.native.prevent="deleteRow(scope.$index, tableData)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </template>
  <script>
  import axios from 'axios';
    export default {
        name:"RecordsTest",
      data() {
        return {
          tableData: []
        }
      },
      methods: {
        formatDate(column) {
            const date = new Date(column.time); 
            const formattedDate = date.toLocaleDateString('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' });
            return formattedDate;
            },
            deleteRow(index, rows) {
            this.$confirm('此操作将删除该记录(非永久性删除), 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
            rows.splice(index, 1);
            this.$message({
                type: 'success',
                message: '删除成功!'
            });
            }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消删除'
            });          
            });
        }
      },
      mounted(){ 
        const user = JSON.parse(localStorage.getItem('user'));
        const student_id = parseInt(user.student_id);
        axios.get('http://localhost:8080/records', { 
        params: { 
          student_id: student_id
        } 
      })
      .then(response => {
        this.tableData = response.data;
      })
      .catch(error => {
       
        console.error(error);
      });
      }
    }
  </script>