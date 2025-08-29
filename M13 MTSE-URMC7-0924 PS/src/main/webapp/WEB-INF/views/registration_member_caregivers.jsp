<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"    %>

<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec"  %>


<!DOCTYPE html>


<html lang = "en">


  <head>


    <meta charset = "UTF-8" />


    <meta name = "viewport"    content = "initial-scale = 1.0, width = device-width" />

    <meta name = "description" content = "MOW Application" />
    <meta name = "author"      content = "Ivonne Lim" />


    <title> Meals on Wheels (MOW) </title>


    <link href = "/images/logo-mow.png"  rel = "icon"       type = "image/x-icon" />


    <link href = "/css/jquery-ui.css"    rel = "stylesheet" type = "text/css" />

    <link href = "/css/styles.css"       rel = "stylesheet" type = "text/css" />


  </head>


  <body>


    <div id = "root">


      <div id = "page-container">


        <div id = "content-wrap">


          <jsp:include page = "header.jsp" />


          <main id = "main">


            <div id = "breadcrumb">


              <a href = "/home">Home</a> > <a href = "/registration">Registration</a> > <a href = "/registration_member?username=${member.email}">Member</a> > <span>Caregivers</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Registration (Caregivers) </u> </h1>


              <br />


              <table>


                <caption> <b>Member:</b> <c:out value = "${member.nric_uen}" /> <c:out value = "${member.names}" /> <c:out value = "${member.surname}" /> <br /> <br /> </caption>


                <tr style = "background-color: lightgray">


                  <th style = "border: 1px solid black"> <span> ID      </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> NRIC    </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Name(s) </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Surname </span> <br/> </th>


                  <th style = "border: 1px solid black"> <span> Phone   </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Status  </span> <br /> </th>


                  <th style = "border: 1px solid black">


                    <div style = "display: flex; gap: 60px;">
                  
                      <span> Action(s) </span>

                      <form name = "create" method = "get" action = "/registration_member_caregivers_caregiver">
                        <input type = "hidden" name = "member_id" value = "${member.id}" />
                        <input type = "submit" name = "submit"    value = "Create" style = "width: 100px" /> <br />
                      </form>

                    </div>


                  </th>


                </tr>


                <c:if test = "${empty caregivers}">
                <tr>


                  <td style = "width:  5ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 42ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 220px; border: 1px solid black;"> <p> </p> </td>


                </tr>
                </c:if>


                <c:forEach var = "caregiver" items = "${caregivers}">
                <tr>


                  <td style = "width:  5ch; border: 1px solid black;">  <span> <c:out value = "${caregiver.id}"       /> </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <span> <c:out value = "${caregiver.nric_uen}" /> </span> <br /> </td>


                  <td style = "width: 42ch; border: 1px solid black;">  <span> <c:out value = "${caregiver.names}"    /> </span> <br /> </td>


                  <td style = "width: 22ch; border: 1px solid black;">  <span> <c:out value = "${caregiver.surname}"  /> </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <span> <c:out value = "${caregiver.phone}"    /> </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <span> <c:out value = "${caregiver.status}"   /> </span> <br /> </td>


                  <td style = "width: 220px; border: 1px solid black;">


                    <div style = "display: flex; gap: 20px;">

                      <form name = "update" method = "get" action = "/registration_member_caregivers_caregiver">
                        <input type = "hidden" name = "id"        value = "${caregiver.id}" />
                        <input type = "hidden" name = "member_id" value = "${member.id}"   />
                        <input type = "submit" name = "submit"    value = "Update" style = "width: 100px" />
                      </form>
  
                      <form name = "delete" method = "post" action = "/registration_member_caregivers_deletion">
                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
                        <input type = "hidden" name = "id"        value = "${caregiver.id}" />
                        <input type = "hidden" name = "member_id" value = "${member.id}"   />
                        <input type = "submit" name = "submit"    value = "Delete" style = "width: 100px" /> <br />
                      </form>

                    </div>


                  </td>


                </tr>
                </c:forEach>


                <tr id = "page-navigation">


                  <td colspan = "7" style = "text-align: right">

                    <c:if test = "${not empty caregivers}">

                      <c:if test = "${current > 0}">
                      <a href="/registration_member_caregivers?member_id=${member.id}&page=${current-1}&size=${size}">Prev</a>
                      </c:if>

                      <c:forEach begin = "0" end = "${pages - 1}" var = "i">

                      <c:if test = "${i != current}">
                      <a href="/registration_member_caregivers?member_id=${member.id}&page=${i}&size=${size}"><c:out value = "${i + 1}" /></a>
                      </c:if>

                      <c:if test = "${i == current}">
                      <a href="javascript:void(0)" style = "color: gray; text-decoration: none;"><c:out value = "${i + 1}" /></a>
                      </c:if>

                      </c:forEach>
        
                      <c:if test = "${current < pages - 1}">
                      <a href="/registration_member_caregivers?member_id=${member.id}&page=${current+1}&size=${size}">Next</a>
                      </c:if>

                    </c:if>

                  </td>


                </tr>


              </table>


              <br />


            </div>


          </main>


        </div>


        <jsp:include page = "footer.jsp" />


      </div>


      <noscript> Error: JavaScript is blocked! </noscript>

      <script src = "/js/jquery.js"    type = "text/javascript"> </script>
      <script src = "/js/jquery-ui.js" type = "text/javascript"> </script>

      <script src = "/js/scripts.js"   type = "text/javascript"> </script>


    </div>


  </body>


</html>
