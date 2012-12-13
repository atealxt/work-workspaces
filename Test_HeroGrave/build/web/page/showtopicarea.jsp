<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">                   
        <title><s:property value="topicarea.name"/></title>
        <link rel="stylesheet" href="../common/common.css" type="text/css" media="all">         
        <script src="../common/common.js" type="text/javascript"></script>
        <script type="text/javascript">           
            function headSubmit(){
                document.getElementById("head").submit();
            }
            function pageSel(evt){
             	if(pageChk(evt)){    
                    var page = $("text1").value;                    
                    window.location = window.location.toString().replace("Topicarea.action","topicarea.jsp") 
                        +"?areaId=" + ${param.areaId} + "&page=" + page;
             	}                
            }            
        </script>        
    </head>
    <body class="">        
        
        <s:form id="head" action="Topicarea.action" onsubmit="this.submit.disabled='true';">
            
        <input type="hidden" id="areaId" name="areaId" value="${param.areaId}"/>
        <input type="hidden" id="page" name="page" value="${param.page}"/>        
    	
    	<!--head-->        
        <table class="head1" align="center" border="1" cellspacing="1">
            <tr><td align="left">

            <div class="head_left"> 
                logo <br/> 
                <s:text name="welcomeMSG"/>
            </div>

            <div class="head_right">
               <s:if test="%{#session.USER != null}">
                   欢迎:${sessionScope.USER.name}
                   <a class="link1" >用户中心</a>
                   <a class="link1" href="logout.jsp?areaId=${param.areaId}&page=${param.page}&station=topicarea">退出</a>
               </s:if>   
               <s:else>
                   <a class="link1" 
                        href="login.jsp?areaId=${param.areaId}&page=${param.page}&station=topicarea">登陆</a>
                   <a class="link1" >注册</a>                       
               </s:else>
            </div>

            <div class="divType2">
               <span class="spanType1"><a class="link1" href="Index.action"><s:text name="bbsName"/></a></span>
               <span class="spanType1"><a class="link3" onclick="headSubmit()"><s:property value="topicarea.name"/></a></span>
            </div>	       		       	

            </td></tr>
    	</table>
        </s:form>
    	
    	<!--page select-->
    	<table class="pageselect" align="center"  border="0" cellspacing="0">   			
            <tr height="30">
                    <td width="2500">     		
                            ${param.page} / <s:property value="pageCount"/>页
                            
                            <s:set name="a" value="1" />
                            <s:iterator value="listpageCount">
                                <a class="link4" href="topicarea.jsp?areaId=${param.areaId}&page=${param.page}">
                                    <s:property value="#a"/>
                                </a>
                                <s:set name="a" value="#a+1"/>
                            </s:iterator>
                            
                            <!--»-->
                            跳转到第<input type="text" id="text1" size="3"  maxlength="3" onkeydown="pageSel(event)"  />页     			       		
                    </td>
                    <td align="center" width="6%"><a class="link4" >发布新帖</a></td>
            </tr>	       	      		       	 
    	</table>

    	<!--main-->
    	<table class="main1" align="center"  border="1" cellspacing="0">
            <tr><td>	
            <table align="center"  border="0" cellpadding="3" height="30">
            <tr height="30">
                <td width="2500">标题</td>
                <td width="15%">作者</td>
                <td width="10%">回复/查看</td>
                <td width="15%">最后发布</td>											
            </tr>
            </table> 	
            </td></tr> 

            <tr><td>	
            <table  align="left"  border="0" cellpadding="3">
            <tr><td>
                <s:iterator value="listTopictitle">
                    <tr class="list" onmouseover="this.className='liston'" onmouseout="this.className='list'">
                    <td width="2500">
                        <a class="link4" onmouseover="this.className='link5'" onmouseout="this.className='link4'" 
                            href="topicinfo.jsp?title=<s:property value="id"/>&areaId=${param.areaId}&page=1">
                        <s:property value="title"/></a>
                    </td>
                    <td width="15%">
                        <a class="link4">
                        <s:property value="createUser"/></a>
                    </td>
                    <td width="10%"><s:property value="replycount"/>/<s:property value="viewcount"/></td>
                    <td width="15%">
                        <s:property value="latestreplyusername"/><br/>                        
                        <s:date name="latestreplytime" format="yyyy/MM/dd HH:mm:ss" />
                    </td>
                    </tr>                        
                </s:iterator>					
             </td></tr>
             </table> 	
           </td></tr>	         
	         
    	</table>



        
    </body>
</html>