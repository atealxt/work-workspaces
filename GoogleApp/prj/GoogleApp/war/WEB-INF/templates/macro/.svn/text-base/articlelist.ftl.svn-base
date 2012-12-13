<#macro page>
<#import "/common/paging.ftl" as pag>
<#escape x as x?html>
<#if articles?size != 0>

    <#list articles as a>
    <div class="post">
        <h2 class="title">${a.title}</h2>
        <div class="entry">
            <#noescape><#if a.postBySummary>${a.summary}<#else>${a.content}</#if></#noescape>
        </div>
        <div class="meta">
            <p class="byline">
              Posted on ${a.createTime?string("MMM d, yyyy")} by ${a.createby}
              <br>Catalog: ${a.type}
            </p>
            <p class="links">
                <a href="<#if a.alias?exists>/article/${a.createTime?string("yyyyMMdd")}/${a.alias}.html<#else>/article/${a.id}.html</#if>" class="more">Read full article (${a.readCnt})</a> <b>|</b>
                <a href="<#if a.alias?exists>/article/${a.createTime?string("yyyyMMdd")}/${a.alias}.html#comment<#else>/article/${a.id}.html#comment</#if>" class="comments">Comments (${a.commentCnt})</a>
            </p>
        </div>
    </div>
    </#list>

<#if currentP?exists && maxP?exists>
    <div class="return-to-top">
      <@pag.page currentP="${currentP}" maxP="${maxP}" /><br><br>
    </div>
</#if>

<#else>
    ${noArticles}
</#if>

</#escape>
</#macro>