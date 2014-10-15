jQuery(document).ready(function() {
	jQuery('#selAll').click(function() {
		jQuery('.sel').each(function(){
	        jQuery(this).attr('checked', !jQuery(this).attr('checked'));
	    });
	});

	jQuery('#addCategory').click(function() {
		jQuery('#fCategory').attr("action", "?m=add");
	});
});

function beforeDel() {
    if (confirm("您确定要删除该信息？(Are you sure to delete ?)")) {
        return true;
    }
    return false;
}

function edit(tId,oldName){
    var newName = window.prompt("请输入新的分类名(Please input the type name)",oldName);
    if(newName!=null){
    	jQuery('#Category').val(newName);
    	jQuery('#fCategory').attr("action", "?m=upd&id="+tId);
    	jQuery('#fCategory').submit();
    }
}