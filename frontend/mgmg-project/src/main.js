import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import CustomButton from "@/components/common/CustomButton.vue";
import CustomInput from "@/components/common/CustomInput.vue";
import vueCookies from "vue-cookies";
import AOS from "aos";
import "aos/dist/aos.css";

Vue.config.productionTip = false;

Vue.component("CustomButton", CustomButton);
Vue.component("CustomInput", CustomInput);

Vue.use(vueCookies);
Vue.$cookies.config("15d");

new Vue({
  created() {
    AOS.init({
      duration: 1200,
      mirror: false,
    });
  },
  router,
  store,
  vuetify,
  CustomInput,
  CustomButton,
  render: (h) => h(App),
}).$mount("#app");
