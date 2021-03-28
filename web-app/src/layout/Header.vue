<template>
  <a-layout-header class="header" :style="{ background: '#fff', padding: 0}">
    <a-tooltip placement="bottom">
      <template slot="title">
        <span>刷新页面</span>
      </template>
      <a-button @click="clickReload" class="reload-btn" icon="reload" type="link">Reload</a-button>
    </a-tooltip>

    <a-tooltip placement="bottom" class="question-btn">
      <template slot="title">
        <span>About</span>
      </template>
      <a-button class="reload-btn" icon="question-circle" type="link"/>
    </a-tooltip>

    <a-dropdown>
      <a-button class="avatar-btn" type="link">
        <div class="avatar-btn-main">
          <a-avatar class="avatar"
                    :size="26"
                    src="https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png"/>
          <div class="avatar-btn-username">{{ details.username }}</div>
        </div>
      </a-button>
      <a-menu slot="overlay">
        <a-menu-item>
          <a-icon class="icon" type="smile"/>
          个人中心
        </a-menu-item>
        <a-menu-item>
          <a-icon class="icon" type="setting"/>
          系统设置
        </a-menu-item>
        <a-menu-divider/>
        <a-menu-item @click="handleLogout">
          <a-icon class="icon" type="logout"/>
          退出登录
        </a-menu-item>
      </a-menu>
    </a-dropdown>

  </a-layout-header>
</template>

<script>
export default {

  data() {
    return {
      details: this.$store.state.user.details ? this.$store.state.user.details : {}
    }
  },

  methods: {

    clickReload() {
      this.$router.go(0)
    },

    handleLogout() {
      let that = this
      this.$confirm({
        title: '你确定要注销你的登录信息吗?',
        content: '点击确定将删除你在网站保留的登录信息！',
        onOk() {
          that.$router.push('/login')
          that.$store.commit('user/userLogout')
        },
      });
    }

  }

}
</script>

<style scoped>

.reload-btn {
  font-size: 15px;
  margin-left: 10px;
  color: rgba(0, 0, 0, 0.65);
}

.question-btn {
  float: right;
  margin: 15px 12px 0 0;
}

.avatar-btn {
  float: right;
  font-size: 15px;
  color: rgba(0, 0, 0, 0.55);
  height: 64px;
}


.avatar-btn-main {
  display: flex;
}

.avatar-btn-username {
  line-height: 62px;
  font-size: 14px;
}

.avatar {
  margin-right: 12px;
  margin-top: 19px;
}

</style>