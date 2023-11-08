<template>
    <div>
      <div>
        <h2 style="text-align: center;">考试须知</h2>
        <div class="main">
          <span>1.考前三十分钟，考生需持符合报考规定的并与准考证显示信息一致的有效证件，进入规定的考场。</span>
          <span>2.考试期间考生可携带物品:签字笔、铅笔、橡皮、二十四色彩笔（包含黑色、蓝色、棕色、绿色、灰色、橙色、粉色、紫色、红色、黄色）铅笔盒、塑料瓶装水、药品、纸巾和准考证。</span>
          <span>3.考试期间需放在指定区域的禁止携带物品：处于关闭状态下的手机、相机或任何其他电子产品、字典、笔记本、修正液/修正带等、纸张、书包、手提包、行李箱。</span>
          <span>4.考场内不得相互借用文具。严禁在考场内饮食。</span>
          <span>5.考生入场后，按号入座，将本人《准考证》放在课桌上，以便核验。</span>
          <span>6.统一开考信号发出后才能开始答题。</span>
          <span>7.笔试开考10分钟内,允许迟到考生进入考场参加考试,考试结束时间按照统一规定结束。开考10分钟后,禁止迟到考生入场考试。提前离场考生离场后不得进场续考。考试最后十分钟内考生不得离场。</span>
          <span>8.考生领到试卷后，必须先在指定位置准确，清楚地填写姓名，准考证号，座位号等栏目。</span>
          <span>9.考生遇试卷分发错误及试题字迹不清等问题可举手询问，监考人员应当众答复；涉及试题内容的疑问，不得向监考人员询问。</span>
          <span>10.考生在考场内必须保持安静，严格遵守考场纪律，不准交头接耳，左顾右盼；不准偷看，抄袭他人答案；不准夹带，冒名或换卷，对于违反考场规则，不服从监考人员管理和舞弊者按违反考场规则处理，情节严重的将取消本次考试资格或限期停考。</span>

        </div>
        <el-checkbox v-model="checked" label="1" style="left: 40%;">我已阅读以上内容并同意以上要求</el-checkbox><br>
        <el-button type="primary" style="margin-left: 45%;width: 12%; margin-top: 10px;" @click="toPage">进入考试</el-button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'ReadTest',
    data() {
      return {
        checked: false,
        name:null,
        student_id:null
      }
    },
    methods: {
      toPage() {
        const name = this.name;
        const student_id = this.student_id;
        if (this.checked === true) {
          this.$router.push({name:'OnlineTestView',params:{ param1: name, param2: student_id }});
        } else {
          this.$message({
          message: '请勾选同意条款才可进入考试界面！',
          type: 'warning'
        });
      }
    }
  },
  mounted(){
    this.name= this.$route.params.param1;
    this.student_id = this.$route.params.param2;
  }
}
  </script>
  
  <style>
  .main {
    display: flex;
    flex-direction: column;
    width: 65%;
    margin-left: 17%;
    margin-top: 1%;
  }
  .main span {
    margin-bottom: 10px; /* 设置段落之间的底部间距为10像素 */
  }
  </style>
  