<template>
  <div style="padding-left: 480px;padding-top: 80px" id="background">
    <el-card style="width: 50%; " shadow="always">
      <div style="margin:10px">
        <div style="width: 100%; text-align:center;font-weight: bolder; color: lightseagreen" >个人信息</div>
      </div>
      <el-form ref="form" :model="form" label-width="90px ">
        <el-form-item style="text-align: center" label-width="0">

        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.sex" disabled></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" disabled></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" disabled></el-input>
        </el-form-item>

        <!--        <el-form-item label="密码">-->
        <!--          <el-input v-model="form.password" show-password></el-input>-->
        <!--        </el-form-item>-->

      </el-form>
      <div style="text-align: center">
        <el-button type="primary" @click="$router.push('/user_info_change')">修改信息</el-button>
        <el-button type="info" @click="$router.push('/wen_wu')">返回</el-button>
      </div>
    </el-card>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "user_info",
  data(){
    return {
      form: {}

    }
  },
  created() {
    let str1 = sessionStorage.getItem("user") || "{}"
    this.form = JSON.parse(str1)

  },
  methods: {

  }
}
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
#background{
  background:url("img/img.png");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;}
</style>
