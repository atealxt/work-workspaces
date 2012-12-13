<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>皇家金典后台管理—文章类别管理</title>
		<link href="/css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/JavaScript">
		<!--
			
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
												<input type="hidden" name="method" value="save" />
												<html:hidden property="pageno"/>
												<html:hidden property="initial"/>
												<html:hidden property="classId" />
												<table width="100%" height="183" border="0" cellpadding="0"
													align="center" cellspacing="0">
													<tr align="center">
														<td height="39" colspan="4">
															<strong>文章类别管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>
														</td>
													</tr>
													<tr>
														<td align="right" width="250">
															类别名称:&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td align="left">
															<html:text property="className" styleClass="reg_stedit" size="50"></html:text>
															<font color="red">*</font>
														</td>
														
													</tr>
													<tr>
														<td align="right">
															类别说明:&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td align="left">
															<html:textarea property="classMark" style="width:300px;height:150px;" styleClass="reg_stedit" ></html:textarea>
														</td>
													</tr>
												</table>
												<table height="40" border="0" align="center" cellpadding="0"
													cellspacing="0">
													<tr>
														<td>
															&nbsp;
														</td>
													</tr>
													<tr>
														<td>
															<input type="submit" value=" 保存资料 " class="reg_stedit"/>
															<input type="button" value=" 返回上页 " onclick="javascript:history.back(1);" class="reg_stedit"/>
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
														</td>
													</tr>
												</table>
											</html:form>
											<table width="98%" border="0" align="center" cellpadding="0"
												cellspacing="1" bgcolor="#BD9768">
												<tr>
													<td bgcolor="#F9F3E0" style="padding:25px;">
														此处提供对文章类别的
														<b>编辑</b>功能。
															注意 
														<font color="red">*</font>为必填项
													</td>
												</tr>
											</table>
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
