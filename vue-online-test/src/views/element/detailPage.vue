<template>
    <el-container style="height: 100%; border: 1px solid #eee;width: 100%;">
        <el-header style="font-size:40px; background-color: rgb(238, 241, 246);text-align: center;">
            考试详细
        </el-header>
        <el-main>
            <el-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
                <el-table-column prop="studentId" label="学生Id" width="180" sortable>
                </el-table-column>
                <el-table-column prop="studentName" label="学生姓名" width="180">
                </el-table-column>
                <el-table-column prop="examName" label="考试名称" width="180">
                </el-table-column>
                <el-table-column prop="time" label="提交时间" width="180" sortable>
                </el-table-column>
                <el-table-column prop="state" label="考试状态" width="180">
                </el-table-column>
                <el-table-column prop="core" label="考试分数" width="180" sortable>
                </el-table-column>
            </el-table>
        </el-main>
    </el-container>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            tableData: []
        }
    },
    methods: {
        tableRowClassName({ row }) {
            // row 是当前行的数据对象
            if (row.state === '已完成') {
                return 'success-row';
            }
            return ''; // 默认情况下返回空字符串，不添加额外的 class
        }
    },
    // 在 mounted 钩子中
    mounted() {
        const examId = this.$route.params.examId;
        const studentId=this.$route.params.studentId
        console.log(examId);
        if (examId != null) {
            axios({
                method: 'get',
                url: 'http://localhost:4399/exam/getExamById',
                params: {
                    examId: examId  // 在 params 对象中传递参数
                }
            }).then(res => {
                this.tableData = res.data.data;
            })
        }else if(studentId!=null){
            axios({
                method: 'get',
                url: 'http://localhost:4399/exam/getExamByStudent',
                params: {
                    studentId:studentId  // 在 params 对象中传递参数
                }
            }).then(res => {
                this.tableData = res.data.data;
            })
        }

    }
}
</script>

<style>
.el-table .warning-row {
    background: oldlace;
}

.el-table .success-row {
    background: #f0f9eb;
}
</style>