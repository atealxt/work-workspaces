	var http_request = false;
	var tryAgain = false;
	var timer = false;

	function createAjaxObj(){
		if(window.XMLHttpRequest){
     			return new XMLHttpRequest();
     		}
  		if(window.ActiveXObject){
     			var objs=["MSXML2.XMLHttp.5.0","MSXML2.XMLHttp.4.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp","Microsoft.XMLHTTP"];
     			var xmlhttp;
     			for(var i=0;i<objs.length;i++){
      				try{
         				xmlhttp=new ActiveXObject(objs[i]);
         				return xmlhttp;
       				}
       				catch(e){ }
    			 }
  		}
	}

	function getResource(uri,timeoutNumber,control){
	 	tryAgain = function () {
	 		http_request.abort();
	 		http_request.onreadystatechange = null;
      			getResource(uri,timeoutNumber);
    		}

		timer = setTimeout(tryAgain, timeoutNumber);

		http_request.onreadystatechange = function() {
			if (http_request.readyState != 4) {
            			// Ignore non-loaded readyStates
            			// ...will timeout if do not get to "Loaded"
            			return;
        		}
        		clearTimeout(timer);  // readyState==4, no more timer
        		if (http_request.status==200) {  // "OK status"
        				//http_request.responseText
						//control.innerHTML="Request complate:  " + http_request.responseText;
						var al = http_request.responseText;
						control.value=al;

        		}
				/*
        		else if (http_request.status==304) {
            			// "Not Modified": No change to display
        		}
        		*/
        		else if (http_request.status >= 400 && http_request.status < 500) {
            			// Client error, probably bad URI
            			alert("UriErrorException!");
        		}
        		else if (http_request.status >= 500 && http_request.status < 600) {
            			// Server error, try again after delay
            			timer = setTimeout(tryAgain, timeoutNumber);
        		}
        		else {
            			alert("StateErrorException!");
        		}
		}
		http_request.open("GET", uri, true);
		http_request.send(null);

	}

	//Ajax Main Method
	//timeoutNumber is advised to more than 10s.
	function send_request(uri,timeoutNumber,control){

		if(uri.replace(/(^\s*)|(\s*$)/g, "") == ""){
			alert("UriNullException!");
			return false;
		}

		var patrn =/^[0-9]{4,6}$/g;
		if (!patrn.compile(timeoutNumber)) {
			alert("NumberException");
			return false ;
		}

		http_request = false;
		http_request=createAjaxObj();
		if (!http_request) {
			alert("CreatObjectException!");
			return false;
		}

		getResource(uri,timeoutNumber,control);

		return true;
	}

