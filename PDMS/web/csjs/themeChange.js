Ext.ux.ThemeChange = Ext.extend(Ext.form.ComboBox, {
    editable : false,
    displayField : 'theme',
    valueField : 'css',
    typeAhead : true,
    mode : 'local',
    triggerAction : 'all',
    selectOnFocus : true,
    initComponent : function() {
        var themes = [
        ['默认', 'xtheme-indigo.css'],
        ['蓝色', 'ext-all.css'],
        ['暗蓝色', 'xtheme-slate.css'],
        ['银白色', 'xtheme-silverCherry.css'],
        ['深夜', 'xtheme-midnight.css']
        ];
        this.store = new Ext.data.SimpleStore( {
            fields : ['theme', 'css'],
            data : themes
        });
        this.value = 'xtheme-indigo.css';
    },
    initEvents : function() {
        this.on('collapse', function() {
            Ext.util.CSS.swapStyleSheet('theme', '../extjs/resources/css/'+ this.getValue());
            pageTheme = '../extjs/resources/css/'+ this.getValue();

            //change iframe's style
            for(var i=1;i<tabPanel.items.length;i++){
                var winTemp = tabPanel.items.get(i).iframe.dom.contentWindow;
                var changeStyle = winTemp.changeStyle;
                if(changeStyle != undefined){
                    winTemp.changeStyle();
                }
            }

        });
    }
});
Ext.reg('themeChange', Ext.ux.ThemeChange);