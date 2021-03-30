<template>
  <div class="main">
    <a-steps :current="0">
      <a-step title="填写销售信息" description="This is a description."/>
      <a-step title="配送申请" sub-title="Left 00:00:08" description="This is a description."/>
      <a-step title="Waiting" description="This is a description."/>
    </a-steps>
  </div>
</template>

<script>
export default {
  name: "Create"
}
</script>

<style scoped>
.main {
  padding: 30px;
  background: #ffffff;
}
</style>