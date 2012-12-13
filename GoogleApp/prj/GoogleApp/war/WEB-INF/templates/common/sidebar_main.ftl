<#macro page>
<#escape x as x?html>
    <div id="sidebar">
        <ul>
            <li>
                <h2>What's New</h2>
                <ul>
                    <li>2011/06/03 1.06 Released</li>
                    <li style="text-align:right;" class="href-no-style"><a href="/releasenote" target="_blank">View release Notes</a></li>
                </ul>
            </li>
            <li>
                <h2>Who I Am</h2>
                Luis Ashurei, a programmer, now living in Beijing.<br>
                Interest in Object-oriented, Open Source, Framework, Agile, Cloud Computing and Programming ideas.<br>
                Contact me if you have any question or interest.<br>
                <img border="0" align="absbottom" src="/images/logo/email_atea.png"/>
            </li>
            <li>
                <h2>Links</h2>
                <ul>
                    <#list links as x>
                    <li><a href="${x.link}" title="${x.title}" target="_blank">${x.msgShow}</a></li>
                    </#list>
                </ul>
            </li>
        </ul>
    </div>
</#escape>
</#macro>
