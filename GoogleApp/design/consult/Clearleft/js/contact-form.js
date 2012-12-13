$(document).ready(function(){

	var Revealer = function(relativeParent,absoluteChild,speed) {
		var revealHeight = absoluteChild.height();
		relativeParent.height(revealHeight);
		relativeParent.addClass('hidden');
		this.toggleReveal = function() {
			if (!relativeParent.parent().find(':animated').length) {
				if (relativeParent.css('display')!='none' ) {
	        		relativeParent.animate({height:0}, speed, function() {
						relativeParent.hide();
					});
				} else {
	        		relativeParent.height(0).show().animate({height: revealHeight}, speed);
				}
			}
		};
	};
	
	$('#contact').after($('#contact-form'));
	
	var contactForm = new Revealer(
		$('#contact-form .flow'),				// the relatively positioned container
		$('#contact-form .flow .contents'),		// the absolutely positioned child
		300										// the speed of the animation
	);
	
	$('.contact-link').live('click',function(){
	    $('html, body').animate({scrollTop:0}, 'fast');
        contactForm.toggleReveal();
		return false;
	});
	$('#contact-form button[type="reset"]').live('click',function(){
        contactForm.toggleReveal();
		return false;
    });

	$('#contact-form form').live('submit',function(){
		var values = $(this).serialize();
		$.post('/includes/contact/index.php',values,function(markup) {
			$('#contact-form .contents').html(markup);
		},'html');
		return false;
	});
});