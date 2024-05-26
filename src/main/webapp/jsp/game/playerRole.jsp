<%--
  Created by IntelliJ IDEA.
  User: Michele
  Date: 15/05/2024
  Time: 19:01
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row justify-content-center align-items-center mb-3">
    <div class="col-sm-12 col-md-10 col-lg-8 p-2 mx-auto text-center" style="margin: auto">
        <div id="playerRole" class="justify-content-center align-items-center w-100 mb-2"></div>
        <!--<img id="playerImage" class="img-fluid d-block mx-auto">  Don't set "alt". It'll be set in a js function -->
        <div id="cardContainer" class="card-container">
            <div id="card" class="card mx-auto">
                <div class="card-face card-front">
                    <!-- Front of card -->
                </div>
                <div class="card-face card-back">
                    <!-- Back of card -->
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="/jsp/utils/errorMessage.jsp"/>