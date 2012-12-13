<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
 	<title>系统登录</title>
	<link rel="stylesheet" type="text/css" href="js/ext/resources/css/ext-all.css" />
 	<script type="text/javascript" src="js/ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="js/ext/ext-all.js"></script>
	<script type="text/javascript">
	Ext.onReady(function (){
		Ext.QuickTips.init();
	    //================  Login form  =======================
    	var simple = new Ext.FormPanel({
	        labelWidth: 60,
	        labelAlign: 'right',
	        labelSeparator: ':   ',
	        frame:true,
	        bodyStyle:'padding:17px 17px 0',
	        width: 370,
	        defaults: {width: 230,msgTarget: 'side'},
	        defaultType: 'textfield',
	        items: [{
	                fieldLabel: '登录ID',
	                name: 'loginname',
	                allowBlank:false,
	                blankText:'请输入登录ID'
            	},{
	                inputType:'password',
	                fieldLabel: '密&nbsp;&nbsp;&nbsp;码',
	                name: 'password',
	                allowBlank:false,
	                blankText:'请输入密码'
            	}
        	],
	        keys: [{
	            key: [10,13],
	            fn: function(){
	                userlogin();
	            }
	        }]
   		});
    	var windowLogin = new Ext.Window({
	        title: '中小型应用智能系统',
	        width: 400,
	        height:200,
	        draggable: true,
	        collapsed: false,
	        layout: 'fit',
	        closable:false,
	        collapsible:false,
	        plain:true,
	        modal:true,
	        resizable:false,
	        bodyStyle:'padding:11px;',
	        buttonAlign:'center',
	        items: simple,
	        buttons: [{
	            text: '登录',
	               type: 'submit',
	               handler:userlogin
	            },{
	               text: '重置',
	               handler:function(){simple.form.reset();}
			}]
	    });
    	windowLogin.show();
    	function userlogin(){
		    if(simple.form.isValid()){
				Ext.MessageBox.show({
	               //title: 'login',
	               msg: '正在登录...',
	               progressText: '',
	               width:300,
	               progress:true,
	               closable:false,
	               animEl: 'loading'
	            });
				var f = function(v){
		             return function(){
		                 var i = v/11;
		                 Ext.MessageBox.updateProgress(i, '');
		             };
		        };
		        for(var i = 1; i < 13; i++){
		           setTimeout(f(i), i*30);
		        }
         		simple.form.doAction('submit',{
		            url:'<%=path%>/system/user!doLogin.action',
		            method:'post',
					params:{useAjax:'yes'},
					success:function(form,action){
						//alert(action.result.success);
		                if(action.result.success=='1'){
		                	document.location.href = '<%=path%>/index.jsp';
		                }else{
		                	Ext.Msg.alert('登录失败','用户名密码不匹配！');
		                }
			        },
					failure:function(){
			             Ext.Msg.alert('登录失败','服务器无法连接，请稍后再试!');
			        }
			    });
	  		} 
		}
		
	});
	</script>
</head>
<body>
  
</body>
</html>
