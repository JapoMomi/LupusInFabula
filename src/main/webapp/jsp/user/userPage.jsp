<%--
  Created by IntelliJ IDEA.
  User: Jacopo Momesso
  Date: 11/05/2024
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
    <c:import url="/jsp/include/head.jsp"/>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user/user.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user/updateCredentials.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user/deleteAccount.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/friend/friend.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/utils/searchPlayer.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>

<body>

<jsp:include page="/jsp/include/navbar.jsp"/>

<div class="container card">

    <div class="radio-inputs">
        <label class="radio">
            <input type="radio" name="radio" value="friends" checked="">
            <span class="name">Friends</span>
        </label>

        <label class="radio">
            <input type="radio" name="radio" value="changeCredentials">
            <span class="name">Change Credentials</span>
        </label>

        <label class="radio">
            <input type="radio" name="radio" value="deleteAccount">
            <span class="name">Delete Account</span>
        </label>
    </div>

    <div class="box" id="contentBox">
        <div id="friendsPage">
            <jsp:include page="../friend/friend.jsp"/>
        </div>
        <div class="row" id="changeCredentialsPage" style="display: none;">
            <jsp:include page="updateCredential.jsp"/>
        </div>
        <div class="row" id="deleteAccountPage" style="display: none;">
            <jsp:include page="deleteAccount.jsp"/>
        </div>
    </div>

</div>

<script src="${pageContext.request.contextPath}/js/user/userPage.js"></script>
<script src="${pageContext.request.contextPath}/js/user/updateCredentials.js"></script>
<script src="${pageContext.request.contextPath}/js/user/deleteAccount.js"></script>
<script src="${pageContext.request.contextPath}/js/friend/friendManagement.js"></script>
<script src="${pageContext.request.contextPath}/js/utils/searchPlayer.js"></script>

</body>
</html>
