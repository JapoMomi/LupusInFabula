<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <h1>Lupus in Fabula</h1>
</header>
<div class="container">
    <nav class="navbar navbar-expand-lg bg-light" role="navigation">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <a class="navbar-brand" href="<c:url value="/jsp/home.jsp"/>">
                <img src="${pageContext.request.contextPath}/media/favicon.ico" alt="logo" style="width:40px;">
            </a>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="margin: auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/jsp/home.jsp"/>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/"/>">Personal Area</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        Game
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="<c:url value="/jsp/game/createNewGame.jsp"/>">Create New
                            Game</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/"/>">Join Game</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</div>
