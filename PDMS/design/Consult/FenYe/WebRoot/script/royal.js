
/**查看购物车*/
function enterCart() {
	var url = "/cart/ShoppingCart.jhtml";
	window.location.href = "/cart/ShoppingCart.jhtml";
}
/**加入商品并打开购物车*/
function addToCart(id) {
	var url = "/cart/ShoppingCart.jhtml?method=add&proID=" + id;
	window.location.href = url;
}
/**继续购物*/
function goOnShopping() {
	var url = "/shopping/ShowProduct.jhtml";
	window.location.href = url;
}

/**进入地址薄*/
function enterAddress()
{
	var url = "/order/CustomerAddress.jhtml";
	window.location.href = url;
}
/**我的订单*/
function myOrderList()
{
	var url = "/order/WebOrder.jhtml";
	window.location.href = url;
}
//
//var param = "toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes,status=yes'";
///**查看购物车*/
//function enterCart() {
//	var url = "/cart/ShoppingCart.jhtml";
//	if(window.cartWindow)
//	alert();
//	var cartWindow = window.open(url, "cartWindow");
//	cartWindow.focus();
//	cartWindow.location.href = url;
//	cartWindow.document.location.reload();
//}
///**加入商品并打开购物车*/
//function addToCart(id) {
//	var url = "/cart/ShoppingCart.jhtml?method=add&proID=" + id;
//	var cartWindow = window.open(url, "cartWindow");
//	cartWindow.focus();
//	cartWindow.location.href = "/cart/ShoppingCart.jhtml";
//	cartWindow.document.location.reload();
//}


