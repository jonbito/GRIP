<template>
    <v-row>
        <v-col cols="12" md="3">
            <h2>A Project</h2>
        </v-col>
        <v-col cols="12" md="9">
            <v-card>
                <v-card-text>
                    <v-form
                            v-model="formValid"
                            lazy-validation
                            ref="form"
                            @submit.prevent="submit"
                    >
                        <v-col cols="12" md="6" class="pa-0">
                            <h3 class="mb-2">Name <span class="red--text">*</span></h3>
                            <v-text-field
                                    placeholder="Enter a project name"
                                    outlined
                                    dense
                                    maxlength="50"
                                    v-model="name"
                                    :rules="nameRules"
                                    autofocus
                            />
                        </v-col>
                        <v-col cols="6" md="3" class="pa-0">
                            <h3 class="mb-2">Key <span class="red--text">*</span></h3>
                            <v-text-field
                                    outlined
                                    dense
                                    v-model="keyComputed"
                                    maxlength="10"
                                    :rules="keyRules"
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
                        </v-col>
                        <v-btn
                                color="primary"
                                class="mt-2"
                                type="submit"
                                :loading="loading"
                        >
                            Create project
                        </v-btn>
                    </v-form>
                </v-card-text>
            </v-card>
        </v-col>
    </v-row>
</template>

<script>
    import client from "../client";

    export default {
  name: 'Home',
  components: {
  },
    data: () => ({
        formValid: false,
        key: '',
        keyRules: [
            v => !!v || 'Project key is required',
            v => (v && !Number.isInteger(+v.charAt(0))) || 'Project keys must start with an uppercase letter, followed by one or more uppercase alphanumeric characters',
            v => (v && RegExp(/^[a-zA-Z0-9]+$/).test(v)) || 'Project keys must start with an uppercase letter, followed by one or more uppercase alphanumeric characters',
        ],
        name: '',
        nameRules: [
            v => !!v || 'Project name is required'
        ],
        loading: false,
    }),
    computed: {
      keyComputed: {
          get() {
              return this.key;
          },
          set(value) {
              this.key = value.toUpperCase();
          }
      }
    },
    methods: {
      submit() {
          if(!this.$refs.form.validate()) return;

          this.loading = true;
          client.post('/project/create', {
              key: this.key,
              name: this.name
          }).then((response) => {
              console.log(response);
          }).then(() => {
              this.loading = false;
          })
      }
    }
}
</script>
