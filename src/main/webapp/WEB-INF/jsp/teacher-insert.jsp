<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<html>
<head>
    <title>Εισαγωγή Καθηγητή</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/teacher-insert.css">
</head>
<body>
    <%@ include file="header.jsp"%>



    <div class="main-content">

        <div class="form m-button">
            <form method="POST" action="">

                <div class="row m-bottom">
                    <input class="m-bottom" type="text" name="firstname" value="${requestScope.teacherInsertDTO.firstname}" required placeholder="Όνομα">
                    <p class="validation-error">${requestScope.firstnameMessage}</p>
                </div>
                <div class="row m-bottom">
                    <input class="m-bottom" type="text" name="lastname" value="${requestScope.teacherInsertDTO.lastname}" required placeholder="Επώνυμο">
                    <p class="validation-error">${requestScope.lastnameMessage}</p>
                </div>
                <div class="row">
                    <button type="submit">Εγγραφή Καθηγητή</button>
                </div>

            </form>
        </div>

        <div class="m-bottom">
            <a href="${pageContext.request.contextPath}/teachers">Επιστροφή</a>
        </div>




    </div>






    <%@ include file="footer.jsp"%>
</body>
</html>
