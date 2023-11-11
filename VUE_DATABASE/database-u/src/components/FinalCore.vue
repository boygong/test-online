<template>
  <div class="container" ref="scrollContainer">
    <h1>你本次考试的成绩是 <span style="color: red;">{{ core }}</span> 分</h1>
    <div v-for="(question, index) in visibleList" :key="index" class="item-style">
      <div>
        <p>{{ index+1 +' . '+ question.content }}</p>
      </div>
      <div>
        <el-form label-position="top">
            <el-form-item>
              <el-radio-group v-model="results[index]">
                <el-radio :label="1">
                  <span>{{ question.type === 1 ? 'T.' : 'A.' }}</span>
                  <span>{{ question.opa }}</span>
                </el-radio>
              </el-radio-group>
            </el-form-item>
             <el-form-item>
              <el-radio-group v-model="results[index]" >
                <el-radio :label="2">
                  <span>{{ question.type === 1 ? 'F.' : 'B.' }}</span>
                  <span>{{ question.opb }}</span>
                </el-radio>
              </el-radio-group>
            </el-form-item>
              <el-form-item v-if="question.type === 1 ? false : true">
                  <el-radio-group v-model="results[index]">
                    <el-radio :label="3" >
                      {{  'C. ' + question.opc }}
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
              <el-form-item v-if="question.type === 1 ? false : true">
                <el-radio-group v-model="results[index]">
                  <el-radio :label="4"> 
                    {{ 'D. ' + question.opd }}</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-form>
      </div>
      <div>
        <span>我的答案: {{ getAnswerLetter(results[index]) }}</span>
        <span style="margin-left: 70%;">正确答案: {{ question.correctAnswer }}</span>
      </div>
      <el-backtop target=".container"></el-backtop>
    </div>
  </div>
</template>

<script>
export default {
  name:"finalCore",
  data() {
    return {
      formList: [], 
      visibleList: [],
      pageNo: 1,
      pageSize: 10, 
      isLoading: false, 
      results: [], 
      core:0,
      corrects:[]
    };
  },
  mounted() {
    this.formList = this.$route.query.param1;
    this.results = this.$route.query.param2;
    this.visibleList = this.formList.slice(0, this.pageSize); 
    this.$refs.scrollContainer.addEventListener('scroll', this.handleScroll);
    this.corrects = Array(this.formList.length).fill(null)
    for (let i = 0; i < this.formList.length; i++) {
        this.corrects[i] = this.formList[i].correctAnswer;
      }
      for (let i = 0; i < this.results.length; i++) {
        if(this.corrects[i] === String.fromCharCode(64 + this.results[i]) )
          {
            this.core +=10;
          }
        }
  },
  methods: {
    handleScroll() {
      const container = this.$refs.scrollContainer;
      const scrollHeight = container.scrollHeight;
      const scrollTop = container.scrollTop;
      const clientHeight = container.clientHeight;
      if (scrollHeight - scrollTop <= clientHeight && !this.isLoading) {
        this.loadMoreData();
      }
    },
    getAnswerLetter(value) {
      const options = ['A', 'B', 'C', 'D']; 
      return options[value-1]; 
    },
    loadMoreData() {
      this.isLoading = true;
      setTimeout(() => {
        const start = this.pageNo * this.pageSize;
        const end = start + this.pageSize;
        this.visibleList = [...this.visibleList, ...this.formList.slice(start, end)];
        this.pageNo++;
        this.isLoading = false;
      }, 500);
    },
  },
};
</script>

<style scoped>
.container {
  height: 600px;
  overflow-y: auto;
}

.item-style {
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 10px;
}
</style>
