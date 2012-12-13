Hilite={
exact:true,
max_nodes:1000,
style_name:'hilite',
style_name_suffix:true
};
Hilite.hiliteElement=function(elm,query){
if(!query||elm.childNodes.length==0)
return;
query=query.split(/[\s,\+\.]+/);
var qre=new Array();
for(var i=0;i<query.length;i++){
query[i]=query[i].toLowerCase();
if(Hilite.exact)
qre.push('\\b'+query[i]+'\\b');
else
qre.push(query[i]);
}
qre=new RegExp(qre.join("|"),"i");
var stylemapper={};
for(var i=0;i<query.length;i++){
if(Hilite.style_name_suffix)
stylemapper[query[i]]=Hilite.style_name+(i+1);
else
stylemapper[query[i]]=Hilite.style_name;
}
var textproc=function(node){
var match=qre.exec(node.data);
if(match){
var val=match[0];
var k='';
var node2=node.splitText(match.index);
var node3=node2.splitText(val.length);
var span=node.ownerDocument.createElement('SPAN');
node.parentNode.replaceChild(span,node2);
span.className=stylemapper[val.toLowerCase()];
span.appendChild(node2);
return span;
}else{
return node;
}
};
Hilite.walkElements(elm.childNodes[0],1,textproc);
};
Hilite.walkElements=function(node,depth,textproc){
var skipre=/^(script|style|textarea)/i;
var count=0;
while(node&&depth>0){
count++;
if(count>=Hilite.max_nodes){
var handler=function(){
Hilite.walkElements(node,depth,textproc);
};
setTimeout(handler,50);
return;
}
if(node.nodeType==1){
if(!skipre.test(node.tagName)&&node.childNodes.length>0){
node=node.childNodes[0];
depth++;
continue;
}
}else if(node.nodeType==3){
node=textproc(node);
}
if(node.nextSibling){
node=node.nextSibling;
}else{
while(depth>0){
node=node.parentNode;
depth--;
if(node.nextSibling){
node=node.nextSibling;
break;
}
}
}
}
};