<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#import "common/login.ftl" as login>
<#escape x as x?html>
<@com.page title="${title}">

</head>
<body>

<@content.page currentPage="Contact">

<#if user?exists>

  <p>Hello,${user.nickname}! (You can
  <a href="${logoutUrl}">sign out</a>.)</p>

<#else>
  <p>Hello! (You can
  <a href="###" class="modalInput" rel="#openid">Sign in</a>
  .)</p>
  <@login.page/>
</#if>

<#if user?exists>
  <form id="f" name="f" action="?sending" method="post" onSubmit="cmnApi.disableWhenSubmiting(document.getElementById('submit'))">

      <table>

      <tr><td></td></tr>

      <tr>
      <td>
        <label for="yourname">Your name:</label>
      </td>
      <td>
        <input type="text" name="yourname" id="yourname" size="47" value="<#if user?exists>${user.nickname}<#elseif guestName?exists>${guestName}</#if>"/>
      </td>
      </tr>

      <tr>
      <td>
        <label for="youremail">Your contact:</label>
      </td>
      <td>
        <input type="text" name="youremail" id="youremail" size="47" value="<#if user?exists>${user.email}<#elseif guestUrl?exists>${guestUrl}</#if>"/>
      </td>
      </tr>

      <tr>
      <td VALIGN="top">
        <label for="content">Your message :</label>
      </td>
      <td>
        <span id="loading"><img src="/images/loading.gif" class="loading">loading editor...</span>
        <div id="divContent">
          <textarea id="content" name="content" cols="80" rows="15" style="height:300px;width:600px;visibility:hidden;"></textarea>
        </div>
      </td>
      </tr>

      <tr>
      <td></td>
      <td align="">
        <div class="button-container" id="divHidden" style="visibility:hidden;">
          <label for="captcha">${captcha}</label><input type="text" name="captcha" id="captcha" size="2" value=""/>
          <input type="hidden" value="${captchaUUID}" name="captchaUUID"/>
          <input type="submit" id="submit" value="Post" onclick="return confirm('Are you sure to post?')" />
        </div>
      </td>
      </tr>

      </table>

  </form>
<#else>
<hr>
</#if>
</@content.page>

  <script type="text/javascript" src="/tiny_mce/tiny_mce.js"></script>
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
