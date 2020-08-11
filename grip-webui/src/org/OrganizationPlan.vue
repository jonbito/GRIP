<template>
  <div>
    <v-responsive class="mx-auto" style="max-width: 1024px;">
      <div class="text-center my-8">
        <h2 class="text-h2">Pick a plan for your team</h2>
      </div>
      <v-row>
        <v-col cols="12" md="4">
          <v-card
              class="organization-plan-card"
              outlined
          >
            <div class="ma-8">
              <div class="text-center">
                <h4 class="text-h4 font-weight-bold mb-3">Free</h4>
                <p>The basics of Grip for every team no matter the size</p>
              </div>
            </div>
            <div>
              <div class="grey lighten-4 pa-4">

                <div>
                  <v-icon color="success" class="mr-2 mb-1">mdi-infinity</v-icon>
                  Unlimited projects
                </div>
                <div>
                  <v-icon color="success" class="mr-2 mb-1">mdi-infinity</v-icon>
                  Unlimited users
                </div>
                <div>
                  <v-icon color="success" class="mr-2">mdi-check</v-icon>
                  Community support
                </div>

              </div>
            </div>
            <div class="ma-8">
              <div class="d-flex justify-center align-center">
                <span class="text-h4 font-weight-bold">$0</span>
              </div>
            </div>
            <div class="ma-2 mb-4">
              <v-btn to="/-/organizations/create" color="primary" block x-large>Join for free</v-btn>
            </div>
          </v-card>
        </v-col>
        <v-col cols="12" md="4">
          <v-card
              class="organization-plan-card"
              outlined
          >
            <div class="ma-8">
              <div class="text-center">
                <h4 class="text-h4 font-weight-bold mb-3">Team</h4>
                <p>Advanced collaboration and support for teams</p>
              </div>
            </div>
            <div>
              <div class="grey lighten-4 pa-4">

                <div>
                  <v-icon color="success" class="mr-2 mb-1">mdi-arrow-left</v-icon>
                  Everything in Free
                </div>
                <div>
                  <v-icon color="success" class="mr-2 mb-1">mdi-check</v-icon>
                  Test
                </div>
                <div>
                  <v-icon color="success" class="mr-2">mdi-infinity</v-icon>
                  Unlimited Test
                </div>

              </div>
            </div>
            <div class="ma-8">
              <div class="d-flex justify-center align-center">
                <span class="text-h4 font-weight-bold mr-2">$4</span>
                <span class="font-weight-bold">per user/month</span>
              </div>
            </div>
            <div class="ma-2 mb-4">
              <v-btn color="primary" block x-large>Continue with Team</v-btn>
            </div>
          </v-card>
        </v-col>
        <v-col cols="12" md="4">
          <v-card
              class="organization-plan-card"
              outlined
          >
            <div class="ma-8">
              <div class="text-center">
                <h4 class="text-h4 font-weight-bold mb-3">Enterprise</h4>
                <p>Security, compliance, and flexible deployment for enterprises</p>
              </div>
            </div>
            <div>
              <div class="grey lighten-4 pa-4">

                <div>
                  <v-icon color="success" class="mr-2 mb-1">mdi-arrow-left</v-icon>
                  Everything in Team
                </div>
                <div>
                  <v-icon color="success" class="mr-2 mb-1">mdi-check</v-icon>
                  SAML single sign-on
                </div>
                <div>
                  <v-icon color="success" class="mr-2">mdi-check</v-icon>
                  Advanced auditing
                </div>

              </div>
            </div>
            <div class="ma-8">
              <div class="d-flex justify-center align-center">
                <span class="text-h4 font-weight-bold mr-2">$21</span>
                <span class="font-weight-bold">per user/month</span>
              </div>
            </div>
            <div class="ma-2 mb-4">
              <v-btn color="primary" block x-large>Start Enterprise trial</v-btn>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-responsive>
    <v-footer absolute padless height="50"/>
  </div>
</template>

<script>
export default {
  name: "OrganizationPlan"
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
