<template>
    <v-app>

        <v-navigation-drawer
                clipped
                app
                v-if="false"
        >
            <v-list dense>
                <v-list-item to="/">
                    <v-list-item-action>
                        <v-icon>
                            mdi-home
                        </v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>
                            Home
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item link>
                    <v-list-item-action>
                        <v-icon>
                            mdi-card-account-mail
                        </v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>
                            Contact
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>

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
            >
                <v-icon class="mr-2">mdi-home</v-icon>
                <span>Grip</span>
            </v-btn>

            <ProjectMenu/>

            <v-spacer></v-spacer>

            <HeaderSearchButton @click="showSearchDrawer = !showSearchDrawer" />
            <HeaderUserMenu />
        </v-app-bar>

        <v-content>
            <v-container fluid style="max-width:1280px;margin:0 auto;">
                <router-view/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import ProjectMenu from "./components/ProjectMenu";
    import HeaderUserMenu from "./components/HeaderUserMenu";
    import HeaderSearchButton from "./components/HeaderSearchButton";
    import SearchDrawer from "./components/SearchDrawer";

    export default {
        name: 'App',
        components: {
            ProjectMenu,
            HeaderUserMenu,
            HeaderSearchButton,
            SearchDrawer
        },
        data: () => ({
            showSearchDrawer: null
        }),
    };
</script>

<style>
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
</style>
