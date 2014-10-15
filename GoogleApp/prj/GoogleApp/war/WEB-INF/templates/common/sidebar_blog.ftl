<#macro page>
<#escape x as x?html>
    <div id="sidebar">
        <ul>
            <li id="calendar"/>
            <script type="text/javascript">
                function calendar(url){
				    jQuery.get(url, function(data) {
				        jQuery('#calendar').html(data);
				    });
                }
                function changeContent(url){
				    jQuery.get(url, function(data) {
				        jQuery('#content').html(data);
				    });
                }
                jQuery(document).ready(function() {
                    calendar('/calendar');
                });
            </script>
            <li>
                <h2>Catalog</h2>
                <ul>
                    <#if types?exists && types?size gt 0>
                      <#list types as t>
                        <li><a href="/blog/${t.name}">${t.name}(${t.count})</a></li>
                      </#list>
                    </#if>
                </ul>
            </li>
            <li>
                <h2>Latest Comments</h2>
                <ul>
                    <#if latestCmt?exists && latestCmt?size gt 0>
                        <#list latestCmt as c>
                        <li><a href="<#if c.alias?exists>/article/${c.createTime?string("yyyyMMdd")}/${c.alias}.html#${c.id}<#else>/article/${c.articleId}.html#${c.id}</#if>" class="more">${c.title}</a><br></li>
                        ${c.content}
                        <li style="text-align:right;margin-right:40px">--${c.name}</li>
                        </#list>
                    <#else>
                        ${noComments}
                    </#if>
                </ul>
            </li>
        </ul>
    </div>
</#escape>
</#macro>
