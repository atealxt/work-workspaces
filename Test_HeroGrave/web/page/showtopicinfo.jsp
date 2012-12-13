<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">                   
        <title><s:property value="topictitle.title"/></title>
        <link rel="stylesheet" href="../common/common.css" type="text/css" media="all">         
        <script src="../common/common.js" type="text/javascript"></script>
        <script type="text/javascript">           
            function headSubmit(){
                document.getElementById("head").submit();
            }
            function headSubmit2(){
                window.location = window.location.toString().replace("Topicinfo.action","topicarea.jsp") 
                    +"?areaId=" + ${param.areaId} + "&page=1";
            }            
            function pageSel(evt){                
                if(pageChk(evt)){
                    var page = $("text1").value;
                    window.location = window.location.toString().replace("Topicinfo.action","topicinfo.jsp") 
                        +"?title=" + ${param.title} +"&areaId=" + ${param.areaId} + "&page=" + page;
             	}                
            }            
        </script>        
    </head>
    <body class="">        
        
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
                   <a class="link1" href="logout.jsp?areaId=${param.areaId}&title=${param.title}&page=1&station=topicinfo">退出</a>
               </s:if>   
               <s:else>
                    <a class="link1" 
                        href="login.jsp?areaId=${param.areaId}&title=${param.title}&page=1&station=topicinfo">登陆</a>
                    <a class="link1" >注册</a>                                                                 
               </s:else>
            </div>

        <s:form id="head" action="Topicinfo.action" onsubmit="this.submit.disabled='true';">            
        <input type="hidden" id="areaId" name="areaId" value="${param.areaId}"/>
        <input type="hidden" id="page" name="page" value="${param.page}"/> 
        <input type="hidden" id="title" name="title" value="${param.title}"/>
        <input type="hidden" id="station" name="station" value="topicinfo"/>

            <div class="divType2">
               <span class="spanType1"><a class="link1" href="Index.action"><s:text name="bbsName"/></a></span>
               <span class="spanType1"><a class="link3" onclick="headSubmit2()"><s:property value="topicarea.name"/></a></span>
               <span class="spanType1"><a class="link3" onclick="headSubmit()"><s:property value="topictitle.title"/></a></span>
            </div>	       		       	
        </s:form>

            </td></tr>
    	</table>
                         
    	
    	<!--page select-->
    	<table class="pageselect" align="center"  border="0" cellspacing="0">   			
            <tr height="30">
                <td width="2500">     		
                    ${param.page} / <s:property value="pageCount"/>页

                    <s:if test="%{#request.pageCount != null}">
                    <s:bean name="org.apache.struts2.util.Counter">           
                       <s:param name="last" value="#request.pageCount" />
                       <s:iterator>
                            <a class="link4" href="topicinfo.jsp?title=${param.title}&areaId=${param.areaId}&page=<s:property/>">
                                <s:property/>
                            </a>
                       </s:iterator>
                    </s:bean> 
                    </s:if>

                    <!--»-->
                    跳转到第<input type="text" id="text1" size="3"  maxlength="3" onkeydown="pageSel(event)"  />页     			       		
                </td>
                <td align="center" width="12%"><a class="link4" >发布新帖</a> <a class="link4" >回复主题</a></td>
            </tr>	       	      		       	 
    	</table>
        
    	<!--main-->
    	<table class="main1" align="center"  border="1" cellspacing="0">
            <tr>
            <td>	

            <tr height="30">
                <td><div class="titlebar"><b><s:property value="topictitle.title"/></b></div></td>											
            </tr>

            <tr><td>	
            <table align="left" cellpadding="0" border="0">							
            <tr>			
                <td class="titleborder3" align="center" width="15%"><s:property value="createUser.name"/></td>
                <td class="titleborder3" width="80%"><s:date name="topictitle.createtime" format="yyyy/MM/dd HH:mm:ss" /></td>			
                <td class="titleborder2" width="5%">楼主</td>
            </tr>
            <tr>
                <td class="titleborder" align="left">
                    <div class="userinfo">
                        <img src="../<s:property value="createUserinfo.iconpath"/>"><br/><br/>
                        <s:if test="createUser.userlevel.id == 0">坛主</s:if>
                        <s:elseif test="createUser.userlevel.id == 1">管理员</s:elseif>
                        <s:elseif test="createUser.userlevel.id == 2">版主</s:elseif>
                        <s:else>会员</s:else><br/>
                        头衔: <s:property value="creatUserlevelshow.name"/>
                            <s:if test="createUserinfo.sex == 1">
                                <img src="../picture/default/sex_male.gif">
                            </s:if>
                            <s:elseif test="createUserinfo.sex == 2">
                                <img src="../picture/default/sex_female.gif">
                            </s:elseif>
                            <s:else/>
                            <br/>
                        等级: 
                            <s:if test="%{#request.creatUserlevelshow != null}">
                            <s:bean name="org.apache.struts2.util.Counter">           
                               <s:param name="last" value="creatUserlevelshow.id" />
                               <s:iterator>
                                   <img src="../picture/default/level.gif">
                               </s:iterator>
                            </s:bean></s:if><br/>
                        文章: <s:property value="createUserinfo.article"/><br/>                            
                        积分: <s:property value="createUserinfo.score"/><br/>                        
                        注册日期：<s:date name="createUser.regtime" format="yyyy/MM/dd" /><br/>
                    </div>	
                </td>	
                <td class="titleborder4"><div class="defaultcontent">
                        <h3><s:property value="topictitle.title"/></h3>
                        <s:property value="topictitle.title_text"/>
                     </div>
                </td>
           </tr>										
           </table> 	
           </td></tr>	

           <tr><td><div class="fengexian"></div></td></tr>	
           
           <s:iterator value="replyInfo" status="listStat">
            <tr><td>	
            <table align="left" cellpadding="0" border="0">							
            <tr>			
                <td class="titleborder3" align="center" width="15%"><s:property value="user.name"/></td>
                <td class="titleborder3" width="80%"><s:date name="topicinfo.replytime" format="yyyy/MM/dd HH:mm:ss" /></td>			
                <td class="titleborder2" width="5%">第<s:property value="#listStat.index + 1"/>楼</td>
            </tr>
            <tr>
                <td class="titleborder" align="left">
                    <div class="userinfo">
                        <img src="../<s:property value="userinfo.iconpath"/>"><br/><br/>
                        <s:if test="user.userlevel.id == 0">坛主</s:if>
                        <s:elseif test="user.userlevel.id == 1">管理员</s:elseif>
                        <s:elseif test="user.userlevel.id == 2">版主</s:elseif>
                        <s:else>会员</s:else><br/>                        
                        头衔: <s:property value="userlevelshow.name"/>
                            <s:if test="userinfo.sex == 1">
                                <img src="../picture/default/sex_male.gif">
                            </s:if>
                            <s:elseif test="userinfo.sex == 2">
                                <img src="../picture/default/sex_female.gif">
                            </s:elseif>
                            <s:else/>
                            <br/>
                        等级: 
                            <s:if test="%{#request.userlevelshow != null}">
                            <s:bean name="org.apache.struts2.util.Counter">           
                               <s:param name="last" value="userlevelshow.id" />
                               <s:iterator>
                                   <img src="../picture/default/level.gif">
                               </s:iterator>
                            </s:bean></s:if><br/>
                        文章: <s:property value="userinfo.article"/><br/>                            
                        积分: <s:property value="userinfo.score"/><br/>                        
                        注册日期：<s:date name="user.regtime" format="yyyy/MM/dd" /><br/>                        
                    </div>	
                </td>	
                <td class="titleborder4"><div class="defaultcontent">
                        <h3>回复:<s:property value="topictitle.title"/></h3>
                        <s:property value="topicinfo.text" escape="false" />
                     </div>
                </td>
            </tr>										
            </table> 	
            </td></tr>	

            <tr><td><div class="fengexian"></div></td></tr>
           </s:iterator>
           
           <s:if test="%{#session.USER != null}">
           <s:form action="Reply.action" onsubmit="this.submit.disabled='true';"> 
            <input type="hidden" id="areaId" name="areaId" value="${param.areaId}"/>
            <input type="hidden" id="page" name="page" value="${param.page}"/> 
            <input type="hidden" id="title" name="title" value="${param.title}"/> 
            <input type="hidden" id="urlLocation" name="urlLocation"/>
            
            <script type="text/javascript"> 
                //var vTem = "areaId=" + $("areaId").value + "&page=" + $("page").value + "&title=" + $("title").value;
                var vTem = "areaId=" + $("areaId").value + "&title=" + $("title").value;
                $("urlLocation").value = "topicinfo.jsp?" + vTem;          
            </script>            
            
            <tr><td>	
            <table align="left" cellpadding="0" border="0">							

            <tr>
                <td class="titleborder" align="right" width="15%"></td>	
                <td width="85%">
                    <div class=""><b>快速回复:</b>
                        <textarea class="" id="tb_ReplyBody" name="tb_Reply" rows="2" cols="20" style="height: 230px; width: 100%;"></textarea>
                        <input type="submit" id="sub_Reply" value="<s:text name="submit"/>" onclick="return replyChk('tb_ReplyBody')" />
                        <input type="reset" id="sub_Clean" value="清除" onclick="" />
                        <s:token />
                    </div>
                </td>
           </tr>										
           </table> 	
           </td></tr>               
           </s:form>               
           </s:if>


           </td>
           </tr>   
    	</table>        
        


    </body>
</html>