<#import "../common/common.ftl" as com>
<#import "../common/content.ftl" as content>
<#escape x as x?html>
<@com.page title="${title}">

    <meta name="robots" content="noindex, nofollow">
</head>
<body>

  <script type="text/javascript" src="/tiny_mce/tiny_mce.js"></script>
  <script type="text/javascript" >
    tinyMCE.init({
        // General options
        mode : "exact",
        elements : "content",
        theme : "advanced",
        skin : "o2k7",
        skin_variant : "black",
        plugins : "codesel,safari,pagebreak,style,layer,table,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,inlinepopups",
        relative_urls : false,
        remove_script_host : true,
        document_base_url : "/",

        // Theme options
        theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,|,forecolor,backcolor",
        theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,ltr,rtl,|,codesel",
        theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,|,fullscreen",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        theme_advanced_resizing : true
    });
    tinyMCE.init({
        mode : "exact",
        elements : "summery",
        theme : "advanced",
        theme_advanced_toolbar_location : "",
        relative_urls : false,
        remove_script_host : true,
        document_base_url : "/"
    });

    window.onbeforeunload = confirmLeave;
    var needToConfirmLeave = true;
    function confirmLeave() {
        if (needToConfirmLeave){
            needToConfirmLeave = false;
            return "Are you sure you want to quit without applying the changes?";
        }
    }

  </script>

  <form id="f" name="f" action="/articlelist?m=${m}<#if topic?exists>&id=${topic.id}</#if>" method="post">

      <table>

      <tr><td></td></tr>

      <tr>
      <td>
        <label for="title">TITLE:</label><br>
        <input type="text" id="title" name="title" maxlength="250" size="255" style="width:98%;"
          value="<#if topic?exists>${topic.title}</#if>"/>
      </td>
      </tr>

      <tr>
      <td>
        <label for="content">CONTENT:</label><br>
        <div id="divContent">
          <textarea id="content" name="content" cols="80" rows="15" style="height:600px;width:98%;;visibility:hidden;"><#if topic?exists>${topic.content}</#if></textarea>
        </div>
      </td>
      </tr>

      <tr>
      <td>
        <label for="type">CLASSIFICATION:</label><br>
        <table id="type" border="0" style="width:95%;">
            <#if types?exists && types?size gt 0>
              <tr>
              <#list types as t>
                <#if t_index?number!=0 && t_index?number%6 == 0></tr><tr></#if>
                <td><input type="checkbox" id="${t.id}" name="nCatalog" value="${t.name}" <#if topic?exists && topic.type?exists && topic.type == t.name>checked</#if> /><label for="${t.id}">${t.name}</label></td>
              </#list>
              </tr>
            </#if>
         </table>
      </td>
      </tr>

      <tr>
      <td>
      <p>
      EntryName(Article alias, Not required. Can only contains letter, number, -, _, and no more than 150 character)<br>
      <input type="text" id="alias" name="alias" maxlength="150" style="width:98%;"
          value="<#if topic?exists && topic.alias?exists>${topic.alias}</#if>"/>
      </p>
      </td>
      </tr>

      <tr>
      <td>
      <p>
        SUMMERY(be use to use when Long articles, need check "post by SUMMERY")<br>
        SUMMERY:<br>
        <textarea id="summery" name="summery" rows="5" cols="20" style="width:98%;visibility:hidden;"><#if topic?exists>${topic.summary}</#if></textarea>
        <label for="chkSummery">post by SUMMERY</label>
        <input id="chkSummery" name="chkSummery" type="checkbox" <#if topic?exists && topic.postBySummary>checked</#if>/><br>
      </p>
      </td>
      </tr>

      <tr>
      <td align="">
        <div class="button-container">
          <input type="submit" id="submit" value="POST" onclick="needToConfirmLeave = false;"/>
          <input type="button" id="cancel" value="CANCEL" onclick="window.location.href='/articlelist'"/>
        </div>
      </td>
      </tr>

      </table>

  </form>

</body>
</html>

</@com.page>
</#escape>