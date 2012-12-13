<#import "../common/common.ftl" as com>
<#import "../common/paging.ftl" as pag>
<#escape x as x?html>
<@com.page title="${title}">

    <meta name="robots" content="noindex, nofollow">
    <link type="text/css" rel="stylesheet" href="/stylesheets/admin.css" />
    <script src="/javascripts/admin.js" type="text/javascript"></script>
</head>
<body>

<#if greetings?size != 0>

    <form method="post" action="?m=d">
    <h2>Guest book</h2>
    <table id="Listing" class="Listing" cellSpacing="0" cellPadding="0" border="0">
       <tr style="text-align:center">
           <th width="28" style="text-align:center"><a id="selAll">Select all</a></th>
           <th width="70">Posted By</th>
           <th width="120">Date</th>
           <th>&nbsp;</th>
           <th width="40" style="text-align:center">Referrals</th>
       </tr>

       <#list greetings as c>
       <tr class="NoAlt" style="text-align:center">
           <th width="45" style="text-align:right">${c_index+1}# <input type="checkbox" class="sel" id="${c.id}" value="${c.id}"></th>
           <th width="70">
               <a href="<#if c.contact?exists>${c.contact}</#if>" target="_blank"><#if c.name?exists>${c.name}</#if></a>
           </th>
           <th width="120">${c.time}</th>
           <th>&nbsp;</th>
           <th width="40" style="text-align:center"><a href="?m=d&id=${c.id}" onclick="return beforeDel()">Delete</a></th>
       </tr>
       <tr>
           <td></td>
           <td colspan="4"><#noescape>${c.content}</#noescape></td>
       </tr>
       </#list>
   </table>

   <table>
     <tr><td colspan="6">${chooseGreetings}<input type="submit" value="Delete" onclick="return beforeDel()"></td></tr>
   </table>

   <div class="return-to-top"><br>
   <@pag.page currentP="${currentP}" maxP="${maxP}" />
   </div>
   </form>

<#else>
    ${noGreetings}
</#if>

</body>
</html>

</@com.page>
</#escape>
