<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"    %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"       prefix = "fn"   %>
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

    <style>


      /* Configuration of jQuery Widget(s) */

      .ui-dialog .ui-dialog-titlebar {
        background-color: orange;
        color: white
      }


    </style>


  </head>


  <body>


    <div id = "root">


      <div id = "page-container">


        <div id = "content-wrap">


          <jsp:include page = "header.jsp" />


          <main id = "main">


            <div id = "breadcrumb">


              <a href = "/home">Home</a> > <a href = "/meal-delivery">Meal Delivery</a> > <span>Available Pickups (Assignment)</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Available Pickups (Assignment) </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/meal-delivery_assignment" modelAttribute = "selectWrapper">


                <table>


                  <tr style = "background-color: lightgray">


                    <th style = "border: 1px solid black"> <span> ID                    </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Food Service Provider </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Time Available        </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Address               </span> <br /> </th>


                    <th style = "border: 1px solid black">


                      <div style = "text-align: left">
                  
                        <label>
                          <input type = "checkbox" id = "checkbox" name = "checkbox" value = "Select All" />
                          <span> Select All </span>
                        </label>

                      </div>


                    </th>


                  </tr>


                  <c:if test = "${empty meals}">
                  <tr>


                    <td style = "width:  5ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 44ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 42ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 220px; border: 1px solid black;"> <p> </p> </td>


                  </tr>
                  </c:if>


                  <c:forEach var = "meal" items = "${meals}">
                  <tr>


                    <td style = "width:  5ch; border: 1px solid black;"> <span> <c:out value = "${meal.id}" />                   </span> <br /> </td>


                    <td style = "width: 44ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.provider}">
                      <c:out value = "${meal.provider.names}" /> <c:out value = "${meal.provider.surname}" />
                      </c:if>

                                                                                                                                 </span> <br /> </td>


                    <td style = "width: 22ch; border: 1px solid black;"> <span>
                      <fmt:parseDate  value = "${meal.time_end}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeEnd" type = "both" />
                      <fmt:formatDate value = "${parseTimeEnd}"  pattern = "yyyy-MM-dd HH:mm" />                                 </span> <br /> </td>


                    <td style = "width: 42ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.member}">
                      <c:out value = "${meal.member.address}" />
                      </c:if>

                                                                                                                                 </span> <br /> </td>


                    <td style = "width: 220px; border: 1px solid black;">


                      <div style = "text-align: left">

                        <label>
                          <input type = "checkbox" name = "ids[]" value = "${meal.id}" />
                          <span> Select </span> <br />
                        </label>

                      </div>


                    </td>


                  </tr>
                  </c:forEach>


                  <tr id = "page-navigation">


                    <td colspan = "14" style = "text-align: right">

                      <c:if test = "${not empty meals}">

                        <c:if test = "${current > 0}">
                        <a href="/meal-delivery_assignment?page=${current-1}&size=${size}">Prev</a>
                        </c:if>

                        <c:forEach begin = "0" end = "${pages - 1}" var = "i">

                        <c:if test = "${i != current}">
                        <a href="/meal-delivery_assignment?page=${i}&size=${size}"><c:out value = "${i + 1}" /></a>
                        </c:if>

                        <c:if test = "${i == current}">
                        <a href="javascript:void(0)" style = "color: gray; text-decoration: none;"><c:out value = "${i + 1}" /></a>
                        </c:if>

                        </c:forEach>
        
                        <c:if test = "${current < pages - 1}">
                        <a href="/meal-delivery_assignment?page=${current+1}&size=${size}">Next</a>
                        </c:if>

                      </c:if>

                    </td>


                  </tr>


                  <tr>


                    <td> <span> Total: </span> <br /> </td>


                    <td> <span> <!-- ${fn:length(meals)} --> <c:out value = "${elements}" /> <br /> </td>


                    <td>


                      <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />

                      <span> Rider: </span> <br />


                    </td>


                    <td>


                      <div style = "text-align: right" >

                        <select id = "rider_id" name = "rider_id" required = "true" style = "width: 600px">

                              <option value = ""> </option>

                          <c:if test = "${not empty riders}">
                            <c:forEach var = "rider" items = "${riders}">

                              <option value = "${rider.id}">
                                <c:out value = "${rider.nric_uen}" /> <c:out value = "${rider.names}" /> <c:out value = "${rider.surname}" /> <c:out value = "${rider.status}" /> </option> 

                            </c:forEach>
                          </c:if>

                        </select> <br />

                      </div>


                    </td>


                    <td style = "width: 220px">


                      <div style = "text-align: left">

                        <form:button style = "width: 100px"> Assign </form:button>

                      </div>


                    </td>


                  </tr>


                </table>


              </form:form>


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

      <script nonce = "${random-value}">


        /* Migrated event handlers to enable Content Security Policy (CSP) */

        document.getElementById("checkbox").addEventListener("click", (listener) => selectAll('checkbox','ids[]'));


      </script>


    </div>


  </body>


</html>
