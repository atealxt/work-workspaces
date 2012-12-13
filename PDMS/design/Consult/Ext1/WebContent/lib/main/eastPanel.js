EastPanel=function(){
	EastPanel.superclass.constructor.call(this,{
        region:'east',
        title: '项目状态',
        collapsible: true,
        collapsed:true,
        split:true,
        width: 225,
        minSize: 175,
        maxSize: 400,
        layout:'fit',
        margins:'0 5 0 0',
        items:
		new Ext.grid.PropertyGrid({
                closable: false,
                source: {
                    "(name)": "Properties Grid",
                    "grouping": false,
                    "autoFitColumns": true,
                    "productionQuality": false,
                    "created": new Date(Date.parse('10/15/2006')),
                    "tested": false,
                    "version": .01,
                    "borderWidth": 1
                }
            })		
	});
};
Ext.extend(EastPanel,Ext.Panel);
