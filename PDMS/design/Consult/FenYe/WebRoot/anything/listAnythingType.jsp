<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/papa.tld" prefix="papa"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>皇家金典后台管理—类别管理</title>
		<link href="/css/admin.css" rel="stylesheet" type="text/css" />
		<script language="javascript" type="text/javascript"
			src="/script/web.js"></script>
		<script type="text/JavaScript">
		<!--
			/**搜索*/
			function search()
			{
				var m=document.AnythingTypeForm;
				m.initial.value=true;
				m.submit();			
			}
			
			/**添加*/
			function add()
			{
				var m=document.AnythingTypeForm;
				var url=m.action+"?method=add";
				document.location.href=url;
			}
			/**编辑*/
			function edit()
			{
				var m=document.AnythingTypeForm;
				if(isCheck(m.refID))
				{
					var idList=choose(m,'refID');
					var id=getFirstID(idList);
					var url=m.action+"?method=edit&classId="+id;
					if(idList.indexOf(",")!=-1)
					{
						if(window.confirm("你选择了多个类别,系统将只对你选择的第一个类别进行修改!"))
							document.location.href=url;
					}
					else
						document.location.href=url;
				}
				else
				{
					alert("你必须选中一项进行此操作!!");
					return false;
				}
			} 
			/**delete*/
			function del()
			{
				var m=document.AnythingTypeForm;
				if( m.refID==null) return;
				if(isCheck(m.refID))
				{
					if(window.confirm("您确定要删除选中的类别吗？"))
					{
						m.method.value='delete';
						m.target="_self"
						m.submit();
					}
					else
						return false;
				}
				else
				{
					alert("你必须至少选中一项进行此操作!!");
					return false;
				}
			}
			
		//-->
		</script>
		<style type="text/css">
		<!--
			
		-->
		</style>
	</head>
	<body>
		<table width="100%" height="327" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="63" valign="top">
					<jsp:include flush="true" page="../include/adminTop.jsp"></jsp:include>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" height="322" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td width="18%" valign="top">
								<jsp:include flush="true" page="../include/adminLeft.jsp"></jsp:include>
							</td>
							<td width="82%" valign="top">
								<table width="99%" height="657" border="0" align="right"
									cellpadding="0" cellspacing="1" bgcolor="#BD9768">
									<tr>
										<td valign="top" bgcolor="#FFFFFF">
											<!-------------------- main body begin here ---------------------->
											<html:form action="/anything/AnythingType">
												<input type="hidden" name="method" value="unspecified" />
												<table width="180" height="3" border="0" align="center"
													cellpadding="0" cellspacing="0">
													<tr>
														<td></td>
													</tr>
												</table>
												<table width="100%" height="28" align="center"
													cellpadding="0" cellspacing="0"
													style="border:1px #CCCCCC solid;">
													<tr>
														<td nowrap height="20" align="right">
															建档日期：
														</td>
														<td>
															<html:text property="beginDate" size="10"
																styleClass="reg_stedit" />
															到
															<html:text property="endDate" size="10"
																styleClass="reg_stedit" />
														</td>
														<td nowrap height="20" align="right">
															搜索类别：
														</td>
														<td>
															<html:select property="searchType" styleClass="select"
																style="width:50px;">
																<html:option value="0">&nbsp;</html:option>
																<html:option value="1">标题</html:option>
																<html:option value="2">备注</html:option>
															</html:select>
														</td>
														<td nowrap height="20" align="right">
															关键字：
														</td>
														<td align="left">
															<html:text property="searchValue" size="20"
																styleClass="reg_stedit" />
														</td>
														<td align="left">
															<input type="button" value=" 搜 索 " onclick="search();"
																class="reg_stedit" />
														</td>
													</tr>
												</table>
												<table width="98%" height="28" border="0" align="center"
													cellpadding="0" cellspacing="1">
													<tr>
														<td bgcolor="#F9F3E0">
															<table width="100%" height="34" border="0" align="center"
																cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
																<tr>
																	<td>
																		<table height="30" border="0" cellpadding="0"
																			cellspacing="4" bgcolor="#FFFFFF">
																			<tr>
																				<td width="63" align="center">
																					<input type="button" value=" 添加 " onclick="add();"
																						class="reg_stedit" />
																				</td>
																				<td width="63" align="center">
																					<input type="button" value=" 修改 " onclick="edit();"
																						class="reg_stedit" />
																				</td>
																				<td width="63" align="center">
																					<input type="button" value=" 删除 " onclick="del();"
																						class="reg_stedit" />
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
												<table width="98%" border="0" align="center" cellpadding="0"
													cellspacing="1" bgcolor="#CCCCCC">
													<tr bgcolor="#F9F3E0">
														<td height="25" align="center" bgcolor="#F1E3B8">
															选择
														</td>
														<td align="center" bgcolor="#F1E3B8">
															类别名称
														</td>
														<td align="center" bgcolor="#F1E3B8">
															类别说明
														</td>
														<td align="center" bgcolor="#F1E3B8">
															建档日期
														</td>
													</tr>
													<c:forEach items="${list}" var="type">
														<tr>
															<td>
																<input type="checkbox" name="refID"
																	value="${type.classId}" />
															</td>
															<td align="center" bgcolor="#FFFFFF">
																${type.className}
															</td>
															<td align="center" bgcolor="#FFFFFF">
																${type.classMark}
															</td>
															<td align="center" bgcolor="#FFFFFF">
																${type.createDate}
															</td>
														</tr>
													</c:forEach>
												</table>
												<table width="100%"> 
													<tr>
														<td align="left" valign="top">
															&nbsp;&nbsp;&nbsp;
															<input type="checkbox" name="selSwap"
																style="border:0px;background-color:#FFFFFF" value="all"
																onclick="selChkBox(this.form,this,'refID');" />
															全选
														</td>
														<td align="right"> 
															<papa:pager pager="${pager}" form="AnythingTypeForm"/>
														</td>
													</tr>
												</table>
												<table width="98%" border="0" align="center" cellpadding="0"
													cellspacing="1" bgcolor="#BD9768">
													<tr>
														<td bgcolor="#F9F3E0" style="padding:25px;">
															<b>操作说明：</b>此处提供对类别的管理功能。
														</td>
													</tr>
												</table>
												<table width="773" height="5" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td width="181"></td>
													</tr>
												</table>
											</html:form>
											<!-------------------- main body end here ---------------------->
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>

</html>
