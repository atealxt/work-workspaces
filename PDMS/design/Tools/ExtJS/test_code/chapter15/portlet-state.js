Divo.app.PortalState = function(){
	// -------------------- private properties ------------------
	var sm = Ext.state.Manager;
	var portal;
	
	// -------------------- public method ------------------
	return{
		init : function(portalId){
			portal = Ext.ComponentMgr.get(portalId);
		},
		getVisiblePortlets : function(){
			return prefsSelect;//原来为prefs;在index.jsp中定义
		}
	};
}