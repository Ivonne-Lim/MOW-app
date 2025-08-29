<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"    %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"             prefix = "fmt"  %>

<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec"  %>
<%@ taglib uri = "http://www.springframework.org/tags/form"     prefix = "form" %>


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


              <a href = "/home">Home</a> > <a href = "/service-feedback">Service Feedback</a> > <span>Feedback Submission</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Feedback Submission </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/service-feedback_submission" modelAttribute = "submission">


                <table>


                  <tr style = "background-color: lightgray">


                    <td style = "border: 1px solid black" colspan = "4">


                      <span> <b> Service Feedback Details </b> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "id"> ID: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "id" />
                      
                      <span> <c:out value = "${submission.id}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "pickup_id"> <span style = "color: blue"> Delivery End Time: </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                      <select id = "pickup_id" name = "pickup_id" style = "width: 300px">

                            <option value = "" <c:if test = "${empty submission.pickup}"> selected </c:if> > </option>

                        <c:if test = "${not empty deliveries}">
                          <c:forEach var = "delivery" items = "${deliveries}">

                            <option value = "${delivery.id}" <c:if test = "${not empty submission.pickup && submission.pickup.id == delivery.id}"> selected </c:if> >
                              <fmt:parseDate  value = "${delivery.time_end}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeEnd" type = "both" />
                              <fmt:formatDate value = "${parseTimeEnd}"      pattern = "yyyy-MM-dd HH:mm" /> </option>

                          </c:forEach>
                        </c:if>

                      </select> <br />
                      </sec:authorize>

                      <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                      <select id = "pickup_id" name = "pickup_id" style = "width: 300px" disabled = "true">

                            <option value = "" <c:if test = "${empty submission.pickup}"> selected </c:if> > </option>

                        <c:if test = "${not empty deliveries}">
                          <c:forEach var = "delivery" items = "${deliveries}">

                            <option value = "${delivery.id}" <c:if test = "${not empty submission.pickup && submission.pickup.id == delivery.id}"> selected </c:if> >
                              <fmt:parseDate  value = "${delivery.time_end}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeEnd" type = "both" />
                              <fmt:formatDate value = "${parseTimeEnd}"      pattern = "yyyy-MM-dd HH:mm" /> </option>

                          </c:forEach>
                        </c:if>

                      </select> <br />
                      </sec:authorize>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Menu Name: </span> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${empty submission.pickup}">
                      <span> - </span> <br />
                      </c:if>

                      <c:if test = "${not empty submission.pickup}">
                      <span> <c:out value = "${submission.pickup.meal.menu.name}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Diet: </span> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${empty submission.pickup}">
                      <span> - </span> <br />
                      </c:if>

                      <c:if test = "${not empty submission.pickup}">
                      <span> <c:out value = "${submission.pickup.meal.menu.diet}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Frozen: </span> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <c:if test = "${empty submission.pickup}">
                      <span> - </span> <br />
                      </c:if>

                      <c:if test = "${not empty submission.pickup}">
                      <span> <c:out value = "${submission.pickup.meal.menu.frozen}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "status"> Status: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                       <c:if test = "${empty submission.status}">
                       <form:hidden path = "status" value = "Active" />
                       <span> Active </span> <br />
                       </c:if>

                       <c:if test = "${not empty submission.status}">
                       <form:hidden path = "status" />
                       <span> <c:out value = "${submission.status}" /> </span> <br />
                       </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "rate_meal"> Rate Meal: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">

                      <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                      <form:select path = "rate_meal">
                        <form:option label = "Very Poor" value = "1" />
                        <form:option label = "Poor"      value = "2" />
                        <form:option label = "Average"   value = "3" />
                        <form:option label = "Good"      value = "4" />
                        <form:option label = "Very Good" value = "5" />
                      </form:select> <br />
                      </sec:authorize>

                      <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                      <form:select path = "rate_meal" disabled = "true">
                        <form:option label = "Very Poor" value = "1" />
                        <form:option label = "Poor"      value = "2" />
                        <form:option label = "Average"   value = "3" />
                        <form:option label = "Good"      value = "4" />
                        <form:option label = "Very Good" value = "5" />
                      </form:select> <br />
                      </sec:authorize>

                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "remarks_meal"> <span style = "color: blue"> Remarks Meal: <span> </form:label> <br />


                    </td>

 
                    <td style = "border: 1px solid black" colspan = "3">


                      <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                      <form:input type = "text" path = "remarks_meal" maxlength = "255" size = "80" /> <br />
                      </sec:authorize>

                      <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                      <span> <c:out value = "${submission.remarks_meal}" /> </span> <br />
                      </sec:authorize>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "rate_delivery"> Rate Delivery: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">

                      <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                      <form:select path = "rate_delivery">
                        <form:option label = "Very Poor" value = "1" />
                        <form:option label = "Poor"      value = "2" />
                        <form:option label = "Average"   value = "3" />
                        <form:option label = "Good"      value = "4" />
                        <form:option label = "Very Good" value = "5" />
                      </form:select> <br />
                      </sec:authorize>

                      <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                      <form:select path = "rate_delivery" disabled = "true">
                        <form:option label = "Very Poor" value = "1" />
                        <form:option label = "Poor"      value = "2" />
                        <form:option label = "Average"   value = "3" />
                        <form:option label = "Good"      value = "4" />
                        <form:option label = "Very Good" value = "5" />
                      </form:select> <br />
                      </sec:authorize>

                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "remarks_delivery"> <span style = "color: blue"> Remarks Delivery: <span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                      <form:input type = "text" path = "remarks_delivery" maxlength = "255" size = "80" /> <br />
                      </sec:authorize>

                      <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                      <span> <c:out value = "${submission.remarks_delivery}" /> </span> <br />
                      </sec:authorize>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <input type = "hidden" id = "member_id" name = "member_id" value = "${member.id}" />


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "3">


                      <p> </p>


                    </td>


                    <td>


                      <div style = "text-align: right">

                        <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                        <form:button style = "width: 100px"> Save </form:button> <br />
                        </sec:authorize>

                        <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                        <form:button style = "width: 100px" disabled = "true"> Save </form:button> <br />
                        </sec:authorize>

                      </div>


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                </table>


              </form:form>


              <!-- br / -->

 
              <p> <span style = "color: blue"> Blue </span> denotes optional if applicable. </p>


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
