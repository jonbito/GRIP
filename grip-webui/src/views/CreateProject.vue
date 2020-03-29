<template>
    <v-row>
        <v-col cols="12" md="3">
            <h2>New Project</h2>
            <p>A project is where</p>
        </v-col>
        <v-col cols="12" md="9">
            <v-card>
                <v-card-text>
                    <validation-observer v-slot="{ handleSubmit }">
                        <form @submit.prevent="handleSubmit(submit)">
                            <v-col cols="12" md="6" class="pa-0">
                                <h3 class="mb-2">Name <span class="red--text">*</span></h3>
                                <validation-provider v-slot="{ errors }" name="Project Name" rules="required">
                                    <v-text-field
                                            placeholder="Enter a project name"
                                            outlined
                                            dense
                                            maxlength="50"
                                            v-model="name"
                                            :error-messages="errors"
                                            autofocus
                                    />
                                </validation-provider>
                            </v-col>
                            <v-col cols="6" md="3" class="pa-0">
                                <h3 class="mb-2">Key <span class="red--text">*</span></h3>
                                <validation-provider v-slot="{ errors, pending, passed }" :debounce="500" mode="aggressive" name="Project Key" :rules="'required|alpha_num|starts_with_letter|project_key_available' + (groupId ? ':' + groupId : '')">
                                    <v-text-field
                                            outlined
                                            dense
                                            v-model="key"
                                            maxlength="10"
                                            :error-messages="errors"
                                            :loading="pending"
                                            :success-messages="passed ? 'Project key is available' : []"
                                    >
                                        <template v-slot:append-outer>
                                            <v-tooltip right max-width="350">
                                                <template v-slot:activator="{ on }">
                                                    <v-icon v-on="on" color="primary">mdi-information</v-icon>
                                                </template>
                                                <span>
                                                The project key is used as the prefix of your project's issue keys (e.g. 'TEST-100'). Choose one that is descriptive and easy to type.
                                            </span>
                                            </v-tooltip>
                                        </template>
                                    </v-text-field>
                                </validation-provider>
                            </v-col>

                            <form-error :error="errorMessage" />

                            <v-btn
                                    color="primary"
                                    class="mt-2"
                                    type="submit"
                                    :loading="loading"
                            >
                                Create project
                            </v-btn>
                        </form>
                    </validation-observer>
                </v-card-text>
            </v-card>
        </v-col>
    </v-row>
</template>

<script>
    import client from "../client";
    import FormError from "../components/FormError";
    import keycloak from '../keycloak';

    export default {
  name: 'CreateProject',
  components: {
      FormError
  },
    data: () => ({
        key: '',
        name: '',
        loading: false,
        errorMessage: '',
        groupId: null
    }),
    methods: {
      submit() {
          this.loading = true;
          client.post('/project/create', {
              key: this.key,
              name: this.name
          }).then(() => {
              this.$router.replace('/' + keycloak.tokenParsed.preferred_username + '/' + this.key);
          }).catch((error) => {
              this.errorMessage = error.response.data.message;
          }).then(() => {
              this.loading = false;
          })
      }
    }
}
</script>
