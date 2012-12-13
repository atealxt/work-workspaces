Ext.ux.Portal = Ext.extend(Ext.Panel,{
	layout : 'column',//视图类型
	autoScroll : true,
	cls : 'x-portal',
	defaultType : 'portalcolumn',//容器所放的具体的xtype
	portlets : {},

	initComponent : function(){
		Ext.ux.Portal.superclass.initComponent.call(this);
		this.addEvents({
			validatedrop : true,
			beforedragover : true,
			dragover : true,
			beforedrop : true,
			drop : true
		});
		//alert(this.items.length+':itemLength');
	},
	initEvents : function(){
		Ext.ux.Portal.superclass.initEvents.call(this);
		this.dd = new Ext.ux.Portal.DropZone(this, this.dropConfig);
	},
	
	addPortlet : function(pInfo, pTools, items){
		var p = new Ext.ux.Portlet({
			autoCreate : true,
			tools : pTools,//关闭的图标等
			title : pInfo.text,
			height : pInfo.height,
			items : items,
			plugins : new Ext.ux.MaximizeTool(),
		    html : items?undefined:'add portlet'
		});
		//alert(p.html+':src');
		p.pInfo = pInfo;
		this.portlets[pInfo.id] = p;

		var col = this.items.itemAt(0);
		var minNum = 0;
		if(col.items){
			minNum = col.items.length;
			for(var i = 0; i<this.items.length; i++){//从左至右/按小到大的排序往最少的portalcolumn里加portlet
				var c = this.items.itemAt(i);
				if(c.items.length < minNum){
					minNum = c.items.length;
					col = c;
				}
			}
		}
		
		p.pInfo.checked=true;
		col.add(p);
		p.pInfo.clumn = col.id;
		p.pInfo.entityClumn = col.entityId;
		p.pClmn = col;
		p.pInfo.seq = ++minNum;
		
		return p;
	},
	
	showPortlet : function(pInfo, pTools, items){
		var p = new Ext.ux.Portlet({
			//id : pInfo.id,
			autoCreate : true,
			tools : pTools,//关闭的图标
			title : pInfo.text,
			height : pInfo.height,
			checked : pInfo.checked,
			items : items,
			plugins : new Ext.ux.MaximizeTool(),
		    html : items?undefined:'show portle'
		});
		
		//alert(pInfo.text+",初始时;"+pInfo.clumn);
		p.pInfo = pInfo;
		this.portlets[pInfo.id] = p;
		var col = this.items.itemAt(0);
		for(var i = 0; i<this.items.length; i++){//实现找所对应的column,如果没有找到则往第一列里加portlet
			var c = this.items.itemAt(i);
			//alert(c.id+"本次列的id"+c.columnWidth);
			//alert(pInfo.clumn+":pInfo.clumn");
			if(c.id == pInfo.clumn){
				col = c;
			}
		}
		col.add(p);
		//alert("在"+col.id+"里加:"+p.pInfo.text+":seq"+p.pInfo.seq+",portalId"+this.id);
		p.pClmn = col;
		return p;
	},
	
	removePortlet : function(id){
		var p = this.portlets[id];
		var col =Ext.ComponentMgr.get(p.pInfo.clumn);//当删除的不是每列最后一个时其后同样要改变位置需要保存
		if(p){
			var colLeng=col.items.length;
			if(p.pInfo.seq<colLeng){//非每列最后一项
				var temp;
				for(var i=p.pInfo.seq;i<colLeng;i++){
					temp = col.items.itemAt(i);
					temp.pInfo.seq = temp.pInfo.seq-1;
				}
				p.pInfo.checked=false;
				p.pInfo.seq = -1;
			}else{//每列最后一项
				p.pInfo.checked=false;
				p.pInfo.seq = -1;
			}
			p.pClmn.remove(p, true);
			delete this.portlets[id];
		}
	},
	removeAllPortlets : function(){
		for(var i = 0;i < this.items.length; i++){
			var c = this.items.itemAt(i);
			for(var j = c.items.length-1;j >= 0;j--){
				c.remove(c.items.itemAt(j), true);
			}
		}
		this.portlets = {};
	},
	getAllPortlets : function(){
		var retVal = [];
		for(var i = 0;i < this.items.length; i++){
			var c = this.items.itemAt(i);
			for(var j=0; j<c.items.length; j++){
				retVal.push(c.items.itemAt(j));
			}
		}
		return retVal;
	},
	getPortlet : function(id){
		return this.portlets[id];
	}
});
Ext.reg('portal', Ext.ux.Portal);

Ext.ux.Portal.DropZone = function(portal, cfg){
	this.portal = portal;
	Ext.dd.ScrollManager.register(portal.body);
	Ext.ux.Portal.DropZone.superclass.constructor.call(this, portal.bwrap.dom,cfg);
	portal.body.ddScrollConfig = this.ddScrollConfig;
};

Ext.extend(Ext.ux.Portal.DropZone, Ext.dd.DropTarget,{
	ddScrollConfig :{
		vthresh : 50,
		hthresh : -1,
		animate : true,
		increment : 200
	},

	createEvent : function(dd, e, data, col, c, pos){
		//alert(pos+':pos'); dd 为Ext.dd.DD的实例
		
		return{
			portal : this.portal,
			panel : data.panel,
			columnIndex : col,
			column : c,
			position : pos,
			data : data,
			source : dd,
			rawEvent : e,
			status : this.dropAllowed
		};
	},
	
	notifyOver : function(dd, e, data){//进入到目标的范围内，每一下移动鼠标，它不断执行通知落下目标该函数
		
		var xy = e.getXY(), portal = this.portal, px = dd.proxy;
		// case column widths
		if(!this.grid){
			this.grid = this.getGrid();
		}

		// handle case scroll where scrollbars appear during drag
		var cw = portal.body.dom.clientWidth;
		if(!this.lastCW){
			this.lastCW = cw;
		}else if(this.lastCW != cw){
			this.lastCW = cw;
			portal.doLayout();
			this.grid = this.getGrid();
		}

		// determine column
		var col = 0, xs = this.grid.columnX, cmatch = false;
		for(var len = xs.length;col < len; col++){
			if(xy[0] < (xs[col].x + xs[col].w)){
				cmatch = true;
				break;
			}
		}
		// no match, fix last index
		if (!cmatch){
			col--;
		}

		// find insert position
		var p, match = false, pos = 0, c = portal.items.itemAt(col), items = c.items.items;

		for(var len = items.length;pos < len; pos++){
			p = items[pos];
			var h = p.el.getHeight();
			if(h !== 0 && (p.el.getY() + (h / 2)) > xy[1]){
				match = true;
				break;
			}
		}

		var overEvent = this.createEvent(dd, e, data, col, c, match	&& p ? pos : c.items.getCount());

		if(portal.fireEvent('validatedrop', overEvent) !== false
				&& portal.fireEvent('beforedragover', overEvent) !== false){

			// make sure proxy width is fluid
			px.getProxy().setWidth('auto');

			if(p){
				px.moveProxy(p.el.dom.parentNode, match	? p.el.dom : null);
			}else{
				px.moveProxy(c.el.dom, null);
			}
			this.lastPos = {
				c : c,
				col : col,
				p : match && p ? pos : false
			};
			this.scrollPos = portal.body.getScroll();

			portal.fireEvent('dragover', overEvent);

			return overEvent.status;;
		}else{
			return overEvent.status;
		}
	},

	notifyOut : function(){//移出落下目标的范围后，它执行通知落下目标的该函数
		delete this.grid;
	},

	notifyDrop : function(dd, e, data){//在落下目标身上完成落下动作后，它执行通知落下目标的该函数
		delete this.grid;
		if(!this.lastPos){
			return;
		}
		
		var c = this.lastPos.c,col = this.lastPos.col,pos = this.lastPos.p;
		
		//拖动保存开始
		var colItems = Ext.ComponentMgr.get(portalId).items;
		var pColumn;//Ext.ComponentMgr.get(dd.panel.pInfo.clumn);
		for(var k=0;k<colItems.length;k++){
			if(colItems.itemAt(k).id==dd.panel.pInfo.clumn){
				pColumn = Ext.ComponentMgr.get(portalId).items.itemAt(k);
			}
		}
		
		var colLeng=pColumn.items.length;
		if(pColumn.id==c.id){//在同一列中拖动
			if(typeof(pos)=='number' && (dd.panel.pInfo.seq-1)>pos){//新的位置小于同列中旧位置
				var temp;
				var tempPanel;
				for(var i=pos;i<dd.panel.pInfo.seq-1;i++){
					temp = pColumn.items.itemAt(i);
					temp.pInfo.seq = eval(temp.pInfo.seq)+1;
				}
				var tempPanel = dd.panel;
				tempPanel.pInfo.seq = pos+1;
			}else if(pos && (dd.panel.pInfo.seq-1)<pos){//新的位置大于同列中旧位置,往下拖时pos比实际的位置大1
				var temp;
				var tempPanel;
				for(var i=pos-1;i>dd.panel.pInfo.seq-1;i--){
					temp = pColumn.items.itemAt(i);
					temp.pInfo.seq = eval(temp.pInfo.seq)-1;
				}
				var tempPanel = dd.panel;
				if(tempPanel.pInfo.seq!=pos){//只是拖动但位置没有变化
					tempPanel.pInfo.seq = pos;
				}
			}else if(typeof(pos)!='number'){//当拖到同列最后一个位置时pos为false
				var temp;
				var tempPanel;
				for(var i=colLeng-1;i>dd.panel.pInfo.seq-1;i--){
					temp = pColumn.items.itemAt(i);
					temp.pInfo.seq = eval(temp.pInfo.seq)-1;
				}
				var tempPanel = dd.panel;
				if(tempPanel.pInfo.seq==colLeng){
					return;
				}
				tempPanel.pInfo.seq = colLeng;
			}
		}else{//非同一列中拖动
			var temp;
			var tempPanel;
			var oldSeq = dd.panel.pInfo.seq;
			//新列先变化
			if(typeof(pos)=='number'){//拖到新列非最后一个位置
				for(var i=pos;i<c.items.length;i++){
					temp = c.items.itemAt(i);
					temp.pInfo.seq = eval(temp.pInfo.seq)+1;
				}
				tempPanel = dd.panel;
				tempPanel.pInfo.seq = pos+1;
				tempPanel.pInfo.clumn = c.id;
				tempPanel.pInfo.entityClumn = c.entityId;
			}else{//拖到新列的最后一个位置
				tempPanel = dd.panel;
				tempPanel.pInfo.seq = c.items.length+1;
				tempPanel.pInfo.clumn = c.id;
				tempPanel.pInfo.entityClumn = c.entityId;
			}
			
			//原来的列进行变化,oldSeq为被拖的portlet的原位置
			for(var i=eval(oldSeq);i<colLeng;i++){
				temp = pColumn.items.itemAt(i);
				temp.pInfo.seq = eval(temp.pInfo.seq)-1;
			}
		}
		//拖动保存结束
		
		var dropEvent = this.createEvent(dd, e, data, col, c,
				pos !== false ? pos : c.items.getCount());//该函数是为某PortalColumn初始化一个事件

		if(this.portal.fireEvent('validatedrop', dropEvent)!== false
				&& this.portal.fireEvent('beforedrop', dropEvent)!== false){
			dd.proxy.getProxy().remove();
			dd.panel.el.dom.parentNode.removeChild(dd.panel.el.dom);
			if(pos!== false){
				c.insert(pos,dd.panel);
				dd.panel.pInfo.clumn=c.id;
				
			}else{//表明只需要保存新增的一个
				c.add(dd.panel);
				dd.panel.pInfo.clumn=c.id;//将被拖的item的clumn赋值以保存姿态
			}

			c.doLayout();
			this.portal.fireEvent('drop',dropEvent);

			// scroll position is lost on drop, fix it
			var st = this.scrollPos.top;
			if(st){
				var d = this.portal.body.dom;
				setTimeout(function(){
					d.scrollTop = st;
				},10);
			}
		}
		
		delete this.lastPos;
	},

	// internal cache of body and column coords
	getGrid : function(){
		var box = this.portal.bwrap.getBox();
		box.columnX = [];
		this.portal.items.each(function(c){//c其实是每个items的迭代类型为PortalColumn
			box.columnX.push({
				x : c.el.getX(),
				w : c.el.getWidth()
			});
		});
		/*this.portal.items.each(function(c){
			alert(c instanceof Ext.ux.PortalColumn);
		})*/
		return box;
	}
});
	
Ext.ux.Portlet = Ext.extend(Ext.Panel,{
    anchor: '100%',
    frame: true,
    collapsible: true,//下拉三角,控制缩小区域
    draggable: true,
    cls: 'x-portlet',

    onResizer : function(oResizable, iWidth, iHeight, e){
        this.setHeight(iHeight);
        this.resizer.on("resize", this.onResizer, this);
    },
 
    onCollapse : function(doAnim, animArg){
        this.el.setHeight("");//remove height set by resizer
        Ext.ux.Portlet.superclass.onCollapse.call(this, doAnim, animArg);
    }
});
Ext.reg('portlet', Ext.ux.Portlet);

Ext.ux.PortalColumn = Ext.extend(Ext.Container,{
	layout : 'anchor',
	autoEl : 'div',
	defaultType : 'portlet',
	cls : 'x-portal-column'
});
Ext.reg('portalcolumn', Ext.ux.PortalColumn);