/*
 * Ext JS Library 2.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

// Add the additional 'advanced' VTypes
Ext.apply(Ext.form.VTypes, {
  
  password: function(val, field) {
  	if (field.initialPassField) {
      var pwd = Ext.getCmp(field.initialPassField);
      return (val == pwd.getValue());
    }
    return true;
  },
  
  passwordText: 'Passwords do not match'
},{invalidText:"ddddddddddddddddd"});


Ext.onReady(function(){

    Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    Ext.form.Field.prototype.msgTarget = 'side';

    var bd = Ext.getBody();

	
    var pwd = new Ext.FormPanel({
      labelWidth: 125,
      frame: true,
      title: 'Password Verification',
      bodyStyle:'padding:5px 5px 0',
      width: 350,
      defaults: {
        width: 175,
        inputType: 'password'
      },
      defaultType: 'textfield',
      items: [{
        fieldLabel: 'Password',
        name: 'pass',
        id: 'pass'
      },{
        fieldLabel: 'Confirm Password',
        name: 'pass-cfrm',
        vtype: 'password',
        initialPassField: 'pass' // id of the initial password field
      }]
    });

    pwd.render(bd);
});