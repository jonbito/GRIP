<#import "template.ftl" as layout>
<@layout.registrationLayout displayInfo=true; section>
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
        <form id="kc-reset-password-form" action="${url.loginAction}" method="post">
            <v-card class="mx-auto" outlined style="padding: 8px;">
                <v-card-title>
                    Reset Password
                </v-card-title>
                <v-card-text>
                    <#if auth?has_content && auth.showUsername()>
                        <v-text-field hide-details class="mb-5" label="Email" outlined name="username" autofocus value="${auth.attemptedUsername}"></v-text-field>
                    <#else>
                        <v-text-field hide-details class="mb-5" label="Email" outlined name="username" autofocus></v-text-field>
                    </#if>
                </v-card-text>
                <v-card-actions style="padding-top:0;">
                    <v-btn type="submit" block color="primary" name="login">Reset password</v-btn>
                </v-card-actions>
            </v-card>
        </form>

        <p class="mt-5">Already have login and password? <a href="${url.loginUrl}">Sign in</a></p>
    </#if>
</@layout.registrationLayout>
