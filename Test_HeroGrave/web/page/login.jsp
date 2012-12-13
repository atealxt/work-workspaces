<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登陆</title>
        <link rel="stylesheet" href="../common/common.css" type="text/css" media="all">
        <script src="../common/common.js" type="text/javascript"></script>
        <script type="text/javascript"> 
            function checklogin(){
                if($("username").value.replace(/(^\s*)|(\s*$)/g, "")==""){
                    alert("请输入用户名");
                    $("username").focus();
                    return false;
                }
                
                if($("psw").value.replace(/(^\s*)|(\s*$)/g, "")==""){
                    alert("请输入密码");
                    $("psw").focus();
                    return false;
                }                
                
                return true;
            }
        </script>
        
    </head>
    <body onload="saveLocationUrl()">
        <s:form action="Login.action" onsubmit="this.submit.disabled='true';">
            <input type="hidden" id="areaId" name="areaId" value="${param.areaId}"/>
            <input type="hidden" id="page" name="page" value="${param.page}"/> 
            <input type="hidden" id="title" name="title" value="${param.title}"/>
            <input type="hidden" id="station" name="station" value="${param.station}"/>
            <input type="hidden" id="urlLocation" name="urlLocation" />
            
            <table class="head1" align="center" border="1" cellspacing="1">
            <tr><td align="left">

            <div class="head_left"> 
                logo <br/> 
                <s:text name="welcomeMSG"/>
            </div> 
            
            <div class="head_right"></div>
            
            <div class="divType2">
               <span class="spanType1"><a class="link1" href="Index.action"><s:text name="bbsName"/></a></span>
               
            </div>            
            
            </td></tr>
            
            </table>
            <br/><br/>
            
            <s:if test="%{#request.loginerror != null}">
                <table  align="center"  border="0" cellspacing="0">                                
                    <tr>			
                        <td align="center" ><h3>用户名或密码错误！</h3></td>
                    </tr>                                 
                </table>                
            </s:if>            
            
            <table  align="center"  border="0" cellspacing="0">                                
                <tr>			
                    <td width="80">用户名:</td>
                    <td width="100" align="right"><input type="text" id="username" name="username" style="width:12em;" maxlength="8"/></td>
                </tr> 
                <tr>			
                    <td width="80">密码:</td>
                    <td width="100" align="right"><input type="password" id="psw" name="psw" style="width:12em;" maxlength="16"/></td>
                </tr>
                <!--
                <tr>			
                    <td width="80">验证码:</td>
                    <td width="100" align="left">
                        <input type="text" id="chkcode" name="chkcode" style="width:56px;" maxlength="4" />
                    </td>
                </tr>     
                -->
                <tr>			
                    <td width="80"><s:token /></td>
                    <td width="100" align="right">
                        <input type="submit" id="submit" value="<s:text name="submit"/>" onclick="return checklogin()"/>
                    </td>
                </tr>                 
                
            </table>
            
        </s:form>            
        
    </body>
</html>
