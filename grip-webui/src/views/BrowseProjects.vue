<template>
    <div>
        <div class="d-flex justify-space-between mt-3">
            <h1>Projects</h1>
            <v-btn color="success" v-if="projects.length > 0">New Project</v-btn>
        </div>
        <v-divider class="mt-3" />
        <div class="text-center mt-8">
            <v-icon size="128">mdi-folder</v-icon>
            <h2 class="mb-2">You currently have no projects</h2>
            <p class="mb-6">Let's create your first project in Grip</p>
            <v-btn color="primary" to="/projects/new">Create project</v-btn>
        </div>
    </div>
</template>

<script>
export default {
  name: 'Home',
  components: {
  },
    methods: {
    },
    data: () => ({
      projects: [],
    })
}
</script>
