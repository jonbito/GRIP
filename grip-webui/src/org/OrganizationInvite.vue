<template>
  <div>
    <v-responsive class="mx-auto mb-16" style="max-width: 768px;">
      <div class="text-center my-8 mt-16">
        <h2 class="text-h2 mb-10">Welcome to Grip</h2>
        <h4 class="text-h5">Woohoo! You've joined the thousands of teams who are doing their best work with GitHub. Now add your teammates to start collaborating—we can't wait to see what you build.</h4>
      </div>
      <div class="text-center">
        <img src="../assets/invite-people-icon.png" style="width: 200px; height: 200px;" />
      </div>
      <p class="text-h6 mb-2">Add organization members</p>
      <p class="text--secondary mb-2">Organization members will be able to view repositories, organize into teams, review code, and tag other members using @mentions.</p>
      <p class="mb-8"><a href="#">Learn more about permissions for organizations <v-icon color="primary" small>mdi-open-in-new</v-icon></a></p>
      <validation-observer v-slot="{ handleSubmit, invalid, debouncing }">
        <form @submit.prevent="handleSubmit(submit)">
            <h3 class="mb-1">Add email addresses of team members</h3>
            <validation-provider v-slot="{ errors, debouncing }" name="Project Name" rules="combo_box_emails">
              <v-combobox
                  outlined
                  :delimiters="[',']"
                  deletable-chips
                  chips
                  multiple
                  v-model="emails"
                  :error-messages="errors"
                  :loading="debouncing"
                  :search-input.sync="search"
              >
                <template v-slot:no-data>
                  <v-list-item v-if="search && emailValid(search)">
                    <v-list-item-content>
                      <v-list-item-title>
                        Press <kbd>enter</kbd> to invite <strong>{{ search }}</strong>
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-combobox>
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
            Invite
          </v-btn>
        </form>
      </validation-observer>
      <div class="text-caption text-center mt-5">
        <a href="#">Skip this step</a>
      </div>
    </v-responsive>
  </div>
</template>

<script>
import FormError from "@/components/FormError";
import client from "@/client";

export default {
  name: "OrganizationInvite",
  components: {
    FormError
  },
  data: () => ({
    emails: '',
    search: null,
    errorMessage: '',
    loading: false
  }),
  methods: {
    emailValid(email) {
      const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
    submit() {
      this.loading = true;
      client.post('/org/create', {
        name: this.name,
        email: this.email
      }).then(() => {
        // next page
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
