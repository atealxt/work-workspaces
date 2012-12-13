<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#escape x as x?html>
<@com.page title="${title}">

</head>
<body>

<@content.page>

        <div class="post">
            <h2>Release Notes</h2>
            <div class="entry">
                <ol>
                    <li class="href-no-line"><a href="#1.06">Version 1.06</a></li>
                    <li class="href-no-line"><a href="#1.03">Version 1.03</a></li>
                    <li class="href-no-line"><a href="#0.11">Version 0.11</a></li>
                    <li class="href-no-line"><a href="#0.10">Version 0.10</a></li>
                    <li class="href-no-line"><a href="#0.07">Version 0.07</a></li>
                    <li class="href-no-line"><a href="#0.06">Version 0.06</a></li>
                    <li class="href-no-line"><a href="#0.05">Version 0.05</a></li>
                </ol>
            </div>
        </div>

        <a name="1.06"></a>
        <div class="post">
            <h2>Version 1.06</h2>
            <div class="entry">
                <ul>
                    <li>Support GAE 1.5.0</li>
                    <li>Support OAuth2 Sign in</li>
                    <li>Spring RESTful Action</li>
                </ul>
            </div>
        </div>

        <a name="1.03"></a>
        <div class="post">
            <h2>Version 1.03</h2>
            <div class="entry">
                <ul>
                    <li>Support GAE 1.3.1</li>
                    <li>Message internationalization (i18n)</li>
                    <li>Using freemarker-gae-pre2.jar instead of original freemarker.jar</li>
                </ul>
            </div>
        </div>

        <a name="0.11"></a>
        <div class="post">
            <h2>Version 0.11</h2>
            <div class="entry">
                <ul>
                    <li>Calendar</li>
                </ul>
            </div>
        </div>

        <a name="0.10"></a>
        <div class="post">
            <h2>Version 0.10</h2>
            <div class="entry">
                <ul>
                    <li>Support GAE 1.2.6</li>
                    <li>Captcha</li>
                    <li>Guest Cookie</li>
                    <li>Error page effect</li>
                    <li>Source code refactoring</li>
                </ul>
            </div>
        </div>

        <a name="0.07"></a>
        <div class="post">
            <h2>Version 0.07</h2>
            <div class="entry">
                <ul>
                    <li>Administer (Guest book)</li>
                    <li>Article's alias(used in url)</li>
                    <li>[Bug][090702] loading css style is after loading editor - resolved</li>
                </ul>
            </div>
        </div>

        <a name="0.06"></a>
        <div class="post">
            <h2>Version 0.06</h2>
            <div class="entry">
                <ul>
                    <li>Site theme</li>
                    <li>Blog (Except Calendar)</li>
                    <li>Administer (Article, Comment, Type)</li>
                    <li>Url rewrite</li>
                    <li>Replaced editor with tiny_mce</li>
                </ul>
            </div>
        </div>

        <a name="0.05"></a>
        <div class="post">
            <h2>Version 0.05</h2>
            <div class="entry">
                <ul>
                    <li>Greeting</li>
                    <li>Mail</li>
                    <li>Use editor nicedit</li>
                </ul>
            </div>
        </div>

        <div class="post">
            <h2>Timestamp</h2>
            <div class="entry">
                <ul>
                    <li>2010/06 - 1.0 Released</li>
                    <li>2009/07 - Website Open Source</li>
                    <li>2009/06 - Website Beta Released</li>
                    <li>2009/05 - Website Created</li>
                </ul>
            </div>
        </div>

</@content.page>

</@com.page>
</#escape>
