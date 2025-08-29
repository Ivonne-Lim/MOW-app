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


    <link href = "/images/logo-mow.png" rel = "icon"       type = "image/x-icon" />


    <link href = "/css/jquery-ui.css"   rel = "stylesheet" type = "text/css" />

    <link href = "/css/styles.css"      rel = "stylesheet" type = "text/css" />


  </head>


  <body>


    <div id = "root">


      <div id = "page-container">


        <div id = "content-wrap">


          <jsp:include page = "header.jsp" />


          <main id = "main">


            <div id = "breadcrumb">


              <a href = "/home">Home</a> > <span>Error</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Error </u> </h1>


              <br />

      
              <table>


                <tr>


                  <td style = "border: 1px solid black"> <h4> Message: </h4> </td>


                  <td style = "border: 1px solid black; width: 1200px;"> <h4> <c:out value = "${message}" /> </h4> </td>


                </tr>


                <tr>


                  <td style = "border: 1px solid black"> <h4> Date and Time: </h4> </td>


                  <td style = "border: 1px solid black">


                    <c:if test = "${not empty dateTime}">
                      <h4> <c:out value = "${dateTime}" /> </h4>
                    </c:if>

                    <c:if test = "${empty dateTime}">
                      <h4> - </h4>
                    </c:if>


                  </td>


                </tr>


              </table>


              <br />


              <p> Try clicking [Back] button of web browser for returning to previous web page. </p>


              <p> Otherwise click [Home] button or hyperlink to start over. </p>

              <form name = "home" method = "get" action = "/home">
                <input type = "submit" name = "submit" value = "Home" style = "width: 100px" /> <br />
              </form>


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
