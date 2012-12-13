<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>usercenter</title>
        <link rel="stylesheet" href="../common/common.css" type="text/css" media="all">
        <script src="../common/common.js" type="text/javascript"></script>
        
        <link rel="stylesheet" type="text/css" href="../extjs/resources/css/ext-all.css" /> 
        <script type="text/javascript" src="../extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="../extjs/ext-all.js"></script> 
        <script type="text/javascript">
            var tabs;
            Ext.onReady(ExtInit);

            function ExtInit() {
                tabs = new Ext.TabPanel({
                    renderTo: 'divtab',
                    width:450,
                    height:200,
                    activeTab: 0,
                    frame:true,
                    items:[
                        {contentEl:'baseinfo', title: '基本信息'},
                        {contentEl:'pswinfo', title: '密码'}
                    ]
                });
                
                Ext.get('btnImg').on('click', selectIMG );
                
                Ext.get('btnEma').on('click', function(e){
                    Ext.MessageBox.prompt('Email', '请输入Email:', saveEmail);
                });                
                
            }

            
        </script>
        
    </head>
    <body onload="">
        
        <table class="head1" align="center" border="1" cellspacing="1">
        <tr><td align="left">

        <div class="head_left"> 
            logo <br/> 
            <s:text name="welcomeMSG"/>
        </div> 

        <div class="head_right">
            欢迎:${sessionScope.USER.name}
            <a class="link1" href="logout.jsp?station=index">退出</a>
        </div>

        <div class="divType2">
           <span class="spanType1"><a class="link1" href="Index.action"><s:text name="bbsName"/></a></span>
        </div>

        </td></tr>
        </table>
        <br/><br/>

        <!--main-->
        <table class="main1" align="center"  border="1" cellspacing="0">
            <tr>
            <td>	
            
            <tr><td>	
            <table align="left" cellpadding="0" border="0">							
            <tr>			
                    <td class="titleborder3" width="15%">
                        <span class="userinfo">
                            <s:if test="user.userlevel.id == 0">坛主:</s:if>
                            <s:elseif test="user.userlevel.id == 1">管理员:</s:elseif>
                            <s:elseif test="user.userlevel.id == 2">版主:</s:elseif>
                            <s:else>会员:</s:else>
                            <s:property value="user.name"/>                       
                        </span>
                    </td>
                    <td class="" width="80%"></td>			
                    <td class="" width="5%"></td>
            </tr>
            <tr>
                <td class="titleborder" align="left">
                    <div class="userinfo">
                        <img src="../<s:property value="userinfo.iconpath"/>"><br/><br/>
                        头衔: <s:property value="userinfo.userlevelshow.name"/>
                            <s:if test="userinfo.sex == 1">
                                <img src="../picture/default/sex_male.gif">
                            </s:if>
                            <s:elseif test="userinfo.sex == 2">
                                <img src="../picture/default/sex_female.gif">
                            </s:elseif>
                            <s:else/><br/>                        
                        等级:
                            <s:if test="%{userinfo.userlevelshow != null}">
                            <s:bean name="org.apache.struts2.util.Counter">           
                               <s:param name="last" value="userinfo.userlevelshow.id" />
                               <s:iterator>
                                   <img src="../picture/default/level.gif">
                               </s:iterator>
                            </s:bean></s:if><br/>                        
                        文章:<s:property value="userinfo.article"/><br/>
                        积分:<s:property value="userinfo.score"/><br/>
                        金钱:<s:property value="userinfo.money"/><br/>
                        在线时间:<s:property value="userinfo.online_time"/>h<br/>
                        注册日期:<s:date name="user.regtime" format="yyyy/MM/dd" /><br/>

                    </div>	
                </td>	
                <td width="500">
                    
                    <s:form action="UpdateUserInfo.action">
                    <table> <tr><td>
                    <div class="link1"><b>资料变更：</b></div>
                    <div id="divtab" class="defaultcontent">
                        <div id="baseinfo"  class="x-hide-display">
                            <table  align="left"  border="0" cellspacing="5">  
			            <tr>			
			                <td>头像: <img id="imgid" src=""><input type=hidden id="hiddenIMG" name="hiddenIMG"/></td>
			                <td width="50" class="titleborder5"><input type="button" id="btnImg" style="width:5em;" value="选择"/></td>
			            </tr> 
			            <tr>			
			                <td>性别: </td>
					  <td width="50" class="titleborder5">
					   	<select name="sexsel" id="sexsel" style="width:5em;"> 
			                			<option value="0"> 保密</option>
                                                                <s:if test="userinfo.sex == 1">
                                                                    <option value="1" selected> 男</option><option value="2"> 女</option>
                                                                </s:if>
                                                                <s:elseif test="userinfo.sex == 2">
                                                                    <option value="1"> 男</option><option value="2" selected> 女</option>
                                                                </s:elseif>
			                	</select>					   	
					   </td>
			            </tr>	
			            <tr>			
			                <td>Email: <input type="text" name="txtEmail" id="txtEmail"value="<s:property value="user.email"/>" style="width:10em;" readonly/></td>
			                <td width="50" class="titleborder5"><input type="button" id="btnEma" style="width:5em;" value="变更"/></td>
			            </tr> 			            		            
                             </table>                            
                        </div>
                        <div id="pswinfo"  class="x-hide-display">
                            <table  align="left"  border="0" cellspacing="5">  
			            <tr>			
			                <td align="right">
			                	原密码: <input type="password" name="pswOld" style="width:12em;" maxlength="16">
			                </td>
			                <td width="50" class="titleborder5"><input type="button" id="btnOldp" style="width:5em;" value="忘记了?" onclick="" disabled/></td>
			            </tr>
			            <tr>			
			                <td align="right">
			                	新密码: <input type="password" name="pswNew" style="width:12em;" maxlength="16">
			                </td>
			            </tr>	
			            <tr>			
			                <td align="right">
			                	重复: <input type="password" name="pswRep" style="width:12em;" maxlength="16">
			                </td>
			            </tr>		            
                             </table>                            
                        </div>                        
                    </div>
                    </td> </tr>
                    <tr><td align="right"><input type="submit" id="subUpdate" value="<s:text name="submit"/>" onclick="" />&nbsp;&nbsp;</td></tr>
                    </table>
                    </s:form>
                    
                </td>
            </tr>            
            
            </table> 	
           </td></tr>	


           </td>


           </tr> 
        </table>    
    </body>
</html>
