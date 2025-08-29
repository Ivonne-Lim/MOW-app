<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"    %>

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


              <a href = "/home">Home</a> > <a href = "/menu-planning">Menu Planning</a> > <a href = "/menu-planning_veg">Vegetarian Menus</a> > <span>Vegetarian Menu</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Menu Planning (Vegetarian Menu) </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/menu-planning_veg_menu" modelAttribute = "menu">


                <table>


                  <tr style = "background-color: lightgray">


                    <td style = "border: 1px solid black" colspan = "4">


                      <span> <b> Menu Details </b> </span> <br />


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
                      
                      <span> <c:out value = "${menu.id}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "seq_day"> Sequence Day: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "number" path = "seq_day" required = "true" maxlength = "2" size = "1" min = "0" max = "99" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "seq_time"> Sequence Time: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "number" path = "seq_time" required = "true" maxlength = "2" size = "1" min = "0" max = "99" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "name"> Menu Name: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "name" required = "true" maxlength = "20" size = "20" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "diet"> Diet: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "diet" value = "Vegetarian" />
                      
                      <span> Vegetarian </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "frozen"> Frozen: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:select path = "frozen">
                        <form:option label = "No"  value = "No"  />
                        <form:option label = "Yes" value = "Yes" />
                      </form:select> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "status"> Status: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:select path = "status">
                        <form:option label = "Pending" value = "Pending" />
                        <form:option label = "Active"  value = "Active"  />
                        <form:option label = "Deleted" value = "Deleted" />
                      </form:select> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <span> Food Service Provider: </span> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">

                     
                      <span> <c:out value = "${provider.nric_uen}" /> <c:out value = "${provider.names}" /> <c:out value = "${provider.surname}" /> </span> <br />


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
