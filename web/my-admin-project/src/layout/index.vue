<template>
    <div>
        <div class="side-bar">
            <el-menu :default-active="activeMenu" class="el-menu-vertical-demo" background-color="#545c64"
                text-color="#fff" active-text-color="#ffd04b" router>
                <template v-for="item in menus">
                    <el-submenu v-if="item.children && item.children.length" :index="item.menuId.toString()"
                        :key="item.menuId">
                        <template slot="title">
                            <i class="el-icon-menu"></i>
                            <span>{{ item.menuName }}</span>
                        </template>
                        <el-menu-item v-for="subItem in item.children" :key="subItem.menuId" :index="subItem.url">
                            <span>{{ subItem.menuName }}</span>
                        </el-menu-item>
                    </el-submenu>
                    <el-menu-item v-else :key="item.menuId" :index="item.url">
                        <i class="el-icon-menu"></i>
                        <span slot="title">{{ item.menuName }}</span>
                    </el-menu-item>
                </template>
                <el-menu-item index="logout" @click="logout">
                    <i class="el-icon-switch-button"></i>
                    <span slot="title">退出</span>
                </el-menu-item>
            </el-menu>

            <div class="main-content">
                <router-view />
            </div>
        </div>
    </div>
</template>

<script>
import { getUserMenus } from '@/api/menu'
import { logout } from "@/api/auth"
import { removeToken } from '@/utils/auth'

export default {
    data() {
        return {
            menus: [],
            activeMenu: ''
        }
    },
    created() {
        this.fetchMenus()
        this.activeMenu = this.$route.path
    },
    watch: {
        '$route.path'(newPath) {
            this.activeMenu = newPath
        }
    },
    methods: {
        fetchMenus() {
            getUserMenus().then(response => {
                this.menus = response
            }).catch(error => {
                console.error('获取菜单失败:', error)
            })
        },
        addRoutes(menus) {
            menus.forEach(menu => {
                if (menu.children && menu.children.length) {
                    this.addRoutes(menu.children)
                } else if (menu.url) {
                    const route = {
                        path: menu.url,
                        component: () => import(`@/views${menu.url}.vue`)
                    }
                    this.$router.addRoutes([route])
                }
            })
        },
        logout() {
            logout().then(() => {
                removeToken()
                this.$router.push('/login')
            }).catch(error => {
                console.error('退出失败:', error)
            })
        }
    }
}
</script>

<style lang="scss" scoped>
.side-bar {
    display: flex;
    height: 100vh;
}

.main-content {
    flex: 1;
    padding: 20px;
}
</style>