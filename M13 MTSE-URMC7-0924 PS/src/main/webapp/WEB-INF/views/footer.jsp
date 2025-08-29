<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"   %>

<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>


<!-- html -->
<!-- body -->
<!-- div id = "root" -->
<!-- div id = "page-container" -->


<footer id = "footer">


  <table>


    <tr>


      <td colspan = "7">


        <span> Sitemap </span> <br />


        <p> </p>


      </td>


      <td>


        <a href = "/pdf/policy-privacy.pdf" target = "blank"> Privacy Policy </a> <br />


        <p > </p>


      </td>


      <td>


        <a href = "/contacts"> Contacts </a> <br />


        <p> </p>


      </td>


      <td style = "text-align: right">


        <a href = "/pdf/guide-user.pdf" target = "blank"> User Guide </a> <br />


        <p> </p>


      </td>


    </tr>


    <tr>

      <td style = "width: 100px">


        <p> </p>


        <p> </p>


        <p> </p>


        <p> </p>


        <p> </p>


      </td>


      <td colspan = "5" style = "border: 1px solid black; padding: 0px;">


        <table>


          <tr>


            <td style = "width: 100px">


              <p> </p>


              <a href = "/home"> <i> Home </i> </a> <br />


              <sec:authorize access = "!isAuthenticated()">

                <p> </p>

              </sec:authorize>

              <sec:authorize access = "isAuthenticated()">

                <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')">
                <a href = "/menu-planning"> <i> Menu Planning </i> </a> <br />
                </sec:authorize>

                <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> <i> Menu Planning </i> </a> <br />
                </sec:authorize>

              </sec:authorize> 


              <p> </p>


              <p> </p>


            </td>


            <td style = "width: 100px">


              <p> </p>


              <sec:authorize access = "!isAuthenticated()">

                <form name = "access" method = "get" action = "/access">
                  <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
                  <a href = "javascript:document.forms['access'].submit()"> <i> Login </i> </a> <br />
                </form>

              </sec:authorize>

              <sec:authorize access = "isAuthenticated()">

                <form name = "access" method = "post" action = "/logout">
                  <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
                  <a href = "javascript:document.forms['access'].submit()"> <i> Logout </i> </a> <br />
                </form>

              </sec:authorize>


              <sec:authorize access = "!isAuthenticated()">

                <p> </p>

              </sec:authorize>

              <sec:authorize access = "isAuthenticated()">

                <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'VOLUNTEER - FSP')">
                <a href = "/meal-preparation"> <i> Meal Preparation </i> </a> <br />
                </sec:authorize>

                <sec:authorize access = "hasAnyRole('PARTNER - FDR', 'VOLUNTEER - FDR')">
                <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> <i> Meal Preparation </i> </a> <br />
                </sec:authorize>

              </sec:authorize> 


              <p> </p>


              <p> </p>


            </td>


            <td style = "width: 100px">


              <p> </p>


              <p> </p>


              <p> </p>


              <p> </p>


              <p> </p>


            </td>


            <td style = "width: 100px">


              <p> </p>


              <sec:authorize access = "!isAuthenticated()">
              <a href = "/registration"> <i> Registration </i> </a> <br />
              </sec:authorize>

              <sec:authorize access = "hasAnyRole('MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
              <a href = "/registration"> <i> Registration </i> </a> <br />
              </sec:authorize>

              <sec:authorize access = "hasAnyRole('ADMINISTRATOR')">
              <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> <i> Registration </i> </a> <br />
              </sec:authorize>


              <sec:authorize access = "!isAuthenticated()">

                <p> </p>

              </sec:authorize>

              <sec:authorize access = "isAuthenticated()">

                <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
                <a href = "/meal-delivery"> <i> Meal Delivery </i> </a> <br />
                </sec:authorize>

                <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')">
                <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> <i> Meal Delivery </i> </a> <br />
                </sec:authorize>

              </sec:authorize> 


              <p> </p>


              <p> </p>


            </td>


            <td style = "width: 100px">


              <p> </p>


              <sec:authorize access = "!isAuthenticated()">

                <p> </p>

              </sec:authorize>

              <sec:authorize access = "isAuthenticated()">

                <sec:authorize access = "hasAnyRole('ADMINISTRATOR')">
                <a href = "/evaluation"> <i> Evaluation </i> </a> <br />
                </sec:authorize>

                <sec:authorize access = "hasAnyRole('MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
                <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> <i> Evaluation </i> </a> <br />
                </sec:authorize>

              </sec:authorize>


              <sec:authorize access = "!isAuthenticated()">

                <p> </p>

              </sec:authorize>

              <sec:authorize access = "isAuthenticated()">

                <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
                <a href = "/service-feedback"> <i> Service Feedback </i> </a> <br />
                </sec:authorize>

              </sec:authorize> 


              <p> </p>


              <p> </p>


            </td>


          </tr>


        </table>


      </td>


      <td style = "width: 100px">


        <p> </p>


        <p> </p>


        <p> </p>


        <p> </p>


        <p> </p>


      </td>


      <td style = "width: 100px; border: 1px solid black;">


        <span>
          <i>
            App by: <br />
            Ivonne Lim
          </i>
        </span> <br />


        <p> </p>


        <span>
          <i>
            Copyright: <br />
            MerryMeal Ltd
          </i>
        </span> <br />


      </td>


      <td style = "width: 100px; border: 1px solid black;">


        <img width = "90px" height = "100px" src = "/images/portrait-lim-ivonne.jpg" alt = "Ivonne Lim" /> <br />


      </td>


      <td style = "width: 100px">


        <p> </p>


        <p> </p>


        <p> </p>


        <p> </p>


        <p> </p>


      </td>


    </tr>


    <tr>


      <td colspan = "10">


        <p> </p>


      </td>


    </tr>


  </table>


</footer>


<!-- /div -->
<!-- /div -->
<!-- /body -->
<!-- /html -->
