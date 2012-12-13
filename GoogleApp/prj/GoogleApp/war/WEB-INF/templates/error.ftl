<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#escape x as x?html>
<@com.page title="${title}">

    <link type="text/css" rel="stylesheet" href="/stylesheets/error.css" />
</head>
<body>

<@content.page>

<div id="dayin1">
    <div id="dayin2">Sorry!</div>
    <div id="dayin3">${information}</div>    
</div>

</@content.page>

</@com.page>
</#escape>