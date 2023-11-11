<template>
  <div>
    <div class="profile">
        <el-button @click="dialogFormVisible = true" type="text">
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </el-button>
      <el-dialog title="个人信息" :visible.sync="dialogFormVisible" width="30%">
              <div style="display: flex;">
          <el-form :model="form" style="flex: 1;">
            <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-input v-model="form.name" autocomplete="off" :disabled="true" style="width: 100px;"></el-input>
            </el-form-item>
            <el-form-item label="学生Id" :label-width="formLabelWidth">
              <el-input v-model="form.student_id" autocomplete="off" :disabled="true" style="width: 100px;"></el-input>
            </el-form-item>
            <el-form-item label="班级" :label-width="formLabelWidth">
              <el-input v-model="form.class_name" autocomplete="off" :disabled="true" style="width: 100px;"></el-input>
            </el-form-item>
          </el-form>
          <div style="flex: 1;">
            <el-form :model="form">
              <el-form-item label="个人头像" :label-width="formLabelWidth">
                <el-image
                    style="width: 100px; height: 100px"
                    :src="require(`@/assets/${form.image}`)"
                    :fit="fit"></el-image>
              </el-form-item>
            </el-form>
          </div>
        </div>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
          </div>
        </el-dialog>
    </div>
    <div class="main-div">
      <h1 style="left: 300px; position: relative;bottom: 20px;">在线考试步骤</h1>
      <el-steps :active="active" finish-status="success">
          <el-step title="步骤 1"></el-step>
          <el-step title="步骤 2"></el-step>
          <el-step title="步骤 3"></el-step>
        </el-steps>
        <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
    </div>
    <div class="div-bottom">
          <h2>
          {{ message }}
          </h2>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      message:"点击在线考试，查看我的考试",
      active: 0,
      gridData: [
          ],
        dialogTableVisible: false,
        dialogFormVisible: false,
        form: {
          name: null,
          class_name:null,
          student_id:null,
          image:null
        },
        formLabelWidth: '70px',
    };
  },
  methods :{
    next() {
        if (this.active++ > 2) this.active = 0;
        const content = ["点击在线考试，查看我的考试","选择未完成的考试，进入考试","完成考试，查看成绩"]
        this.message = content[this.active];
      }
  },
  mounted(){
    const user = JSON.parse(localStorage.getItem('user'));
        axios.get('http://localhost:8080/findAllData', { params: { student_id: parseInt(user.student_id) } })
            .then(response => {
            this.form = response.data;
            const image = this.form.image;
            this.form.image = (image === null ? '2.jpg' : image );
            console.log(response.data);
            })
            .catch(error => {
            console.error(error);
            this.$message.error('出错啦~');
            });
  }
};
</script>

<style scoped>
.profile{
  position: absolute;
  top: 10px;
  right: 15px;
}
.main-div{
  align-items: center;
  position: absolute;
  width: 690px;
  left: 520px;
  top: 100px;
}
.div-bottom{
  position: absolute;
  left: 600px;
  top: 290px;
}
</style>
