<template>
  <el-row class="tac">
    <el-col >
      <el-menu
          style="width: 200px"
          default-active="2"
          class="el-menu-vertical-demo"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <span>功能1</span>
          </template>
          <el-menu-item index="1-1" @click="$router.push('/wen_wu')">功能1-1(目前只有这个)</el-menu-item>
          <el-menu-item index="1-2" >功能1-2</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <el-icon><location /></el-icon>
            <span>功能2</span>
          </template>
          <el-menu-item index="2-1" >功能2-1</el-menu-item>
          <el-menu-item index="2-2" >功能2-2</el-menu-item>
        </el-sub-menu>

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