Divo.app.PortalManager = function(){
	// ----------------- private properties ----------------------
	//将prefs[]移到portal.jsp里定义
	
	var portalState, portal, settingId;
	var checkBoxes = [];
	var me;

	// -----------------  private method ----------------------
	function createCheckbox(){
		if(document.getElementById("tbl-dn-ext")){//设置栏只需要加载一次,以后只需要改变状态
			return;
		}
		checkBoxes = [];
		var div = Ext.get(settingId);
		div.dom.innerHTML = "";
		var tbl = document.createElement("table");
		tbl.width = '100%';
		tbl.setAttribute("id", "tbl-dn-ext");
		tbl.border='0';
		var tBody = document.createElement("tBody");
		tbl.appendChild(tBody);
		tbl.cellPadding = 2;
		tbl.cellSpacing = 2;
		var row = document.createElement("tr");
		tBody.appendChild(row);
		var cell = document.createElement("td");
		cell.colSpan = 10;
		cell.height = '1px';
		//cell.innerHTML = "Portlet select:";
		row.appendChild(cell);
		for(var i = 0;i < prefs.length; i++){
			var text = prefs[i].text;
			var rowInd = Math.round(i/5 - 0.4, 0) + 1;
			//alert(rowInd+' '+i);
			if(rowInd >= tbl.rows.length - 1){
				var row = document.createElement("tr");
				tBody.appendChild(row);
			}
			var row = tbl.rows[rowInd];
			var cell = document.createElement("td");
			cell.width = "2%";
			row.appendChild(cell);
			var chk = document.createElement("input");
			chk.type = "checkbox";
			chk.id = prefs[i].id;
			chk.txt = text;
			cell.appendChild(chk);
			checkBoxes.push(chk);
			chk.onclick = onChkChanged;
			cell = document.createElement("td");
			cell.width = "18%";
			row.appendChild(cell);
			cell.innerHTML = text;
		}
		div.appendChild(tbl);
		//alert(div.dom.outerHTML);
	}

	function onChkChanged(e){
		var evt = e || window.event;
		var chk = evt.target || evt.srcElement;
		//alert(chk.id+':id');
		if(!chk.checked){//remove
			portal.removePortlet(chk.id);
			portal.doLayout();//source in Panel doLayout()....
		}else if(chk.checked){//add
			//从取出来的变量里找对应选中项的高度
			var h = 0;
			var seq = 0;
			for(var i=0;i<prefs.length;i++){
				if(chk.id==prefs[i].id){
					h = prefs[i].height;
					seq = prefs[i].seq;
					break;
				}
			}
			
			showPortlet({
				text : chk.txt,
				id : chk.id,
				checked : true,
				clumn : null,
				entityClumn : null,
				height : h,
				seq : seq
			},false);
			portal.doLayout();//source in Panel doLayout()....
		}
	}

	function restoreState(pTab){
		//var portlets = portalState.getVisiblePortlets();
		if(!pTab[0]) return;
		portal.removeAllPortlets();
		for(var i=0;i<pTab[0].length;i++){
			var p = pTab[0][i];
			//alert(p.seq+'seq,id:'+p.id+',text:'+p.text);
			if(p && p.id && p.checked){//当状态为正常时才显示
				showPortlet(p,true);
				//me.setCheckBox(p.id, true);
			}
		}
		portal.doLayout();
	}
	
	//在改变tab页时重新设置checkbox
	function reseatOnChangeTab(portal){
		for(var k=0;k<checkBoxes.length;k++){
			checkBoxes[k].checked = false;
		}
		
		for(var i=0;i<portal.items.length;i++){
			var thePortlets = portal.items.item(i).items;
			if(!thePortlets){
				return;
			}
			for(var j=0;j<thePortlets.length;j++){
				//alert(thePortlets.item(j).pInfo.checked);
				if(thePortlets.item(j) && thePortlets.item(j).pInfo.id){
					me.setCheckBox(thePortlets.item(j).pInfo.id, true);
				}
			}
		}
	}

	function showPortlet(pInfo,isShow){
		var items;
		for(var i=0; i<prefs.length; i++){
			if(prefs[i].id==pInfo.id){
				items = prefs[i].items;
				break;
			}
		}
		var pTools = [{//每个portlet的右上角关闭事件
			id : 'close',//handler就是一个事件的控制函数
			handler : function(e, target, panel){
				panel.ownerCt.remove(panel, true);
				
				//add by mlc 2008-02-03
				var col =Ext.ComponentMgr.get(panel.pInfo.clumn);//当删除非每列最后一个时其后也同样要改变seq
				var colLeng=col.items.length;
				if(panel.pInfo.seq<=colLeng&&colLeng!=0){//非每列的最后一个
					var temp;
					for(var i=panel.pInfo.seq-1;i<colLeng;i++){
						temp = col.items.itemAt(i);
						temp.pInfo.seq = temp.pInfo.seq-1;
					}
					panel.pInfo.checked=false;
					panel.pInfo.seq=-1;
				}else{//每列最后一个
					panel.pInfo.checked=false;
					panel.pInfo.seq=-1;
				}
				//end
				me.setCheckBox(panel.pInfo.id, false);//change the checkbox
			}
		}];
		if(isShow){
			var p = portal.showPortlet({//show
				id : pInfo.id,
				text : pInfo.text,
				height : eval(pInfo.height),
				clumn : pInfo.clumn,//add by mlc
				entityClumn : pInfo.entityClumn,
				checked : pInfo.checked,
				seq : pInfo.seq
			}, pTools,items);
			//alert(p.title+"   "+p.pInfo.clumn);
		}else if(!isShow){//is add
			var p = portal.addPortlet({
				id : pInfo.id,
				text : pInfo.text,
				height : eval(pInfo.height),
				seq : pInfo.seq
			}, pTools,items);
		}
	}

	// -------------------- public method -----------------------
	return{
		init : function(setId, portId, pset,pTab){
			settingId = setId;
			portalState = pset;
			me = this;
			portal = Ext.ComponentMgr.get(portId);
			restoreState(pTab);
		},
		setCheckBox : function(ext, checked){
			for(var i = 0;i < checkBoxes.length; i++){
				if(checkBoxes[i].id == ext){
					checkBoxes[i].checked = checked;
					break;
				}
			}
		},
		crCheckbox : createCheckbox,
		resetChangeTab : reseatOnChangeTab,
		initParam : function(portId){
			portal = Ext.ComponentMgr.get(portId);
		}
	};
}