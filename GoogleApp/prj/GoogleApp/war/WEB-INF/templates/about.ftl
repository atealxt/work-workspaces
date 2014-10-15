<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#escape x as x?html>
<@com.page title="${title}">

  <link type="text/css" rel="stylesheet" href="/stylesheets/about.css" />
</head>
<body>

<@content.page currentPage="About">

        <div class="post">
            <h2>The technology used in Hero's Grave</h2>
            <div class="entry">
                <table>
                    <tr>
                      <th>Package Name</th>
                      <th>Description</th>
                    </tr>
                    <#list technology as x>
                    <tr>
                      <td><a href="${x.link}">${x.title}</a></td>
                      <td>${x.msgShow}</td>
                    </tr>
                    </#list>
                 </table>
            </div>
        </div>

        <div class="post">
            <br><h2>Source download</h2>
            <div class="text">
                <a href="http://code.google.com/p/herogravebygae/" target="_blank">Download at Google Code</a>
                <span class="href-no-style"><a href="/releasenote">&nbsp;View Release Notes</a></span>
            </div>
        </div>

</@content.page>

</@com.page>
</#escape>