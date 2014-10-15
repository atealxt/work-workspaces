
(function() {
	// Load plugin specific language pack
	tinymce.PluginManager.requireLangPack('codeprotect');

	tinymce.create('tinymce.plugins.codeprotectPlugin', {
		/**
		 * Initializes the plugin, this will be executed after the plugin has been created.
		 * This call is done before the editor instance has finished it's initialization so use the onInit event
		 * of the editor instance to intercept that event.
		 *
		 * @param {tinymce.Editor} ed Editor instance that the plugin is initialized in.
		 * @param {string} url Absolute URL to where the plugin is located.
		 */
		init : function(ed, url) {
			// Register the command so that it can be invoked by using tinyMCE.activeEditor.execCommand('mcecodeprotect');
			
			
			
			function isCodeProtectElm(n) {
				return /^(mceCodeProtect)$/.test(n.className);
			};

			codeLeftDelim = ed.getParam("codeprotect_left_delim", "{{");
			codeRightDelim = ed.getParam("codeprotect_right_delim", "}}");


			var t = this;
			
			t.editor = ed;
			t.url = url;
			
			ed.addCommand('mcecodeprotect', function() {
				ed.windowManager.open({
					file : url + '/codeprotect.htm',
					width : 320 + ed.getLang('codeprotect.delta_width', 0),
					height : 120 + ed.getLang('codeprotect.delta_height', 0),
					inline : 1
				}, {
					plugin_url : url, // Plugin absolute URL
					some_custom_arg : 'custom arg' // Custom argument
				});
			});

			// Register codeprotect button
			ed.addButton('codeprotect', {
				title : 'codeprotect.desc',
				cmd : 'mcecodeprotect',
				image : url + '/img/codeprotect.gif'
			});

			// Add a node change handler, selects the button in the UI when a image is selected
			ed.onNodeChange.add(function(ed, cm, n) {
				cm.setActive('codeprotect', n.nodeName == 'IMG');
			});
			

			// Turn String -> HTML ( {{}} -> IMG )			
			ed.onBeforeSetContent.add(function(ed, o) {
			   // Replaces all a characters with b characters
				var startPos = 0;
			
				while ((startPos = o.content.indexOf(codeLeftDelim, startPos)) != -1) {
					
					
					ep = o.content.indexOf('>', startPos + 2);
					sp = o.content.indexOf('<', startPos + 2);
					if (ep < sp) {
						startPos++;
						continue;
					}
					// Find end of object
					endPos = o.content.indexOf(codeRightDelim, startPos);
					var codeStr = o.content.substring(startPos + 2, endPos);
					endPos += 2;
	
					// Insert image
					var contentAfter = o.content.substring(endPos);
	
					o.content = o.content.substring(0, startPos);
					o.content += '<img width="11" height="11"';
					o.content += ' src="' + url + '/img/codeprotect_symbol.gif' +'"';
					o.content += ' alt="' + codeStr + '" class="mceCodeProtect" />';
					o.content += contentAfter;
					startPos++;
				}
			});



			// Turn HTML -> String ( IMG -> {{}} )
			ed.onPostProcess.add(function(ed, o) {
                if (o.get) {
                	var startPos = -1;
					while ((startPos = o.content.indexOf('<img', startPos+1)) != -1) {
						var endPos = o.content.indexOf('/>', startPos);
						var attribs = t._parseAttributes(o.content.substring(startPos + 4, endPos));
						endPos += 2;
                	
                    	if (!attribs['src'] || !attribs['alt'] || -1 == attribs['src'].indexOf('codeprotect')) {
							startPos += 3;
							continue;
						}
	
						// Insert embed/object chunk
						chunkBefore = o.content.substring(0, startPos);
						chunkAfter = o.content.substring(endPos);
						o.content = chunkBefore + codeLeftDelim + attribs['alt'] + codeRightDelim + chunkAfter;
                    }
                }
            });
			
			
			ed.onNodeChange.add(function(ed, cm, n) {
				
				cm.setActive('codeprotect', n.nodeName == 'IMG' && isCodeProtectElm(n));
				
			});

			
		},
		
		/**
		 * Creates control instances based in the incomming name. This method is normally not
		 * needed since the addButton method of the tinymce.Editor class is a more easy way of adding buttons
		 * but you sometimes need to create more complex controls like listboxes, split buttons etc then this
		 * method can be used to create those.
		 *
		 * @param {String} n Name of the control to create.
		 * @param {tinymce.ControlManager} cm Control manager to use inorder to create new control.
		 * @return {tinymce.ui.Control} New control instance or null if no control was created.
		 */
		createControl : function(n, cm) {
			return null;
		},

		/**
		 * Returns information about the plugin as a name/value array.
		 * The current keys are longname, author, authorurl, infourl and version.
		 *
		 * @return {Object} Name/value array containing information about the plugin.
		 */
		getInfo : function() {
			return {
				longname : 'CodeProtect Ex plugin for TinyMCE v3.x',
				author : 'Vorapoap Lohwongwatana',
				authorurl : 'http://tinymce.moxiecode.com',
				infourl : 'http://wiki.moxiecode.com/index.php/TinyMCE:Plugins/codeprotect',
				version : "0.9.3"
			};
		},
		
		_parseAttributes : function(attribute_string) {
			var attributeName = "";
			var attributeValue = "";
			var withInName;
			var withInValue;
			var attributes = new Array();
			var whiteSpaceRegExp = new RegExp('^[ \n\r\t]+', 'g');
	
			if (attribute_string == null || attribute_string.length < 2)
				return null;
	
			attribute_string = attribute_string.replace(/'/g, "&#39;");
	
	
			withInName = withInValue = false;
			for (var i=0; i<attribute_string.length; i++) {
				var chr = attribute_string.charAt(i);
	
				if ((chr == '"' || chr == "'") && !withInValue)
					withInValue = true;
				else if ((chr == '"' || chr == "'") && withInValue) {
					withInValue = false;
									
					var pos = attributeName.lastIndexOf(' ');
					if (pos != -1)
						attributeName = attributeName.substring(pos+1);
					attributeValue = attributeValue.replace(/&#39;/g,"'");
					attributeValue = attributeValue.replace(/&lt;/g,"<");
					attributeValue = attributeValue.replace(/&gt;/g,">");
					attributeValue = attributeValue.replace(/&amp;/g,"&");
					attributeValue = attributeValue.replace(/&#35;/g,"#");
					attributes[attributeName.toLowerCase()] = attributeValue.substring(1);
	
					attributeName = "";
					attributeValue = "";
				} else if (!whiteSpaceRegExp.test(chr) && !withInName && !withInValue)
					withInName = true;
	
				if (chr == '=' && withInName)
					withInName = false;
	
				if (withInName)
					attributeName += chr;
	
				if (withInValue)
					attributeValue += chr;
			}
	
			return attributes;
		}
	});

	// Register plugin
	tinymce.PluginManager.add('codeprotect', tinymce.plugins.codeprotectPlugin);
})();

