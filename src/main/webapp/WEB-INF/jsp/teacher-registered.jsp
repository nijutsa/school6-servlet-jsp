
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Επιτυχής Εισαγωγή</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/success.css">
</head>
<body>

    <div class="success m-bottom">
        <h1>Επιτυχής Εγγραφή</h1>
        <p>Όνομα: ${requestScope.teacherReadOnlyDTO.firstname}</p>
        <p>Επώνυμο: ${requestScope.teacherReadOnlyDTO.lastname}</p>
    </div>

    <div>
        <a href="${pageContext.request.contextPath}/teachers">Επιστροφή</a>
    </div>



</body>
</html>
