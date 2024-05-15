<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row">
    <div class="col-sm-12 col-md-10 col-lg-8 p-2" style="margin: auto">
        <div id="gameActions"></div>
    </div>
</div>

<c:import url="/jsp/utils/errorMessage.jsp"/>
<div class="button-container">
    <button id="sendActions">
        <span class="text" id="textActionsBt"></span>
    </button>
</div>