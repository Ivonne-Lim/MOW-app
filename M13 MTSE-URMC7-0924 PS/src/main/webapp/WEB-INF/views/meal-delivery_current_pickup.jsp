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


              <a href = "/home">Home</a> > <a href = "/meal-delivery">Meal Delivery</a> > <a href = "/meal-delivery_current">Current Pickups</a> > <span>Current Pickup</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Current Pickup </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/meal-delivery_current_pickup" modelAttribute = "pickup">


                <table style = "float:left">


                  <tr style = "background-color: lightgray">


                    <td style = "border: 1px solid black" colspan = "4">


                      <span> <b> Pickup Details </b> </span> <br />


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
                      
                      <span> <c:out value = "${pickup.id}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Food Service Provider: </span> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <c:if test = "${not empty pickup.meal && not empty pickup.meal.provider}"> 
                      <span> <c:out value = "${pickup.meal.provider.nric_uen}" /> <c:out value = "${pickup.meal.provider.names}" /> <c:out value = "${pickup.meal.provider.surname}" /> </span> <br /> 
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Diet: </span> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${not empty pickup.meal && not empty pickup.meal.menu}">
                      <span> <c:out value = "${pickup.meal.menu.diet}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Frozen: </span> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${not empty pickup.meal && not empty pickup.meal.menu}">
                      <span> <c:out value = "${pickup.meal.menu.frozen}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Allergies: </span> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <c:if test = "${not empty pickup.meal && not empty pickup.meal.member}">
                      <span> <c:out value = "${pickup.meal.member.allergies}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Time Available: </span> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${not empty pickup.meal}">
                      <span> <fmt:parseDate  value = "${pickup.meal.time_end}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseMealTimeEnd" type = "both" />
                             <fmt:formatDate value = "${parseMealTimeEnd}"     pattern = "yyyy-MM-dd HH:mm" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Address: <span> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${not empty pickup.meal && not empty pickup.meal.member}">
                      <span> <c:out value = "${pickup.meal.member.address}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span style = "color: blue"> Unit: <span> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${not empty pickup.meal && not empty pickup.meal.member}">
                      <span> <c:out value = "${pickup.meal.member.unit}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Postal: </span> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${not empty pickup.meal && not empty pickup.meal.member}">
                      <span> <c:out value = "${pickup.meal.member.postal}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "status"> Status: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:select path = "status">
                        <form:option label = "Accepted" value = "Accepted" />
                        <form:option label = "Started"  value = "Started"  />
                        <form:option label = "Ended"    value = "Ended"    />
                      </form:select> <br />


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "4">


                      <p> </p>


                   </td>


                  </tr>


                </table>


                <table style = "padding: 0px 20px">


                  <tr style = "background-color: lightgray">


                    <td style = "border: 1px solid black" colspan = "4">


                      <span> <b> Additional Details </b> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "time_accept"> Time Accepted: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "time_accept" />
                      
                      <span> <fmt:parseDate  value = "${pickup.time_accept}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeAccept" type = "both" />
                             <fmt:formatDate value = "${parseTimeAccept}"    pattern = "yyyy-MM-dd HH:mm" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "time_start"> <span style = "color: blue"> Time Started: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "time_start" />
                      
                      <span> <fmt:parseDate  value = "${pickup.time_start}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeStart" type = "both" />
                             <fmt:formatDate value = "${parseTimeStart}"    pattern = "yyyy-MM-dd HH:mm" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "time_end"> <span style = "color: blue"> Time Ended: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "time_end" />
                      
                      <span> <fmt:parseDate  value = "${pickup.time_end}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeEnd" type = "both" />
                             <fmt:formatDate value = "${parseTimeEnd}"    pattern = "yyyy-MM-dd HH:mm" /> </span> <br />


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                        <input type = "hidden" id = "meal_id" name = "meal_id" value = "${pickup.meal.id}" />


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                        <input type = "hidden" id = "rider_id" name = "rider_id" value = "${pickup.rider.id}" />


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "3">


                      <p> </p>


                    </td>


                    <td>


                      <div style = "text-align: right">

                        <form:button style = "width: 100px"> Save </form:button> <br />

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
