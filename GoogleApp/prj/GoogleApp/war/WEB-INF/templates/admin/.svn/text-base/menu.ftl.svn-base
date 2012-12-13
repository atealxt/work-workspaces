<#import "../common/common.ftl" as com>
<#escape x as x?html>
<@com.page title="${title}">

    <meta name="robots" content="noindex, nofollow">
</head>
<body>

    <div id="sidebar">
        <ul>
            <li>

                <h2><a href="/" target="_blank">Hero's Grave</a></h2>

                <h2>Administer</h2>
                <ul>
                    <#list links as x>
                    <li><a <#if x.link?exists>href="${x.link}"</#if> target="main">${x.msgShow}</a></li>
                    </#list>
                </ul>

                -------------------------<br>
                <ul>
                    <li><a target="main">${recycling}</a></li><#--TODO-->
                    <li><a target="main">${setting}</a></li>
                    <li><a target="_self">${exit}</a></li>
                </ul>
            </li>
        </ul>
    </div>

</body>
</html>

</@com.page>
</#escape>