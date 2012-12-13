<#import "common/common.ftl" as com>
<#import "common/content.ftl" as content>
<#import "macro/articlelist.ftl" as articlelist>
<#escape x as x?html>
<@com.page title="Index">

    <link type="text/css" rel="stylesheet" href="/stylesheets/index.css" />
    <script src="http://cdn.jquerytools.org/1.2.2/all/jquery.tools.min.js"></script>
    <script src="/javascripts/index.js" type="text/javascript"></script>
</head>
<body>

<@content.page title="${title}" currentPage="Homepage" sidebar="sidebar_main.ftl" logoutUrl="${logoutUrl}">

        <div class="images">
			<div>
				<img src="/images/my1_compress.jpg"/>
			</div>
			<div>
				<img src="/images/my2_compress.jpg"/>
			</div>
        </div>
		<div class="slidetabs">
			<a href="###">1</a>
			<a href="###">2</a>
		</div>
        <div class="post">
            <h1 class="welcome_title">Welcome to my website </h1>
            <div class="entry">
                <p>
                   <img src="/images/234_little_ps.jpg" alt="Luis Ashurei" class="left" />
                   <p class="welcome"><#noescape>${welcome}</#noescape></p>
                </p>
            </div>
            <div class="welcome_meta"></div>
        </div>

        <@articlelist.page />

</@content.page>

</@com.page>
</#escape>
