const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,

  //其他配置
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:28080",
        changeOrigin: true,
        pathRewrite: {
          "^/api": "/api"
        }
      }
    }
  }

});