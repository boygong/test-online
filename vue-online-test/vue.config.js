const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave : false,
  
  devServer:{
    port:4399,
    proxy: 'http://localhost:8080'
    

  },
})
