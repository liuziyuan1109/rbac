<template>
  <div class="success-page">
    <el-main>
      <div class="content">
        <h1>登录成功！</h1>
        <p>欢迎，{{ username }}！</p>
      </div>
    </el-main>
  </div>
</template>

<script>
import { getInfo } from "@/api/auth.js"
import { getAccessToken, removeToken } from "@/utils/auth.js"

export default {
  name: "HomeView",
  data() {
    return {
      username: ""
    }
  },
  created() {
    this.$store.dispatch('user/fetchUserInfo')
    const token = getAccessToken()
    if (!token) {
      // 如果没有 token，跳转到登录页面
      this.$router.push({ path: '/login' })
    } else {
      // 请求用户信息
      getInfo().then((res) => {
        if (res.username) {
          this.username = res.username
        } else {
          this.$router.push({ path: '/login' })
        }
      }).catch(() => {
        this.$router.push({ path: '/login' })
      })
    }
  },
  methods: {
    handleLogout() {
      
      removeToken() // 清除token
      this.$router.push({ path: '/login' }) // 跳转到登录页面
    }
  }
}
</script>

<style scoped>
.success-page {
  height: 100%;
}

.content {
  margin: 20px;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>
