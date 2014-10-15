<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#import "common/paging.ftl" as pag>
<#import "common/login.ftl" as login>

<#escape x as x?html>
<@com.page title="${title}">

    <script src="/javascripts/guestbook.js" type="text/javascript"></script>

</head>
<body>
<@content.page currentPage="Guestbook">

<#if user?exists>

  <p>Hello,${user.nickname}! (You can
  <a href="${logoutUrl}">Sign out</a>.)</p>

<#else>
  Hello! You can <a href="###" class="modalInput" rel="#openid">Sign in</a> to post.<p>
  <@login.page/>
  </p>
  <hr>
</#if>

<#if greeting.greetings?size != 0>
  <#list greeting.greetings as x>
    <p>
    <#if x.author?exists>
      <b>
          <#if user?exists><#--for protect users against spam-->
            <a href="mailto:${x.author.email}" title="Contact by mail"> ${x.author.nickname}</a>
          <#else>
            <#assign indexEmail=x.author.nickname?index_of('@')>
            <#if indexEmail != -1>
            ${x.author.nickname?substring(0, indexEmail)}
            <#else>
            ${x.author.nickname}
            </#if>
          </#if>
      </b>
    <#elseif x.guestName?exists>
      ${x.guestName}
    <#else>
      An anonymous person
    </#if>
      wrote at <i>${x.date?string("MMM d, ''yy h:mm a")}</i>:
      <#if master>
        <a href="?delmsg=${x.id}">del content</a>
      </#if>
    </p>
    <blockquote>
      <#noescape>${x.content}</#noescape>
    </blockquote>
  </#list>
<div class="return-to-top">
<@pag.page currentP="${greeting.currentP}" maxP="${greeting.maxP}" />
</div>

<#else>
  <p>The guestbook has no messages.</p>
</#if>

<#if user?exists>
<table>
<tr><td>
  <form id="f" name="f" action="?putmsg" method="post" onSubmit="return beforeSubmit();">
    <hr><span id="loading"><img src="/images/loading.gif" class="loading">loading editor...</span>
    <div id="divHidden" style="visibility:hidden;">
    <div><textarea id="content" name="content" cols="80" rows="15" style="height:300px;width:610px;visibility:hidden;"></textarea></div><div height="30"></div>
    <div class="button-container">
      <#if !user?exists>
        <div align=left style="float:left;"><label for="guest">Your name: </label><input type="text" name="guest" id="guest" size="20" value="<#if guestName?exists>${guestName}</#if>"/></div>
        <label for="captcha">${captcha}</label><input type="text" name="captcha" id="captcha" size="2" value=""/>
      </#if>
      <input type="hidden" value="${captchaUUID}" name="captchaUUID"/>
      <input type="submit" value="Post Greeting" id="submit"/>
    </div>
    </div>
  </form>
</td></tr>
</table>
<#else>
<hr>
</#if>

</@content.page>

  <script type="text/javascript" src="/tiny_mce/tiny_mce.js"></script>
  <script type="text/javascript">
    tinyMCE.init({
        // General options
        mode : "exact",
        elements : "content",
        theme : "advanced",
        skin : "o2k7",
        skin_variant : "black",
        plugins : "safari,pagebreak,style,layer,table,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,inlinepopups",
        relative_urls : false,
        remove_script_host : true,
        document_base_url : "/",

        // event
        init_instance_callback : function(){
            jQuery('#loading').css("display", "none");
            jQuery('#divHidden').css("visibility", "");
        },

        // Theme options
        theme_advanced_buttons1 : "newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,|,insertdate,inserttime,|,forecolor,backcolor",
        theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,charmap,emotions,fullscreen",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom"
    });
  </script>

</@com.page>
</#escape>
