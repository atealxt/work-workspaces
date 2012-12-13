
/**进入邮件编辑页面*/
function sendMail(m) {
	m.action = "/manage/vendition/UserSendEmail.jhtml";
	m.target = "_self";
	m.method.value = "editMail";
	m.submit();
}

