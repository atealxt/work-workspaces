<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<table width="100%" height="59" border="0" cellpadding="0"
	cellspacing="0" background="/manage/images/bg.gif">
	<tr>
		<td width="68%">
			<table id="Table_01" width="282" height="59" border="0"
				cellpadding="0" cellspacing="0">
				<tr>
		           <td rowspan="3"><img src="/manage/images/admin_01.jpg" width="75" height="59" alt="" /></td>
		           <td><img src="/manage/images/admin_02.jpg" width="207" height="17" alt="" /></td>
		         </tr>
		         <tr>
		           <td><img src="/manage/images/admin_03.jpg" width="207" height="27" alt="" /></td>
		         </tr>
		         <tr>
		           <td><img src="/manage/images/admin_04.jpg" width="207" height="15" alt="" /></td>
		         </tr>
			</table>
		</td>
		<td width="32%" valign="bottom">
			<table width="284" height="32" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="71" align="center" valign="middle" class="bg2"><a href="/manage/login/index.jhtml"><strong>后台首页</strong></a></td>
		            <td width="71" align="center" valign="middle" class="bg2"><a href="/" target="_blank"><strong>网站首页</strong></a></td>
		            <td width="71" align="center" valign="middle" class="bg2"><a href="/manage/system/SystemConfig.jhtml?method=edit"><strong>系统设置</strong></a></td>
		            <td width="71" align="center" valign="middle" class="bg2"><a href="/manage/login/index.jhtml?method=logout"><strong>系统退出</strong></a></td>
				</tr>
				<tr>
					<td>
						<jsp:include flush="true" page="/include/warn.jsp" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>