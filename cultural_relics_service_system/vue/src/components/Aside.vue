<template>
  <el-row class="tac">
    <el-col >
<!--      <el-menu-->
<!--          style="width: 200px"-->
<!--          default-active="2"-->
<!--          class="el-menu-vertical-demo"-->
<!--      >-->
<!--        <el-sub-menu index="1">-->
<!--          <template #title>-->
<!--            <el-icon><location /></el-icon>-->
<!--&lt;!&ndash;            <span>功能1</span>&ndash;&gt;-->
<!--          </template>-->
<!--          <el-menu-item index="1-1" @click="$router.push('/wen_wu')">文物博览</el-menu-item>-->
<!--           <el-menu-item index="1-2" @click="$router.push('/time_line')">时间轴</el-menu-item>-->
<!--        </el-sub-menu>-->

<!--      </el-menu>-->

      <el-menu
          class="el-menu-vertical-demo"
          background-color="#F2F6FC"
          text-color="#409EFF"
          active-text-color="#67C23A">

        <el-menu-item index="1">
          <i class="el-icon-menu"></i>
          <span slot="title" @click="$router.push('/wen_wu')"><b>文物博览</b></span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span slot="title" @click="$router.push('/time_line')"><b>时间轴</b></span>
        </el-menu-item>


      </el-menu>
    </el-col>


  </el-row>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>