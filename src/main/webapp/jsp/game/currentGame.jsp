<%--
  Author: LupusInFabula Group
  Version: 1.0
  Since: 1.0
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Game</title>
    <c:import url="/jsp/include/head.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/game/gameActions.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/utils/playersStatus.css">
</head>

<body>
<jsp:include page="/jsp/include/navbar.jsp"/>

<div class="container">

    <c:choose>
        <c:when test="${isMaster}">
            <jsp:include page="gameActions.jsp"/>
        </c:when>
        <c:otherwise>
            <%-- <jsp:include page="game.....jsp"/> --%>
        </c:otherwise>
    </c:choose>

    <div class="row">
        <div id="playersStatus" class="column row col-md-6"></div>
        <div id="gameLog" class="column col-md-6"></div>
    </div>

</div>
<c:import url="/jsp/include/footer.jsp"/>
<c:import url="/jsp/include/foot.jsp"/>

<script src="${pageContext.request.contextPath}/js/game/gameActions.js"></script>
<script src="${pageContext.request.contextPath}/js/utils/playersStatus.js"></script>

</body>
</html>
