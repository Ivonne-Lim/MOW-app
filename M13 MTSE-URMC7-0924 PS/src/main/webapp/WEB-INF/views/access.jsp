<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"   %>

<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>


<!DOCTYPE html>


<html lang = "en">


  <head>


    <meta charset = "UTF-8">


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


              <a href = "/home">Home</a> >

                <c:if test = "${empty logout_success}">
                <span>Login</span>
                </c:if>

                <c:if test = "${not empty logout_success}">
                <span>Logout</span>
                </c:if>


              <br />


            </div>


            <div id = "content">


              <c:if test = "${empty logout_success}">
              <h1> <u> Login </u> </h1>
              </c:if>

              <c:if test = "${not empty logout_success}">
              <h1> <u> Logout </u> </h1>
              </c:if>


              <br />


              <div id = "notification">


                <c:if test = "${empty login_failure && empty login_success && empty logout_success}">
                  <br />
                  <br />
                  <br />
                  <br />
                </c:if>

                <c:if test = "${not empty login_failure}">
                  <span style = "color: red"> ${login_failure} </span> <br />
                  <br />
                  <span> <i> Click here for <a href = "/registration"> Registration </a>. </i> </span> <br />
                  <br />
                </c:if>

                <c:if test = "${not empty login_success}">
                  <span style = "color: green"> ${login_success} </span> <br />
                  <br />
                  <span> <i> Click here for <a href = "/home"> Home </a>. </i> </span> <br />
                  <br />
                </c:if>

                <c:if test = "${not empty logout_success}">
                  <span style = "color: green"> ${logout_success} </span> <br />
                  <br />
                  <span> <i> Click here for <a href = "/access"> Login </a>. </i> </span> <br />
                  <br />
                </c:if>


              </div>


              <div id = "access">


                <c:if test = "${not empty login_success || not empty logout_success}">

                  <form>

                    <table>

                      <tr style = "height: 44px">

                        <td colspan = "2> <p> </p> </td>

                      </tr>

                      <tr>

                        <td colspan = "2"> <p> </p> </td>

                      </tr>

                      <tr style = "height: 44px">

                        <td> <p> </p> </td>

                        <td> <p> </p> </td>

                      </tr>

                      <tr style = "height: 44px">

                        <td> <p> </p> </td>

                        <td> <p> </p> </td>

                      </tr>

                      <tr style = "height: 44px">

                        <td> <p> </p> </td>

                        <td> <p> </p> </td>

                      </tr>

                    </table>

                  </form>

                </c:if>


                <c:if test = "${empty login_success && empty logout_success}">

                  <form name = "form" method = "post" action = "/login">

                    <table>

                      <tr style = "background-color: lightgray">
  
                        <td style = "border: 1px solid black" colspan = "2"> <span> <b> Credentials </b> </span> <br /> </td>

                      </tr>
              
                      <tr>

                        <td colspan = "2"> <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"> </input> <br /> </td>

                      </tr>

                      <tr>

                        <td style = "border: 1px solid black"> <label for = "email"> Email: </label> <br /> </td>

                        <td style = "border: 1px solid black">
                          <input type = "email" id = "email" name = "email" required = "true" maxlength = "80" size = "80" /> <br /> </td>

                      </tr>

                      <tr>

                        <td style = "border: 1px solid black"> <label for = "password"> Password: </label> <br /> </td>

                        <td style = "border: 1px solid black">
                        <input type = "password" id = "password" name = "password" required = "true" maxlength = "64" size = "20" /> <br /> </td>

                      </tr>

                      <tr>

                        <td> <p> </p> </td>

                        <td>
                          <div style = "text-align: right">
                            <input type = "submit" name = "submit" value = "Submit" style = "width: 100px" /> <br />
                          </div>
                        </td>

                      </tr>

                    </table>

                  </form>

                </c:if>


                <br />


              </div>


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
