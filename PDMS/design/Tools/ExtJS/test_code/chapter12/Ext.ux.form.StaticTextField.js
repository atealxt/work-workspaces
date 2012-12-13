Ext.namespace('Ext.ux.form');
Ext.ux.form.StaticTextField = Ext.extend(Ext.form.Field, {
	//设置组件默认常见的HTML元素为div
	defaultAutoCreate : {tag: "div"},
	value : '',
	onRender : function(ct, position){
        Ext.ux.form.StaticTextField.superclass.onRender.call(this, ct, position);
        //将需要显示的文本作为子元素插入到组件中
        Ext.DomHelper.append(this.el, {
        	tag : 'div',
			style :'height:100%;width:100%;',
        	html : this.value
        });
    },
    isDirty : function() {
    	//因为是静态文本所以不存在被改变的情况
        return false;
    },
    isValid : function(){
    	//因为是静态文本所以不需要进行验证
        return true;
    }
});
Ext.reg('xstatictextfield', Ext.ux.form.StaticTextField);