<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书籍列表</title>
</head>
<script type="text/javascript">
	Ext.onReady(function(){
		//定义数据集对象
		var bookStore = new Ext.data.Store({
			autoLoad :true,
			reader: new Ext.data.XmlReader({
				totalRecords: "results",
				record: "Book",
				id: "id"
			},
			Ext.data.Record.create([
				{name: 'id'},
				{name: 'bookName'},
				{name: 'author'},
				{name: 'typeName'},
				{name: 'price'},
				{name: 'brief'}
			])
			),
			proxy : new Ext.data.HttpProxy({
				url : 'bookext.do?method=getBookList'
			})
		})
		//创建工具栏组件
		var toolbar = new Ext.Toolbar([
			{text : '新增书籍',iconCls:'add',handler:showAddBook},
			{text : '修改书籍',iconCls:'option',handler:showModifyBook},
			{text : '删除书籍',iconCls:'remove',handler:showDeleteBooks}
		]);
		//创建Grid表格组件
		var cb = new Ext.grid.CheckboxSelectionModel()
		var bookGrid = new Ext.grid.GridPanel({
			applyTo : 'grid-div',
			frame:true,
			tbar : toolbar,
			store: bookStore,
			stripeRows : true,
			autoScroll : true,
			viewConfig : {
				autoFill : true
			},
			sm : cb,
			columns: [//配置表格列
				new Ext.grid.RowNumberer({
					header : '行号',
					width : 40
				}),//表格行号组件
				cb,
				{header: "书籍编号", width: 80, dataIndex: 'id', sortable: true},
				{header: "书籍名称", width: 80, dataIndex: 'bookName', sortable: true},
				{header: "作者", width: 80, dataIndex: 'author', sortable: true},
				{header: "类型", width: 80, dataIndex: 'typeName', sortable: true},
				{header: "金额", width: 80, dataIndex: 'price', sortable: true},
				{header: "简介", width: 80, dataIndex: 'brief', sortable: true}
			]
		})
		//创建新增或修改书籍信息的form表单
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';//统一指定错误信息提示方式
		var bookForm = new Ext.FormPanel({
			labelSeparator : "：",
			frame:true,
			border:false,
			items : [
				{
					xtype:'textfield',
					width : 200,
					allowBlank : false,
					blankText : '书籍名称不能为空',
					name : 'bookName',
					fieldLabel:'书籍名称'
				},{
					xtype:'textfield',
					width : 200,
					allowBlank : false,
					blankText : '书籍作者不能为空',
					name : 'author',
					fieldLabel:'作者'
				},{
					xtype:'combo',
					width : 200,
					allowBlank : false,
					blankText : '必须选择书籍类型',
					hiddenName : 'bookTypeId',
					name : 'typeName',
					store : new Ext.data.Store({
						autoLoad :true,
						reader: new Ext.data.XmlReader({
							totalRecords: "results",
							record: "BookType",
							id: "id"
						},
						Ext.data.Record.create([
							{name: 'id'},
							{name: 'title'},
							{name: 'detail'}
						])
						),
						proxy : new Ext.data.HttpProxy({
							url : 'bookext.do?method=getBookTypeList'
						})
					}),//设置数据源
					allQuery:'allbook',//查询全部信息的查询字符串
					triggerAction: 'all',//单击触发按钮显示全部数据
					editable : false,//禁止编辑
					loadingText : '正在加载书籍类型信息',//加载数据时显示的提示信息
					displayField:'title',//定义要显示的字段
					valueField : 'id',
					emptyText :'请选择书籍类型',
					mode: 'remote',//远程模式
					fieldLabel:'类型'
				},{
					xtype:'textfield',
					width : 200,
					name : 'price',
					fieldLabel:'金额'
				},{
					xtype:'textarea',
					width : 200,
					name : 'brief',
					fieldLabel:'简介'
				},{
					xtype:'hidden',
					name : 'id'
				}
			],
			buttons:[
				{
					text : '关闭',
					handler : function(){
						win.hide();
					}
				},{
					text : '提交',
					handler : submitForm
				}
			]
		});
		//创建弹出窗口
		var win = new Ext.Window({
			layout:'fit',
		    width:380,
		    closeAction:'hide',
		    height:280,
			resizable : false,
			shadow : true,
			modal :true,
		    closable:true,
		    bodyStyle:'padding:5 5 5 5',
		    animCollapse:true,
			items:[bookForm]
		});
		//显示新建书籍窗口
		function showAddBook(){
			bookForm.form.reset();
			bookForm.isAdd = true;
			win.setTitle("新增书籍信息");
			win.show();
		}
		//显示修改书籍窗口
		function showModifyBook(){
			var bookList = getBookIdList();
			var num = bookList.length;
			if(num > 1){
				Ext.MessageBox.alert("提示","每次只能修改一条书籍信息。")
			}else if(num == 1){
				bookForm.form.reset();
				bookForm.isAdd = false;
				win.setTitle("修改书籍信息");
				win.show();
				var bookId = bookList[0];
				loadForm(bookId);
			}
		}
		//显示删除书籍对话框
		function showDeleteBooks(){
			var bookList = getBookIdList();
			var num = bookList.length;
			if(num == 0){
				return;
			}
			Ext.MessageBox.confirm("提示","您确定要删除所选书籍吗？",function(btnId){
				if(btnId == 'yes'){
					deleteBooks(bookList);
				}
			})
		}
		//删除书籍
		function deleteBooks(bookList){
			var bookIds = bookList.join('-');
			var msgTip = Ext.MessageBox.show({
				title:'提示',
				width : 250,
				msg:'正在删除书籍信息请稍后......'
			});
			Ext.Ajax.request({
				url : 'bookext.do?method=deleteBooks',
				params : {bookIds : bookIds},
				method : 'POST',
				success : function(response,options){
					msgTip.hide();
					var result = Ext.util.JSON.decode(response.responseText);
					if(result.success){
						//服务器端数据成功删除后，同步删除客户端列表中的数据
						for(var i = 0 ; i < bookList.length ; i++){
							var index = bookStore.find('id',bookList[i]);
							if(index != -1){
								var rec = bookStore.getAt(index)
								bookStore.remove(rec);
							}
						}
						Ext.Msg.alert('提示','删除书籍信息成功。');
					}else{
						Ext.Msg.alert('提示','删除书籍信息失败！');
					}
				},
				failure : function(response,options){
					msgTip.hide();
					Ext.Msg.alert('提示','删除书籍信息请求失败！');
				}
			});
		}
		//加载表单数据
		function loadForm(bookId){
			bookForm.form.load({
				waitMsg : '正在加载数据请稍后',//提示信息
				waitTitle : '提示',//标题
				url : 'bookext.do?method=getBookById',//请求的url地址
				params : {bookId:bookId},
				method:'GET',//请求方式
				success:function(form,action){//加载成功的处理函数
					//Ext.Msg.alert('提示','数据加载成功');
				},
				failure:function(form,action){//加载失败的处理函数
					Ext.Msg.alert('提示','数据加载失败');
				}
			});
		}
		//提交表单数据
		function submitForm(){
			//判断当前执行的提交操作，isAdd为true表示执行书籍新增操作，false表示执行书籍修改操作
			if(bookForm.isAdd){
				//新增书籍信息
				bookForm.form.submit({
					clientValidation:true,//进行客户端验证
					waitMsg : '正在提交数据请稍后',//提示信息
					waitTitle : '提示',//标题
					url : 'bookext.do?method=addBook',//请求的url地址
					method:'POST',//请求方式
					success:function(form,action){//加载成功的处理函数
						win.hide();
						updateBookList(action.result.bookId);
						Ext.Msg.alert('提示','新增书籍成功');
					},
					failure:function(form,action){//加载失败的处理函数
						Ext.Msg.alert('提示','新增书籍失败');
					}
				});
			}else{
				//修改书籍信息
				bookForm.form.submit({
					clientValidation:true,//进行客户端验证
					waitMsg : '正在提交数据请稍后',//提示信息
					waitTitle : '提示',//标题
					url : 'bookext.do?method=modifyBook',//请求的url地址
					method:'POST',//请求方式
					success:function(form,action){//加载成功的处理函数
						win.hide();
						updateBookList(action.result.bookId);
						Ext.Msg.alert('提示','修改书籍成功');
					},
					failure:function(form,action){//加载失败的处理函数
						Ext.Msg.alert('提示','修改书籍失败');
					}
				});
			}
		}
		//明细数据修改后，同步更新书籍列表信息
		function updateBookList(bookId){
			var fields = getFormFieldsObj(bookId);
			var index = bookStore.find('id',fields.id);
			if(index != -1){
				var item = bookStore.getAt(index);
				for(var fieldName in fields){
					item.set(fieldName,fields[fieldName]);
				}
				bookStore.commitChanges();
			}else{
				var rec = new Ext.data.Record(fields);
				bookStore.add(rec);
			}
		}
		//取得表单数据
		function getFormFieldsObj(bookId){
			var fields = bookForm.items;
			var obj = {};
			for(var i = 0 ; i < fields.length ; i++){
				var item = 	fields.itemAt(i);
				var value = item.getValue();
				if(item.getXType() == 'combo'){
					var index = item.store.find('id',value);
					if(index != -1){
						var selItem = item.store.getAt(index);
					}
					value = selItem.get('title');
				}
				obj[item.name] = value;
			}
			if(Ext.isEmpty(obj['id'])){
				obj['id'] = bookId;
			}
			return obj;
		}
		//取得所选书籍
		function getBookIdList(){
			var recs = bookGrid.getSelectionModel().getSelections();
			var list = [];
			if(recs.length == 0){
				Ext.MessageBox.alert('提示','请选择要进行操作的书籍！');
			}else{
				for(var i = 0 ; i < recs.length ; i++){
					var rec = recs[i];
					list.push(rec.get('id'))
				}
			}
			return list;
		}
	});
</script>
<body>
<div id='grid-div' style="width:100%; height:100%;"/>
</body>
</html>