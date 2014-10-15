<#import "../common/common.ftl" as com>
<#escape x as x?html>
<@com.page title="${title}">

    <meta name="robots" content="noindex, nofollow">
    <link type="text/css" rel="stylesheet" href="/stylesheets/admin.css" />
    <script src="/javascripts/admin.js" type="text/javascript"></script>
</head>
<body>

<#if types?size != 0>

    <form id="fCategory" method="post">

    <h2>Categories</h2>
    <table id="Listing" class="Listing" cellSpacing="0" cellPadding="0" border="0">
       <tr style="text-align:center">
           <th>Description</th>
           <th>Article Count</th>
           <th width="40" style="text-align:center">Referrals</th>
           <th width="40" style="text-align:center">&nbsp;</th>
       </tr>

       <#list types as a>
       <tr class=<#if a_index?number%2 != 0>"Alt"<#else>"NoAlt"</#if> style="text-align:center">
           <th>${a.name}</th>
           <th width="80" style="text-align:center">${a.count}</th>
           <th width="40" style="text-align:center"><a onclick="edit('${a.id}','${a.name}')">Edit</a></th>
           <th width="40" style="text-align:center"><a href="?m=del&id=${a.id}" onclick="return beforeDel()">Delete</a></th>
       </tr>
       </#list>

    </table>

<#else>
   ${noTypes}
</#if>

    <p class="Category">
      <p id="Category_Action">Add New Category</p>
      <LABEL for="Category">Title</LABEL><br>
      <input type="text" name="Category" id="Category" style="width:350px" />
      <input type="submit" id="addCategory" value="post">
    </p>

    </form>

</body>
</html>

</@com.page>
</#escape>