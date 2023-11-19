<template>
    <div class="teacher-login-container">
        <el-form :model="form" :rules="rules" ref="loginForm" label-width="80px">
            <el-form-item label="教师ID" prop="teacherId">
                <el-input v-model="form.teacherId" placeholder="请输入教师ID"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="login">登录</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
  
<script>
import axios from 'axios';
import { jwtDecode } from "jwt-decode";





export default {
    data() {
        return {
            form: {
                teacherId: '',
                password: '',
            },
            secretKey: 'itheima',
            rules: {
                teacherId: [
                    { required: true, message: '请输入教师ID', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ],
            },
        };
    },
    methods: {
        login() {
            axios({
                method: 'post',
                url: 'http://localhost:4399/teacher/teacherLogin',
                data: this.form
            }).then(res => {
                if (res.data.code === 1) {
                    const token = res.data.data;
                    console.log(token);
                    localStorage.setItem('token', token);
                    this.$message({ message: '登录成功', type: 'success' });

                    console.log("token", token);
                    try {
                        const decoded = jwtDecode(token);
                        const userId = decoded.id;
                        console.log("解析后的用户ID", userId);
                    } catch (error) {
                        console.error("解析token时发生错误", error);
                    }

                    this.$router.push('/teacherPage');
                } else {
                    this.$message({ message: '登录失败，账号或密码不正确', type: 'warning' });
                    console.log("登录失败");
                }

            })
            // 在这里处理教师登录逻辑，例如发送登录请求
            console.log('教师ID:', this.form.teacherId);
            console.log('密码:', this.form.password);
            // 你可以在这里添加登录逻辑，例如发送请求进行教师登录验证
        },
    },
};
</script>
  
<style scoped>
.teacher-login-container {
    max-width: 400px;
    margin: 50px auto;
}
</style>
  