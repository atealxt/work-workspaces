<%@tag description="test_tag_testarea" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="name" required="true" rtexprvalue="true" %>
<%@attribute name="id" required="true" rtexprvalue="true" %>

<textarea name="${name}" id="${id}">
        <c:if test="${pageContext.request.method == 'POST' && !empty param.t1}">
            <c:out value="${param.t1}"/>
        </c:if> 
</textarea>