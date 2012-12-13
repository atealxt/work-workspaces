
Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
Ext.onReady(function(){

    Ext.BLANK_IMAGE_URL = '../extjs/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    /*
    new Ext.Button({
        renderTo :'export',
        text : '导出图片',
        iconCls: '',
        handler : exportImage
    })
    */
});

/** 导出图片 */
function exportImage(){
    var flashMovie = document.getElementById('ampie');
    if (flashMovie) {
        flashMovie.exportImage('../chart/A1301ExportImgAction.action');
    }
}

/** 改变主题 */
function changeStyle(){
    Ext.util.CSS.swapStyleSheet('theme', parent.pageTheme);
}