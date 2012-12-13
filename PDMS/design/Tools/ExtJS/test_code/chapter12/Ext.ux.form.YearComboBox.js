/*
 * 封装Ext.form.ComboBox组件配置，实现组件功能的扩展
 */
Ext.namespace('Ext.ux.form');
Ext.ux.form.YearComboBox = Ext.extend(Ext.form.ComboBox, {
	editable : false,
	displayField:'year',
	valueField:'value',
	typeAhead: true,
	mode: 'local',
	triggerAction: 'all',
	selectOnFocus:true,
	initComponent : function() {
		var years = [];
		for(var i = 2000;i <= 2020 ; i++){
			years.push([+i+"年",i]);
		}
		this.store = new Ext.data.SimpleStore({
			fields: ['year','value'],
			data : years
		});
	}
})
Ext.reg('xyearcombobox', Ext.ux.form.YearComboBox);