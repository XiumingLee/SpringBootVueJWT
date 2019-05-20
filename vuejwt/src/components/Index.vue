<template>
  <div class="hello">
    <button @click="login()">登录</button>
    <button @click="test()">hello(不需要登录)</button>
    <button @click="test1()">测试1(need)</button>
    <button @click="test2()">测试2(need)</button>
    <button @click="logout()">注销登录</button>
    <hr>
    <div style="color:red;">{{msg}}</div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      msg: "Spring Boot和Vue整合JWT认证测试"
    };
  },
  methods: {
    login() {
      this.$http
        .post("/login", {
          username: 1,
          password: 1
        })
        .then(res => {
          // 登录成功
          console.log("登录成功！");
          console.log(res.data);
           /** 将Token保存到localStorage*/
          const authorization = res.data.Authorization;
          localStorage.token = authorization;
          this.msg = authorization;
        })
        .catch(error => {
          console.log("登录失败！");
          console.log(error);
          this.msg = error;
        });
    },
    test() {
      this.$http.get("/hello").then(res => {
        console.log(res);
        this.msg = res.data;
      });
    },
    test1() {
      this.$http.get("/test1").then(res => {
        console.log(res);
        this.msg = res.data;
      });
    },
    test2() {
      this.$http.get("/test2").then(res => {
        console.log(res);
        this.msg = res.data;
      });
    },
    logout(){
      this.$http.get("/logout").then(res => {
        console.log(res);
        localStorage.removeItem("token");
        this.msg = res.data;
      });
    }
  }
};
</script>

<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
