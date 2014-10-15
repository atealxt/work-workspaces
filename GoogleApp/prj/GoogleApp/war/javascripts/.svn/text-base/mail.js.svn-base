jQuery(document).ready(function() {

    var getBaseContent = function(){
        var content = document.getElementsByTagName("iframe")[0].contentWindow.document.body.innerHTML;
        if (content == "<br>") {
            return "";
        }
        content = content.replace("<br>", "\r\n").replace(/<\/?.+?>/g, "");
        return content;
    };

    if (jQuery('#reset').length > 0) {
        jQuery('#reset').click(function() {
            jQuery('#to').val('');
            jQuery('#Subject').val('');
            jQuery('#content').val('');
            if (document.getElementsByTagName("iframe")[0]) {
                document.getElementsByTagName("iframe")[0].contentWindow.document.body.innerHTML = "";
            }
        });
        jQuery('#textStyle').click(function() {
            if (!confirm("Converting this message to plain text will lose some formatting.\r\nAre you sure you want to continue?")) {
                return;
            }
            jQuery('#base').val(base);
            jQuery('#textStyle').html('');
            tinyMCE.get('content').hide();
            jQuery('#content').val(getBaseContent());
        });
    }
});
