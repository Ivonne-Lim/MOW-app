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

    <style>


      /* Configuration of jQuery Widget(s) */

      .ui-dialog .ui-dialog-titlebar {
        background-color: orange;
        color: white
      }


    </style>


  </head>


  <body>


    <div id = "root">


      <div id = "page-container">


        <div id = "content-wrap">


          <jsp:include page = "header.jsp" />


          <main id = "main">


            <div id = "breadcrumb">


              <a href = "/home">Home</a> > <a href = "/registration">Registration</a> > <span>Partner</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Registration (Partner) </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/registration_partner" modelAttribute = "partner">


                <table style = "float: left">


                  <tr style = "background-color: lightgray">


                    <td style = "border: 1px solid black" colspan = "4">


                      <span> <b> Partner Details </b> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "4">


                      <form:hidden path = "created" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "id"> ID: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "id" />
                      
                      <span> <c:out value = "${partner.id}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "nric_uen"> UEN: </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <input type = "text" id = "nric_uen" name = "nric_uen" value = "${partner.nric_uen}" required = "true" maxlength = "10" size = "10" pattern = "^[A-Za-z0-9]{0,10}$" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "names"> Name(s): </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "names" required = "true" maxlength = "80" size = "80" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "role"> Role: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:select path = "role">
                        <form:option label = "Food Service Provider (FSP)" value = "Partner - FSP" />
                        <form:option label = "Food Delivery Rider (FDR)"   value = "Partner - FDR" />
                      </form:select> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "email"> Email: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "email" path = "email" required = "true" maxlength = "80" size = "80" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "phone"> Phone: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "phone" required = "true" maxlength = "10" size = "10" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "address"> Address: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "address" required = "true" maxlength = "255" size = "80" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "unit"> <span style = "color: blue"> Unit: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "unit" maxlength = "10" size = "10" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "postal"> Postal: </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <input type = "text" id = "postal" name = "postal" value = "${partner.postal}" required = "true" maxlength = "6" size = "6" pattern = "^[0-9]{0,6}$" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "status"> Status: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${partner.status == null || partner.status == ''}">
                        <form:hidden path = "status" value = "Pending" />
                        <span> Pending </span> <br />
                      </c:if>

                      <c:if test = "${partner.status != null && partner.status != ''}">
                        <form:hidden path = "status" />
                        <span> <c:out value = "${partner.status}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "notes"> <span style = "color: blue"> Notes: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "notes" />

                      <c:if test = "${partner.notes == null || partner.notes == ''}"> <span> - </span> <br /> </c:if>

                      <c:if test = "${partner.notes != null && partner.notes != ''}"> <span> <c:out value = "${partner.notes}" /> </span> <br /> </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "password"> Password: </label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <input type = "password" id = "password" name = "password" value = "${partner.password}" required = "true" maxlength = "64" size = "20" /> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <label for = "password_confirm"> Password (Confirm): </label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <input type = "password" id = "password_confirm" name = "password_confirm" value = "${partner.password}" required = "true" maxlength = "64" size = "20" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "4">


                      <p> </p>


                   </td>


                  </tr>


                </table>


                <table style = "padding: 0px 20px">


                  <tr style = "background-color: lightgray">


                    <td style = "border: 1px solid black" colspan = "4">


                      <span> <b> Additional Details </b> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td colspan = "4">


                      <form:hidden path = "updated" /> <br />


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "menu_halal_id"> <span style = "color: blue"> Current Menu (Halal): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <select name = "menu_halal_id" style = "width: 600px"
                        <c:if test = "${partner.role == null || partner.role == '' || partner.role != 'Partner - FSP'}"> disabled </c:if> >

                            <option value = "" <c:if test = "${empty partner.menu_halal}"> selected </c:if> > </option>

                        <c:if test = "${not empty menusHalal}">
                          <c:forEach var = "menuHalal" items = "${menusHalal}">

                            <option value = "${menuHalal.id}" <c:if test = "${not empty partner.menu_halal && partner.menu_halal.id == menuHalal.id}"> selected </c:if> >
                              Day:<c:out value = "${menuHalal.seq_day}"   /> Time:<c:out value = "${menuHalal.seq_time}" /> <c:out value = "${menuHalal.name}" />
                              Frozen:<c:out value = "${menuHalal.frozen}" />      <c:out value = "${menuHalal.status}"   /> </option> 

                          </c:forEach>
                        </c:if>

                      </select> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "menu_veg_id"> <span style = "color: blue"> Current Menu (Vegetarian): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <select name = "menu_veg_id" style = "width: 600px"
                        <c:if test = "${partner.role == null || partner.role == '' || partner.role != 'Partner - FSP'}"> disabled </c:if> >

                            <option value = "" <c:if test = "${empty partner.menu_veg}"> selected </c:if> > </option>

                        <c:if test = "${not empty menusVeg}">
                          <c:forEach var = "menuVeg" items = "${menusVeg}">

                            <option value = "${menuVeg.id}" <c:if test = "${not empty partner.menu_veg && partner.menu_veg.id == menuVeg.id}"> selected </c:if> >
                              Day:<c:out value = "${menuVeg.seq_day}"   /> Time:<c:out value = "${menuVeg.seq_time}" /> <c:out value = "${menuVeg.name}" />
                              Frozen:<c:out value = "${menuVeg.frozen}" />      <c:out value = "${menuVeg.status}"   /> </option> 

                          </c:forEach>
                        </c:if>

                      </select> <br />



                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "menu_soft_id"> <span style = "color: blue"> Current Menu (Soft): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <select name = "menu_soft_id" style = "width: 600px"
                        <c:if test = "${partner.role == null || partner.role == '' || partner.role != 'Partner - FSP'}"> disabled </c:if> >

                            <option value = "" <c:if test = "${empty partner.menu_soft}"> selected </c:if> > </option>

                        <c:if test = "${not empty menusSoft}">
                          <c:forEach var = "menuSoft" items = "${menusSoft}">

                            <option value = "${menuSoft.id}" <c:if test = "${not empty partner.menu_soft && partner.menu_soft.id == menuSoft.id}"> selected </c:if> >
                              Day:<c:out value = "${menuSoft.seq_day}"   /> Time:<c:out value = "${menuSoft.seq_time}" /> <c:out value = "${menuSoft.name}" />
                              Frozen:<c:out value = "${menuSoft.frozen}" />      <c:out value = "${menuSoft.status}"   /> </option> 

                          </c:forEach>
                        </c:if>

                      </select> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "menu_normal_id"> <span style = "color: blue"> Current Menu (Normal): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <select name = "menu_normal_id" style = "width: 600px"
                        <c:if test = "${partner.role == null || partner.role == '' || partner.role != 'Partner - FSP'}"> disabled </c:if> >

                            <option value = "" <c:if test = "${empty partner.menu_normal}"> selected </c:if> > </option>

                        <c:if test = "${not empty menusNormal}">
                          <c:forEach var = "menuNormal" items = "${menusNormal}">

                            <option value = "${menuNormal.id}" <c:if test = "${not empty partner.menu_normal && partner.menu_normal.id == menuNormal.id}"> selected </c:if> >
                              Day:<c:out value = "${menuNormal.seq_day}"   /> Time:<c:out value = "${menuNormal.seq_time}" /> <c:out value = "${menuNormal.name}" />
                              Frozen:<c:out value = "${menuNormal.frozen}" />      <c:out value = "${menuNormal.status}"   /> </option> 

                          </c:forEach>
                        </c:if>

                      </select> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "provider_id"> <span style = "color: blue"> Pickup Location: </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <select name = "provider_id" style = "width: 600px"
                        <c:if test = "${partner.role == null || partner.role == '' || partner.role != 'Partner - FDR'}"> disabled </c:if> >

                            <option value = "" <c:if test = "${empty partner.provider}"> selected </c:if> > </option>

                        <c:if test = "${not empty providers}">
                          <c:forEach var = "provider" items = "${providers}">

                            <option value = "${provider.id}" <c:if test = "${not empty partner.provider && partner.provider.id == provider.id}"> selected </c:if> >
                              <c:out value = "${provider.nric_uen}" /> <c:out value = "${provider.names}" /> <c:out value = "${provider.surname}" />
                              <c:out value = "${provider.status}"   /> </option> 

                          </c:forEach>
                        </c:if>

                      </select> <br />


                    </td>


                  </tr>


                  <tr style = "height: "44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


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


      <div id = "jquery-dialogs">

        <div id = "alert-password" title = "Form Validation"> Password and Password (Confirm) do not match. Please amend then click button again. </div>

      </div>


      <noscript> Error: JavaScript is blocked! </noscript>

      <script src = "/js/jquery.js"    type = "text/javascript"> </script>
      <script src = "/js/jquery-ui.js" type = "text/javascript"> </script>

      <script src = "/js/scripts.js"   type = "text/javascript"> </script>

      <script nonce = "${random-value}">


        /* Migrated event handlers to enable Content Security Policy (CSP) */

        document.getElementById("form").addEventListener("submit", (listener) => validatePassword(event));


        /* Activation of jQuery Widget(s) */

        $(document).ready(function() {

          $("#alert-password").dialog({
            autoOpen: false
          });

        });


      </script>
      

    </div>


  </body>


</html>
