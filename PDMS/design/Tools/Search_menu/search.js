
function $(id){
	return document.getElementById(id);
}

function Dsy(){
    this.Items = {};
}
Dsy.prototype.add = function(id,iArray){
    this.Items[id] = iArray;
}
Dsy.prototype.get = function(id){
    return this.Items[id];
}
Dsy.prototype.Exists = function(id){
    if(typeof(this.Items[id]) == "undefined") {
        return false;
    }
    return true;
}

function change(v){
	$("text1").value="";
    var str="0";
    for(i=0;i<v;i++){
        str+=("_"+($(s[i]).selectedIndex-1));
    }
    var ss=$(s[v]);
    with(ss){
        length = 0;
        options[0]=new Option(opt0[v],opt0[v]);
        if(v && $(s[v-1]).selectedIndex>0 || !v){
            if(dsy.Exists(str)){
                ar = dsy.Items[str];
                for(i=0;i<ar.length;i++){
                    options[length]=new Option(ar[i],ar[i]);
                }
                if(v){
                    options[1].selected = true;
                }
            }
        }
        if(++v<s.length){
            change(v);
        }
        else{
        	if($("s1").selectedIndex<1){
        		$("text1").disabled=true;
        	}
        	else if($("s1").selectedIndex==2 && $("s2").selectedIndex==1){
        		$("text1").disabled=true;
        	}
        	else if($("s1").selectedIndex==3 && $("s2").selectedIndex==1){
        		$("text1").disabled=true;
        	}
        	else if($("s1").selectedIndex==3 && $("s2").selectedIndex==2){
        		$("text1").disabled=true;
        	}
        	else{
        		$("text1").disabled=false;
        	}
        }
        }
}

var dsy = new Dsy();

dsy.add("0",["帖子","用户","任务","项目"]);

dsy.add("0_0",["主题帖","回帖"]);	//搜索结果始终是帖子，如果条件传了回贴，结果把具体楼显示出来。
dsy.add("0_0_0",["发表人ID","发表人姓名","主题名","主题内容"]);
dsy.add("0_0_1",["发表人ID","发表人姓名","回复内容"]);

/*
dsy.add("0_1",["角色","用户组","其他"]);
dsy.add("0_1_0",["系统管理员","会员","项目负责人"]);	//附加条件添加不可
dsy.add("0_1_1",[]);
dsy.add("0_1_2",["用户ID","用户名","公司IP"]);
*/
dsy.add("0_1",["角色","其他"]);
dsy.add("0_1_0",["系统管理员","会员","项目负责人"]);	//附加条件添加不可
dsy.add("0_1_1",["用户ID","用户名","公司IP"]);

dsy.add("0_2",["完成状态","验收确认状态","分配人","接收人","验收人","其他"]);
dsy.add("0_2_0",["已完成","未完成"]);	//附加条件添加不可
dsy.add("0_2_1",["已验收","未验收"]);	//附加条件添加不可
dsy.add("0_2_2",["用户ID","用户名"]);
dsy.add("0_2_3",["用户ID","用户名"]);
dsy.add("0_2_4",["用户ID","用户名"]);
dsy.add("0_2_5",["任务ID","任务内容"]);

dsy.add("0_3",["项目成员","其他"]);
dsy.add("0_3_0",["用户ID","用户名"]);
dsy.add("0_3_1",["项目ID","项目名","项目简要"]);

var s=["s1","s2","s3"];
var opt0 = ["一级分类","二级分类","三级分类"];

function setup(){
    for(i=0;i<s.length-1;i++){
        $(s[i]).onchange=new Function("change("+(i+1)+")");
    }
    change(0);
}

function addSearch(){

	if($("s1").selectedIndex <=0){
		alert("请选择搜索条件");
		$("s1").focus();
		return;
	}

	var text_value = $("text1").value;
	if(!$("text1").disabled && text_value==""){
		alert("请输入关键字");
		$("text1").focus();
		return;
	}

	var cd = $("condition_code");
	var condition_value = cd.innerHTML;
	var condition_value_temp = "";
	condition_value_temp = "s1=" + $("s1").selectedIndex;

	if(cd.innerHTML != "" && condition_value.indexOf(condition_value_temp) == -1){
		alert("只能添加一个一级分类条件");
		return;
	}

	condition_value_temp = condition_value_temp + "s2=" + $("s2").selectedIndex;
	
	if(condition_value.indexOf(condition_value_temp) != -1 && $("text1").disabled == true){
		alert("已经添加了相关搜索条件");
		return;
	}	
	
	condition_value_temp = condition_value_temp + "s3=" + $("s3").selectedIndex;

	if(condition_value.indexOf(condition_value_temp) != -1){
		alert("不能重复添加相同分类条件");
		return;
	}

	condition_value = condition_value + condition_value_temp;
	condition_value = condition_value + "c=" + $("text1").value + "<br>";
	cd.innerHTML = condition_value;
	
	var cd_zh = $("condition_zh");
	cd_zh.innerHTML = cd_zh.innerHTML 
					+ $("s1").value + "．"
					+ $("s2").value + "．"
					+ $("s3").value;
	if(!$("text1").disabled)	{	
		cd_zh.innerHTML = cd_zh.innerHTML + " = " + $("text1").value;
	}
	cd_zh.innerHTML = cd_zh.innerHTML + "<br>";
}

function condition_clear(){
	var cd = $("condition_code");
	cd.innerHTML = "";	
	var cd_zh = $("condition_zh");
	cd_zh.innerHTML = "";	
}

function getRet(){
	
	alert($("condition_code").innerHTML);
	
}