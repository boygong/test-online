<template>
  <div id="app">
    <el-container >
      <el-header style="text-align: right; font-size: 30px;display: flex;flex-direction: column;" class="header">
        <div class="time">
          <span>考试还剩: {{ hours }} 小时 {{ minutes }} 分钟 {{ seconds }} 秒</span>
        </div>
      </el-header>
      <el-main>
        <div class="question-grid">
          <div class="question">
            <div style="width: 90%; height: 100px; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
              <span>{{ this.number+1 + '. '}}</span>
              <span>{{ this.type===0 ? '（选择题）' :'（判断题）' }}</span>
              <span>{{  formList[number].content }}</span>
            </div>
            <el-form label-position="top">
              <el-form-item>
              <el-radio-group v-model="this.selectedOptions[this.number]">
                <el-radio :label="1" @change="change(1)">
                  <span>{{ this.type === 1 ? 'T.' : 'A.' }}</span>
                  <span>{{ formList[number].opa }}</span>
                </el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item>
              <el-radio-group v-model="this.selectedOptions[this.number]">
                <el-radio :label="2" @change="change(2)">
                  <span>{{ this.type === 1 ? 'F.' : 'B.' }}</span>
                  <span>{{ formList[number].opb }}</span>
                </el-radio>
              </el-radio-group>
            </el-form-item>
              <el-form-item v-if="this.type === 1 ? false : true">
                  <el-radio-group v-model="this.selectedOptions[this.number]">
                    <el-radio :label="3" @change="change(3)">
                      {{  'C. ' + formList[number].opc }}
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
              <el-form-item v-if="this.type === 1 ? false : true">
                <el-radio-group v-model="this.selectedOptions[this.number]">
                  <el-radio :label="4" @change="change(4)"> 
                    {{ 'D. ' + formList[number].opd }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-form>
            <div>
              <el-button @click="lastpage" v-show="lastbutton">上一题</el-button>
              <span style="left: 32%; position: relative;" v-show="num_">{{ (number + 1) + '/' + formList.length }}</span>
              <el-button @click="nextpage" v-show="nextbutton" style="left: 55%; position: relative;">下一题</el-button>
              <el-button v-show="submit" @click="open" style="left: 32%; position: relative;">提交</el-button>
            </div>
          </div>
          <el-aside style="width: 20%;">
            <div class="row">
              <div v-for="index in length" :key="index" class="col-sm-4" style="display: inline-block; width: 20%; margin-right: 3%;">
                <el-button :type="selectedOptions[index-1] === null ? ' ' : 'primary'" @click="goToQuestion(index)" style="margin-bottom: 10px; width: 100%;">
                  {{ index }}
                </el-button>
              </div>
            </div>
          </el-aside>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name:"OnlineTestView",
  data() {
    return {
      formList: [
        
      ],
      selectedOptions: [],
      type:null,
      number: 0,
      lastbutton: true,
      nextbutton: true,
      num_: true,
      submit: false,
      totalSeconds: 3600, 
      hours: 0,
      minutes: 0,
      seconds: 0,
      whetherto:true,
      showModal: false,
      name:null,
      student_id:null,
      length:null,
      corrects:[]
    };
  },
  created() {
    this.startCountdown();
  },
  mounted(){
    this.whetherto = false;
    this.name= this.$route.params.param1;
    this.student_id = this.$route.params.param2;
    axios.get('http://localhost:8080/testPage', { 
        params: { 
          name: this.name,
          student_id: this.student_id
        } 
      })
      .then(response => {
        
        this.formList = response.data;
        console.log(response.data);
        this.length = this.formList.length;
        this.type = this.formList[0].type;
        this.selectedOptions =  Array(this.length).fill(null);
        this.corrects = Array(this.length).fill(null)
      })
      .catch(error => {
       
        console.error(error);
      });
  },
  methods: {
    compareArraysAndIncrement(arr1, arr2) {
      for (let i = 0; i < this.formList.length; i++) {
        this.corrects[i] = this.formList[i].correctAnswer;
      }
        let result = 0;

        for (let i = 0; i < arr1.length; i++) {
          if(arr2[i] === String.fromCharCode(64 + arr1[i]) )
          {
            result +=10;
          }
        }
        return result;
      },
      addRecord() {
          const core = this.compareArraysAndIncrement(this.selectedOptions, this.corrects);
          const time = new Date().toISOString()
          const name = this.name;
          const student_id = this.student_id;

          const recordData = {
            core,
            time,
            name,
            student_id
          };
          axios.post('http://localhost:8080/addRecords', recordData)
            .then(response => {
              console.log('Record added:', response.data);
            })
            .catch(error => {
              console.error('Failed to add record:', error.response.data);
            });
        }
        ,
        changeState(){
      const state = "已完成"; 
      const student_id = this.student_id; 
      const name = this.name; 

      axios.put('http://localhost:8080/update', null, { 
        params: {
          state: state,
          student_id: student_id,
          name: name
        }
      })
      .then(response => {
        console.log('更新成功', response.data);
      })
      .catch(error => {
        console.error('更新失败', error);
      });
    },
    goToQuestion(index) {
    this.number = index-1;
    this.type = this.formList[this.number].type;

    },
    lastpage: function () {
      if (this.number === 0) {
        this.lastbutton = false;
        this.num_ = false;
        this.submit = false;
      } else {
        this.number--;
        this.nextbutton = true;
        this.num_ = true;
        this.submit = false;
      }
      this.type = this.formList[this.number].type;
    },
    handleConfirm() {
      // 处理确定按钮的点击事件
      console.log('确定按钮被点击');
      // 关闭弹窗
      this.showModal = false;
    },
    nextpage: function () {
      if (this.number === this.formList.length - 1) {
        this.nextbutton = false;
        this.num_ = false;
        this.submit = true;
      } else {
        ++this.number;
        this.lastbutton = true;
        this.num_ = true;
        this.submit = false;
      }
      this.type = this.formList[this.number].type;
    },
    change: function (value) {
      this.selectedOptions[this.number] = value; 
    },
    open() {
      this.$confirm('确定提交吗？, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '提交成功!'
        });
        this.whetherto = true;
        const formList = this.formList;
        const selectedOptions = this.selectedOptions;
        this.$router.push({ name: 'finalCore', query: { param1: formList, param2: selectedOptions } });
        this.changeState();
        this.addRecord();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消提交'
        });
      });
    },
    startCountdown() {
      this.countdownTimer = setInterval(() => {
        if (this.totalSeconds > 0) {
          this.totalSeconds--; 
          this.hours = Math.floor(this.totalSeconds / 3600);
          this.minutes = Math.floor((this.totalSeconds % 3600) / 60);
          this.seconds = this.totalSeconds % 60;
        } else {
          clearInterval(this.countdownTimer);
        }
      }, 1000);
    },
    beforeDestroy() {
    clearInterval(this.countdownTimer);
  },
  },
  beforeRouteLeave(to, from, next) {
    if (this.whetherto === false) {
      next(false);
      this.$message({
          message: '请提交试卷后再离开考试界面！',
          type: 'warning'
        });
    } else {
      next();
    }
  }
};
</script>

<style scoped>
.question-grid {
    display: flex;
    justify-content: space-between;
  }

  .question {
    left: 20%;
    width: 75%;
  }
   .el-header {
    background-color: wheat;
    color:white ;
    line-height: 60px;
  }
  .div-main{
      margin-top: -60px;
  }
  .time{
    display: flex;
    flex-direction: column;
    margin-right: 400px;
    margin-top: 0px;
  }
</style>
