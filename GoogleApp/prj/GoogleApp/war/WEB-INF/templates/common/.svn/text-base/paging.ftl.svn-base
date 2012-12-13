<#--gt greater-->
<#--lt less than-->
<#macro page currentP=0
             maxP=0>

<#if maxP?number gt 0>

    <#if currentP?number != 0>
      <span id="urlFirst"><img src="/images/paging/page-first.gif" title="First Page" class="paging"></img></a></span>
    <#else>
      <img src="/images/paging/page-first-disabled.gif" title="First Page" class="paging-no"></img>
    </#if>

    <#if currentP?number gt 0>
      <span id="urlPrevious"><img src="/images/paging/page-prev.gif" title="Previous Page" class="paging"></img></a></span>
    <#else>
      <img src="/images/paging/page-prev-disabled.gif" title="Previous Page" class="paging-no"></img>
    </#if>

    <#if currentP?number lt maxP?number>
      <span id="urlNext"><img src="/images/paging/page-next.gif" title="Next Page" class="paging"></img></a></span>
    <#else>
      <img src="/images/paging/page-next-disabled.gif" title="Next Page" class="paging-no"></img>
    </#if>

    <#if currentP?number lt maxP?number>
      <span id="urlLast"><img src="/images/paging/page-last.gif" title="Last Page" class="paging"></img></a></span>
    <#else>
      <img src="/images/paging/page-last-disabled.gif" title="Last Page" class="paging-no"></img>
    </#if>

<script type="text/javascript">

    var url = window.location.href,urlFirst,urlLast,urlPrevious,urlNext;
    <#if pagingUrl?exists>url = "${pagingUrl}";</#if><#--for ajax paging(A05BlogController.content())-->
    urlFirst = cmnApi.addPagingParam(url,0);
    <#if currentP?number == 0>
    urlPrevious = cmnApi.addPagingParam(url,0);
    <#elseif currentP?number gt 0>
    urlPrevious = cmnApi.addPagingParam(url,${currentP?number-1});
    </#if>
    <#if currentP?number lt maxP?number>
    urlNext = cmnApi.addPagingParam(url,${currentP?number+1});
    urlLast = cmnApi.addPagingParam(url,${maxP?number});
    </#if>

    <#if pagingUrl?exists>
    urlFirst = "<a href='#' onclick=\"changeContent('" + urlFirst + "')\">";
    urlLast = "<a href='#' onclick=\"changeContent('" + urlLast + "')\">";
    urlPrevious = "<a href='#' onclick=\"changeContent('" + urlPrevious + "')\">";
    urlNext = "<a href='#' onclick=\"changeContent('" + urlNext + "')\">";
    <#else>
    urlFirst = "<a href=\"" + urlFirst + "\">";
    urlLast = "<a href=\"" + urlLast + "\">";
    urlPrevious = "<a href=\"" + urlPrevious + "\">";
    urlNext = "<a href=\"" + urlNext + "\">";
    </#if>

    if(jQuery('#urlFirst').length > 0){
        jQuery('#urlFirst').html(urlFirst + jQuery('#urlFirst').html());
    }
    if(jQuery('#urlLast').length > 0){
        jQuery('#urlLast').html(urlLast + jQuery('#urlLast').html());
    }
    if(jQuery('#urlPrevious').length > 0){
        jQuery('#urlPrevious').html(urlPrevious + jQuery('#urlPrevious').html());
    }
    if(jQuery('#urlNext').length > 0){
        jQuery('#urlNext').html(urlNext + jQuery('#urlNext').html());
    }

</script>

</#if>

</#macro>
