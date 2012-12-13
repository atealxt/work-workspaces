Ext.ux.MaximizeTool = function(){
	this.init = function(ct){
		var maximizeTool = {
			id : 'maximize',
			handler : handleMaximize,
			scope : ct,
			qtip : 'Maximize'
		};
		ct.tools = ct.tools || [];
		var newTools = ct.tools.slice();
		ct.tools = newTools;
		for(var i = 0, len = ct.tools.length;i < len; i++){
			if(ct.tools[i].id == 'maximize')
				return;
		}
		ct.tools[ct.tools.length] = maximizeTool;
	};

	function handleMaximize(event, toolEl, panel){
		//alert(Ext.ComponentMgr.get(panel.pInfo.clumn).items.length+':01 times');
		panel.originalOwnerCt = panel.ownerCt;//panel.originalOwnerCt为该portlet所在的容器,这里为PortalColumn的实例
		//alert(panel.pInfo.seq);//panel即为最大化的那个portlet
		//alert(panel.originalOwnerCt instanceof Ext.ux.PortalColumn);
		panel.originalPosition = panel.ownerCt.items.indexOf(panel);//取得位置:本portlet在容器中的索引位置
		
		panel.originalSize = panel.getSize();//得到的是一个对象,含的是width和height通过panel.getSize().width和panel.getSize().height获得
		//alert(panel.originalSize+':size');
		if (!toolEl.window){
			var defaultConfig ={
				id : (panel.getId() + '-MAX'),
				width : (Ext.getBody().getSize().width - 100),
				height : (Ext.getBody().getSize().height - 100),
				resizable : true,
				draggable : true,
				closable : true,
				closeAction : 'hide',
				hideBorders : true,
				plain : true,
				layout : 'fit',
				autoScroll : false,
				border : false,
				bodyBorder : false,
				frame : true,
				pinned : true,
				bodyStyle : 'background-color: #ffffff;'
			};
			toolEl.window = new Ext.Window(defaultConfig);
			toolEl.window.on('hide', handleMinimize, panel);
			
		}
		if(!panel.dummyComponent||panel.dummyComponent==null){
			var dummyCompConfig = {
				title : panel.title,
				width : panel.getSize().width,
				height : panel.getSize().height,
				html : '&nbsp;'
			};
			panel.dummyComponent = new Ext.Panel(dummyCompConfig);
		}
		//alert(Ext.ComponentMgr.get(panel.pInfo.clumn).items.length+':02 times');
		toolEl.window.add(panel);
		if(panel.tools['toggle'])
			panel.tools['toggle'].setVisible(false);
		if(panel.tools['close'])
			panel.tools['close'].setVisible(false);
		panel.tools['maximize'].setVisible(false);
		//alert(Ext.ComponentMgr.get(panel.pInfo.clumn).items.length+':03 times');
		panel.originalOwnerCt.insert(panel.originalPosition,panel.dummyComponent);
		//alert(Ext.ComponentMgr.get(panel.pInfo.clumn).items.length+':04 times');
		panel.originalOwnerCt.doLayout();
		panel.dummyComponent.setSize(panel.originalSize);
		panel.dummyComponent.setVisible(true);
		panel.dummyComponent.getEl().mask('it is maximized');
		toolEl.window.show(this);
	};

	function handleMinimize(window){
		this.dummyComponent.getEl().unmask();
		this.dummyComponent.setVisible(false);//this是porlet的实例 而dummyComponent是panel的组件,是虚拟的组件
		
		//add by mlc 2008-02-07 begin
		Ext.ComponentMgr.get(this.pInfo.clumn).remove(this.dummyComponent,true);
		this.dummyComponent = null;
		//add by mlc 2008-02-07 end
		//alert(this.originalPosition);

		//alert(this.dummyComponent instanceof Ext.Panel);//this.dummyComponent是 Ext.Panel的实体
		
		this.originalOwnerCt.insert(this.originalPosition, this);
		//alert(this.pInfo.text);
		//alert(Ext.ComponentMgr.get(this.pInfo.clumn).id+':12');
		//alert(this.originalOwnerCt.id);//和上行相同均为容器PortalColumn的id
		//alert(this.id+':add'+this.pInfo.text);
		this.originalOwnerCt.doLayout();
		this.setSize(this.originalSize);
		this.tools['maximize'].setVisible(true);
		if(this.tools['toggle']){
			this.tools['toggle'].setVisible(true);//可缩小的那个工具
		}
		if(this.tools['close']){
			this.tools['close'].setVisible(true);
		}
		//alert(Ext.ComponentMgr.get(this.pInfo.clumn).items.length+':05 times');
		/*ll = this.originalOwnerCt.items.length;
		for(var i=0; i<ll; i++){
			alert(this.originalOwnerCt.items.itemAt(i).id+':the:'+i+':'+ll);//1008
			alert(this.originalOwnerCt.items.itemAt(i).pInfo.text);
			//alert(this.originalOwnerCt.items.itemAt(1) instanceof Ext.Panel);
		}*/
	}
};
// EOP