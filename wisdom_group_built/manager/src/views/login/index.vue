<template>
  <div class="logo">
    <div class="login-container">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" auto-complete="on" label-position="left">
        <div class="backgroundstyle"/>
        <h3 class="title">智慧团建管理系统</h3>
        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input v-model="loginForm.username" name="username" type="text" auto-complete="on" placeholder="username" />
        </el-form-item>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            :type="pwdType"
            v-model="loginForm.password"
            name="password"
            auto-complete="on"
            placeholder="password"
            @keyup.enter.native="handleLogin" />
          <span class="show-pwd" @click="showPwd">
            <svg-icon icon-class="eye" />
          </span>
        </el-form-item>
        <el-form-item class="sign">
          <el-button :loading="loading" type="primary" style="width:100%;" @click.native.prevent="handleLogin">
            登录
          </el-button>
        </el-form-item>
        <div class="tips">
          <!-- <span style="margin-left:50px;font-size:18px"><a target="_blank" href="..#/forgetPwd/index">忘记密码</a>&nbsp;&nbsp;&nbsp;|</span>
          <span style="font-size:18px"><a target="_blank" href="http://www.365yg.com/i6626998678076801539/#mid=1614444196347912">宣传片</a></span> -->
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { isvalidUsername } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error('请输入正确的用户名'))
      } else {
        callback()
      }
    }
    const validatePass = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: 'admin',
        password: 'admin'
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePass }]
      },
      loading: false,
      pwdType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('Login', this.loginForm).then(() => {
            this.loading = false
            this.$router.push({ path: this.redirect || '/' })
          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
$bg:#2d3a4b;
$light_gray:#eee;
.logo{
background:url("../../assets/bcd_2.jpg") 0 0 no-repeat;
background-size: 100% 100%;
height: 100%;
}
/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    input {
      background: transparent;
      border: 1px;
      border-radius: 5px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: rgb(26, 62, 98);
      background-color: #F2EFE8;
      height: 47px;
      &:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: #fff !important;
      }
    }
  }
  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(31, 21, 21, 0.1);
    border-radius: 5px;
    // color: #6e2323;
  }
}

</style>

<style rel="stylesheet/scss" lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;
.login-container {
  position: fixed;
  height: 100%;
  width: 100%;
  // background-color: $bg;
  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 520px;
    max-width: 100%;
    padding: 35px 35px 15px 35px;
    margin: 120px auto;
  }
  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;
    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }
  .svg-container {
    padding: 6px 5px 6px 15px;
    color: black;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }
  // .el-input{
  //   background-color: #FFFF;
  // }
  .title {
    font-size: 26px;
    font-weight: 400;
    color: #FFFFFF;
    margin: 0px auto 40px auto;
    text-align: center;
    font-weight: bold;
  }
  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
  .backgroundstyle{
    position: absolute;
    background-color: #FFFFFF;
    margin-left:-35px;
    margin-top:-10px;
    width: 520px;
    height: 300px;
    opacity: 0.5;
  }
</style>
