<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=social.displayInfo displayWide=(realm.password && social.providers??); section>
    <#if section = "header">
        <h1>Grip</h1>
    <#elseif section = "leftSide">
        <p class="grey--text text--darken-2">Grip offers free unlimited (private) repositories and unlimited collaborators.</p>
        <ul class="title mb-5">
            <li><a class="link-reverse-hover" href="#">More information about Grip</a></li>
            <li><a class="link-reverse-hover" href="#">Grip Support Forum</a></li>
            <li><a class="link-reverse-hover" href="#">Grip Homepage</a></li>
        </ul>
        <p class="grey--text text--darken-2">By signing up for and by signing in to this service you accept our:</p>
        <ul class="title">
            <li><a class="link-reverse-hover" href="#">Privacy Policy</a></li>
            <li><a class="link-reverse-hover" href="#">Grip Terms</a></li>
        </ul>
    <#elseif section = "form">

        <v-card class="mx-auto" outlined style="padding: 8px;">
            <#if realm.password>
                <form id="kc-form-login" onsubmit="login.disabled = true; return true;" action="${url.loginAction}" method="post">
                    <v-card-text>
                        <v-text-field hide-details class="mb-5" label="Username or email" outlined name="username" value="${(login.username!'')}" autofocus></v-text-field>
                        <v-text-field hide-details label="Password" outlined name="password" type="password"></v-text-field>
                        <v-row class="mt-2">
                            <v-col>
                                <#if realm.rememberMe && !usernameEditDisabled??>
                                    <#if login.rememberMe??>
                                        <v-checkbox style="margin-top:0;" label="Remember me" name="rememberMe" hide-details checked></v-checkbox>
                                    <#else>
                                        <v-checkbox style="margin-top:0;" label="Remember me" name="rememberMe" hide-details></v-checkbox>
                                    </#if>
                                </#if>
                            </v-col>
                            <v-col style="text-align: right;padding-top:15px;">
                                <#if realm.resetPasswordAllowed>
                                    <span><a tabindex="5" href="${url.loginResetCredentialsUrl}">${msg("doForgotPassword")}</a></span>
                                </#if>
                            </v-col>
                        </v-row>
                </v-card-text>
                <v-card-actions style="padding-top:0;">
                    <input type="hidden" id="id-hidden-input" name="credentialId" <#if auth.selectedCredential?has_content>value="${auth.selectedCredential}"</#if>/>
                    <v-btn type="submit" block color="primary" name="login">Sign In</v-btn>
                </v-card-actions>
                </form>
            </#if>
        </v-card>
        <#if realm.password && realm.registrationAllowed && !usernameEditDisabled??>
            <p class="mt-5">Don't have an account yet? <a href="${url.registrationUrl}">Register Now</a></p>
        </#if>

            <#if realm.password && social.providers??>
            <v-card class="mx-auto" outlined style="padding: 8px;">
                <v-card-title>Sign in with</v-card-title>
                <v-card-text>
                    <v-row>
                        <#list social.providers as p>
                            <v-col sm="6">
                                <v-btn color="#2e2e2e" style="border-color:#e5e5e5;" block outlined large href="${p.loginUrl}"><v-icon <#if p.providerId == 'facebook'> class="blue--text text--darken-4"</#if>>mdi-${p.providerId}</v-icon> &nbsp; ${p.displayName}</v-btn>
                            </v-col>
                        </#list>
                    </v-row>
                </v-card-text>
            </v-card>
            </#if>
    </#if>

</@layout.registrationLayout>
