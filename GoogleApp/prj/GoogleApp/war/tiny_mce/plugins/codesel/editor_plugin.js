/*
 * Copyright 2009 atealxt@gmail.com All rights reserved.
 */
(function() {
  tinymce.create('tinymce.plugins.CodeSelection', {
  	
    createControl : function(n, cm) {

        switch (n) {
            case 'codesel':
            
            var c = cm.createSplitButton('codesel', {
                title : 'Insert Code',
                image : 'tiny_mce/plugins/codesel/img/code.gif'
            });
            this.createMenu(c.onRenderMenu, new Array("c","c#","css","delphi","groovy","html","java","javafx","javascript","perl","php","python","ruby","scala","shell","sql","vb","xml"));
            return c;
        }

        return null;
    },
    
    createMenu : function(menu, codeType) {    
    	for(var i = 0; i < codeType.length; i++){	
    		menu.add( this.createItem(codeType[i]) );
    	}
    },

    createItem : function(codeType) {
        return function(c, m) {
            m.add( {
                title : codeType,
                onclick : function() {
                	tinyMCE.activeEditor.selection.setContent(
                	  "[code:" + codeType + "]<br>" + tinyMCE.activeEditor.selection.getContent() + "<br>[/code]"
                	);
                }
            });
        };
    }
    
  });

  tinymce.PluginManager.add('codesel', tinymce.plugins.CodeSelection);
})();
