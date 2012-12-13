<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>test_tabbedPanel</title>
        <sx:head/>
        <script type="text/javascript">

        </script>
        

    </head>
    <body>
        <h3>Document   : test_tabbedPanel</h3>
        <h3>Created on : Jul 17, 2008, 2:07:31 PM</h3>
        <h3>Author     : Administrator</h3>   
<s:form action="Test_tabbedPanel">
<a href="<s:url action="Test_tabbedPanel" />">urltest</a>
        
<s:if test="%{mapTest != null}">
<script type="text/javascript">
   function selectTab(id) {
     var tabContainer = dojo.widget.byId("tabContainer");
     tabContainer.selectTab(id);
   }
</script>
                
<sx:tabbedpanel cssStyle="width: 500px; height: 200px;" doLayout="true" id="tabContainer">
   <sx:div label="基本信息" id="tab1" theme="ajax">               
        <table  align="center"  border="0" cellspacing="0">                                
            <tr>			
                <td width="20%">头像：</td>
                <td width="30%" align="right">
                    <a>选择</a>
                </td>
            </tr> 
            <tr>			
                <td >性别：</td>
                <td  align="right">
                    <s:select list="mapTest"></s:select>    
                </td>
            </tr>
            <tr>			
                <td ><s:token /></td>
                <td >
                    <input type="submit" id="submit" value="<s:text name="submit"/>" onclick="return checklogin()"/>
                </td>
            </tr>                 

        </table>        

   </sx:div>   
   <sx:div label="用户信息" id="tab2" theme="ajax">
       
        <table  align="center"  border="0" cellspacing="0">                                
            <tr>			
                <td width="20%">Email:</td>
                <td width="30%" align="right">
                    <input type="text" />
                </td>
            </tr> 
            <tr>			
                <td width="20%">问题提示：</td>
                <td width="30%" align="right">
                    <input type="text" />
                </td>
            </tr>
            <tr>			
                <td width="20%">问题答案：</td>
                <td width="30%" align="right">
                    <input type="text" />
                </td>
            </tr>            
            <tr>			
                <td ><s:token /></td>
                <td >
                    <input type="submit" id="submit" value="<s:text name="submit"/>" onclick="return checklogin()"/>
                </td>
            </tr>                 

        </table>       
   </sx:div>
   <sx:div label="登陆信息" id="tab3" theme="ajax">
        <table  align="center"  border="0" cellspacing="0">                                
            <tr>			
                <td width="20%">原密码：</td>
                <td width="30%" align="right">
                    <input type="password"  style="width:12em;" maxlength="16"/>
                </td>
            </tr> 
            <tr>			
                <td >新密码：</td>
                <td  align="right"><input type="password"  style="width:12em;" maxlength="16"/></td>
            </tr>
            <tr>			
                <td >请重复：</td>
                <td  align="right"><input type="password"  style="width:12em;" maxlength="16"/></td>
            </tr>            
            <tr>			
                <td ><s:token /></td>
                <td >
                    <input type="submit" id="submit" value="<s:text name="submit"/>" onclick="return checklogin()"/>
                </td>
            </tr>                 

        </table>        
   </sx:div>   
</sx:tabbedpanel>

<input type="button" onclick="selectTab('tab1')" value="Select tab 1" />
<input type="button" onclick="selectTab('tab2')" value="Select tab 2" />                
</s:if>        
<br/><input type="submit" value="submit"/> 

</s:form>  


</body>
</html>
