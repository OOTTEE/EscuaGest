<template>
  <v-theme-provider class="pa-10">
    <v-layout full-height>
      <v-container fluid class="h-screen w-auto">
        <v-card id="login" >
          <v-card-title>Login</v-card-title>
          <v-card-content>
            <v-text-field
              label="username"
              placeholder="Username"
              variant="outlined"
            ></v-text-field>
            <v-text-field
              label="password"
              placeholder="Password"
              variant="outlined"
            ></v-text-field>
          </v-card-content>
          <v-card-actions class="justify-end">
            <v-btn variant="outlined" class="mr-4 mb-4">Login</v-btn>
          </v-card-actions>
        </v-card>
      </v-container>
    </v-layout>
  </v-theme-provider>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "LoginLayout",
});
</script>

<style>
#login {
  width: 20pc;
}
</style>
