<template>
  <div style="padding-left: 430px;padding-top: 120px" id="background">
    <el-card style="width: 50%; margin: 10px;">
      <div style="margin:10px">
        <div style="width: 100%; text-align:center;font-size:30px;font-weight: bolder;  padding:15px; color: lightseagreen" >欢迎登录</div>
      </div>
        <el-form ref="form" :model="form" label-width="80px" :rules="rules">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button style="width:40%" type="success" @click="$router.push('/register')">注册</el-button>

            <el-button style="width:40%" type="primary" @click="login">登录</el-button>
          </el-form-item>
        </el-form>
    </el-card>

  </div>
</template>




<script>
import {ElMessage} from "element-plus";
import request from "@/utils/request";

export default {
  name: "login",
  data() {
    return {
      form: {},
      rules:{
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'},
        ],
        password:[
          {required:true,message:'请输入密码',trigger:'blur'},
        ],
      }
      }

  },
  methods:{
    login(){
      this.$refs['form'].validate((valid)=>{
        if(this.form.username=='admin'&&this.form.password=='666666'){
          this.$router.push("/admin_web"),
              ElMessage({
                type: 'success',
                message: '登录成功',
              })
        }
        else if(valid){
          request.post("/user/login",this.form).then(res=>{
            if(res.code==0){
              ElMessage({
                type: 'success',
                message: '登录成功',
              })
              sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息
              this.$router.push("/wen_wu")
            }else{
              ElMessage({
                type: 'error',
                message: '用户名或密码错误',
              })
            }
          })
        }
      })

    }
  }
}
</script>

<style>
#background{
  background:url("img/img_1.png");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;}
</style>
