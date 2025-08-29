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


              <a href = "/home">Home</a> > <span>Registration</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Registration </u> </h1>


              <br />


              <table>


                <tr>


                  <td style = "border: 1px solid black">


                    <img width = "310" height = "170" src = "/images/profile-member.jpg" alt = "Member" /> <br />
                    
                    
                    <br />


                    <sec:authorize access = "!isAuthenticated()">
                    <a href = "/registration_member"> Member and any Caregivers </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('MEMBER')">
                    <a href = "/registration_member"> Member and any Caregivers </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
                    <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Member and any Caregivers </a> <br />
                    </sec:authorize>


                    <br />


                  </td>


                  <td style = "border: 1px solid black">


                    <img width = "310" height = "170" src = "/images/meal-preparation.jpg" alt = "Partner" /> <br />


                    <br />


                    <sec:authorize access = "!isAuthenticated()">
                    <a href = "/registration_partner"> Partner </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'PARTNER - FDR')">
                    <a href = "/registration_partner"> Partner </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('MEMBER', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
                    <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Partner </a> <br />
                    </sec:authorize>


                    <br />


                  </td>


                  <td style = "border: 1px solid black">


                    <img width = "310" height = "170" src = "/images/meal-delivery.jpg" alt = "Volunteer" /> <br />


                    <br />


                    <sec:authorize access = "!isAuthenticated()">
                    <a href = "/registration_volunteer"> Volunteer </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
                    <a href = "/registration_volunteer"> Volunteer </a> <br />
                    </sec:authorize>

                    <sec:authorize access = "hasAnyRole('MEMBER', 'PARTNER - FSP', 'PARTNER - FDR')">
                    <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Volunteer </a> <br />
                    </sec:authorize>


                    <br />


                  </td>


                </tr>


              </table>


              <sec:authorize access = "!isAuthenticated()">
              <p> <span style = "color: orange"> Warning: </span> You will not be able to access your profile after leaving it unless you login. </p>
              </sec:authorize>

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
