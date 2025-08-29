<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"    %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"             prefix = "fmt"  %>

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


              <a href = "/home">Home</a> > <span>Service Feedback</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Service Feedback </u> </h1>


              <br />


              <table>


                <tr style = "background-color: lightgray">


                  <th style = "border: 1px solid black"> <span> ID                </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Delivery Time End </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Menu Name         </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Diet              </span> <br/> </th>


                  <th style = "border: 1px solid black"> <span> Frozen            </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Status            </span> <br /> </th>


                  <th style = "border: 1px solid black">


                    <div style = "display: flex; gap: 60px;">
                  
                      <span> Action(s) </span>

                      <sec:authorize access = "hasAnyRole('MEMBER')">
                      <form name = "create" method = "get" action = "/service-feedback_submission">
                        <input type = "submit" name = "submit"    value = "Create" style = "width: 100px" /> <br />
                      </form>
                      </sec:authorize>

                      <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'PARTNER - FSP', 'VOLUNTEER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                      <form name = "create" method = "get" action = "/service-feedback_submission">
                        <input type = "submit" name = "submit"    value = "Create" style = "width: 100px" disabled = "true" /> <br />
                      </form>
                      </sec:authorize>

                    </div>


                  </th>


                </tr>


                <c:if test = "${empty submissions}">
                <tr>


                  <td style = "width:  5ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width:  8ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 220px; border: 1px solid black;"> <p> </p> </td>


                </tr>
                </c:if>


                <c:forEach var = "submission" items = "${submissions}">
                <tr>


                  <td style = "width:  5ch; border: 1px solid black;"> <span> <c:out value = "${submission.id}" />    </span> <br /> </td>


                  <td style = "width: 22ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty submission.pickup}">
                    <fmt:parseDate  value = "${submission.pickup.time_end}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parsePickupTimeEnd" type = "both" />
                    <fmt:formatDate value = "${parsePickupTimeEnd}"         pattern = "yyyy-MM-dd HH:mm" />
                    </c:if>

                                                                                                                      </span> <br /> </td>


                  <td style = "width: 22ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty submission.pickup && not empty submission.pickup.meal && not empty submission.pickup.meal.menu}">
                    <c:out value = "${submission.pickup.meal.menu.name}" />
                    </c:if>

                                                                                                                       </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty submission.pickup && not empty submission.pickup.meal && not empty submission.pickup.meal.menu}">
                    <c:out value = "${submission.pickup.meal.menu.diet}" />
                    </c:if>

                                                                                                                       </span> <br /> </td>


                  <td style = "width: 8ch; border: 1px solid black;">  <span>

                    <c:if test = "${not empty submission.pickup && not empty submission.pickup.meal && not empty submission.pickup.meal.menu}">
                    <c:out value = "${submission.pickup.meal.menu.frozen}" />
                    </c:if>

                                                                                                                       </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;"> <span> <c:out value = "${submission.status}" /> </span> <br /> </td>


                  <td style = "width: 220px; border: 1px solid black;">


                    <div style = "display: flex; gap: 20px;">

                      <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">

                      <form name = "update" method = "get" action = "/service-feedback_submission">
                        <input type = "hidden" name = "id"        value = "${submission.id}" />
                        <input type = "submit" name = "submit"    value = "Update" style = "width: 100px" />
                      </form>

                      <form name = "delete" method = "post" action = "/service-feedback_deletion">
                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
                        <input type = "hidden" name = "id"        value = "${submission.id}" />
                        <input type = "submit" name = "submit"    value = "Delete" style = "width: 100px" /> <br />
                      </form>

                      </sec:authorize>

                      <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FDR')">

                      <form name = "update" method = "get" action = "/service-feedback_submission">
                        <input type = "hidden" name = "id"        value = "${submission.id}" />
                        <input type = "submit" name = "submit"    value = "View" style = "width: 100px" />
                      </form>

                      <form name = "delete" method = "post" action = "/service-feedback_deletion">
                        <input type = "submit" name = "submit"    value = "Delete" style = "width: 100px" disabled = "true" /> <br />
                      </form>

                      </sec:authorize>

                    </div>


                  </td>


                </tr>
                </c:forEach>


                <tr id = "page-navigation">


                  <td colspan = "7" style = "text-align: right">

                    <c:if test = "${not empty submissions}">

                      <c:if test = "${current > 0}">
                      <a href="/service-feedback?page=${current-1}&size=${size}">Prev</a>
                      </c:if>

                      <c:forEach begin = "0" end = "${pages - 1}" var = "i">

                      <c:if test = "${i != current}">
                      <a href="/service-feedback?page=${i}&size=${size}"><c:out value = "${i + 1}" /></a>
                      </c:if>

                      <c:if test = "${i == current}">
                      <a href="javascript:void(0)" style = "color: gray; text-decoration: none;"><c:out value = "${i + 1}" /></a>
                      </c:if>

                      </c:forEach>
        
                      <c:if test = "${current < pages - 1}">
                      <a href="/service-feedback?page=${current+1}&size=${size}">Next</a>
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
