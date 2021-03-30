<template>
  <div class="login-box">
    <div>
      <div class="box-header">
        <img class="logo" src="../assets/logo.svg" alt=""/>
        <div class="box-header-t">Management</div>
      </div>
      <div class="title">Logistics Management 物流管理系统 - 管理员登录</div>
      <a-tabs @change="tabClick" default-active-key="1" :tabBarStyle="{ textAlign: 'center' }">
        <a-tab-pane key="1" tab="密码登陆">
          <a-input
              v-model="form.email"
              size="large"
              style="margin-top: 10px"
              class="input"
              placeholder="邮箱">
            <a-icon slot="prefix" type="mail"/>
          </a-input>
          <a-input-password
              v-model="form.password"
              size="large"
              class="input"
              placeholder="密码">
            <a-icon slot="prefix" type="lock"/>
          </a-input-password>
        </a-tab-pane>
        <a-tab-pane key="2" tab="验证码登陆" force-render>
          <a-input
              v-model="form.email"
              size="large"
              style="margin-top: 10px"
              class="input"
              placeholder="邮箱">
            <a-icon slot="prefix" type="mail"/>
          </a-input>
          <div style="display: flex">
            <a-input
                v-model="form.code"
                size="large"
                class="input"
                placeholder="验证码">
              <a-icon slot="prefix" type="safety-certificate"/>
            </a-input>
            <a-button class="code-btn" :loading="sendLoading" @click="sendEmail">
              获取验证码
            </a-button>
          </div>
        </a-tab-pane>
      </a-tabs>
      <div style="margin-bottom: 20px">
        <a-checkbox v-model="form.remember">自动登录</a-checkbox>
        <a-button type="link" style="margin-left: 158px">
          忘记密码 ?
        </a-button>
      </div>
      <a-button :loading="submitLoading" class="submit-btn" type="primary" @click="submitLogin">
        确认登陆
      </a-button>
      <div class="des">物流管理系统 - Copyright 2021 高元明</div>
    </div>
  </div>
</template>

<script>
import {AdminLogin, AdminSendEmail} from "@/api/admin";

export default {

  data() {
    return {
      sendLoading: false,
      submitType: '1', //1账号登录 2邮箱登录
      submitLoading: false,
      form: {
        password: '',
        email: '',
        code: '',
        remember: false,
      },
    }
  },

  methods: {

    sendEmail() {
      if (this.checkEmail()) {
        this.sendLoading = true
        AdminSendEmail(this.form.email).then((res) => {
          if (res.status) this.$message.success("验证码发送成功")
          this.sendLoading = false
        })
      }
    },

    submitLogin() {
      if (this.checkEmail()) {
        let type = this.submitType === '1' ? "passwrod" : "email"
        AdminLogin(type, this.form).then((res) => {
          console.log(res.data)
          if (res.status) {
            this.$store.commit('user/saveToken', res.data.token)
            this.$store.commit('user/saveLoginUser', res.data.admin)
            setTimeout(() => {
              this.$router.push("/home")
              this.submitLoading = false
            }, 700)
            this.$message.success("登陆成功")
          }
        })
      }
    },

    tabClick(key) {
      this.submitType = key
    },

    checkEmail() {
      const emailRegex = new RegExp('^\\w{3,}(\\.\\w+)*@[A-z0-9]+(\\.[A-z]{2,5}){1,2}$')
      if (!emailRegex.test(this.form.email)) {
        this.$message.error('请输入正确格式的邮箱');
        return false
      } else {
        return true
      }
    },

  }

}
</script>

<style scoped>

body {
  background: #000000 !important;
}

>>> .ant-tabs-bar {
  border-bottom: none !important;
}

>>> .ant-btn-primary {
  border-color: #5a84fd;
}

.login-box {
  width: 350px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.box-header {
  display: flex;
}

.box-header-t {
  font-weight: bolder;
  font-size: 30px;
}

.logo {
  width: 44px;
  height: 44px;
  margin-right: 20px;
  margin-left: 43px;
}

>>> .ant-tabs-nav {
  width: 350px;
}

>>> .ant-tabs-ink-bar {
  left: 52px;
}

>>> .ant-input-affix-wrapper .ant-input {
  font-size: 12px !important;
}

.title {
  color: #91949c;
  padding-top: 15px;
  padding-bottom: 35px;
  font-size: 13px;
  text-align: center;
}

.input {
  margin-bottom: 25px;
  font-size: 10px;
}

.code-btn {
  height: 40px;
  margin-left: 30px;
}

.submit-btn {
  letter-spacing: 2px;
  background: #5a84fd;
  width: 100%;
  height: 45px;
}

.des {
  padding-top: 25px;
  font-size: 13px;
  text-align: center;
  color: #91949c;
  letter-spacing: 2px;
}
</style>