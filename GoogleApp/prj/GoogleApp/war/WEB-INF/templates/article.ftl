<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#import "common/login.ftl" as login>
<#escape x as x?html>
<@com.page title="${article.title}">

    <script src="/syntaxhighlighter/scripts/syntaxhighlighter.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="/syntaxhighlighter/styles/syntaxhighlighter.css"/>
    <script src="/javascripts/article.js" type="text/javascript"></script>
    <script type="text/javascript">
    var articleId = '${article.id}';
    </script>

</head>
<body>

<@content.page currentPage="Blog" sidebar="sidebar_blog.ftl">

        <div class="post">
            <h2 class="title" id="h2title">${article.title}</h2>
            <div class="entry">
                <#noescape>${article.content}</#noescape>
            </div>
            <div class="meta">
                <p class="byline">
                  Posted on ${article.createTime?string("MMM d, yyyy h:mm a")} by ${article.createby}
                  <br>Catalog: ${article.type}
                </p>
                <p class="links"><span class="more">Read (${article.readCnt})</span> <b>|</b> <a href="#comment" class="comments">Comments (${article.commentCnt})</a></p>
            </div>
        </div>

        <a name="comment"/>
        <div id="cmt_content" class="post">
            <h3 class="title">Comments:</h3>
        </div>
        <a name="commentend"/>

        <div class="post"><#--TODO add flag 'canReply'-->
            <div class="reply">

<#if user?exists>

              <form id="f" name="f" method="post" action="/comment?aid=${article.id}" onSubmit="return beforeSubmit();">
              <table>

              <tr>
              <td>
                <label for="title">Title:</label>
              </td>
              <td>
                <input type="text" name="title" id="title" size="74" value="Re: ${article.title}"/>
              </td>
              </tr>

              <tr>
              <td>
                <label for="yourname">Name:</label>
              </td>
              <td>
                <input type="text" name="yourname" id="yourname" size="74" value="<#if user?exists><#assign indexEmail=user.nickname?index_of('@')><#if indexEmail != -1>${user.nickname?substring(0, indexEmail)}<#else>${user.nickname}</#if><#elseif guestName?exists>${guestName}</#if>"/>
                <span id="yournameErr" style="visibility:hidden;"><font color="red">Required</font></span>
              </td>
              </tr>

              <tr>
              <td>
                <label for="contact">Contact:</label>
              </td>
              <td>
                <input type="text" name="contact" id="contact" size="74" value="<#if user?exists>${user.email}<#elseif guestUrl?exists>${guestUrl}</#if>"/>
              </td>
              </tr>

              <tr>
              <td colSpan="3">
                <a name="reply"></a>
                <textarea id="rep_content" name="rep_content" style="height:200px;width:495px;"></textarea>
                <span id="rep_contentErr" style="visibility:hidden;"><font color="red">Required</font></span>
              </td>
              </tr>

              <tr>
              <td colSpan="3">
                <div>
                  <input type="submit" id="submit" value="Post"/>
                  [Submit with Ctrl+Enter]
                </div>
              </td>
              </tr>

              </table>
              </form>


<#else>
    <a href="###" class="modalInput" rel="#openid">Sign in</a> to post comments.
    <@login.page/>
</#if>

            </div>

        </div>

    <script type="text/javascript">
        var content = jQuery('#content');
        content.html(content.html().replace(/(\[code:)([^\]]*)(\])([\s\S]*?)(\[\/code\])/g,"<pre class=\"brush:$2;\">$4</pre>"));

        SyntaxHighlighter.config.bloggerMode = true;
        SyntaxHighlighter.config.taName = "pre";
        SyntaxHighlighter.config.clipboardSwf = '/syntaxhighlighter/scripts/clipboard.swf';
        SyntaxHighlighter.all();
    </script>


</@content.page>

</@com.page>
</#escape>
