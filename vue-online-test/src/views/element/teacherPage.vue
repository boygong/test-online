<template>
    <div>
        <el-container style="height: 700px; border: 1px solid #eee;width: 2000px;">
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
                    <el-form :inline="true" :model="student" class="demo-form-inline">
                        <el-form-item label="学号">
                            <el-input v-model="student.studentId" placeholder="学生学号"></el-input>
                        </el-form-item>
                        <el-form-item label="姓名">
                            <el-input v-model="student.name" placeholder="学生姓名"></el-input>
                        </el-form-item>
                        <el-form-item label="班级">
                            <el-select v-model="student.className" placeholder="请选择">
                                <el-option v-for="item in className" :key="item.value" :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="findStudent">查询</el-button>
                        </el-form-item>
                    </el-form>




                    <el-dialog title="编辑学生信息" :visible.sync="dialogFormVisible">
                        <el-form :model="editFormData" ref="editForm" label-width="100px">
                            <el-form-item label="学号">
                                <el-input v-model="editFormData.studentId"></el-input>
                            </el-form-item>
                            <el-form-item label="姓名">
                                <el-input v-model="editFormData.name"></el-input>
                            </el-form-item>
                            <el-form-item label="班级">
                                <el-input v-model="editFormData.className"></el-input>
                            </el-form-item>
                            <el-form-item label="密码">
                                <el-input v-model="editFormData.password"></el-input>
                            </el-form-item>
                            <!-- 其他表单项 -->

                            <el-form-item>
                                <el-button type="primary" @click="saveStudentData">确定</el-button>
                                <el-button type="info" @click="noChange">取消</el-button>
                            </el-form-item>
                        </el-form>
                    </el-dialog>


                    <el-table :data="tableData" style="width: 100%">
                        <el-table-column prop="id" label="序号" width="220">
                        </el-table-column>
                        <el-table-column prop="studentId" label="学号" width="220">
                        </el-table-column>
                        <el-table-column prop="name" label="姓名" width="220">
                        </el-table-column>
                        <el-table-column prop="className" label="班级" width="220">
                            <template slot-scope="scope">
                                <span v-if="scope.row.className === 0">软件1班</span>
                                <span v-else-if="scope.row.className === 1">软件2班</span>
                                <span v-else-if="scope.row.className === 2">软件3班</span>
                                <span v-else-if="scope.row.className === 3">软件4班</span>
                                <span v-else-if="scope.row.className === 4">软件5班</span>
                                <span v-else-if="scope.row.className === 5">软件6班</span>
                                <span v-else>未知班级</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="password" label="学生密码" width="220">
                        </el-table-column>
                        <el-table-column label="查看学生考试信息" width="220">
                            <el-button slot-scope="scope" type="info" @click="searchDetail(scope.row.studentId)" plain>查看</el-button>
                        </el-table-column>
                        <el-table-column label="编辑学生信息" width="220">
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
            tableData: [],
            student: {
                studentId: '',
                name: '',
                className: ''
            },
            pageSize: 10,
            pagerCount: 10,
            PagerTotal: 10,
            dialogFormVisible: false,
            editFormData: {
                id: "",
                studentId: "",
                name: "",
                className: "",
                password: "",
                // 其他表单项
            },
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
            formLabelWidth: '120px',
            className: [{
                value: '',
                label: '空'
            }, {
                value: '0',
                label: '软件1班'
            }, {
                value: '1',
                label: '软件2班'
            }, {
                value: '2',
                label: '软件3班'
            }, {
                value: '3',
                label: '软件4班'
            }, {
                value: '4',
                label: '软件5班'
            }, {
                value: '5',
                label: '软件6班'
            }
            ],
            value: '',
            currentStudent: null,
            originalStudentData: null
        }
    },
    methods: {
        findStudent() {
            axios({
                method: 'get',
                url: 'http://localhost:4399/student/getStudentByInform',
                params: this.student,
            }).then(res => {
                this.tableData = res.data.data.students;
                console.log(this.tableData);
            })
        },
        handlePageChange(page) {
            const currentPage = page;
            const pageSize = this.pageSize;

            axios.get(`http://localhost:4399/student/getStudentByPage?page=${currentPage}&pageSize=${pageSize}`)
                .then((response) => {
                    this.tableData = response.data.data.source;
                })
        },
        openEditDialog(student) {
            // 打开编辑对话框，并填充表单数据
            this.dialogFormVisible = true;
            this.editFormData = { ...student };
            this.originalStudentData = { ...student }; // 存储原始数据
            this.currentStudent = student;
        },
        saveStudentData() {
            // 检查是否有修改
            const isModified = !this.isDataEqual(this.editFormData, this.originalStudentData);

            if (!isModified) {
                // 没有修改，弹出提示或其他操作
                this.$message.warning('数据未修改，无需保存');
                return;
            }

            // 在这里发送请求以保存修改后的学生数据
            axios({
                method: 'put',
                data: this.editFormData,
                url: 'http://localhost:4399/student/modifyStudent'
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
                            url: 'http://localhost:4399/student/getAllStudent'
                        }).then(res => {
                            this.PagerTotal = res.data.data.totalPage;
                            this.tableData = res.data.data.source;
                        }).catch(err => {
                            console.log(err)
                        })
                }

            })
            // 使用 this.currentStudent 访问当前学生数据
            // 你可以使用axios或其他HTTP库发送请求
            // 例如：axios.post('/api/updateStudent', this.editFormData)

            // 请求成功后关闭对话框
            this.dialogFormVisible = false;
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
        },
        searchDetail(studentId){
            console.log(studentId);
            this.$router.push({name:'detailPage',params:{studentId:studentId}});
        }
    },
    mounted() {
        axios({
            method: 'get',
            url: 'http://localhost:4399/student/getAllStudent'
        }).then(res => {
            this.PagerTotal = res.data.data.totalPage;
            this.tableData = res.data.data.source;
        }).catch(err => {
            console.log(err)
        })
    }
}
</script>

<style>


</style>