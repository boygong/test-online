<template>
    <div>
        <el-container style="height: 700px; border: 1px solid #eee;width: 100%;">
            <el-header style="font-size:40px; background-color: rgb(238, 241, 246)">线上考试系统教师端</el-header>
            <el-container>
                <el-aside width="200px"><el-menu :default-openeds="['1', '3']">
                        <el-submenu index="1">
                            <template slot="title"><i class="el-icon-message"></i>功能</template>
                            <el-menu-item-group>
                                <el-menu-item index="1-1">
                                    <router-link to="/teacherPage" exact
                                        style="font-size: large;color: black; text-decoration: none;">学生管理</router-link>
                                </el-menu-item>
                                <el-menu-item index="1-2">
                                    <router-link to="/questionPage" exact
                                        style="font-size: large;color: black; text-decoration: none;">题目管理</router-link>
                                </el-menu-item>
                                <el-menu-item index="1-3">
                                    <router-link to="/examPage" exact
                                        style="font-size: large;color: black; text-decoration: none;">考试管理</router-link>
                                </el-menu-item>
                            </el-menu-item-group>
                        </el-submenu>
                    </el-menu>
                </el-aside>
                <el-main>
                    <el-button style="float: right; margin-right: 150px;" type="primary" icon="el-icon-circle-plus-outline"
                        @click="openAddDialog">添加题目</el-button>

                    <el-dialog title="添加题目" :visible.sync="addDialogVisible">
                        <el-form :model="addFormData" label-width="100px">
                            <el-form-item label="题干">
                                <el-input v-model="addFormData.content" type="textarea" autosize></el-input>
                            </el-form-item>
                            <el-form-item label="题目类型">
                                <el-select v-model="addFormData.type" placeholder="请选择" :value-key="'value'">
                                    <el-option label="单选题" value=0></el-option>
                                    <el-option label="判断题" value=1></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="选项A">
                                <el-input v-model="addFormData.opa"></el-input>
                            </el-form-item>
                            <el-form-item label="选项B">
                                <el-input v-model="addFormData.opb"></el-input>
                            </el-form-item>
                            <el-form-item label="选项C">
                                <el-input v-model="addFormData.opc"
                                    :disabled="addFormData.type == 0 ? false : true"></el-input>
                            </el-form-item>
                            <el-form-item label="选项D">
                                <el-input v-model="addFormData.opd"
                                    :disabled="addFormData.type == 0 ? false : true"></el-input>
                            </el-form-item>
                            <el-form-item label="正确答案">
                                <el-select v-model="addFormData.currentAnswer" placeholder="请选择正确选项">
                                    <el-option label="A选项" value='A'></el-option>
                                    <el-option label="B选项" value='B'></el-option>
                                    <el-option label="C选项" value='C'
                                        :disabled="addFormData.type == 0 ? false : true"></el-option>
                                    <el-option label="D选项" value='D'
                                        :disabled="addFormData.type == 0 ? false : true"></el-option>
                                </el-select>
                            </el-form-item>
                            <!-- 其他表单项 -->

                            <el-form-item>
                                <el-button type="primary" @click="saveQuestionData">确定</el-button>
                                <el-button @click="closeAddDialog">取消</el-button>
                            </el-form-item>
                        </el-form>
                    </el-dialog>

                    <!--  编辑表单-->
                    <el-dialog title="编辑学生信息" :visible.sync="dialogFormVisible">
                        <el-form :model="editFormData" ref="editForm" label-width="100px">
                            <el-form-item label="题干">
                                <el-input v-model="editFormData.content" type="textarea" autosize></el-input>
                            </el-form-item>
                            <el-form-item label="题目类型">
                                <el-select v-model="editFormData.type" placeholder="请选择" :value-key="'value'">
                                    <el-option label="单选题" :value="0"></el-option>
                                    <el-option label="判断题" :value="1"></el-option>
                                </el-select>
                            </el-form-item>

                            <el-form-item label="A选项">
                                <el-input v-model="editFormData.opa" type="textarea" autosize></el-input>
                            </el-form-item>
                            <el-form-item label="B选项">
                                <el-input v-model="editFormData.opb" type="textarea" autosize></el-input>
                            </el-form-item>
                            <el-form-item label="C选项">
                                <el-input v-model="editFormData.opc" type="textarea" autosize
                                    :disabled="editFormData.type == 0 ? false : true"></el-input>
                            </el-form-item>
                            <el-form-item label="D选项">
                                <el-input v-model="editFormData.opd" type="textarea" autosize
                                    :disabled="editFormData.type == 0 ? false : true"></el-input>
                            </el-form-item>
                            <el-form-item label="正确选项">
                                <el-select v-model="editFormData.currentAnswer" placeholder="请选择正确选项">
                                    <el-option label="A选项" value='A'></el-option>
                                    <el-option label="B选项" value='B'></el-option>
                                    <el-option label="C选项" value='C'
                                        :disabled="editFormData.type == 0 ? false : true"></el-option>
                                    <el-option label="D选项" value='D'
                                        :disabled="editFormData.type == 0 ? false : true"></el-option>
                                </el-select>
                            </el-form-item>
                            <!-- 其他表单项 -->

                            <el-form-item>
                                <el-button type="primary" @click="modifyQuestionData()">确定</el-button>
                                <el-button type="info" @click="noChange">取消</el-button>
                            </el-form-item>
                        </el-form>
                    </el-dialog>


                    <el-table :data="questionData" style="width: 100%">
                        <el-table-column type="index" label="序号" width="50">
                        </el-table-column>
                        <el-table-column prop="content" label="题干" width="250">
                        </el-table-column>
                        <el-form-item label="题目类型">
                            <el-select v-model="questionData.type" placeholder="请选择">
                                <el-option label="单选题" :value="0"></el-option>
                                <el-option label="判断题" :value="1"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-table-column prop="opa" label="A选项">
                        </el-table-column>
                        <el-table-column prop="opb" label="B选项">
                        </el-table-column>
                        <el-table-column prop="opc" label="C选项">
                        </el-table-column>
                        <el-table-column prop="opd" label="D选项">
                        </el-table-column>
                        <el-table-column prop="currentAnswer" label="正确选项" width="50">
                        </el-table-column>
                        <el-table-column label="编辑题目信息" width="220">
                            <el-button slot-scope="scope" type="primary" @click="openEditDialog(scope.row)">编辑</el-button>
                        </el-table-column>
                    </el-table>

                    <el-pagination :page-size="pageSize" layout="prev, pager, next" :total="PagerTotal"
                        @current-change="handlePageChange"></el-pagination>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            questionData: [],
            pageSize: 10,
            PagerTotal: 16,
            addDialogVisible: false,
            addFormData: {
                // 添加表单的数据
                id: 0,
                content: '',
                type: 0,
                opa: '',
                opb: '',
                opc: '',
                opd: '',
                currentAnswer: ''
                // 其他表单项
            },
            dialogFormVisible: false,
            editFormData: {
                id: 0,
                content: '',
                type: 0,
                opa: '',
                opb: '',
                opc: '',
                opd: '',
                currentAnswer: ''
                // 其他表单项
            },
            currentQuestion: null,
            originalQuestionData: null
        }
    },
    methods: {
        handlePageChange(page) {
            const currentPage = page;
            const pageSize = this.pageSize;

            axios.get(`http://localhost:4399/question/getQuestion?currentPage=${currentPage}&pageSize=${pageSize}`)
                .then((res) => {
                    this.questionData = res.data.data.source;
                })
        },
        openAddDialog() {
            // 打开添加对话框
            this.addDialogVisible = true;
        },

        closeAddDialog() {
            // 关闭添加对话框
            this.addDialogVisible = false;
        },
        saveQuestionData() {
            // ... 其他逻辑
            axios({
                method: 'post',
                data: this.addFormData,
                url: 'http://localhost:4399/question/questionInsert'
            }).then(res => {
                if (res.data.msg == "success") {
                    this.$message.success("插入成功");
                } else {
                    this.$message.warning("插入失败");
                }
            })
            // 请求成功后关闭对话框
            this.dialogFormVisible = false;
            this.addDialogVisible = false;
        },
        //修改题目函数
        modifyQuestionData() {
            // 检查是否有修改
            const isModified = !this.isDataEqual(this.editFormData, this.originalQuestionData);
            if (!isModified) {
                // 没有修改，弹出提示或其他操作
                this.$message.warning('数据未修改，无需保存');
                return;
            }
            axios({
                method: 'put',
                data: this.editFormData,
                url: 'http://localhost:4399/question/modifyQuestion'
            }).then(res => {
                if (res.data.code == 0) {
                    this.$message.warning(res.data.meg)
                }
                else {
                    this.$message({
                        message: "数据修改成功",
                        type: 'success'
                    }),
                        axios({
                            method: 'get',
                            url: 'http://localhost:4399/question/getQuestion'
                        }).then(res => {
                            this.questionData = res.data.data.source;
                            console.log(this.questionData);
                        })
                }
            }
            )

            this.dialogFormVisible = false;
        },
        openEditDialog(question) {
            // 打开编辑对话框，并填充表单数据
            this.dialogFormVisible = true;
            this.editFormData = { ...question };
            this.originalQuestionData = { ...question }; // 存储原始数据
            this.currentQuestion = question;
        },
        isDataEqual(data1, data2) {
            // 比较两个数据对象是否相等
            // 这里可以根据你的数据结构进行比较
            return JSON.stringify(data1) === JSON.stringify(data2);
        },
        noChange() {
            //没有修改，点击取消之后的函数
            this.dialogFormVisible = false;
            this.$message("取消修改")
        }
    },
    mounted() {
        axios({
            method: 'get',
            url: 'http://localhost:4399/question/getQuestion'
        }).then(res => {
            this.questionData = res.data.data.source;
            console.log(this.questionData);
        })
    }
}
</script>

<style></style>