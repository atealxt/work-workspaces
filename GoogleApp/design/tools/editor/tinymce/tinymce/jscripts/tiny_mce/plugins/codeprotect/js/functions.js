tinyMCEPopup.requireLangPack();
tinyMCEPopup.onInit.add(onLoadInit);

var action, element, ed;

function onLoadInit() {
	tinyMCEPopup.resizeToInnerSize();

	var formObj = document.forms[0];
	ed = tinyMCEPopup.editor;
	var elm = ed.selection.getNode();
	var action = "insert";
	var html = new String(ed.dom.getAttrib(elm, 'alt'));
	//html = html.replace(/\'\s/g,"'\n");
	if (elm != null && elm.nodeName == "IMG") 
		action = "update";
	

	formObj.insert.value = ed.getLang('update', 'Insert', true); 
	if (action == "update") {
		formObj.alt.value = html
		window.focus();
	} 
	
	
	resizeInputs();
}
function resizeInputs() {
	var el = document.getElementById('codeprotectAlt');

	if (!tinymce.isIE) {
		 wHeight = self.innerHeight - 65;
		 wWidth = self.innerWidth - 16;
	} else {
		 wHeight = document.body.clientHeight - 70;
		 wWidth = document.body.clientWidth - 16;
	}

	el.style.height = Math.abs(wHeight) + 'px';
	el.style.width  = Math.abs(wWidth) + 'px';
}

function setAttrib(elm, attrib, value) {
	var formObj = document.forms[0];
	var valueElm = formObj.elements[attrib];

	if (typeof(value) == "undefined" || value == null) {
		value = "";

		if (valueElm)
			value = valueElm.value;
	}

	if (value != "") {
		elm.setAttribute(attrib, value);

		eval('elm.' + attrib + "=value;");
	} else
		elm.removeAttribute(attrib);
}
function saveContent() {

	var elm;

	tinyMCEPopup.restoreSelection();


	elm = ed.selection.getNode();

	var name = document.forms[0].alt.value;
	
	

	name = name.replace(/\"/g, "'");
	name = name.replace(/\s+/g, " ");
	

	if (elm != null && elm.nodeName == "IMG") {
		setAttrib(elm, 'alt', name);
		if (elm.nodeName == "IMG")
			elm.setAttribute("alt", name);
	} else {
		var rng = ed.selection.getRng();

		if (rng.collapse)
			rng.collapse(false);


	
		html = '<img width="11" height="11"';
		html += ' src="' + (tinyMCEPopup.getWindowArg("plugin_url") + '/img/codeprotect_symbol.gif') +'"';
		html += ' alt="' + name + '" class="mceCodeProtect" />';
		
		ed.execCommand("mceInsertContent", false, html);


	}
	tinyMCEPopup.close();
}
