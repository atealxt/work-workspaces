jQuery(document).ready(function() {
    _updReadCnt(articleId);
    _getComments(articleId);

    jQuery('#rep_content').keydown(function(event) {
        if (!event.ctrlKey || event.keyCode != 13) {
            return;
        }
        jQuery('#submit').click();
    });
});

function _updReadCnt(articleId) {
    var url = '/article/' + articleId;
    jQuery.post(url);
}

function _getComments(articleId) {
    jQuery.get("/comment", {
        id : articleId
    }, function(data) {
        jQuery('#cmt_content').html(data);
        cmnApi.scrollOffset();
    });
}

function setReplyTitle(name) {
    jQuery('#rep_content').val("@" + name + "\n" + jQuery('#rep_content').val());
    window.location.href = "#reply";
    jQuery('#rep_content').focus();
}

function beforeSubmit() {
    jQuery('#yournameErr').css("visibility", "hidden");
    jQuery('#rep_contentErr').css("visibility", "hidden");
    if (jQuery('#yourname').val().replace(/(^\s*)|(\s*$)/g, '').replace("\r", "\n").replace("\n", "") == "") {
        jQuery('#yourname').focus();
        jQuery('#yourname').value = "";
        jQuery('#yournameErr').css("visibility", "");
        return false;
    } else if (jQuery('#rep_content').val().replace(/(^\s*)|(\s*$)/g, '').replace("\r", "\n").replace("\n", "") == "") {
        jQuery('#rep_content').focus();
        jQuery('#rep_content').val('');
        jQuery('#rep_contentErr').css("visibility", "");
        return false;
    }
    if (jQuery('#title').val().replace(/(^\s*)|(\s*$)/g, '') == "") {
        jQuery('#title').val("Re: " + jQuery('#h2title').html());
    }

    cmnApi.disableWhenSubmiting(document.getElementsById("submit"));
    return true;
}
