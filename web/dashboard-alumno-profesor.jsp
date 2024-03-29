<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel='shortcut icon' type='image/x-icon' href="images/favicon.ico" >
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/dashboard-alumno-profesor.css">
</head>
<body>
    <div class="header">
        <jsp:include page="/cabecera" />
    </div>

    <div class="contenido">
        <c:choose>
            <c:when test="${usertype==0 && sessionScope.misclases!=null && not empty sessionScope.misclases}">
            <br><h4 style="color:#757575;">Estas son tus clases...</h4>
            <table class="tabla-clases">
                <div class = tabla-clases-header>
                    <tr>
                        <th class="hcolumna">Profesor</th>
                        <th class="hcolumna">Asignatura</th>
                        <th class="hcolumna">Curso</th>
                        <th class="hcolumna">Etapa</th>
                        <th class="hcolumna">Descripción</th>
                    </tr>
                </div>
                <div class = "tabla-clases-body">
                    <c:forEach var = "clase" items = "${sessionScope.misclases}">
                        <tr>
                            <td class="columna-left"><c:if test="${clase.profesor==null}">Sin asignar</c:if>
                                    ${clase.profesor.nombre}&nbsp;${clase.profesor.apellidos}</td>
                            <td class="columna-left">${clase.asignatura}</td>
                            <td class="columna-center">${clase.curso}º</td>
                            <td class="columna-left">${clase.etapa}</td>
                            <td class="columna-center">${clase.descripcion}</td>
                        </tr>
                    </c:forEach>
                </div>
            </c:when>
            <c:when test="${usertype==1 && sessionScope.misclases!=null && not empty sessionScope.misclases}">
                <br><h4 style="color:#757575;">Estas son tus clases...</h4>
                <table class="tabla-clases">
                    <div class = tabla-clases-header>
                        <tr>
                            <th class="hcolumna">Alumno</th>
                            <th class="hcolumna">Asignatura</th>
                            <th class="hcolumna">Curso</th>
                            <th class="hcolumna">Etapa</th>
                            <th class="hcolumna">Descripción</th>
                        </tr>
                    </div>
                    <div class = "tabla-clases-body">
                        <c:forEach var = "clase" items = "${sessionScope.misclases}">
                            <tr>
                                <td class="columna-left">${clase.alumno.nombre}&nbsp;${clase.alumno.apellidos}</td>
                                <td class="columna-left">${clase.asignatura}</td>
                                <td class="columna-center">${clase.curso}º</td>
                                <td class="columna-left">${clase.etapa}</td>
                                <td class="columna-center">${clase.descripcion}</td>
                            </tr>
                        </c:forEach>
                    </div>
            </c:when>
            <c:otherwise>
                    <div class="imagen">
                        <br><h4 style="color:#757575;">No hay ninguna clase...</h4>
                        <img src="images/vacio.svg">
                    </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="footer">
        <jsp:include page="/footer" />
    </div>
</body>
</html>
