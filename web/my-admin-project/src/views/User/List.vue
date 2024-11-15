<template>
    <div class="user-list">
      <el-table :data="users" style="width: 100%">
        <el-table-column prop="username" label="用户名"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="gender" label="性别"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <el-table-column label="操作">
        <template #default="scope">
          <el-button
            v-if="hasPermission('delete_user')"
            type="danger"
            @click="deleteUser(scope.row)"
          >
            删除用户
          </el-button>
        </template>
      </el-table-column>
      </el-table>
    </div>
</template>

<script>
export default {
  name: 'UserList',
  data() {
    return {
      users: [
        {
          username: 'user1',
          name: '张三',
          gender: '男',
          phone: '13812345678',
        },
        {
          username: 'user2',
          name: '李四',
          gender: '女',
          phone: '13987654321',
        },
        // more users...
      ],
    };
  },
  computed: {
    permissions() {
      return this.$store.state.user.permissions
    }
  },
  methods: {
    hasPermission(permission) {
      return this.permissions.includes(permission)
    },
    deleteUser() {
      // 删除用户的逻辑
    }
  }
};
</script>

<style>
  .user-list {
    padding: 20px;
  }
</style>
