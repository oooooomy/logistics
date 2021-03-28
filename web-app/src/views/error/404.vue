<template>
  <a-result status="404" title="404" sub-title="Sorry, the page you visited does not exist.">
    <template #extra>
      <a-button type="primary" @click="clickBtn" style="letter-spacing: 1px">
        <a-icon type="enter" />Return
      </a-button>
    </template>
  </a-result>
</template>
<script>
export default {
  data() {
    return {};
  },

  methods: {

    clickBtn() {
      this.$router.go(-1)
    },

  }

};
</script>
