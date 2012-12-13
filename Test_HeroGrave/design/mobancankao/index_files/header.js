var headerDataString = new Array();
var allHeaderString = "";

function getHeaderData()
{
    var html = '';
    html += '<div class="middiv">';
    html += '<u class="u9"></u><u class="u10"></u><u class="u11"></u><u class="u12"></u>';
    html += '<div id="ucontent6acdf7">';
    html += '<h3>';
    html += '推<br />荐<br />商<br />家<br />精<br />选';
    html += '</h3>';
    html += '<div class="ucontentstyle0">';
    html += '<span class="a1" id="spanHeaderAllData"><a href="#" onfocus="this.blur()" onmouseover="showAllHeaderData();" onclick="return false;">全国市场</a></span>';
    for (var i = 0; i < areaList.length; i++)
    {
        html += '<span class="a2" id="spanHeaderData' + i + '"><a href="#" onfocus="this.blur()" onclick="return false;" onmouseover="showHeaderData(' + i + ');">' + areaList[i].name + '</a></span>';
        headerDataString[i] = '';
    }
    if (allAdList.length > 0)
    {
        allHeaderString = '<ul>';
        for (var j = 0; j< allAdList.length; j++)
        {
            var item = allAdList[j];
            allHeaderString += '<li><a href="' + item.url + '"  target="_blank"><span>' + item.city + '</span>' + item.name + '</a></li>';
        }
        allHeaderString += '</ul>';
    }
    html += '</div>';
    html += '<h4>';
    html += '<a href="http://www.levelup.cn/company/cp.shtml" target="_blank">联系推广</a>';
    html += '</h4>';
    html += '<div class="ucontentstyle1">';
    html += '<div class="ucontentstyle2"></div>';
    html += '<div class="ucontentstyle3" id="divHeaderDataContainer">';
    html += allHeaderString;
    html += '</div>';
    html += '</div>';
    html += '</div>';
    html += '<u class="u12"></u><u class="u11"></u><u class="u10"></u><u class="u9"></u>';
    html += '</div>';

    return html;
    
}

function showAllHeaderData()
{
    document.getElementById('divHeaderDataContainer').innerHTML = allHeaderString;

    document.getElementById('spanHeaderAllData').className = 'a1';
    
    for (var i = 0; i < areaList.length; i++)
    {
        
       document.getElementById('spanHeaderData' + i).className = 'a2';
        
        
    }
}

function showHeaderData(id)
{
    if (headerDataString[id] == '' && adList[id].length > 0)
    {
        var str = '<ul>';
        for (var j = 0; j< adList[id].length; j++)
        {
            var item = adList[id][j];
            str += '<li><a href="' + item.url + '" target="_blank"><span>' + item.city + '</span>' + item.name + '</a></li>';
        }
        str += '</url>';
        
        headerDataString[id] = str;
    }
    
    document.getElementById('divHeaderDataContainer').innerHTML = headerDataString[id];
    
    document.getElementById('spanHeaderAllData').className = 'a3';
    
    for (var i = 0; i < areaList.length; i++)
    {
        if (id != i)
        {
            document.getElementById('spanHeaderData' + i).className = 'a2';
        }
        else
        {
            document.getElementById('spanHeaderData' + i).className = 'a4';
        }
    }
    
}
/**






















<ul>

<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
<li><a href="#"><span>大连</span>商家商家商家商家</a></li>
</ul>

*/