(function() {
  tinymce.create('tinymce.plugins.CodeSelection', {
    init : function(ed, url) {
      // Register commands
      ed.addCommand('codesel', function() {
        ed.focus();
        ed.selection.setContent("<strong>Hello world!</strong>");
      });

      // Register buttons
      ed.addButton('codesel', {
        title : 'Insert Code',
        cmd : 'codesel',
        image : url + '/img/code.gif'
      });
    }
  });

  // Register plugin
  tinymce.PluginManager.add('codesel', tinymce.plugins.CodeSelection);
})();
