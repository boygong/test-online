<template>
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
                <el-button style="float: right; margin-right: 500px;" type="primary" icon="el-icon-circle-plus-outline"
                    @click="openAddDialog">添加考试</el-button>
                <el-table :data="examTable" style="width: 100%">
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <el-form label-position="left" inline class="demo-table-expand">
                                <el-form-item label="考试id">
                                    <span>{{ props.row.id }}</span>
                                </el-form-item>
                                <el-form-item label="考试名称">
                                    <span>{{ props.row.examName }}</span>
                                </el-form-item>
                                <el-form-item label="发布老师id">
                                    <span>{{ props.row.teacherId }}</span>
                                </el-form-item>
                                <el-form-item label="发布老师">
                                    <span>{{ props.row.teacherName }}</span>
                                </el-form-item>
                                <el-form-item label="开始时间">
                                    <span>{{ props.row.startTime }}</span>
                                </el-form-item>
                                <el-form-item label="结束时间">
                                    <span>{{ props.row.endTime }}</span>
                                </el-form-item>
                                <el-form-item label="考试班级">
                                    <span>{{ getClassName(props.row.className) }}</span>
                                </el-form-item>
                            </el-form>
                        </template>
                    </el-table-column>
                    <el-table-column label="序号" type="index" sortable>
                    </el-table-column>
                    <el-table-column label="考试名称" prop="examName">
                    </el-table-column>
                    <el-table-column label="开始时间" prop="startTime" sortable>
                    </el-table-column>
                    <el-table-column label="查看详细">
                        <el-button type="primary">查看</el-button>
                    </el-table-column>
                </el-table>

            </el-main>
        </el-container>
    </el-container>
</template>


<script>
import axios from 'axios';

export default {
    data() {
        return {
            examTable: [],
            currentDate: new Date()
        };
    },
    methods: {
        openAddDialog() {
            this.$router.push("/insertExamPage");
        },
        getClassName(classCode) {
            const classMap = {
                0: "软件1班",
                1: "软件2班",
                2: "软件3班",
                3: "软件4班",
                4: "软件5班",
                5: "软件6班",
                6: "其他班级"
                // add more mappings as needed
            };
            return classMap[classCode] || "未知班级";
        },
    },

    mounted() {
        axios({
            method: 'get',
            url: 'http://localhost:4399/exam/getTeacherExam'
        }).then(res => {
            this.examTable = res.data.data;
            console.log(this.examTable);
        })
    }
}
</script>


<style>
.demo-table-expand {
    font-size: 0;
}

.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}

.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
}
</style>