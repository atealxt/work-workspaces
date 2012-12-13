<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#import "common/login.ftl" as login>
<#escape x as x?html>
<@com.page title="${title}">

  <link type="text/css" rel="stylesheet" href="/stylesheets/mail.css" />
  <script src="/javascripts/mail.js" type="text/javascript"></script>
</head>
<body>

<@content.page>

<#if user?exists>

  <script type="text/javascript" src="tiny_mce/tiny_mce.js"></script>
  <script type="text/javascript" >
    tinyMCE.init({
        // General options
        mode : "exact",
        elements : "content ",
        theme : "advanced",
        skin : "o2k7",
        skin_variant : "black",
        plugins : "safari,pagebreak,style,layer,table,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,inlinepopups",
        relative_urls : false,
        remove_script_host : true,
        document_base_url : "/",

        // Theme options
        theme_advanced_buttons1 : "newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,|,insertdate,inserttime,|,forecolor,backcolor",
        theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,charmap,emotions,fullscreen",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom"
    });
  </script>

  <p>Hello,${user.nickname}! (You can
  <a href="${logoutUrl}">sign out</a>.)</p>

  <form id="f" name="f" action="?sending" method="post"><#--enctype="multipart/form-data"-->

      <table>

      <tr><td></td></tr>

      <tr>
      <td>
        <label for="from">From:&nbsp;&nbsp;&nbsp;</label>
      </td>
      <td>
        <input type="text" name="from" id="from" size="47" value="${user.email}" class="readonly" readonly/>
      </td>
      </tr>

      <tr>
      <td>
        <label for="to">To:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
      </td>
      <td>
        <input type="text" name="to" id="to" value="<#if touser?exists>${touser}</#if>" size="47"/>
      </td>
      </tr>

      <tr>
      <td>
      <label for="Subject">Subject:</label>
      </td>
      <td>
        <input type="text" name="Subject" id="Subject" size="86"/>
        <#--
        <input type="text" name="Subject" id="Subject" size="103"/>
        <a onclick="" style="font-size:9pt;color:#990000;">Attach a file</a>-->
      </td>
      </tr>

      <tr style="display:none">
        <td></td>
      <td>
        <input type="file" name="file"/>
      </td>
      </tr>

      <tr>
      <td VALIGN="top">
        Text:<br><br>
        <a id="textStyle" style="font-size:9pt;color:#990000;">Plain Text</a>
      </td>
      <td>
        <input type="hidden" id="base" name="base" value="">
        <div id="divContent">
          <textarea id="content" name="content" cols="80" rows="15" style="height:300px;width:600px;"></textarea>
        </div>
      </td>
      </tr>

      <tr>
      <td><input type="submit" id="submit" value="Post"/> </td>
      <td align=""><input id="reset" type="button" value="Reset"/></td>
      </tr>

      </table>

  </form>

<#else>
  <p>Hello!
  <a href="###" class="modalInput" rel="#openid">Sign in</a>
  to send mail.</p>
</#if>

</@content.page>

</@com.page>
</#escape>