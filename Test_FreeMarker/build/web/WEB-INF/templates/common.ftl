<#macro page title>
  <html>
  <head>
    <title>Test_FreeMarker</title>
  </head>
  <body>
    <h1>${title?html}</h1>
    <hr>
    <#nested>
  </body>
  </html>
</#macro>