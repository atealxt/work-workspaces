<DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假流程Demo</title>
</head>
<body>

<a href="ask4leave">refresh</a>
<a href="ask4leave?action=deploy">deploy</a>

<span style="float:right;">
    <#if USERNAME?exists>Hello <b>${USERNAME}</b>, you can <a href="login.ftl">Sign in again.</a></#if>
</span>
<hr>
流程定义<br>
<#if processDefinitions?exists>
    <#list processDefinitions as definition>
        id: ${definition.id} | name: ${definition.name} | version: ${definition.version}
        | <a href="ask4leave?action=del&pid=${definition.deploymentId}">remove</a>
        | <a href="ask4leave?action=start&id=${definition.id}">start</a>
        <br>
    </#list>
</#if>
<hr>
流程实例<br>
<#if processInstances?exists>
    <#list processInstances as instance>
        id: ${instance.id} |
        activity: <#list instance.findActiveActivityNames() as activeActivity>${activeActivity} </#list> |
        state: ${instance.state}
        | <a href="viewInstance.ftl?id=${instance.id}">view</a>
        <br>
    </#list>
</#if>
<hr>
待办任务<br>
<#if tasks?exists>
    <#list tasks as task>
        id: ${task.id} | name: ${task.name}
        <#if task.getFormResourceName()?exists>| <a href="${task.getFormResourceName()}&id=${task.id}">excute</a></#if>
        <br>
    </#list>
</#if>
<hr>
历史流程<br>
<#if historyProcess?exists && historyProcess?size != 0>
    <#list historyProcess as h>
        id: ${h.processInstanceId} |
        state: ${h.state} |
        duration: <#list duration as d><#if d_index == h_index>${d}</#if></#list> |
        endActivityName: <#list endActivityName as n><#if n_index == h_index>${n}</#if></#list>
        <br>
    </#list>
</#if>

</body>
</html>