jQuery.noConflict();

var cmnApi = {};

cmnApi.addPagingParam = function(oriUrl, currentPage) {
    if (currentPage == undefined) {
        currentPage = 0;
    }
    var newUrl = oriUrl.replace(/([\?\&]page=?)(\d+)/g, "$1" + currentPage);// change currentPage if
    // has exist
    newUrl = newUrl.replace(/([\?]{1})$/g, "?page=" + currentPage);// add currentPage if end with
    // '?'
    if (!newUrl.match(/[\?\&]/g)) {
        // no param yet
        newUrl = newUrl + "?page=" + currentPage;
    } else if (!newUrl.match(/[\?\&]{1}page=\d+/g)) {
        // has param but not contains 'page'
        newUrl = newUrl + "&page=" + currentPage;
    }
    // return newUrl;
    return newUrl.replace(/#/, "");
};
cmnApi.hiddenWhenLoading = function(objId, time) {
    jQuery('#' + objId).css("visibility", "hidden");
    var timer = window.setTimeout(function() {
        jQuery('#' + objId).css("visibility", "");
        clearTimeout(timer);
    }, time);
};
cmnApi.disableWhenSubmiting = function(obj) {
    obj.disabled = true;
    obj.value = "Waiting..";
};
cmnApi.delHtmlTag = function(str, containsEnter) {
    if (!containsEnter) {
        return str.replace(/<[^>]*>/g, "");
    }
    return str.replace(/<[^>]*>/g, "").replace(/\n/g, "");
};
cmnApi.scrollOffset = function() {
    var loc = window.location.href;
    var indexAnchorName = loc.indexOf("#");
    if (indexAnchorName == -1) {
        return;
    }
    var anchorName = loc.substring(indexAnchorName + 1);
    var anchor = jQuery("a[name='" + anchorName + "']");
    if (anchor.length == 0) {
        return;
    }
    $document.scrollTop(anchor.offset().top);
};

window.cmnApi = cmnApi;

var $document = jQuery(document);
var $window = jQuery(window);

$document.ready(function() {

    var $switcher = jQuery('#return-to-top');
    if (!$switcher) {
        return;
    }

    var slideSwitcher = function() {
        if ($document.scrollTop() == 0) {
            $switcher.slideUp('fast');
        } else {
            $switcher.slideDown('fast');
        }
    };

    var moveSwitcher = function() {
        var delay = 500;
        var executionTimer;
        return function() {
            if (!!executionTimer) {
                clearTimeout(executionTimer);
            }
            executionTimer = setTimeout(function() {
                $switcher.animate({
                    'top' : $window.height() + $document.scrollTop() - $switcher.height() - 25
                }, 'slow', slideSwitcher);
            }, delay);
        };
    }();

    $switcher.animate({
        'left' : $window.width() - 105
    }, function() {
        $window.bind('scroll', moveSwitcher);
    });

    $switcher.hide();

    $window.resize(function() {
        $switcher.animate({
            'left' : $window.width() - 105
        }, moveSwitcher);
    });
});

$document.ready(function() {

    jQuery('a.return-to-top2').click(function() {
        jQuery('html').animate({
            scrollTop : 0
        }, 'fast');
        jQuery('body').animate({
            scrollTop : 0
        }, 'fast');
        return false;
    });
});

$document.ready(function() {

    var $search = jQuery('#formSearch');
    if (!$search) {
        return;
    }

    $search.submit(function() {
        return false;
    });

    var jqSearchGoogle = function(searchText, site) {
        if (jQuery.trim(searchText) == "") {
            return;
        }
        var keystr = encodeURIComponent(searchText);
        url = "http://www.google.com/search?q=";
        url = url + keystr;
        url += "&ie=UTF-8&domains=" + site + "&sitesearch=" + site;
        window.location = url;
    };

    $search.find('#search-text').keydown(function(event) {
        if (event.keyCode == 13) {
            jqSearchGoogle(jQuery('#search-text').val(), 'atealxt.appspot.com');
        }
    });

    jQuery('#search-submit').click(function() {
        jqSearchGoogle(jQuery('#search-text').val(), 'atealxt.appspot.com');
    });

});

$document.ready(function() {

    var $search = jQuery('#formSearch');
    if (!$search) {
        return;
    }
    var $searchInput = $search.find('#search-text');
    var $searchLabel = $search.find('label');

    if ($searchInput.val()) {
        $searchLabel.hide();
    }

    $searchInput.focus(function() {
        $searchLabel.hide();
    }).blur(function() {
        if (this.value == '') {
            $searchLabel.show();
        }
    });

    $searchLabel.click(function() {
        $searchInput.trigger('focus');
    });
});