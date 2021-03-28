<template>
  <a-result status="403" title="403" sub-title="Sorry, you are not authorized to access this page.">
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
