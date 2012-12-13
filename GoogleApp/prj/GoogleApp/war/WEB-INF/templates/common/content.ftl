<#import "login.ftl" as login>
<#macro page title="Hero's Grave"
             currentPage=""
             sidebar=""
             loginUrl=""
             logoutUrl="">

<div id="login">
    <span>
    <!--<#if openId?exists>
        <a href="###" class="modalInput" rel="#openid">Sign in</a>
    <#elseif logoutUrl != "">
        <a href="${logoutUrl}">Logout</a>
    </#if>-->
    </span>
</div>
<div id="logo">
    <h1><a href="/">${title}</a></h1>
    <h2>${subtitle}</h2>
</div>

<#-- start header -->
<div id="header">
    <div id="menu">
        <ul>
            <li <#if currentPage == "Homepage">class="current_page_item"</#if>><a href="/home">Homepage</a></li>
            <li <#if currentPage == "Blog">class="current_page_item"</#if>><a href="/blog">Blog</a></li>
            <li <#if currentPage == "Guestbook">class="current_page_item"</#if>><a href="/guestbook">Guestbook</a></li>
            <li <#if currentPage == "About">class="current_page_item"</#if>><a href="/about">About</a></li>
            <li <#if currentPage == "Contact">class="current_page_item"</#if>><a href="/contact">Contact</a></li>
        </ul>
        <div id="search">
            <form method="get" id="formSearch">
              <label for="search-text"><img src="http://www.google.com/coop/intl/en/images/google_custom_search_watermark.gif"></label>
              <input type="text" name="s" id="search-text" size="15"/>
              <input type="button" id="search-submit" value="Search" />
            </form>
        </div>
        <#-- end #search -->
    </div>
</div>
<#-- end header -->

<#-- start page -->
<div id="wrapper">
<div id="wrapper-btm">
<div id="page">

    <#if sidebar != "">

      <#-- start sidebar -->
      <#import sidebar as sbar>
      <@sbar.page/>
      <#-- end sidebar -->

      <#-- start content -->
      <div id="content">
        <#nested>
        <div id="return-to-top"><a href="###" class="return-to-top2">Return to Top</a></div>
      </div>
      <#-- end content -->

    <#else>
      <div id="content_nobar">
        <#nested>
        <div id="return-to-top"><a href="###" class="return-to-top2">Return to Top</a></div>
      </div>
    </#if>

    <div style="clear: both;">&nbsp;</div>
</div>
<#-- end page -->
</div>
</div>

<#-- start footer -->
<div id="footer">
    <div id="footer-wrap">
        <p id="legal">
            (c) 2009-2011 Hero's Grave. Powered by Google App Engine
        </p>
    </div>
</div>
<#-- end footer -->

<#if openId?exists>
<@login.page/>
</#if>

</body>
</html>

</#macro>
