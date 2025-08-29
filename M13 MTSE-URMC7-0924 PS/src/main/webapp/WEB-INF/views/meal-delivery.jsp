<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"   %>

<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>


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


              <a href = "/home">Home</a> > <span>Meal Delivery</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Meal Delivery </u> </h1>


              <br />


              <table>


                <tr>


                  <td style = "border: 1px solid black">


                    <img width = "310" height = "170" src = "/images/pickups-available.jpg" alt = "Available Pickups" /> <br />
                    
                    
                    <br />


                    <sec:authorize access = "hasAnyRole('PARTNER - FDR', 'VOLUNTEER - FDR')">
                    <a href = "/meal-delivery_acceptance"> Available Pickups (Acceptance) </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'VOLUNTEER - FSP')">
                    <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Available Pickups (Acceptance) </a> <br />
                    </sec:authorize>


                    <br />


                  </td>


                  <td style = "border: 1px solid black">


                    <img width = "310" height = "170" src = "/images/pickups-available.jpg" alt = "Available Pickups" /> <br />


                    <br />


                    <sec:authorize access = "hasAnyRole('ADMINISTRATOR')">
                    <a href = "/meal-delivery_assignment"> Available Pickups (Assignment) </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
                    <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Available Pickups (Assignment) </a> <br />
                    </sec:authorize>


                    <br />


                  </td>


                  <td style = "border: 1px solid black">


                    <img width = "310" height = "170" src = "/images/pickups-current.jpg" alt = "Current Pickups" /> <br />


                    <br />


                    <sec:authorize access = "hasAnyRole('MEMBER', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                    <a href = "/meal-delivery_current"> Current Pickups </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'PARTNER - FSP', 'VOLUNTEER - FSP')">
                    <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Current Pickups </a> <br />
                    </sec:authorize>


                    <br />


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
