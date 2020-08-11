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
                <svg width="24" height="24" id="Layer_1" data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 128.13 136.7"><defs><linearGradient id="linear-gradient" x1="49.04" y1="32.22" x2="49.04" y2="94.1" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="#40c7f4"/><stop offset="1" stop-color="#457bbe"/></linearGradient><linearGradient id="New_Gradient_Swatch_copy" x1="83" y1="32.22" x2="83" y2="94.1" xlink:href="#linear-gradient"/><linearGradient id="New_Gradient_Swatch" x1="116.81" y1="32.22" x2="116.81" y2="60.29" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="#f26b6b"/><stop offset="0.43" stop-color="#ef3a47"/></linearGradient><linearGradient id="New_Gradient_Swatch-2" x1="150.78" y1="32.22" x2="150.78" y2="60.29" xlink:href="#New_Gradient_Swatch"/><linearGradient id="linear-gradient-2" x1="100" y1="68.58" x2="100" y2="167.78" gradientUnits="userSpaceOnUse"><stop offset="0" stop-color="#409fd8"/><stop offset="1" stop-color="#426bb3"/></linearGradient></defs><path d="M36.44,32.22H61.63V94.1l-25.19-.32Z" transform="translate(-35.94 -31.72)" style="stroke:#231f20;stroke-miterlimit:10;fill:url(#linear-gradient)"/><path d="M70.25,32.22H95.76L95.44,94.1l-25.19-.32Z" transform="translate(-35.94 -31.72)" style="stroke:#231f20;stroke-miterlimit:10;fill:url(#New_Gradient_Swatch_copy)"/><path d="M104.06,32.22h25.51V60.29L104.06,60Z" transform="translate(-35.94 -31.72)" style="stroke:#231f20;stroke-miterlimit:10;fill:url(#New_Gradient_Swatch)"/><path d="M138.18,32.22h25.2V60.29h-25.2Z" transform="translate(-35.94 -31.72)" style="stroke:#231f20;stroke-miterlimit:10;fill:url(#New_Gradient_Swatch-2)"/><path d="M163.38,68.58v35.09c0,4,.7,10.12-.64,13.08l-62.83,51-63.47-52V102.71l93.13-.32V94.1l-25.51-.32.31-25.2Z" transform="translate(-35.94 -31.72)" style="stroke:#231f20;stroke-miterlimit:10;fill:url(#linear-gradient-2)"/></svg>
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
