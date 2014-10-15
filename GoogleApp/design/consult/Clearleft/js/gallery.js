$(document).ready(function(){
    function resizeGallery() {
        if ($('.gallery').parent().width() < 880) {
              $('.gallery').addClass('narrow');
        }
        else {
            $('.gallery').removeClass('narrow');
        }

    }
    resizeGallery();
    $(window).resize(function(){
         resizeGallery();
    });
	
	var polaroid = new function() {
		
		this.current_item = readCookie('polaroid') ? parseInt(readCookie('polaroid')) : 1;

		this.init = function() {
	 		this.itemwidth = $('#polaroid').width();
			$('#polaroid ul').css('left',0-((this.current_item-1) * this.itemwidth));
			this.checkArrowVisibility();
		};
		
		this.total_items = $('#polaroid ul').children().length;
		$('#polaroid').append('<a href="#" class="cycler next" title="Next case">Next</a><a href="#" class="cycler prev" title="Previous case">Previous</a>');
	
		this.cycle = function(amount) {
			if (!$(':animated').length) {
				var xpos = parseInt($('#polaroid ul').css('left'),10);
				xpos = xpos-(amount*this.itemwidth);
				$('#polaroid ul').animate( {
					left : xpos
				}, 'fast');
				this.current_item += amount;
				document.cookie = 'polaroid='+this.current_item+'; path=/';
				this.checkArrowVisibility();
			}
		};
		
		this.checkArrowVisibility = function() {
			if (this.current_item == 1) {
				$('#polaroid .prev').css('visibility','hidden');
			} else {
				$('#polaroid .prev').css('visibility','visible');
			}
			if (this.current_item  == this.total_items) {
				$('#polaroid .next').css('visibility','hidden');
			} else {
				$('#polaroid .next').css('visibility','visible');
			}
		};

		this.init();

	};

	$(window).resize(function(){
       	polaroid.init();
   	});
	
	$('#polaroid .next').click(function() {
		polaroid.cycle(1);
		return false;
	});
	
	$('#polaroid .prev').click(function() {
		polaroid.cycle(-1);
		return false;
	});

});
