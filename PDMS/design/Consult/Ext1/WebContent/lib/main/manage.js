Ext.BLANK_IMAGE_URL = 'lib/extjs/resources/images/default/s.gif';
Ext.QuickTips.init();
var main, menu,east,header;
Ext.onReady(function() {
			header = new Ext.BoxComponent({
				region:'north',
				el: 'north',
				height:80
			});		
			
			menu = new MenuPanel();
			main = new MainPanel();
			east = new EastPanel();

			//布局
			var viewport = new Ext.Viewport( {
				layout : 'border',
				items : [header, menu, main,east]
			});
			
		});
		