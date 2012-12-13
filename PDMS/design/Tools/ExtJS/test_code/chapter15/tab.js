Ext.namespace("Forcg.pccw");

Forcg.pccw.TabPanelEx = Ext.extend(Ext.TabPanel,{
	defaultType : 'panel',
	returnval:'',
	initComponent : function(){
        this.frame = true;
        Forcg.pccw.TabPanelEx.superclass.initComponent.call(this);
         this.addEvents("changecolumn");
         this.addEvents("addbutton");
    },
    
	//private
    initTab : function(item, index){
        var before = this.strip.dom.childNodes[index];
        var cls = item.closable ? 'x-tab-strip-closable1' : '';
        if(item.disabled){
            cls += ' x-item-disabled';
        }
        if(item.iconCls){
            cls += ' x-tab-with-icon';
        }
        var c1=index==this.activeTab && item.menus ?'x-tab-strip-fu':'test';
        var p = {
            id: this.id + '__' + item.getItemId(),
            text: item.title,
            cls: cls,
			clls : c1,
			linkId : 'link'+index,
            iconCls: item.iconCls || ''
        };
		var el = before ?
                 this.itemTpl.insertBefore(before, p,true) :
                 this.itemTpl.append(this.strip, p,true);

        Ext.fly(el).addClassOnOver('x-tab-strip-over');

        if(item.tabTip){
            Ext.fly(el).child('span.x-tab-strip-text', true).qtip = item.tabTip;
        }
        item.on('disable', this.onItemDisabled, this);
        item.on('enable', this.onItemEnabled, this);
        item.on('titlechange', this.onItemTitleChanged, this);
        item.on('beforeshow', this.onBeforeShowItem, this);
        
        var a = Ext.get('link'+index);
        var act = this.activeTab;
        
        //当移动鼠标出现三层子菜单begin
		el.on({
			'mouseover': {
				scope: this,
				fn:function(){
					if(Ext.get('menu0')){
						Ext.get('menu0').remove();
					}
					//当该标签还没有创建时且处于选中状态时进行创建子菜单操作
					if(!Ext.get('menu0') && this.activeTab == item){
						var mee = item.getEl().createChild("<div style='zIndex:10006;position:absolute;left:40px;top:1px;width:67px;' id='menu0'></div>");
						mee.createChild("<div style='zIndex:10002;width: 100%' class='menu_top' id='menu1'>编辑列</div>");
						mee.createChild("<div style='zIndex:10003;width: 100%' class='menu_middle' id='menu2'>添加标签页</div>");
						//mee.createChild("<div style='zIndex:10004;width: 100%' class='menu_buttom' id='menu3'>待用菜单2</div>");
						//设定为移动图标坐标的附近
						Ext.get('menu0').setX(a.getX());
						Ext.get('menu0').setY(a.getY()+19);
					}
				},
				delegate: 'a.x-tab-strip-fu'//取得且样式表为x-tab-strip-fu的链接部分的click事件
			}
		});
		//end
		
		//当弹出的菜单项时隐藏事件begin
		el.on({
			'mouseout': {
				scope: this,
				fn:function(){
					if(Ext.get('menu0')){
						//alert('tab页为"'+item.title+'"的操作');
						
						//alert(portalId);
						//alert(Ext.ComponentMgr.get(portalId).items.length);
						//alert(Ext.ComponentMgr.get(portalId).el.dom.outerHTML);
						//编辑布局菜单
						Ext.get('menu1').on('click',function(){
							returnval = window.showModalDialog('../portal/column_select.jsp',null,'scroll:yes;resizable:no;help:no;status:no;dialogWidth:400px;dialogHeight:378px');
							this.fireEvent('changecolumn',returnval);
						},this);
						//删除列
						Ext.get('menu2').on('click',function(){
							this.fireEvent('addtabpage');
						},this);
						//默认隐藏事件
						Ext.get('menu0').on('mouseleave',function(){
							if(Ext.get('menu0')){
								Ext.get('menu0').remove();
							}
						},this);
					}
				},
				delegate: 'a.x-tab-strip-fu'
			}
		});
		
		//end
		//alert(this.strip.dom.outerHTML);//所有部分���еĲ��
    },
    
    //modify by mlc 2008-03-16 21:29
    setActiveTab : function(item){
        item = this.getComponent(item);
        if(!item || this.fireEvent('beforetabchange', this, item, this.activeTab) === false){
            return;
        }
        if(!this.rendered){
            this.activeTab = item;
            return;
        }
        if(this.activeTab != item){
            if(this.activeTab){
                var oldEl = this.getTabEl(this.activeTab);
                
                //add by mlc 2008-03-17 begin --为原激活者去除"当前"cls
                if(Ext.fly(oldEl) && Ext.fly(oldEl).child("a.x-tab-strip-fu",false)){
	                Ext.fly(oldEl).child("a.x-tab-strip-fu",false).replaceClass('x-tab-strip-fu','test');
                }
                //add end
                
                if(oldEl){
                    Ext.fly(oldEl).removeClass('x-tab-strip-active');
                }
                this.activeTab.fireEvent('deactivate', this.activeTab);
            }
            var el = this.getTabEl(item);
            Ext.fly(el).addClass('x-tab-strip-active');
            //add by mlc 2008-03-17 begin --为当前激活者加入"当前"的cls
            if(Ext.fly(el).child('a.test',false)){
            	Ext.fly(el).child("a.test",false).replaceClass('test','x-tab-strip-fu');
            }
            //add end
            
            this.activeTab = item;
            this.stack.add(item);

            this.layout.setActiveItem(item);
            if(this.layoutOnTabChange && item.doLayout){
                item.doLayout();
            }
            if(this.scrolling){
                this.scrollToTab(item, this.animScroll);
            }
            item.fireEvent('activate', item);
            this.fireEvent('tabchange', this, item);
        }
    },
    
	onRender : function(ct, position){
        Ext.TabPanel.superclass.onRender.call(this, ct, position);
		
        if(this.plain){
            var pos = this.tabPosition == 'top' ? 'header' : 'footer';
            this[pos].addClass('x-tab-panel-'+pos+'-plain');
        }

        var st = this[this.stripTarget];
        
        this.stripWrap = st.createChild({cls:'x-tab-strip-wrap', cn:{
            tag:'ul', cls:'x-tab-strip x-tab-strip-'+this.tabPosition}});
        this.stripSpacer = st.createChild({cls:'x-tab-strip-spacer'});
		
        this.strip = new Ext.Element(this.stripWrap.dom.firstChild);
        
        this.edge = this.strip.createChild({tag:'li', cls:'x-tab-edge'});
        this.strip.createChild({cls:'x-clear'});
		
        this.body.addClass('x-tab-panel-body-'+this.tabPosition);
        //alert(st.dom.outerHTML);
        if(!this.itemTpl){
            var tt = new Ext.Template(
				'<li class="{cls}" id="{id}"><a id="{linkId}" class="{clls}"></a><a class="x-tab-strip-close" onclick="return false;"></a>',
                '<a class="x-tab-right" href="#" onclick="return false;"><em class="x-tab-left1">',
                '<span class="x-tab-strip-inner"><span class="x-tab-strip-text {iconCls}">{text}</span></span>',
				'</em></a></li>'
            );
            tt.disableFormats = true;
            tt.compile();
            Ext.TabPanel.prototype.itemTpl = tt;
        }
        this.items.each(this.initTab, this);
    }
});

Ext.reg('tabpanelEx', Forcg.pccw.TabPanelEx);