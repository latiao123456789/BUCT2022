<template>
  <div style="padding-left: 480px;padding-top: 80px" id="background">
    <el-card style="width: 40%" shadow="always">
      <div style="margin:10px">
        <div style="width: 100%; text-align:center;font-weight: bolder;color: lightseagreen" >个人信息修改</div>
      </div>
      <el-form ref="form" :model="p_form" label-width="80px">
        <el-form-item style="text-align: center" label-width="0">

        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="p_form.username" ></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="p_form.sex" label="男" size="large">男</el-radio>
          <el-radio v-model="p_form.sex" label="女" size="large">女</el-radio>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="p_form.age"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="p_form.address"></el-input>
        </el-form-item>

        <!--        <el-form-item label="密码">-->
        <!--          <el-input v-model="form.password" show-password></el-input>-->
        <!--        </el-form-item>-->

      </el-form>
      <div style="padding-left: 90px">
        <el-button type="primary" @click=update>保存</el-button>
        <el-button type="info" @click="$router.push('/wen_wu')">返回</el-button>
      </div>
    </el-card>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "user_info_change",
  data(){
    return {
      p_form: {}

    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    this.p_form = JSON.parse(str)
    name=this.p_form.username
    console.log(name)
    console.log(name)

  },
  methods: {
    update() {
      request.put("/user", this.p_form).then(res => {
        console.log(res)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "更新成功"
          })
          sessionStorage.setItem("user", JSON.stringify(this.p_form))
          // 触发Layout更新用户信息
          this.$emit("userInfo")
          this.$router.push('/wen_wu')
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
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
