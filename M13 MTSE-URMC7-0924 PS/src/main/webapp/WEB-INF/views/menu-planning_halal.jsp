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


              <a href = "/home">Home</a> > <a href = "/menu-planning">Menu Planning</a> > <span>Halal Menus</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Menu Planning (Halal Menus) </u> </h1>


              <br />


              <table>


                <tr style = "background-color: lightgray">


                  <th style = "border: 1px solid black"> <span> ID        </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Seq Day   </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Seq Time  </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Menu Name </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Diet      </span> <br/> </th>


                  <th style = "border: 1px solid black"> <span> Frozen    </span> <br /> </th>


                  <th style = "border: 1px solid black"> <span> Status    </span> <br /> </th>


                  <th style = "border: 1px solid black">


                    <div style = "display: flex; gap: 60px;">
                  
                      <span> Action(s) </span>

                      <form name = "create" method = "get" action = "/menu-planning_halal_menu">
                        <input type = "submit" name = "submit"    value = "Create" style = "width: 100px" /> <br />
                      </form>

                    </div>


                  </th>


                </tr>


                <c:if test = "${empty menus}">
                <tr>


                  <td style = "width:  5ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 10ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 10ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 22ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width:  8ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 12ch; border: 1px solid black;">  <p> </p> </td>


                  <td style = "width: 220px; border: 1px solid black;"> <p> </p> </td>


                </tr>
                </c:if>


                <c:forEach var = "menu" items = "${menus}">
                <tr>


                  <td style = "width:  5ch; border: 1px solid black;"> <span> <c:out value = "${menu.id}"       /> </span> <br /> </td>


                  <td style = "width: 10ch; border: 1px solid black;"> <span> <c:out value = "${menu.seq_day}"  /> </span> <br /> </td>


                  <td style = "width: 10ch; border: 1px solid black;"> <span> <c:out value = "${menu.seq_time}" /> </span> <br /> </td>


                  <td style = "width: 22ch; border: 1px solid black;"> <span> <c:out value = "${menu.name}"     /> </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;"> <span> <c:out value = "${menu.diet}"     /> </span> <br /> </td>


                  <td style = "width:  8ch; border: 1px solid black;"> <span> <c:out value = "${menu.frozen}"   /> </span> <br /> </td>


                  <td style = "width: 12ch; border: 1px solid black;"> <span> <c:out value = "${menu.status}"   /> </span> <br /> </td>


                  <td style = "width: 220px; border: 1px solid black;">


                    <div style = "display: flex; gap: 20px;">

                      <form name = "update" method = "get" action = "/menu-planning_halal_menu">
                        <input type = "hidden" name = "id"        value = "${menu.id}" />
                        <input type = "submit" name = "submit"    value = "Update" style = "width: 100px" />
                      </form>
  
                      <form name = "delete" method = "post" action = "/menu-planning_halal_deletion">
                        <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
                        <input type = "hidden" name = "id"        value = "${menu.id}" />
                        <input type = "submit" name = "submit"    value = "Delete" style = "width: 100px" /> <br />
                      </form>

                    </div>


                  </td>


                </tr>
                </c:forEach>


                <tr id = "page-navigation">


                  <td colspan = "8" style = "text-align: right">

                    <c:if test = "${not empty menus}">

                      <c:if test = "${current > 0}">
                      <a href="/menu-planning_halal?page=${current-1}&size=${size}">Prev</a>
                      </c:if>

                      <c:forEach begin = "0" end = "${pages - 1}" var = "i">

                      <c:if test = "${i != current}">
                      <a href="/menu-planning_halal?page=${i}&size=${size}"><c:out value = "${i + 1}" /></a>
                      </c:if>

                      <c:if test = "${i == current}">
                      <a href="javascript:void(0)" style = "color: gray; text-decoration: none;"><c:out value = "${i + 1}" /></a>
                      </c:if>

                      </c:forEach>
        
                      <c:if test = "${current < pages - 1}">
                      <a href="/menu-planning_halal?page=${current+1}&size=${size}">Next</a>
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
