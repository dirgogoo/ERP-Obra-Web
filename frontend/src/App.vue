<template>
  <div id="app">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Alexandria:wght@100..900&display=swap" rel="stylesheet">
    <v-app>
      <router-view />
    </v-app>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
*{
  margin: 0;
  padding: 0;
  font-family: "Alexandria", serif;
  font-optical-sizing: auto;
  font-weight: 400;
  font-style: normal;
  overflow: hidden;
}

#app{
  height: 100vh;
  font-size: 0.96em;
}
</style>
