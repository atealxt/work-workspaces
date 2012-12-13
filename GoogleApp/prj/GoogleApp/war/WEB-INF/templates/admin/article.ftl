<#import "../common/common.ftl" as com>
<#import "../common/paging.ftl" as pag>
<#escape x as x?html>
<@com.page title="${title}">

    <meta name="robots" content="noindex, nofollow">
    <link type="text/css" rel="stylesheet" href="/stylesheets/admin.css" />
    <script src="/javascripts/admin.js" type="text/javascript"></script>
</head>
<body>

<#if articles?size != 0>

    <h2>Posts</h2>
    <table id="Listing" class="Listing" cellSpacing="0" cellPadding="0" border="0">
       <tr style="text-align:center">
           <th>Description</th>
           <th width="70" style="text-align:center">Views</th>
           <th width="70" style="text-align:center">Comments</th>
           <th width="40" style="text-align:center">Referrals</th>
           <th width="40" style="text-align:center">&nbsp;</th>
           <th width="40" style="text-align:center">&nbsp;</th>
       </tr>

       <#list articles as a>
       <tr class=<#if a_index?number%2 != 0>"Alt"<#else>"NoAlt"</#if> style="text-align:center">
           <th><a href="?m=edit&id=${a.id}">${a.title}(${a.createTime?string("yyyy/MM/dd HH:mm")})</a></th>
           <th width="70" style="text-align:center">${a.readCnt}</th>
           <th width="70" style="text-align:center">${a.commentCnt}</th>
           <th width="40" style="text-align:center"><a href="/article/${a.id}" target="_blank">View</a></th>
           <th width="40" style="text-align:center"><a href="?m=edit&id=${a.id}">Edit</a></th>
           <th width="40" style="text-align:center"><a href="?m=d&id=${a.id}" onclick="return beforeDel()">Delete</a></th>
       </tr>
       </#list>
   </table>

   <div class="return-to-top"><br>
   <@pag.page currentP="${currentP}" maxP="${maxP}" />
   </div>

<#else>
    ${noArticles}
</#if>

</body>
</html>

</@com.page>
</#escape>
