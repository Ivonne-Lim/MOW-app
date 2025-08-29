<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"   %>

<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>


<!-- html -->
<!-- body -->
<!-- div id = "root" -->
<!-- div id = "page-container" -->
<!-- div id = "content-wrap" -->


<header id = "header">


  <table>


    <tr>


      <td style = "width: 100px">


        <a href = "/home"> <img width = "39px" height = "38px" src = "/images/logo-mow.png" alt = "MOW Logo" /> </a> <br />


      </td>


      <td style = "width: 100px">


        <sec:authorize access = "!isAuthenticated()">

          <p> </p>

        </sec:authorize>

        <sec:authorize access = "isAuthenticated()">

          <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')">
          <a href = "/menu-planning"> Menu Planning </a> <br />
          </sec:authorize>

          <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
          <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Menu Planning </a> <br />
          </sec:authorize>

        </sec:authorize>      


      </td>


      <td style = "width: 100px">


        <sec:authorize access = "!isAuthenticated()">

          <p> </p>

        </sec:authorize>

        <sec:authorize access = "isAuthenticated()">

          <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'VOLUNTEER - FSP')">
          <a href = "/meal-preparation"> Meal Preparation </a> <br />
          </sec:authorize>

          <sec:authorize access = "hasAnyRole('PARTNER - FDR', 'VOLUNTEER - FDR')">
          <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Meal Preparation </a> <br />
          </sec:authorize>

        </sec:authorize>


      </td>


      <td style = "width: 100px">


        <p> </p>


      </td>


      <td style = "width: 100px">


        <sec:authorize access = "!isAuthenticated()">

          <p> </p>

        </sec:authorize>

        <sec:authorize access = "isAuthenticated()">

          <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FDR', 'VOLUNTEER - FDR')">
          <a href = "/meal-delivery"> Meal Delivery </a> <br />
          </sec:authorize>

          <sec:authorize access = "hasAnyRole('PARTNER - FSP', 'VOLUNTEER - FSP')">
          <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Meal Delivery </a> <br />
          </sec:authorize>

        </sec:authorize>


      </td>


      <td style = "width: 100px">


        <sec:authorize access = "!isAuthenticated()">

          <p> </p>

        </sec:authorize>

        <sec:authorize access = "isAuthenticated()">

          <sec:authorize access = "hasAnyRole('ADMINISTRATOR', 'MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
          <a href = "/service-feedback"> Service Feedback </a> <br />
          </sec:authorize>

        </sec:authorize>


      </td>


      <td style = "width: 100px">


        <p> </p>


      </td>


      <td style = "width: 100px">


        <sec:authorize access = "!isAuthenticated()">
        <a href = "/registration"> Registration </a> <br />
        </sec:authorize>
        
        <sec:authorize access = "hasAnyRole('MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
        <a href = "/registration"> Registration </a> <br />
        </sec:authorize>

        <sec:authorize access = "hasAnyRole('ADMINISTRATOR')">
        <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Registration </a> <br />
        </sec:authorize>


      </td>


      <td style = "width: 100px">


        <sec:authorize access = "!isAuthenticated()">

          <p> </p>

        </sec:authorize>

        <sec:authorize access = "isAuthenticated()">

          <sec:authorize access = "hasAnyRole('ADMINISTRATOR')">        
          <a href = "/evaluation"> Evaluation </a> <br />
          </sec:authorize>

          <sec:authorize access = "hasAnyRole('MEMBER', 'PARTNER - FSP', 'PARTNER - FDR', 'VOLUNTEER - FSP', 'VOLUNTEER - FDR')">
          <a href = "javascript:void(0)" style = "color: gray; text-decoration: none;"> Evaluation </a> <br />
          </sec:authorize>

        </sec:authorize>


      </td>


      <td style = "width: 100px; text-align: right;">


        <sec:authorize access = "!isAuthenticated()">

          <form name = "access" method = "get" action = "/access">
            <input type = "submit" name = "access" value = "Login" style = "width: 100px" /> <br />
          </form>

          <a href = "/oauth2/authorization/facebook"> <img width = "18px" height = "18px" src = "/images/logo-facebook.png" alt = "FB Logo" /> </a> <br />

        </sec:authorize>

        <sec:authorize access = "isAuthenticated()">

          <form name = "access" method = "post" action = "/logout">
            <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />
            <input type = "submit" name = "access" value = "Logout" style = "width: 100px" /> <br />
          </form>

        </sec:authorize>


      </td>


    </tr>


  </table>


</header>


<!-- /div -->
<!-- /div -->
<!-- /div -->
<!-- /body -->
<!-- /html -->
