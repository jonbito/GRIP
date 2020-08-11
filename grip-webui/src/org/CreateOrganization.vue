<template>
  <div>
    <div class="pa-4 mb-5" style="border-bottom: 1px solid #ededed;">
      Selected plan: <strong>Free</strong>
    </div>
    <v-responsive class="mx-auto" style="max-width: 1024px;">
      <div class="text-center my-8 mb-12">
        <h2 class="text-h2">Set up your team</h2>
      </div>
    </v-responsive>
    <v-responsive class="mx-auto" style="max-width: 544px;">
      <validation-observer v-slot="{ handleSubmit, debouncing, invalid }">
        <form @submit.prevent="handleSubmit(submit)">
            <h3 class="mb-1">Organization account name <span class="red--text">*</span></h3>
            <validation-provider v-slot="{ errors, debouncing, passed }" :debounce="1000" name="Project Name" rules="required|alpha_num_dash_space|org_url_available">
              <v-text-field
                  outlined
                  dense
                  v-model="name"
                  :error-messages="errors"
                  :loading="debouncing"
                  :hint="!debouncing && passed && 'Your URL will be: ' + url"
                  persistent-hint
                  :append-icon="passed && !debouncing ? 'mdi-check success--text' : ''"
                  autofocus
              />
            </validation-provider>
            <h3 class="mb-2">Contact email <span class="red--text">*</span></h3>
            <validation-provider :debounce="1000" v-slot="{ errors, passed, debouncing }" name="Email" rules="required|email">
              <v-text-field
                  outlined
                  dense
                  v-model="email"
                  :loading="debouncing"
                  :append-icon="passed ? 'mdi-check success--text' : ''"
                  :error-messages="errors"
              />
            </validation-provider>

          <form-error :error="errorMessage" />

          <v-btn
              color="primary"
              class="mt-2"
              type="submit"
              block
              x-large
              :disabled="invalid || debouncing"
              :loading="loading"
          >
            Next
          </v-btn>
        </form>
      </validation-observer>
      <div class="text-caption text-center mt-5">
        By creating an account, you agree to the <a href="#">Terms of Service</a>. For more information about Grips's privacy practices, see the <a href="#">Grip Privacy Statement</a>. We'll occasionally send you account-related emails.
      </div>
    </v-responsive>
    <v-footer absolute padless height="50"/>
  </div>
</template>

<script>
import FormError from "@/components/FormError";
import client from "@/client";

export default {
  name: "CreateOrganization",
  components: {
    FormError
  },
  data: () => ({
    name: '',
    email: '',
    errorMessage: '',
    loading: false
  }),
  computed: {
    url: function() {
      return 'https://grip.com/' + this.name.toLowerCase().replace(' ', '-').replace(/^[-\s]+|[-\s]+$/g, '');
    }
  },
  methods: {
    submit() {
      this.loading = true;
      client.post('/org/create', {
        name: this.name,
        email: this.email
      }).then((response) => {
        this.$router.replace('/organizations/' + response.data.url + '/invite');
      }).catch(err => {
        console.log(err.response);
      }).finally(() => {
        this.loading = false;
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.organization-plan-card {
  border: 1px solid #d1d5da;

  &:hover {
    border-color: #79b8ff;
    box-shadow: 0 8px 16px rgba(149, 157, 165, .2);
  }
}
</style>
