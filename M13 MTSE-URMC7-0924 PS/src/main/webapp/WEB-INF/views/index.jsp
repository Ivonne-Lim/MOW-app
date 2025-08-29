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


          <main id = "main-home">


            <div id = "breadcrumb">


              <p> </p>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Meals on Wheels (MOW) <span style = "color: darkred"> by MerryMeal </span> </u> </h1>


              <br />


              <span>
                MOW is an intenational programme that plans, prepares and delivers meals to eligible
                members at home who are unable to buy or make their own meals.
              </span> <br />


              <br />


              <span>
                In Singapore, the programme is currently run by the Agency for Integrated Care (AIC) and
                various charitable organisations serving different regions.
              </span> <br />


              <br />


              <span>
                With Singapore's population aging rapidly, MerryMeal is a fictitious charity organisation with a
                mission to provide an additional service throughout Singapore.
              </span> <br />


              <br />


              <span>
                MerryMeal requires this web Enterprise Resource Planning (ERP) application as a business
                need:
              </span> <br />


              <!-- br / -->

            
              <ol type = "1" start = "1">


                <li> Managing </li>


                <br />


                <ol type = "a" start = "1">


                  <li> Registration and suitability evaluation of disabled, unwell and elderly members
                       and their caregivers, partners and volunteers. </li>


                  <br />

            
                  <li> Meal planning, preparation and delivery by the partners and volunteers for the members
                       via caregivers according to lunch and dinner schedule and any diet restrictions. </li>


                  <br />


                </ol>


                <li> Ascertaining the usability, security, performance, efficiency and effectiveness so well able to
                     sustain the food security and nutrition aspects in the quality of life among the members. </li>


                <br /> 


              </ol>


              <div id = "sidebar">


                <img width = "260px" height = "240px" src = "/images/logo-merrymeal.jpg" alt = "MerryMeal Logo" style = "border: 1px solid black" />


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
