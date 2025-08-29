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


  </head>


  <body>


    <div id = "root">


      <div id = "page-container">


        <div id = "content-wrap">


          <jsp:include page = "header.jsp" />


          <main id = "main">


            <div id = "breadcrumb">


              <a href = "/home">Home</a> > <a href = "/meal-preparation">Meal Preparation</a> > <span>Normal Meals</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Meal Preparation (Normal Meals) </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/meal-preparation_normal_updation" modelAttribute = "selectWrapper">


                <table>


                  <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')">

                  <c:if test = "${not empty menu}">
                  <caption> <b>Current Menu:</b> <c:out value = "${menu}" /> <br /> <br /> </caption>
                  </c:if>

                  <c:if test = "${empty menu}">
                  <caption> <b>Current Menu:</b> <span style = "color:red"> Not set </span> <br /> <br /> </caption>
                  </c:if>

                  </sec:authorize>


                  <tr style = "background-color: lightgray">


                    <th style = "border: 1px solid black"> <span> ID             </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Seq Day        </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Seq Time       </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Menu Name      </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Frozen         </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Allergies      </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> NRIC           </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Name(s)        </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Surname        </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Time Available </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Time Started   </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Time Ended     </span> <br /> </th>


                    <th style = "border: 1px solid black"> <span> Status         </span> <br /> </th>


                    <th style = "border: 1px solid black">


                      <div style = "display: flex; gap: 25px;">
                  
                        <label>
                          <input type = "checkbox" id = "checkbox" name = "checkbox" value = "Select All" />
                          <span> Select All </span>
                        </label>

                        <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')">
                        <input type = "submit" name = "submit" value = "Generate" style = "width: 100px" formmethod = "post" formaction = "/meal-preparation_normal_generation" /> <br />
                        </sec:authorize>

                        <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                        <input type = "submit" name = "submit" value = "Generate" style = "width: 100px" formmethod = "post" formaction = "/meal-preparation_normal_generation" disabled = "true" /> <br />
                        </sec:authorize>

                      </div>


                    </th>


                  </tr>


                  <c:if test = "${empty meals}">
                  <tr>


                    <td style = "width:  5ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 10ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 10ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width:  8ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 42ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                    <td style = "width: 220px; border: 1px solid black;"> <p> </p> </td>


                  </tr>
                  </c:if>


                  <c:forEach var = "meal" items = "${meals}">
                  <tr>


                    <td style = "width:  5ch; border: 1px solid black;"> <span> <c:out value = "${meal.id}" />    </span> <br /> </td>


                    <td style = "width: 10ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.menu}">
                      <c:out value = "${meal.menu.seq_day}" />
                      </c:if>

                                                                                                                   </span> <br /> </td>


                    <td style = "width: 10ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.menu}">
                      <c:out value = "${meal.menu.seq_time}" />
                      </c:if>

                                                                                                                   </span> <br /> </td>


                    <td style = "width: 22ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.menu}">
                      <c:out value = "${meal.menu.name}" />
                      </c:if>

                                                                                                                   </span> <br /> </td>


                    <td style = "width:  8ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.menu}">
                      <c:out value = "${meal.menu.frozen}" />
                      </c:if>

                                                                                                                   </span> <br /> </td>


                    <td style = "width: 42ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.member}">
                      <c:out value = "${meal.member.allergies}" />
                      </c:if>

                                                                                                                   </span> <br /> </td>



                    <td style = "width: 12ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.member}">
                      <c:out value = "${meal.member.nric_uen}" />
                      </c:if>

                                                                                                                   </span> <br /> </td>


                    <td style = "width: 22ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.member}">
                      <c:out value = "${meal.member.names}" />
                      </c:if>

                                                                                                                   </span> <br /> </td>


                    <td style = "width: 22ch; border: 1px solid black;"> <span>

                      <c:if test = "${not empty meal.member}">
                      <c:out value = "${meal.member.surname}" />
                      </c:if>

                                                                                                                   </span> <br /> </td>


                    <td style = "width: 22ch; border: 1px solid black;"> <span>
                      <fmt:parseDate  value = "${meal.time_avail}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeAvail" type = "both" />
                      <fmt:formatDate value = "${parseTimeAvail}"  pattern = "yyyy-MM-dd HH:mm" />                 </span> <br /> </td>


                    <td style = "width: 22ch; border: 1px solid black;"> <span>
                      <fmt:parseDate  value = "${meal.time_start}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeStart" type = "both" />
                      <fmt:formatDate value = "${parseTimeStart}"  pattern = "yyyy-MM-dd HH:mm" />                 </span> <br /> </td>


                    <td style = "width: 22ch; border: 1px solid black;"> <span>
                      <fmt:parseDate  value = "${meal.time_end}"   pattern = "yyyy-MM-dd'T'HH:mm" var = "parseTimeEnd"    type = "both" />
                      <fmt:formatDate value = "${parseTimeEnd}"    pattern = "yyyy-MM-dd HH:mm" />                 </span> <br /> </td>


                    <td style = "width: 12ch; border: 1px solid black;"> <span> <c:out value = "${meal.status}" /> </span> <br /> </td>


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
                        <a href="/meal-preparation_normal?page=${current-1}&size=${size}">Prev</a>
                        </c:if>

                        <c:forEach begin = "0" end = "${pages - 1}" var = "i">

                        <c:if test = "${i != current}">
                        <a href="/meal-preparation_normal?page=${i}&size=${size}"><c:out value = "${i + 1}" /></a>
                        </c:if>

                        <c:if test = "${i == current}">
                        <a href="javascript:void(0)" style = "color: gray; text-decoration: none;"><c:out value = "${i + 1}" /></a>
                        </c:if>

                        </c:forEach>
        
                        <c:if test = "${current < pages - 1}">
                        <a href="/meal-preparation_normal?page=${current+1}&size=${size}">Next</a>
                        </c:if>

                      </c:if>

                    </td>


                  </tr>


                  <tr>


                    <td colspan> <p> </p> </td>


                    <td> <span> Total: </span> <br /> </td>


                    <td> <span> <!-- ${fn:length(meals)} --> <c:out value = "${elements}" /> <br /> </td>


                    <td colspan = "8">


                      <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />


                    </td>


                    <td>


                      <label for = "status"> <b> New Status: </b> </label> <br />


                    </td>


                    <td>


                      <select id = "status" name = "status">
                        <option label = "Generated" value = "Generated" />
                        <option label = "Available" value = "Available" />
                        <option label = "Started"   value = "Started"   />
                        <option label = "Ended"     value = "Ended"     />
                      </select> <br />


                    </td>


                    <td style = "width: 220px">


                      <div style = "display: flex; gap: 20px;">

                        <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')">
                        <form:button style = "width: 100px"> Update </form:button>
                        <input type = "submit" name = "submit" value = "Delete" style = "width: 100px" formmethod = "post" formaction = "/meal-preparation_normal_deletion" /> <br />
                        </sec:authorize>

                        <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                        <form:button style = "width: 100px" disabled = "true"> Update </form:button>
                        <input type = "submit" name = "submit" value = "Delete" style = "width: 100px" formmethod = "post" formaction = "/meal-preparation_normal_deletion" disabled = "true" /> <br />
                        </sec:authorize>

                      </div>


                      <br />


                      <div style = "display: flex; gap: 20px;">

                        <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')">
                        <input type = "submit" name = "submit" value = "Print" style = "width: 100px" formmethod = "post" formaction = "/meal-preparation_normal_printing" /> <br />
                        </sec:authorize>

                        <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER')">
                        <input type = "submit" name = "submit" value = "Print" style = "width: 100px" formmethod = "post" formaction = "/meal-preparation_normal_printing" disabled = "true" /> <br />
                        </sec:authorize>

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
