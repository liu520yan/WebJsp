<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello1</title>
</head>
<body>
<c:if test="${not empty accConfMsgs}">
    <c:forEach items="${accConfMsgs}" var="msg">
        ${msg.code}
        <%--<div class="alert positive">--%>
            <%--<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>--%>
        <%--</div>--%>
    </c:forEach>
</c:if>

<%-- Warning messages --%>
<c:if test="${not empty accInfoMsgs}">
    <c:forEach items="${accInfoMsgs}" var="msg">
        ${msg.code}
        <%--<div class="alert neutral">--%>
            <%--<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>--%>
        <%--</div>--%>
    </c:forEach>
</c:if>

<%-- Error messages (includes spring validation messages)--%>
<c:if test="${not empty accErrorMsgs}">
    <c:forEach items="${accErrorMsgs}" var="msg">
        ${msg.code}
        <%--<div class="alert negative">--%>
            <%--<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>--%>
        <%--</div>--%>
    </c:forEach>
</c:if>
</body>
</html>