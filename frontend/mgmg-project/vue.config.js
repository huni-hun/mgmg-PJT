const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  devServer: {
    host: "0.0.0.0",
    port: 8080,
    open: true,
    allowedHosts: "all",
    client: {
      webSocketURL: "wss://j7b306.p.ssafy.io:443/ws",
    },
    headers: {
      "Access-Control-Allow-Origin": "*",
    },
  },
});
