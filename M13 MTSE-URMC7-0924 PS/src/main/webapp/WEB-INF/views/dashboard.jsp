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


              <p> </p>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Meals on Wheels (MOW) <span style = "color: darkred"> by MerryMeal </span> </u> </h1>


              <br />


              <div class = "accordion" style = "width: 1200px">

                <h3> Registration </h3>

                <div class = "accordion">

                  <h4> Count of active members:    <c:out value = "${members}"  default = "?" /> </h4>

                  <div>

                    <p> Count of active members with Halal diet:      <c:out value = "${membersHalal}"  default = "?" /> </p>
                    <p> Count of active members with Vegetarian diet: <c:out value = "${membersVeg}"    default = "?" /> </p>
                    <p> Count of active members with Soft diet:       <c:out value = "${membersSoft}"   default = "?" /> </p>
                    <p> Count of active members with Normal diet:     <c:out value = "${membersNormal}" default = "?" /> </p>

                  </div>

                  <h4> Count of active caregivers: <c:out value = "${caregivers}" default = "?" /> </h4>

                  <div>

                    <!-- Empty -->

                  </div>

                  <h4> Count of active partners:   <c:out value = "${partners}" default = "?" /> </h4>

                  <div>

                    <p> Count of active partners with Provider role:   <c:out value = "${partnersProvider}"   default = "?"   /> </p>
                    <p> Count of active partners with Rider role:      <c:out value = "${partnersRider}"      default = "?"   /> </p>

                  </div>

                  <h4> Count of active volunteers: <c:out value = "${volunteers}" default = "?" /> </h4>

                  <div>

                    <p> Count of active volunteers with Provider role: <c:out value = "${volunteersProvider}" default = "?" /> </p>
                    <p> Count of active volunteers with Rider role:    <c:out value = "${volunteersRider}"    default = "?" /> </p>

                  </div>

                </div>

                <h3> Evaluation </h3>

                <div>

                  <p> List of active members with now > (next evaluation - <c:out value = "${notice}" default = "?" /> days): <br />

                    <c:if test = "${empty membersScheduled}">
                        <c:out value = "-" /> <br />
                    </c:if>                  

                    <c:if test = "${not empty membersScheduled}">
                      <ul>
                      <c:forEach var = "memberScheduled" items = "${membersScheduled}">
                        <li>
                        <c:out value = "${memberScheduled.names}" /> <c:out value = "${memberScheduled.surname}" /> <c:out value = "${memberScheduled.nric_uen}" /> </br>
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>

                  <p> List of pending members: <br />

                    <c:if test = "${empty membersPending}">
                       <c:out value = "-" /> <br />
                    </c:if>

                    <c:if test = "${not empty membersPending}">
                      <ul>
                      <c:forEach var = "memberPending" items = "${membersPending}">
                        <li>
                        <c:out value = "${memberPending.names}" />    <c:out value = "${memberPending.surname}" />    <c:out value = "${memberPending.nric_uen}" /> <br />
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>

                  <p> List of pending caregivers: <br />

                    <c:if test = "${empty caregiversPending}">
                        <c:out value = "-" /> <br />
                    </c:if>

                    <c:if test = "${not empty caregiversPending}">
                      <ul>
                      <c:forEach var = "caregiverPending" items = "${caregiversPending}">
                        <li>
                        <c:out value = "${caregiverPending.names}" /> <c:out value = "${caregiverPending.surname}" /> <c:out value = "${caregiverPending.nric_uen}" /> <br />
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>


                  <p> List of pending partners: <br />

                    <c:if test = "${empty partnersPending}">
                        <c:out value = "-" /> <br />
                    </c:if>

                    <c:if test = "${not empty partnersPending}">
                      <ul>
                      <c:forEach var = "partnerPending" items = "${partnersPending}">
                        <li>
                        <c:out value = "${partnerPending.names}" />   <c:out value = "${partnerPending.nric_uen}" /> <br />
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>

                  <p> List of pending volunteers: <br />

                    <c:if test = "${empty volunteersPending}">
                        <c:out value = "-" /> <br />
                    </c:if>

                    <c:if test = "${not empty volunteersPending}">
                      <ul>
                      <c:forEach var = "volunteerPending" items = "${volunteersPending}">
                        <li>
                        <c:out value = "${volunteerPending.names}" /> <c:out value = "${volunteerPending.surname}" /> <c:out value = "${volunteerPending.nric_uen}" /> <br />
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>

                </div>

                <h3> Menu Planning </h3>

                <div>

                  <p> List of active providers with count of active Halal menus: <br />

                    <c:if test = "${empty repertoiresMenuHalal}">
                        <c:out value = "-" /> <br />
                    </c:if>

                    <c:if test = "${not empty repertoiresMenuHalal}">
                      <ul>
                      <c:forEach var = "repertoireMenuHalal" items = "${repertoiresMenuHalal}">
                        <li>
                        <c:out value = "${repertoireMenuHalal.names}" /> <c:out value = "${repertoireMenuHalal.surname}" /> <c:out value = "${repertoireMenuHalal.nric_uen}" />
                       (<c:out value = "${repertoireMenuHalal.count}" />) <br />
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>

                  <p> List of active providers with count of active Vegetarian menus: <br />

                    <c:if test = "${empty repertoiresMenuVeg}">
                        <c:out value = "-" /> <br />
                    </c:if>

                    <c:if test = "${not empty repertoiresMenuVeg}">
                      <ul>
                      <c:forEach var = "repertoireMenuVeg" items = "${repertoiresMenuVeg}">
                        <li>
                        <c:out value = "${repertoireMenuVeg.names}" /> <c:out value = "${repertoireMenuVeg.surname}" /> <c:out value = "${repertoireMenuVeg.nric_uen}" />
                       (<c:out value = "${repertoireMenuVeg.count}" />) <br />
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>

                  <p> List of active providers with count of active Soft menus: <br />

                    <c:if test = "${empty repertoiresMenuSoft}">
                        <c:out value = "-" /> <br />
                    </c:if>

                    <c:if test = "${not empty repertoiresMenuSoft}">
                      <ul>
                      <c:forEach var = "repertoireMenuSoft" items = "${repertoiresMenuSoft}">
                        <li>
                        <c:out value = "${repertoireMenuSoft.names}" /> <c:out value = "${repertoireMenuSoft.surname}" /> <c:out value = "${repertoireMenuSoft.nric_uen}" />
                       (<c:out value = "${repertoireMenuSoft.count}" />) <br />
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>

                  <p> List of active providers with count of active Normal menus: <br />

                    <c:if test = "${empty repertoiresMenuNormal}">
                        <c:out value = "-" /> <br />
                    </c:if>

                    <c:if test = "${not empty repertoiresMenuNormal}">
                      <ul>
                      <c:forEach var = "repertoireMenuNormal" items = "${repertoiresMenuNormal}">
                        <li>
                        <c:out value = "${repertoireMenuNormal.names}" /> <c:out value = "${repertoireMenuNormal.surname}" /> <c:out value = "${repertoireMenuNormal.nric_uen}" />
                       (<c:out value = "${repertoireMenuNormal.count}" />) <br />
                        </li>
                      </c:forEach>
                      </ul>
                    </c:if>

                  </p>

                </div>

                <h3> Meal Preparation </h3>

                <div>

                  <p> Count of open meal preparations:   <c:out value = "${mealsOpen}"   default = "?" /> </p>
                  <p> Count of closed meal preparations: <c:out value = "${mealsClosed}" default = "?" /> </p>

                </div>

                <h3> Meal Delivery </h3>

                <div>

                  <p> Count of available meal preparations: <c:out value = "${mealsAvailable}" default = "?" /> </p>
                  <p> Count of open meal deliveries:        <c:out value = "${pickupsOpen}"    default = "?" /> </p>
                  <p> Count of closed meal deliveries:      <c:out value = "${pickupsClosed}"  default = "?" /> </p>

                </div>

                <h3> Service Feedback </h3>

                <div class = "accordion">

                  <h4> Count of active feedback submissions: <c:out value = "${submissions}" default = "?" /> </h4>
                  
                  <div>

                    <p> Count of active feedback submissions with meal rating >= 3 and delivery rating >= 3: <c:out value = "${submissionsGood}" default = "?" /> </p>
                    <p> Count of active feedback submissions with meal rating < 3 or delivery rating < 3:    <c:out value = "${submissionsPoor}" default = "?" /> </p>

                  </div>
                  
                </div>

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

      <script nonce = "${random-value}">


        /* Activation of jQuery Widget(s) */

        $(document).ready(function() {

          $(".accordion").accordion({
            heightStyle: "content"
          });

        });


      </script>


    </div>


  </body>


</html>
