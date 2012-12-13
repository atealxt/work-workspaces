<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">                   
        <title><s:text name="bbsName"/></title>
        <link rel="stylesheet" href="../common/common.css" type="text/css" media="all">         
        <script src="../common/common.js" type="text/javascript"></script>
        <script type="text/javascript">  
            
        </script>        
    </head>
    <body class="">        
        
        <s:form id="main" name="main" action="" onsubmit="this.submit.disabled='true';">        
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
                   <a class="link1" href="Usercenter.action">用户中心</a>
                   <a class="link1" href="logout.jsp?station=index">退出</a>
               </s:if>   
               <s:else>
                   <a class="link1" 
                        href="login.jsp?station=index">登陆</a>
                   <a class="link1" >注册</a>                       
               </s:else>
            </div>

           <div class="divType2">
               主题:<span class="spanType1">${requestScope.TitleCount}</span>
               帖子:<span class="spanType1">${requestScope.ReplyCount}</span>
               共<span class="spanType1">${requestScope.UserCount}</span>位会员
               欢迎新会员:<span class="spanType1">${requestScope.LatestUserName}</span>
           </div>	       		       	

           </td></tr>
    	</table>
    	
    	<!--main-->          
    	<table class="main1" align="center"  border="1" cellspacing="0">
    	<tr><td>
            <s:iterator value="listTopicarea" status="listStat">
                                
                <div class="divType3">     		
                    <fieldset>
                    <legend>论坛</legend>
                    <table  align="left"  border="1" cellpadding="3">
                        <s:iterator status="listStat">
                            
                            <tr><td>
                                <s:if test='#listStat.odd == true'>
                                    <div class="divType4">
                                        <a class="link2" href="topicarea.jsp?areaId=<s:property value='id'/>&page=1">
                                        <s:property value="name"/></a>
                                    </div>
                                </s:if>
                                <s:else>
                                    <div class="divType5">
                                        <a class="link2" href="topicarea.jsp?areaId=<s:property value='id'/>&page=1">
                                        <s:property value="name"/></a>
                                    </div>
                                </s:else>
                                
                            </td></tr>
                            
                        </s:iterator>                                					
                    
                    </table> 
                    </fieldset>				       			       		
                </div>
                
            </s:iterator>
	</td></tr>
    	</table>    	
        </s:form>
        
    </body>
</html>