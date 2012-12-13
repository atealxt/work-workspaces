// script for topic.csdn.net/u  
// For example http://topic.csdn.net/u/20070501/18/b91b54b7-5654-4cb0-a9ee-033fb118f754.html
// 2007-09-16 create

function HongjunGuo_attachEvent(en, func, cancelBubble)
{
    if(window.attachEvent)
    {
        window.attachEvent(en,func);
        return true;
    }
    else if(!window.attachEvent && window.addEventListener)
    {
        var cb = cancelBubble ? true : false;
        window.addEventListener(en.toLowerCase().substr(2), func, cb);
        return true;
    }
    else
    {
        return false;  
    }
}
function HongjunGuo_detachEvent(en, func, cancelBubble)
{
    if(window.attachEvent)
    {
        window.removeEvent(en,func);
        return true;
    }
    else if(!window.attachEvent && window.addEventListener)
    {
        var cb = cancelBubble ? true : false;
        window.removeEventListener(en.toLowerCase().substr(2), func, cb);
        return true;
    }
    else
    {
        return false;
    }    
}