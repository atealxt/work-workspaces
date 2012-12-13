<#macro page>

<style>
.modal {
	background-color:#fff;
	display:none;
	padding:15px;
	text-align:left;
	border:2px solid #333;
	width:560px;
	opacity:0.9;
	-moz-border-radius:6px;
	-webkit-border-radius:6px;
	-moz-box-shadow: 0 0 50px #ccc;
	-webkit-box-shadow: 0 0 50px #ccc;
}
</style>

<#if openId?exists>
<div class="modal" id="openid">
<p>
<center>
  <#assign keys = openId?keys>
  <#list keys as key>
  <a href="${openId[key]}" style="padding:5px 5px 0px 5px;">
  <img src="${key}" alt="${openId[key]}" style="margin-bottom:10px;"/>
  </a>
  </#list>
</center>
</p>
</div>
</#if>

<script src="http://cdn.jquerytools.org/1.2.5/jquery.tools.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
var triggers = $(".modalInput").overlay({
	// some mask tweaks suitable for modal dialogs
	mask: {
		color: '#ebecff',
		loadSpeed: 200,
		opacity: 0.9
	},
	top: '30%'
});
});
</script>

</#macro>
