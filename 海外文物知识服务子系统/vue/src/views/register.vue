<template>
  <div style="padding-left: 430px;padding-top: 120px" id="background">
    <el-card style="width: 50%; margin: 10px;">
      <div style="margin:10px">
        <div style="width: 100%; text-align:center;font-size:30px;font-weight: bolder;  padding:15px; color: lightseagreen" >欢迎注册</div>
      </div>
      <el-form ref="form" :model="form" label-width="80px" :rules="rules">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirm">
          <el-input v-model="form.confirm" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width:40%" type="success" @click="register">注册</el-button>
          <el-button style="width:40%" type="primary" @click="$router.push('/login')">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>

  </div>
</template>



<!--<template>-->
<!--  <div style="width:100%;height:100vh;overflow:hidden" id="background">-->
<!--    <div style="width:400px;margin:150px auto">-->
<!--      <div style="color:black;font-size:30px;text-align: center;padding:30px 0">用户注册</div>-->
<!--      <el-form ref="form" :model="form" label-width="120px" :rules="rules">-->
<!--        <el-form-item label="用户名" prop="username">-->
<!--          <el-input v-model="form.username"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="密码" prop="password">-->
<!--          <el-input v-model="form.password" show-password></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="确认密码" prop="confirm">-->
<!--          <el-input v-model="form.confirm" show-password></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item>-->
<!--          <el-button style="width:40%" type="success" @click="register">注册</el-button>-->
<!--          <el-button style="width:40%" type="primary" @click="$router.push('/login')">登录</el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </div>-->

<!--  </div>-->
<!--</template>-->

<script>
import {ElMessage} from "element-plus";
import request from "@/utils/request";

export default {
  name: "register",
  data(){
    return{
      form:{},
      rules:{
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'},
        ],
        password:[
          {required:true,message:'请输入密码',trigger:'blur'},
        ],
        confirm:[
          {required:true,message:'请确认密码',trigger:'blur'},
        ],
      }
    }
  },
  methods:{
    register(){
      if(this.form.password!==this.form.confirm){
        ElMessage({
          type: 'error',
          message: '两次密码输入不一致',
        })
        return
      }

      this.$refs['form'].validate((valid)=> {

        if(valid){
          request.post("/user/register",this.form).then(res=>{
            if(res.code==0) {
              ElMessage({
                type: 'success',
                message: '注册成功',
              })
              this.$router.push("/login")
            }
            else{
              ElMessage({
                type: 'error',
                message: '用户名已被注册',
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