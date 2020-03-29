<#import "template.ftl" as layout>
<@layout.registrationLayout; section>
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
        <form action="${url.registrationAction}" method="post">

            <v-card class="mx-auto" outlined style="padding: 8px;">
                <v-card-title>
                    Register for Grip
                </v-card-title>
                <v-card-text>
                    <v-row>
                        <v-col md="6" cols="12" style="padding-bottom: 0;">
                            <v-text-field hide-details class="mb-5" label="${msg("firstName")}" outlined name="firstName" value="${(register.formData.firstName!'')}" autofocus></v-text-field>
                        </v-col>
                        <v-col md="6" cols="12" style="padding-bottom: 0;">
                            <v-text-field hide-details class="mb-5" label="${msg("lastName")}" outlined name="lastName" value="${(register.formData.lastName!'')}"></v-text-field>
                        </v-col>
                    </v-row>
                    <v-text-field hide-details class="mb-5" label="${msg("email")}" outlined name="email" value="${(register.formData.email!'')}"></v-text-field>
                    <#if !realm.registrationEmailAsUsername>
                        <v-text-field hide-details class="mb-5" label="${msg("username")}" outlined name="username" value="${(register.formData.username!'')}"></v-text-field>
                    </#if>
                    <#if passwordRequired??>
                        <v-text-field hide-details class="mb-5" label="${msg("password")}" outlined name="password" type="password"></v-text-field>
                        <v-text-field hide-details class="mb-5" label="${msg("passwordConfirm")}" outlined name="password-confirm" type="password"></v-text-field>
                    </#if>
                    <#if recaptchaRequired??>
                        <div class="form-group">
                            <div class="${properties.kcInputWrapperClass!}">
                                <div class="g-recaptcha" data-size="compact" data-sitekey="${recaptchaSiteKey}"></div>
                            </div>
                        </div>
                    </#if>
                </v-card-text>
                <v-card-actions style="padding-top:0;">
                    <v-btn type="submit" block color="primary">${msg("doRegister")}</v-btn>
                </v-card-actions>
            </v-card>
        </form>

        <p class="mt-5">Already have login and password? <a href="${url.loginUrl}">Sign in</a></p>
    </#if>
</@layout.registrationLayout>
