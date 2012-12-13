jQuery.noConflict();
var $document = jQuery(document);

$document.ready(function() {

    var $slidetabs = jQuery('.slidetabs');
    $slidetabs.tabs('.images > div', {
      effect: 'fade',
      fadeOutSpeed: "slow",
      rotate: true,
      onClick: function(event, tabIndex) {}
    }).slideshow({
      clickable: false,
      interval: 5000
    });

    var $imgs = jQuery('.images > div > img');
    var loaded = false;
    var checkAvailable = setInterval(function() {
      if (loaded) {
        clearInterval(checkAvailable);
        jQuery('.slidetabs').data('slideshow').play();
        return;
      }
      $imgs.each(function() {
        if (!jQuery(this).attr("complete")) {
          return;
        }
      });
      loaded = true;
    }, 3000);
  });