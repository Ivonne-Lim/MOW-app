<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"    %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"       prefix = "fn"   %>
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


              <a href = "/home">Home</a> > <a href = "/meal-delivery">Meal Delivery</a> > <span>Current Pickups</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Current Pickups </u> </h1>


              <br />


              <table>


                <tr style = "background-color: lightgray">


                  <th style = "border: 1px solid black"> <span> ID                    </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Food Service Provider </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Diet                  </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Frozen                </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Allergies             </span> <br/> </th>


                  <th style = "border: 1px solid black"> <span> Time Available        </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Address               </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Unit                  </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Postal                </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Status                </span> <br /> </th>


                  <th style = "border: 1px solid black">


                    <div style = "text-align: left">
                  
                      <span> Action(s) </span> <br />

                    </div>


                  </th>


                </tr>


                <c:if test = "${empty pickups}">
                <tr>


                  <td style = "width:  5ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 44ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width:  8ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 42ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 42ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width:  8ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 220px; border: 1px solid black;"> <p> </p> </td>


                </tr>
                </c:if>


                <c:forEach var = "pickup" items = "${pickups}">
                <tr>


                  <td style = "width:  5ch; border: 1px solid black;"> <span> ${pickup.id}                                                  </span> <br /> </td>


                  <td style = "width: 44ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty pickup.meal && not empty pickup.meal.provider}"> 
                    <c:out value = "${pickup.meal.provider.names}" /> <c:out value = "${pickup.meal.provider.surname}" />
                    </c:if>

                                                                                                                                            </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty pickup.meal && not empty pickup.meal.menu}">
                    <c:out value = "${pickup.meal.menu.diet}" />
                    </c:if>

                                                                                                                                            </span> <br /> </td>


                  <td style = "width:  8ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty pickup.meal && not empty pickup.meal.menu}">
                    <c:out value = "${pickup.meal.menu.frozen}" />
                    </c:if>

                                                                                                                                           </span> <br /> </td>


                  <td style = "width: 42ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty pickup.meal && not empty pickup.meal.member}">
                    <c:out value = "${pickup.meal.member.allergies}" />
                    </c:if>

                                                                                                                                          </span> <br /> </td>


                  <td style = "width: 22ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty pickup.meal}">
                    <fmt:parseDate  value = "${pickup.meal.time_end}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeEnd" type = "both" />
                    <fmt:formatDate value = "${parseTimeEnd}"         pattern = "yyyy-MM-dd HH:mm" />
                    </c:if>

                                                                                                                                         </span> <br /> </td>


                  <td style = "width: 42ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty pickup.meal && not empty pickup.meal.member}">
                    <c:out value = "${pickup.meal.member.address}" />
                    </c:if>

                                                                                                                                         </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty pickup.meal && not empty pickup.meal.member}">
                    <c:out value = "${pickup.meal.member.unit}" />
                    </c:if>

                                                                                                                                        </span> <br /> </td>


                  <td style = "width:  8ch; border: 1px solid black;"> <span>

                    <c:if test = "${not empty pickup.meal && not empty pickup.meal.member}">
                    <c:out value = "${pickup.meal.member.postal}" />
                    </c:if>

                                                                                                                                       </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;"> <span> ${pickup.status}                                         </span> <br /> </td>


                  <td style = "width: 220px; border: 1px solid black;">


                    <div style = "display: flex; gap: 20px;">

                      <sec:authorize access = "hasAnyRole('PARTNER - FDR', 'VOLUNTEER - FDR')">
                      <form name = "update" method = "get" action = "/meal-delivery_current_pickup">
                        <input type = "hidden" name = "id"        value = "${pickup.id}" />
                        <input type = "submit" name = "submit"    value = "Update" style = "width: 100px" />
                      </form>
                      </sec:authorize>

                      <sec:authorize access = "hasAnyRole('MEMBER')">
                      <form name = "update" method = "get" action = "/meal-delivery_current_pickup">
                        <input type = "submit" name = "submit"    value = "Update" style = "width: 100px" disabled = "true" />
                      </form>
                      </sec:authorize>
  
                      <form name = "caregivers" method = "get" action = "/meal-delivery_current_pickup_caregivers">
                        <input type = "hidden" name = "id"        value = "${pickup.id}" />
                        <input type = "submit" name = "submit"    value = "Caregivers" style = "width: 100px" /> <br />
                      </form>

                    </div>


                  </td>


                </tr>
                </c:forEach>


                <tr id = "page-navigation">


                  <td colspan = "14" style = "text-align: right">

                    <c:if test = "${not empty pickups}">

                      <c:if test = "${current > 0}">
                      <a href="/meal-delivery_current?page=${current-1}&size=${size}">Prev</a>
                      </c:if>

                      <c:forEach begin = "0" end = "${pages - 1}" var = "i">

                      <c:if test = "${i != current}">
                      <a href="/meal-delivery_current?page=${i}&size=${size}"><c:out value = "${i + 1}" /></a>
                      </c:if>

                      <c:if test = "${i == current}">
                      <a href="javascript:void(0)" style = "color: gray; text-decoration: none;"><c:out value = "${i + 1}" /></a>
                      </c:if>

                      </c:forEach>
        
                      <c:if test = "${current < pages - 1}">
                      <a href="/meal-delivery_current?page=${current+1}&size=${size}">Next</a>
                      </c:if>

                    </c:if>

                  </td>


                </tr>


                <tr>


                  <td> <span> Total: </span> <br /> </td>


                  <td> <span> <!-- ${fn:length(pickups)} --> <c:out value = "${elements}" /> <br /> </td>


                  <td colspan = "9">


                    <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />


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
