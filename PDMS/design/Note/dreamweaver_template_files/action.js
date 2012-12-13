var canSub = true;
function TexFocus(){
	if('block'!=$('code_pic').style.display){ 
		$('code_pic').src='/include/validateimg.php';
		$('code_pic').style.display='block';
	}
}
function submitForm(event) {
	try{
		if(event.ctrlKey&&event.keyCode==13)
		return true;
	}catch(event){}
	return false
}
function commSubmit(form)
{
	if (!canSub)return false;
	if ('请在这里输入您要发表的评论信息' == $('comment_text').value)
	{
		$('comment_text').focus();
		return false;
	}
	if ($('comment_text').value.length > 500 || $('comment_text').value.length < 4)
	{
		alert("评论字数必须在4到500个之间！");
		$('comment_text').focus();
		return false;
	}
	if (!(/^[a-z]|[0-9]|[A-Z]{4}$/.test($('validate').value)))
	{
		alert("请输入4位数的验证码！");
		$('validate').focus();
		return false;
	}
	canSub = false;
	$('sub').disabled = true;
	postfeeback();
}
//ajax load freeback list..
function loadfeeback(id,page){
	var URL = "/plus/ajax_feedback.php";
	var pars = 'arcID=' + id + '&nowpage='+page+'&action=show';
	new Ajax.Updater(
		{success: 'comm_content'},
		URL, {method: "get",parameters:pars}
	);	
}

//ajax submit data....
function postfeeback(){
	var opt = {
		method: 'post',
		postBody: 'action=send&arcID='+$('arcID').value
			+'&notuser=1&isconfirm=1&msg='+ escape($('comment_text').value)
			+'&username=' + escape($('username').value) + '&validate='+$('validate').value,
		onSuccess: function(t) {
			alert(t.responseText);
			//Element.update($('ajaxBackMsg'), t.responseText);
			loadfeeback($('arcID').value,1);
			canSub = true;
			if (t.responseText=="恭喜你，成功发表评论!"){
			$('comment_text').value = '';
			$('username').value = 'okAjaxer';
			$('validate').value = '';
			$('code_pic').src = '/include/validateimg.php?'+Math.random();
			}
			$('sub').disabled = false;
		},
		on404: function(t) {Element.update($('ajaxBackMsg'),'服务器错误，请稍后再试！');},
		onFailure: function(t) {Element.update($('ajaxBackMsg'),'服务器错误，请稍后再试！');}
	};
	new Ajax.Request("/plus/ajax_feedback.php", opt);
}

//ajax good and bad
function postGoodBad(i,a,d){
	var opt = {
		method: 'post',
		postBody: 'fid='+i+'&action='+a+'&arcID='+d,
		onSuccess: function(t) {Element.update($(i + 'span' + a), t.responseText);},
		on404: function(t) {},
		onFailure: function(t) {}
	};
	new Ajax.Request("/plus/ajax_feedback.php", opt);
}
