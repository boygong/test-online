<template>
  <div>
    <div class="profile">
        <el-button @click="dialogFormVisible = true" type="text">
          <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
        </el-button>
      <el-dialog title="个人信息" :visible.sync="dialogFormVisible" width="30%">
          <el-form :model="form">
            <el-form-item label="活动名称" :label-width="formLabelWidth">
              <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="活动区域" :label-width="formLabelWidth">
              <el-select v-model="form.region" placeholder="请选择活动区域">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
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
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px'
    };
  },
  methods :{
    next() {
        if (this.active++ > 2) this.active = 0;
        const content = ["点击在线考试，查看我的考试","选择未完成的考试，进入考试","完成考试，查看成绩"]
        this.message = content[this.active];
      }
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
