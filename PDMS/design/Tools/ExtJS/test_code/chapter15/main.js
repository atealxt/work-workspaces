Ext.namespace("Divo.app");

Divo.app.PortletDemo = function(){
	/* ----------------------- private properties ----------------------- */
	settingId = 'extdiv';

	var portalState;
	var portalMgr;
	var pa;
	var index = 0;
	var color;
	var setBtn;
	
	/* ----------------------- private method ----------------------- */
	function createLayout(){
		pa = new Forcg.pccw.TabPanelEx({//create tabPanel
			id : 'mainview',
			//region :'center',
			deferredRender : false,
			frame:true,
			renderTo: 'tabs',
			autoScroll: true,
			style:'width=100%',
			monitorResize : true,
			margins : '0 0 0 0',
			activeTab : 0,
			iconCls: 'tabs',
			border : false,
			plain:false
		});
		for(var i=0;i<portalTab.length;i++){//create panel页
			pa.add(portalTab[i][1]);
		}
		pa.render();
		
		var k=0;
		for(var i=pa.items.length-1;i>=0;i--){
			var endLay = pa.items.itemAt(i);
			if(endLay.iscur=='1'){
				k=i;
				continue;
			}
		    endLay.show();
		}
		pa.items.itemAt(k).show();//最后show当前的
		
		setBtn = new Ext.ux.ImageButton({
			image : '../ext/images/button_set.gif',
			iconCls:'bt',
			handler : function(){
				var setTemp = document.getElementById("setDiv").style.display;
				if(setTemp==""||setTemp=="none"){
					document.getElementById("setDiv").style.display="block";
				}else if(document.getElementById("setDiv").style.display=="block"){
					document.getElementById("setDiv").style.display="none";
				}
			}
		});
		setBtn.render("setBut");
		
		var saveBtn = new Ext.ux.ImageButton({//create save button
			image : '../ext/images/button_save.gif',
			flag : 'unChangeCol',
			iconCls:'bt',
			handler : saveFun
		});
		saveBtn.render("saveBut");
			
		color = new Ext.ux.ThemeChange();//create css change button
		color.render("hr_color");
		
		var sett = new Ext.Panel({
			region : 'west',
			id : 'settings-panel',
			//title : 'Settings',
			split : true,
			width : '100%',
			collapsible : true,
			collapseMode: 'mini',
			renderTo:'setDiv',
			collapsed : false,//默认为折起来
			border: false,
			animCollapse : false,//折起来时有动画效果
			//margins : '5 0 5 5',
			//cmargins : '5 5 5 5',
			items : [{
				html : "<div id='"+settingId+"'></div>",
				id : 'settings-exts',
				autoScroll : true,
				border : false,
				display : 'none'
				//iconCls : 'nav'
			}]
		});
	}

	function saveFun(obj){
		var saveStr = '';
		var tabStr = '';
		for(var k=0;k<pa.items.length;k++){
			var saveColumns = pa.items.item(k).items.item(0).items;//pa.getActiveTab().items.item(0).items;
			tabStr+=pa.items.item(k).items.item(0).entityId+','+pa.items.item(k).coltype+','+pa.items.item(k).clstype+','+pa.items.item(k).iscur+','+encodeURI(pa.items.item(k).title)+':';
			for(var i=0;i<saveColumns.length;i++){
				savePortlets = saveColumns.item(i).items;
				if(savePortlets.length==0){
					//alert("各标签页下每列应保障至少有一个Portlet，名称为“"+pa.items.item(k).title+"”的标签页有空列!请修改后重试！");
					Ext.Msg.alert('',"各标签页下每列应保障至少有一个Portlet，名称为“"+pa.items.item(k).title+"”的标签页有空列!请修改后重试！");
					return;
				}
				if(savePortlets){
					for(var j=0;j<savePortlets.length;j++){
						saveStr+=savePortlets.item(j).pInfo.seq+','+savePortlets.item(j).pInfo.entityClumn+','+pa.items.item(k).items.item(0).entityId+','+savePortlets.item(j).pInfo.id+':';
					}
				}
			}
		}
		saveStr = saveStr.substr(0,saveStr.lastIndexOf(":"));
		tabStr = tabStr.substr(0,tabStr.lastIndexOf(":"));
		pa.doLayout();
		if(hasTab){
			//alert("保存portlet参数--"+saveStr+" 标签页参数---"+tabStr);
			saveByChange(saveStr,tabStr);
		}else if(!hasTab){
			var tabids = [];
			//alert("没有定制参数--"+saveStr+" 标签页参数--"+tabStr);
			tabids = saveByChangeUndef(saveStr,tabStr);
			if(tabids.length!=pa.items.length){
				//alert("保存失败!请重试!");
				Ext.Msg.alert('','保存失败!请重试!');
				return;
			}
			for(var k=0;k<pa.items.length;k++){
				var saveColumns = pa.items.item(k).items.item(0).items;
				var oldTabid = pa.items.item(k).items.item(0).entityId;
				pa.items.item(k).items.item(0).entityId = tabids[k];//set new tabid
				if(pa.items.item(k).iscur=='1'){
					portalId = pa.items.item(k).items.item(0).id;//tabids[k];
				}
				for(var i=0;i<saveColumns.length;i++){
					savePortlets = saveColumns.item(i).items;
					saveColumns.item(i).entityId = saveColumns.item(i).entityId.replace(oldTabid,tabids[k]);
					if(savePortlets){
						for(var j=0;j<savePortlets.length;j++){//every column = tabid&columnid
							savePortlets.item(j).pInfo.entityClumn = savePortlets.item(j).pInfo.entityClumn.replace(oldTabid,tabids[k]);
						}
					}
				}
			}
			pa.getActiveTab().doLayout();
			pa.getActiveTab().render();
			hasTab = true;
			//alert("恭喜!您已经成功定制了个人门户!");
			Ext.Msg.alert('','恭喜!您已经成功定制了个人门户!');
		}
	}
	
	function addTab(){
		var seq = Ext.ComponentMgr.get('mainview').items.length;
		var seq1 = seq+1;
		var returnval = new Array();
		var columnItms = [];
		var tabInfo;
		
		if(hasTab){
			returnval = window.showModalDialog('../manage/portal_tab_frame.jsp?personId='+personId+'&seq='+seq1,null,'resizable:no;scroll:no;status:no;dialogWidth=400px;dialogHeight=128px;center=yes;help=no');
	        if(returnval == null){
	        	return;
	        }
	        tabInfo = returnval[0];
	        //修改公共的变量的值
	        portalId = 'keyId_'+seq;
	        pa.add(new Ext.Panel({
	        	id:'portal_'+seq,
	        	title:tabInfo[0],
	        	height:720,
	        	iconCls: 'tabs',
		        closable:true,
		        menus:true,
		        coltype : tabInfo[3],
		        clstype : tabInfo[2],
		        autoScroll : true,
		        iscur : '1',
	        	items:[{
		            id : 'keyId_'+seq,//tabInfo[1],
		            xtype : 'portal',
		            entityId : tabInfo[1],
		            items: findColumnsByType(tabInfo[3],tabInfo[1],'keyId_'+seq),
		            border : false
		        }]
	        })).show();
    	}else if(!hasTab){
    		returnval = window.showModalDialog('../manage/portal_tab_frame_un.jsp?seq='+seq,null,'resizable:no;scroll:no;status:no;dialogWidth=400px;dialogHeight=128px;center=yes;help=no');
	        if(returnval == null){
	        	return;
	        }
	        tabInfo = returnval[0];
	        portalId = 'keyId_'+seq;
	        pa.add(new Ext.Panel({
	        	id:'portal_'+seq,
	        	title:tabInfo[0],
	        	height:720,
	        	iconCls: 'tabs',
		        closable:true,
		        menus:true,
		        coltype : tabInfo[3],
		        clstype : tabInfo[2],
		        autoScroll : true,
		        iscur : '1',
	        	items:[{
		            id : 'keyId_'+seq,
		            xtype : 'portal',
		            entityId : 'portal_'+seq,//tabInfo[1],
		            items: findColumnsByType(tabInfo[3],tabInfo[1],'keyId_'+seq),
		            border : false
		        }]
	        })).show();
    	}
    	pa.getActiveTab().doLayout();
		pa.getActiveTab().render();
	}

	/* ----------------------- public method ----------------------- */
	return{
		init : function(){
			createLayout();
			portalState = new Divo.app.PortalState();
			portalMgr = new Divo.app.PortalManager();
			for(var i=0;i<portalTab.length;i++){
				portalState.init((portalTab[i])[1].items[0].id);//通过portId从容器里取得一个portal
				portalMgr.init(settingId,(portalTab[i])[1].items[0].id,portalState,portalTab[i]);
			}
			portalMgr.crCheckbox();
			portalMgr.resetChangeTab(pa.getActiveTab().items.item(0));//将当前页的setting设置成checked状态
			Ext.util.CSS.swapStyleSheet('theme', '../ext/css/'+pa.activeTab.clstype+'.css');
			portalMgr.initParam(portalId);
			
			if(!hasCur){//when no tab iscur=1 then set portalId is first panel
				pa.getActiveTab().iscur = '1';
				portalId = pa.getActiveTab().items.item(0).id;
			}
			
			setBtn.on("click",function(){
				if(setBtn.image=="../ext/images/button_set.gif"){
					setBtn.setImage("../ext/images/button_set_unfolder.gif");
				}else{
					setBtn.setImage("../ext/images/button_set.gif");
				}
			});
			
			color.on('select',function(combo,record,index){//bind the color select css
				pa.getActiveTab().clstype = color.getValue().substr(0,color.getValue().lastIndexOf(".css"));
			});
			
			if("首页"!=pa.getActiveTab().title){//chang banel height
				document.getElementById("hr-ka").style.display="none";
			}
			
			Ext.ComponentMgr.get('mainview').on('remove',function(tabs, tab){//remove tab and synchronized to db
				portalTabDel(tab.items.itemAt(0).entityId);
			});
			
			pa.on("beforeremove",function(){
				if(!hasTab || pa.items.length==1){
					Ext.Msg.alert('','该标签页不允许删除!');
					return false;
				}
			});
			
			Ext.ComponentMgr.get('mainview').on('beforetabchange',function(tabs, newtab,oldtab){//当从一个tab页转入另一个tab页前调用此事件进行当前位置（库中的变更）
				if(!("首页"==newtab.title)){
					document.getElementById("hr-ka").style.display="none";
				}else if(document.getElementById("hr-ka").style.display=="none"){
					document.getElementById("hr-ka").style.display="block";
				}
				//changeTab(newtab.items.itemAt(0).id,oldtab.items.itemAt(0).id);
				newtab.iscur='1';
				oldtab.iscur='0';
				portalId = newtab.items.itemAt(0).id;//将portalId置为当前tabId
				portalMgr.initParam(portalId);//复位在mgr中Ext.Component.get...
			});
			
			pa.on('tabchange',function(tabs, newtab){//add by mlc 2008-03-17 --当从一个tab页转入另一个tab页后调用此事件
				portalMgr.resetChangeTab(newtab.items.item(0));//重新将设置条件的checkbok按每个tab页的选择情况进行复位
				Ext.util.CSS.swapStyleSheet('theme', '../ext/css/'+newtab.clstype+'.css');//set the css
			});
			
			pa.on("addtabpage",addTab);//弹出添加tab页的事件
			
			pa.on("changecolumn",function testa(returnval){//绑定在原码事件里
				if(returnval==null || returnval==pa.getActiveTab().coltype){
					return;
				}
				pa.getActiveTab().coltype = returnval;
				var entityId = pa.getActiveTab().items.item(0).entityId;
				var keyId = pa.getActiveTab().items.item(0).id;
				portalState.init(portalId);//通过portId从容器里取得一个portal
				var changedcolumn = findColumnsByType(returnval,entityId,keyId);//this function defined in index
				var activeTab = pa.getActiveTab();//处于当前状态的即为事件的对象,is instance of panel
				var panelItems = activeTab.items;
				for(var i=0;i<panelItems.length;i++){//先将portal其内容清空
					panelItems.item(i).body.update("<div id='ok'></div>");
				}
				
				//同步所有的portlet的数组
				var p=0;
				var tempTab=[];
				var tempPortlets = [];
				tempTab[0]=tempPortlets;
				
				var saveColumns = pa.getActiveTab().items.item(0).items;
				var k=0;
				var q=0;
				for(var i=0;i<saveColumns.length;i++){
					savePortlets = saveColumns.item(i).items;
					for(var j=0;j<savePortlets.length;j++){
						k++;
						var tempPortlet = new Object();
						var temppInfo = new Object();
						tempPortlet.clumn = changedcolumn[q-changedcolumn.length*(Math.floor(q/changedcolumn.length))].id;
						tempPortlet.entityClumn = changedcolumn[q-changedcolumn.length*(Math.floor(q/changedcolumn.length))].entityId;
						//alert(changedcolumn[q-changedcolumn.length*(Math.floor(q/changedcolumn.length))].id);
						//alert(changedcolumn[q-changedcolumn.length*(Math.floor(q/changedcolumn.length))].entityId);
						q++;
						tempPortlet.seq = eval(Math.ceil(q/changedcolumn.length));
						temppInfo.clumn = tempPortlet.clumn;
						temppInfo.entityClumn = tempPortlet.entityClumn;
						temppInfo.seq = tempPortlet.seq;
						temppInfo.id = savePortlets.item(j).pInfo.id;
						temppInfo.text = savePortlets.item(j).title;
						tempPortlet.id = temppInfo.id;
						tempPortlet.items = savePortlets.item(j).items;
						tempPortlet.pInfo = temppInfo;
						tempPortlet.text = savePortlets.item(j).title;
						tempPortlet.height = savePortlets.item(j).height;
						tempPortlet.checked = true;
						tempPortlet.title = savePortlets.item(j).title;
						tempPortlets.push(tempPortlet);
					}
				}
				
				panelItems.clear();//important
				activeTab.add({
					xtype : 'portal',
					id : portalId,
					entityId : entityId,
					iconCls: 'tabs',
					closable:true,
					menus:true,
					items:changedcolumn,
					border : false
				});
				activeTab.doLayout();
				activeTab.render();
				portalMgr.init(settingId, portalId, portalState,tempTab);
		 	});
		}
	};
}();

Ext.onReady(Divo.app.PortletDemo.init, Divo.app.PortletDemo, true);