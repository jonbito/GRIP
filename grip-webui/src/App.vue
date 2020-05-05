<template>
    <v-app>

        <ProjectNavigationDrawer />

        <SearchDrawer v-model="showSearchDrawer" />

        <v-app-bar
                app
                clipped-left
                dense
                flat
                color="primary"
                dark
        >
            <v-btn
                    to="/"
                    text
                    class="mr-2"
                    active-class="no-active"
                    icon
            >
                <v-icon>mdi-home</v-icon>
            </v-btn>

            <ProjectMenu/>

            <v-spacer></v-spacer>

            <HeaderSearchButton
                    @click="showSearchDrawer = !showSearchDrawer"
                    v-shortkey="['/']"
                    @shortkey.native="showSearchDrawer = !showSearchDrawer"
            />
            <HeaderUserMenu />
        </v-app-bar>

        <v-content>
            <v-container fluid class="px-8">
                <router-view/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import ProjectMenu from "./components/ProjectMenu";
    import ProjectNavigationDrawer from "./components/ProjectNavigationDrawer";
    import HeaderUserMenu from "./components/HeaderUserMenu";
    import HeaderSearchButton from "./components/HeaderSearchButton";
    import SearchDrawer from "./components/SearchDrawer";

    export default {
        name: 'App',
        components: {
            ProjectMenu,
            ProjectNavigationDrawer,
            HeaderUserMenu,
            HeaderSearchButton,
            SearchDrawer
        },
        data: () => ({
            showSearchDrawer: null
        }),
    };
</script>

<style lang="scss">
    a {
        text-decoration: none;
    }

    a.link {
        text-decoration: none;
    }

    a.link:hover {
        text-decoration: underline;
    }

    .v-btn-plain:before {
        display: none !important;
    }
    .v-btn {
        text-transform: none;
    }
    .v-tab {
        letter-spacing: .05em;
        text-transform: none;
        justify-content: flex-start;
    }
    .theme--light.v-data-table {
        th.sortable:hover {
            background: #f5f5f5;
        }
        tbody tr:hover:not(.v-data-table__expanded__content):not(.v-data-table__empty-wrapper) {
            background:#fcfcfc !important;
        }
    }
    .v-btn--active.no-active::before {
        opacity: 0 !important;
    }
    .v-toolbar--flat {
        .v-toolbar__content {
            padding-left: 0;
        }
    }
</style>
