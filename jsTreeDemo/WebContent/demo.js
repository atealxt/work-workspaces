$(document).ready(function() {

    var tree = $("#mytree").jstree({
        "json_data" : {
            "data" : [ {
                "attr" : {
                    "id" : 1
                },
                "data" : {
                    "title" : "AAA",
                    "attr" : {
                        "href" : "###aaa"// 可添加自定义属性
                    }
                }
            }, {
                "attr" : {
                    "id" : 2
                },
                "data" : {
                    "title" : "BBB"
                },
                "children" : [ {
                    "attr" : {
                        "id" : 3
                    },
                    "data" : {
                        "title" : "BBB child 1"
                    }
                }, {
                    "attr" : {
                        "id" : 4
                    },
                    "data" : {
                        "title" : "BBB child 2"
                    }
                } ]
            }, {
                "attr" : {
                    "id" : 5
                },
                "data" : {
                    "title" : "Root"
                },
                "state" : "closed"
            } ],
            "progressive_render" : true,
            "ajax" : {
                "url" : "MyServlet",
                "data" : function(n) {
                    return {
                        "id" : n.attr ? n.attr("id").replace("node_", "") : 1
                    };
                }
            }
        },
        "plugins" : [ "themes", "json_data", "checkbox", "ui" ]
    });

    $("#mytree a").live("click", function(e) {
        alert($(e.target).parents().attr('id'));
    });

    $('#button1').click(function() {
        var str = "";
        jQuery('#mytree .jstree-checked').each(function() {
            var node = jQuery(this);
            var id = node.attr('id');
            str = str + " id: " + id + "\n";
        });
        alert(str);
    });

});
