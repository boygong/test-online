<template>
    <el-container style="height: 100%; border: 1px solid #eee;width: 100%;">
        <el-header style="font-size:40px; background-color: rgb(238, 241, 246);text-align: center;">
            创建考试
        </el-header>
        <el-main>
            <el-button type="primary" icon="el-icon-arrow-left" @click="returnLastPage"
                style="margin-bottom: 20px;">上一页</el-button>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="考试名称" prop="examName" required>
                    <el-input v-model="ruleForm.examName"></el-input>
                </el-form-item>
                <el-form-item label="考试班级" prop="className" required>
                    <el-select v-model="ruleForm.className" placeholder="请选择考试班级">
                        <el-option label="软件1班" value=0></el-option>
                        <el-option label="软件2班" value=1></el-option>
                        <el-option label="软件3班" value=2></el-option>
                        <el-option label="软件4班" value=3></el-option>
                        <el-option label="软件5班" value=4></el-option>
                        <el-option label="软件6班" value=5></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="考试时间" required>
                    <el-col :span="11">
                        <el-form-item prop="date1">
                            <el-date-picker v-model="ruleForm.startTime" type="datetime"
                                placeholder="开始时间"></el-date-picker>
                            <el-date-picker v-model="ruleForm.endTime" type="datetime" placeholder="结束时间"></el-date-picker>
                        </el-form-item>
                    </el-col>
                </el-form-item>


                <el-form-item label="考试题目" required>
                    <!-- 选择题目表格 -->
                    <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
                        @selection-change="handleSelectionChange" @row-click="handleRowClick"
                        @select="handleSelectionChange" :row-key="rowKey">
                        <el-table-column type="selection" width="55" :reserve-selection="true"></el-table-column>
                        <el-table-column prop="id" label="序号" width="50"></el-table-column>
                        <el-table-column prop="content" label="题干" width="250"></el-table-column>
                        <el-table-column label="题目类型">
                            <template slot-scope="scope">
                                <span v-if="scope.row.type === 0">单选题</span>
                                <span v-if="scope.row.type === 1">判断题</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="opa" label="A选项"></el-table-column>
                        <el-table-column prop="opb" label="B选项"></el-table-column>
                        <el-table-column prop="opc" label="C选项"></el-table-column>
                        <el-table-column prop="opd" label="D选项"></el-table-column>
                        <el-table-column prop="currentAnswer" label="正确选项" width="50"></el-table-column>
                    </el-table>
                </el-form-item>

                <el-pagination :page-size="pageSize" layout="prev, pager, next" :total="PagerTotal"
                    @current-change="handlePageChange"></el-pagination>

                <div style="margin-top: 20px">
                    <el-button @click="toggleSelection()">取消选择</el-button>
                </div>
                <el-form-item label="老师id" prop="teacherId" required>
                    <el-input v-model="ruleForm.teacherId" disabled></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </el-container>
</template>

<script>
import { jwtDecode } from "jwt-decode";
import axios from 'axios';
export default {
    data() {
        return {
            pageSize: 10,
            PagerTotal: 16,
            ruleForm: {
                examName: '',
                className: 0,
                startTime: '',
                endTime: '',
                questions: [],
                teacherId: 1457
            },
            selectedQuestions: [], // 已选的题目
            rules: {
                examName: [
                    { required: true, message: '请输入考试名称', trigger: 'blur' },
                    { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
                ],
                className: [
                    { required: true, message: '请选择考试班级', trigger: 'change' }
                ],
                questions: [
                    {
                        validator: (rule, value, callback) => {
                            if (value.length !== 10) {
                                callback(new Error('题目数量必须为十条'));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur' // 或者 'change'
                    }
                ],
                startTime: [
                    { type: 'date', required: true, message: '请选择开始时间', trigger: 'change' },
                    {
                        validator: (rule, value, callback) => {
                            const endTime = this.ruleForm.endTime;
                            if (value && endTime && value >= endTime) {
                                callback(new Error('开始时间必须在结束时间之前'));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'change'
                    }
                ],
                endTime: [
                    { type: 'date', required: true, message: '请选择结束时间', trigger: 'change' },
                    {
                        validator: (rule, value, callback) => {
                            const startTime = this.ruleForm.startTime;
                            if (value && startTime && value <= startTime) {
                                callback(new Error('结束时间必须在开始时间之后'));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'change'
                    }
                ],
            },

            tableData: [],
        }
    },
    methods: {
        returnLastPage() {
            this.$router.push("/examPage");
        },
        rowKey(row) {
            return row.id;
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    const requestData = {
                        examName: this.ruleForm.examName,
                        className: this.ruleForm.className,
                        startTime: this.ruleForm.startTime,
                        endTime: this.ruleForm.endTime,
                        questions: this.ruleForm.questions,
                        teacherId: 1457 // 你的教师 ID，这里写死了一个示例值
                    };
                    console.log(requestData);
                    // 发送 POST 请求给后端
                    axios.post('http://localhost:4399/exam/insertExam', requestData)
                        .then(response => {
                            if (response.data.code === 1) {
                                console.log('Exam added successfully:', response.data);
                                this.$message({
                                    message: '插入成功',
                                    type: 'success',
                                });
                                this.$router.push('/examPage');
                            }
                            else{
                                this.$message({
                                    message: '插入失败',
                                    type: 'warning'
                                });
                            }
                            // 请求成功的处理

                        })
                        .catch(error => {
                            // 请求失败的处理
                            console.error('Error adding exam:', error);
                        });
                } else {
                    // 如果不存在 token，可以根据具体需求进行处理
                    console.log('localStorage 中不存在 token');
                }
            });
        },

        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },

        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        loadData(page) {
            const currentPage = page;
            const pageSize = this.pageSize;

            axios.get(`http://localhost:4399/question/getQuestion?currentPage=${currentPage}&pageSize=${pageSize}`)
                .then((res) => {
                    // Save the currently selected questions
                    const selectedIds = this.selectedQuestions.map(row => row.id);

                    this.tableData = res.data.data.source;

                    // Reset the selected state for the newly loaded data
                    this.tableData.forEach(row => {
                        if (selectedIds.includes(row.id)) {
                            this.$refs.multipleTable.toggleRowSelection(row, true);
                        }
                    });
                })
                .catch((error) => {
                    console.error('Error loading data:', error);
                });
        },


        handleRowClick(row) {
            // 如果选中的题目已经达到10个，不再允许选择其他题目
            if (this.selectedQuestions.length < 10 || this.isSelected(row)) {
                if (!this.isSelected(row)) {
                    this.selectedQuestions.push({ ...row });
                } else {
                    // 如果点击的题目已经被选中，取消选择
                    const index = this.selectedQuestions.findIndex(selectedRow => selectedRow.id === row.id);
                    if (index !== -1) {
                        this.selectedQuestions.splice(index, 1);
                    }
                }

                this.$refs.multipleTable.toggleRowSelection(row);
            }
            console.log("选择变化:" + (this.ruleForm.questions).length);
        },

        handleSelectionChange(selection) {
            // 将已选中的题目数量控制在 10 以内
            if (selection.length > 10) {
                const extraSelection = selection.slice(10);
                extraSelection.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            }

            // 将选中的题目赋值给 questions
            this.ruleForm.questions = selection;

            // 如果你希望在每次选中或取消选中时都更新 selectedQuestions，可以添加以下代码
            this.selectedQuestions = selection;
        },

        isSelected(row) {
            return this.selectedQuestions.some(selectedRow => selectedRow.id === row.id);
        },


        handlePageChange(page) {
            // 保存当前页的选中状态
            const currentPageSelection = this.tableData.filter(row => this.isSelected(row));

            // 加载新数据
            this.loadData(page);

            // 在数据加载后，重新设置选中状态
            this.$nextTick(() => {
                currentPageSelection.forEach(selectedRow => {
                    const foundRow = this.tableData.find(row => row.id === selectedRow.id);
                    if (foundRow) {
                        this.$refs.multipleTable.toggleRowSelection(foundRow, true);
                    }
                });
            });
            console.log("页面变化:" + (this.ruleForm.questions).length);
        },


    },
    mounted() {
        this.loadData(1);
        // 从 localStorage 中获取存储的 token
        const token = localStorage.getItem('token');

        // 判断是否存在 token
        if (token) {
            // 如果存在，你可以使用 token 进行相应的操作
            console.log('从 localStorage 中获取到的 token:', token);

            // 接下来可以对 token 进行解析等操作
            try {
                const decoded = jwtDecode(token);
                const id = decoded.id;
                console.log('解析后的用户ID:', id);
                this.ruleForm.teacherId = id;
                console.log("目前老师ID" + this.ruleForm.teacherId);
            } catch (error) {
                console.error('解析token时发生错误', error);
            }
        } else {
            // 如果不存在 token，可以根据具体需求进行处理
            console.log('localStorage 中不存在 token');
        }
    }
}
</script>