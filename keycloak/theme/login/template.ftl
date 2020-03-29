<#macro registrationLayout bodyClass="" displayInfo=false displayMessage=true displayRequiredFields=false displayWide=false showAnotherWayIfPresent=true>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml" class="${properties.kcHtmlClass!}">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="robots" content="noindex, nofollow">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
        <link href="https://cdn.materialdesignicons.com/5.0.45/css/materialdesignicons.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">

        <#if properties.meta?has_content>
            <#list properties.meta?split(' ') as meta>
                <meta name="${meta?split('==')[0]}" content="${meta?split('==')[1]}"/>
            </#list>
        </#if>
        <title>${msg("loginTitle",(realm.displayName!''))}</title>
        <link rel="icon" href="${url.resourcesPath}/img/favicon.ico" />
        <#if properties.styles?has_content>
            <#list properties.styles?split(' ') as style>
                <link href="${url.resourcesPath}/${style}" rel="stylesheet" />
            </#list>
        </#if>
        <#if properties.scripts?has_content>
            <#list properties.scripts?split(' ') as script>
                <script src="${url.resourcesPath}/${script}" type="text/javascript"></script>
            </#list>
        </#if>
        <#if scripts??>
            <#list scripts as script>
                <script src="${script}" type="text/javascript"></script>
            </#list>
        </#if>
    </head>

    <body class="${properties.kcBodyClass!}">
        <div id="app">
        <v-app>
            <header>
                <svg width="24" height="24" class="tanuki-logo" viewBox="0 0 36 36">
                    <path class="tanuki-shape tanuki-left-ear" fill="#e24329" d="M2 14l9.38 9v-9l-4-12.28c-.205-.632-1.176-.632-1.38 0z"></path>
                    <path class="tanuki-shape tanuki-right-ear" fill="#e24329" d="M34 14l-9.38 9v-9l4-12.28c.205-.632 1.176-.632 1.38 0z"></path>
                    <path class="tanuki-shape tanuki-nose" fill="#e24329" d="M18,34.38 3,14 33,14 Z"></path>
                    <path class="tanuki-shape tanuki-left-eye" fill="#fc6d26" d="M18,34.38 11.38,14 2,14 6,25Z"></path>
                    <path class="tanuki-shape tanuki-right-eye" fill="#fc6d26" d="M18,34.38 24.62,14 34,14 30,25Z"></path>
                    <path class="tanuki-shape tanuki-left-cheek" fill="#fca326" d="M2 14L.1 20.16c-.18.565 0 1.2.5 1.56l17.42 12.66z"></path>
                    <path class="tanuki-shape tanuki-right-cheek" fill="#fca326" d="M34 14l1.9 6.16c.18.565 0 1.2-.5 1.56L18 34.38z"></path>
                </svg>
            </header>
            <v-content>
                <v-container fluid style="max-width:960px;margin:0 auto;">

                    <#nested "header">

                    <v-row>
                        <v-col order-sm="3" order-md="1" order-lg="1" order-xl="1" order="3" cols="12" md="6">
                            <#nested "leftSide">
                        </v-col>
                        <v-col order="2" cols="12" md="6">
                            <#if displayMessage && message?has_content && (message.type != 'warning' || !isAppInitiatedAction??)>
                                <v-alert type="${message.type}" dense outlined>
                                    ${kcSanitize(message.summary)?no_esc}
                                </v-alert>
                            </#if>

                            <#nested "form">

                            <#if auth?has_content && auth.showTryAnotherWayLink() && showAnotherWayIfPresent>
                                <form id="kc-select-try-another-way-form" action="${url.loginAction}" method="post" <#if displayWide>class="${properties.kcContentWrapperClass!}"</#if>>
                                    <div <#if displayWide>class="${properties.kcFormSocialAccountContentClass!} ${properties.kcFormSocialAccountClass!}"</#if>>
                                        <div class="${properties.kcFormGroupClass!}">
                                            <input type="hidden" name="tryAnotherWay" value="on" />
                                            <a href="#" id="try-another-way" onclick="document.forms['kc-select-try-another-way-form'].submit();return false;">${msg("doTryAnotherWay")}</a>
                                        </div>
                                    </div>
                                </form>
                            </#if>

                            <#if displayInfo>
                                <#nested "info">
                            </#if>
                        </v-col>
                    </v-row>

                </v-container>
            </v-content>

        </v-app>
        </div>


    <script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
    <script>
        new Vue({
            el: '#app',
            vuetify: new Vuetify(),
        })
    </script>

    </body>
    </html>
</#macro>
