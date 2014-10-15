(function() {
  tinymce.create('tinymce.plugins.CodeSelection', {
  	
    codeFactory : function(codeType) {
        return function(c, m) {
            m.add( {
                title : codeType,
                onclick : function() {
                	tinyMCE.activeEditor.selection.setContent(
                	  "[code:" + codeType + "]" + tinyMCE.activeEditor.selection.getContent() + "[/code]"
                	);
                }
            });
        };
    },
  	
    createControl : function(n, cm) {

        switch (n) {
            case 'codesel':
            
            var c = cm.createSplitButton('codesel', {
                title : 'Insert Code',
                image : 'tiny_mce/plugins/codesel/img/code.gif'
            });
            c.onRenderMenu.add( this.codeFactory('java') );
            return c;
        }

        return null;
    }
    
  });

  tinymce.PluginManager.add('codesel', tinymce.plugins.CodeSelection);
})();
