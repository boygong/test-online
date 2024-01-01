<template>
  <div class="wrapper">
    <div class="card-switch">
      <label class="switch">
        <input type="checkbox" class="toggle">
        <span class="slider"></span>
        <span class="card-side"></span>
        <div class="flip-card__inner">
          <div class="flip-card__front">
            <div class="title">Log in</div>
            <form class="flip-card__form">
              <input class="flip-card__input" v-model="id" name="id" placeholder="id">
              <input class="flip-card__input" v-model="password" name="password" placeholder="Password" type="password">
              <Button class="flip-card__btn" @click="ToMain">Let`s go!</Button>
            </form>
          </div>
          <div class="flip-card__back">
            <div class="title">Sign up</div>
            <form class="flip-card__form">
              <input class="flip-card__input" v-model="name" placeholder="Name">
              <input class="flip-card__input" v-model="classname" name="class_name" placeholder="软件x班">
              <input class="flip-card__input" v-model="Id1" name="student_id" placeholder="Id">
              <input class="flip-card__input" v-model="password1" name="password" placeholder="Password" type="password">
              <input class="flip-card__input" v-model="confirmPassword" name="confirm_password"
                placeholder="Confirm Password" type="password">
              <button class="flip-card__btn" @click="tosign">Confirm!</button>
            </form>
          </div>
        </div>
      </label>
    </div>
  </div>
</template>

<script>
import { Button, Loading } from 'element-ui';
import axios from 'axios';

export default {
  data() {
    return {
      id: null,
      password: null,
      name: null,
      classname: null,
      Id1: null,
      password1: null,
      confirmPassword: null
    };
  },
  methods: {
    handleBeforeUnload() {
      // 在用户离开页面之前执行清理操作
      localStorage.removeItem('user');
    },
    ToMain() {
      if (!this.id || !this.password) {
        this.$message({
          message: '请输入账号和密码',
          type: 'error'
        });
        return;
      }
      Loading.service();
      const userData = {
        studentId: this.id,
        password: this.password
      };
      axios.post('http://localhost:4399/admin', userData)
        .then(response => {
          console.log(response.data);
          console.log("response.data结束标记")
          const token = response.data;
          localStorage.setItem('token', token);
          this.$message({ message: '登录成功', type: 'success' });
          localStorage.setItem('user', JSON.stringify(userData));
          const user = JSON.parse(localStorage.getItem('user'));
          console.log(user.studentId);
          this.$router.push('/home/about');
        })
        .catch(() => {
          this.$message.error('出错啦~');
        })
        .finally(() => {
          Loading.service().close(); // 关闭加载状态
        });
    },

    tosign() {
      if (!this.name || !this.classname || !this.Id1 || !this.password1 || !this.confirmPassword) {
        this.$message({
          message: '请填写完整注册信息',
          type: 'error'
        });
        return;
      }
      if (this.password1 !== this.confirmPassword) {
        this.$message({
          message: '两次密码输入不一致',
          type: 'error'
        });
        return;
      }
      if (this.Id1 > 100000) {
        this.$message({
          message: 'id过长',
          type: 'error'
        });
        return;
      }
      if (this.class_name > 6 || this.class_name < 1) {
        this.$message({
          message: '班级序号有误',
          type: 'error'
        });
        return;
      }
      Loading.service();
      const userData = {
        studentId: this.Id1,
        password: this.password1,
        className: this.classname,
        name: this.name
      };
      axios.post('http://localhost:4399/sign', userData)
        .then(response => {
          console.log(response.data);
        })
        .catch(error => {
          if (error.response.status === 400) {
            console.log(error.response.data);
          }
          this.$message.error('出错啦~');
        })
        .finally(() => {
          Loading.service().close(); // 关闭加载状态
        });
    }
  },
  components: {
    Button
  },
  created() {
    window.addEventListener('beforeunload', this.handleBeforeUnload);
  },
  beforeDestroy() {
    window.removeEventListener('beforeunload', this.handleBeforeUnload);
    // localStorage.removeItem('token');
    // localStorage.removeItem('user');
  },
};
</script>

<style scoped>
.wrapper {
  --input-focus: #2d8cf0;
  --font-color: #323232;
  --font-color-sub: #666;
  --bg-color: #fff;
  --bg-color-alt: #666;
  --main-color: #323232;
  /* display: flex; */
  /* flex-direction: column; */
  /* align-items: center; */
  background-image: url("../assets/home.jpg");
  background-size: cover;
  background-position: center;
  width: 100%;
  height: 100vh;
}

.card-switch {
  padding-top: 15%;
  margin-left: 45%;
}

/* switch card */
.switch {
  transform: translateY(-200px);
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 30px;
  width: 50px;
  height: 20px;
}

.card-side::before {
  position: absolute;
  content: 'Log in';
  left: -70px;
  top: 0;
  width: 100px;
  text-decoration: underline;
  color: var(--font-color);
  font-weight: 600;
}

.card-side::after {
  position: absolute;
  content: 'Sign up';
  left: 70px;
  top: 0;
  width: 100px;
  text-decoration: none;
  color: var(--font-color);
  font-weight: 600;
}

.toggle {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  box-sizing: border-box;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: var(--bg-colorcolor);
  transition: 0.3s;
}

.slider:before {
  box-sizing: border-box;
  position: absolute;
  content: "";
  height: 20px;
  width: 20px;
  border: 2px solid var(--main-color);
  border-radius: 5px;
  left: -2px;
  bottom: 2px;
  background-color: var(--bg-color);
  box-shadow: 0 3px 0 var(--main-color);
  transition: 0.3s;
}

.toggle:checked+.slider {
  background-color: var(--input-focus);
}

.toggle:checked+.slider:before {
  transform: translateX(30px);
}

.toggle:checked~.card-side:before {
  text-decoration: none;
}

.toggle:checked~.card-side:after {
  text-decoration: underline;
}

/* card */

.flip-card__inner {
  width: 300px;
  height: 350px;
  position: relative;
  background-color: transparent;
  perspective: 1000px;
  /* width: 100%;
    height: 100%; */
  text-align: center;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

.toggle:checked~.flip-card__inner {
  transform: rotateY(180deg);
}

.toggle:checked~.flip-card__front {
  box-shadow: none;
}

.flip-card__front,
.flip-card__back {
  padding: 20px;
  position: absolute;
  display: flex;
  flex-direction: column;
  justify-content: center;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  background: lightgrey;
  gap: 20px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  box-shadow: 4px 4px var(--main-color);
}

.flip-card__back {
  width: 100%;
  transform: rotateY(180deg);
}

.flip-card__form {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
}

.title {
  margin: 20px 0 20px 0;
  font-size: 25px;
  font-weight: 900;
  text-align: center;
  color: var(--main-color);
}

.flip-card__input {
  width: 250px;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 15px;
  font-weight: 600;
  color: var(--font-color);
  padding: 5px 10px;
  outline: none;
}

.flip-card__input::placeholder {
  color: var(--font-color-sub);
  opacity: 0.8;
}

.flip-card__input:focus {
  border: 2px solid var(--input-focus);
}

.flip-card__btn:active,
.button-confirm:active {
  box-shadow: 0px 0px var(--main-color);
  transform: translate(3px, 3px);
}

.flip-card__btn {
  margin: 20px 0 20px 0;
  width: 120px;
  height: 40px;
  border-radius: 5px;
  border: 2px solid var(--main-color);
  background-color: var(--bg-color);
  box-shadow: 4px 4px var(--main-color);
  font-size: 17px;
  font-weight: 600;
  color: var(--font-color);
  cursor: pointer;
}</style>