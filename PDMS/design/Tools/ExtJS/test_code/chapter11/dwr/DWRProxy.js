Ext.data.DWRProxy = function(config){
    Ext.data.DWRProxy.superclass.constructor.call(this);
    if(config && config.dwrMethod){
		this.dwrMethod = config.dwrMethod;
	}
};

Ext.extend(Ext.data.DWRProxy, Ext.data.DataProxy, {
    load : function(params, reader, callback, scope, arg){
		params = params || [];
		if(this.fireEvent("beforeload", this, params) !== false){
			if(this.dwrMethod){
				//加载数据成功的处理函数
				var success = this.processResponse.createDelegate(this, [reader, callback, scope, arg], 1);
				//加载数据失败的处理函数
				var failure = this.handleFailure.createDelegate(this, [reader, callback, scope, arg], 1);
				//处理请求参数
				var callParams = [];
				if(params instanceof Array){
					callParams = callParams.concat(params);
				}else{
					for(var i in params){
						callParams.push(params[i]);
					}
				}
				//将调用DWR方法的元数据对象加入参数数组中
				callParams.push({callback:success, errorHandler:failure});
				//调用DWR方法并出动参数
				this.dwrMethod.apply(this, callParams); 
			}else{
				//触发读取异常事件
				this.fireEvent("loadexception",this, null,'dwrMethod undefined !');
			}
		}else{
			callback.call(scope, null, arg, false);
		}
    },

    processResponse : function(response, reader, callback, scope, arg){
        var result;
        try {
			//判断响应类型调用数据解析器解析数据
			if(typeof(response) == 'string'){
				result = reader.read({responseText:response});
			}else{
				result = reader.readRecords(response);
			}
        }catch(e){
            this.fireEvent("loadexception", this, null, response, e);
            callback.call(scope, null, arg, false);
            return;
        }
        callback.call(scope, result, arg, true);
    },
    
    handleFailure : function(response, reader, callback, scope, arg){
		//触发读取异常事件
        this.fireEvent("loadexception", this, null, response);
    }
    
});