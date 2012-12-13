<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#import "macro/articlelist.ftl" as articlelist>
<#escape x as x?html>
<@com.page title="${title}">

</head>
<body>

<@content.page currentPage="Blog" sidebar="sidebar_blog.ftl">

<@articlelist.page />

</@content.page>

</@com.page>
</#escape>