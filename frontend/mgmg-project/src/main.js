import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import CustomButton from "@/components/common/CustomButton.vue";
import CustomInput from "@/components/common/CustomInput.vue";
import vueCookies from "vue-cookies";
Vue.config.productionTip = false;

Vue.component("CustomButton", CustomButton);
Vue.component("CustomInput", CustomInput);

Vue.use(vueCookies);
Vue.$cookies.config("15d");

new Vue({
  router,
  store,
  vuetify,
  CustomInput,
  CustomButton,
  render: (h) => h(App),
}).$mount("#app");
