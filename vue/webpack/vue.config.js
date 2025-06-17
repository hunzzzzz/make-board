const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  outputDir: "../../src/main/resources/static",
  devServer: {
    port: 8080,
    proxy: "http://localhost:8080",
    disableHostCheck: true,
  },
  transpileDependencies: true,
});
