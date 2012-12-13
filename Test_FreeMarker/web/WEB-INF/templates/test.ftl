<#import "/common.ftl" as com>
<#escape x as x?html>

<!--OK!-->
<@com.page title="${tt}">

<br><#-- test 1 -->
${message}

<br><#-- test 2 -->
${lastUpdated?string("yyyy-MM-dd HH:mm:ss")}

<br><#-- test 3 -->
<#noescape>&lt;aabbcc&gt;</#noescape>

<br><#-- test 4 -->
<#assign foo=true/> 
${foo?string("yes", "no")}

</@com.page>

</#escape>