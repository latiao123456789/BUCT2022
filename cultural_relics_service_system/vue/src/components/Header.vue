<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 200px; padding-left: 30px; font-weight: bolder; color: dodgerblue">海外文物知识服务系统</div>

    <div style="flex: 1"></div>

    <div style="width:100px">
      <el-dropdown>
      <span class="el-dropdown-link" style="width: 200px; padding-left: 30px; font-weight: bold; color: black">
        {{  p_form.username }}

        <!--        用户-->
        <el-icon class="el-icon--right">
          <arrow-down />
        </el-icon>
      </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/user_info_change')">修改信息</el-dropdown-item>
            <!--            <el-dropdown-item @click=infoEdit>修改信息</el-dropdown-item>-->
            <el-dropdown-item @click="$router.push('/user_info')">查看个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>


      </el-dropdown>
    </div>



  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      // form对象属性跟数据库中一一对应，弹窗表格引进来的
      form: {},
      p_form:{},
      dialogVisible: false,
    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    this.p_form = JSON.parse(str)

  },
  methods:{
    infoEdit(){
      this.dialogVisible=true
    }
  }
}
</script>

<style scoped>

</style>