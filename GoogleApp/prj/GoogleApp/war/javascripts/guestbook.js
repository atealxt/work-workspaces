function beforeSubmit() {
    tinyMCE.triggerSave();
    var len = cmnApi.delHtmlTag(jQuery('#content').val()).length;
    if (len < 5 || len > 5000) {
        alert("Content length must between 5 and 5000!\n文字长度错误!");
        return false;
    }

    cmnApi.disableWhenSubmiting(document.getElementsById("submit"));
    return true;
}