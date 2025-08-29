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


              <a href = "/home">Home</a> > <a href = "/evaluation">Evaluation</a> > <a href = "/evaluation_partners">Partners</a> > <span>Partner</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Evaluation (Partner) </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/evaluation_partners_partner" modelAttribute = "partner">


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


                      <form:label path = "nric_uen"> UEN: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "nric_uen" />

                      <span> <c:out value = "${partner.nric_uen}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "names"> Name(s): </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "names" />

                      <span> <c:out value = "${partner.names}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "role"> Role: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "role" />
                      
                      <span> <c:out value = "${partner.role}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "email"> Email: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "email" />

                      <span> <c:out value = "${partner.email}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "phone"> Phone: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "phone" />

                      <span> <c:out value = "${partner.phone}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "address"> Address: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "address" />

                      <span> <c:out value = "${partner.address}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "unit"> <span style = "color: blue"> Unit: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "unit" />

                      <span> <c:out value = "${partner.unit}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "postal"> Postal: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "postal" />

                      <span> <c:out value = "${partner.postal}" /> </span> <br />


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


                      <form:label path = "notes"> <span style = "color: blue"> Notes: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "notes" maxlength = "255" size = "80" /> <br />


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


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <c:if test = "${partner.menu_halal == null}">
                        <input type = "hidden" id = "menu_halal_id" name = "menu_halal_id" value = "" />
                        <span> - </span> <br />
                      </c:if>

                      <c:if test = "${partner.menu_halal != null}">
                        <input type = "hidden" id = "menu_halal_id" name = "menu_halal_id" value = "${partner.menu_halal.id}" />
                        <span> Day:<c:out value = "${partner.menu_halal.seq_day}"   /> Time:<c:out value = "${partner.menu_halal.seq_time}" /> <c:out value = "${partner.menu_halal.name}" />
                               Frozen:<c:out value = "${partner.menu_halal.frozen}" />      <c:out value = "${partner.menu_halal.status}"   /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "menu_veg_id"> <span style = "color: blue"> Current Menu (Vegetarian): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <c:if test = "${partner.menu_veg == null}">
                        <input type = "hidden" id = "menu_veg_id" name = "menu_veg_id" value = "" />
                        <span> - </span> <br />
                      </c:if>

                      <c:if test = "${partner.menu_veg != null}">
                        <input type = "hidden" id = "menu_veg_id" name = "menu_veg_id" value = "${partner.menu_veg.id}" />
                        <span> Day:<c:out value = "${partner.menu_veg.seq_day}"    /> Time:<c:out value = "${partner.menu_veg.seq_time}" /> <c:out value = "${partner.menu_veg.name}" />
                               Frozen: <c:out value = "${partner.menu_veg.frozen}" />      <c:out value = "${partner.menu_veg.status}"   /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "menu_soft_id"> <span style = "color: blue"> Current Menu (Soft): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <c:if test = "${partner.menu_soft == null}">
                        <input type = "hidden" id = "menu_soft_id" name = "menu_soft_id" value = "" />
                        <span> - </span> <br />
                      </c:if>

                      <c:if test = "${partner.menu_soft != null}">
                        <input type = "hidden" id = "menu_soft_id" name = "menu_soft_id" value = "${partner.menu_soft.id}" />
                        <span> Day:<c:out value = "${partner.menu_soft.seq_day}"   /> Time:<c:out value = "${partner.menu_soft.seq_time}" /> <c:out value = "${partner.menu_soft.name}" />
                               Frozen:<c:out value = "${partner.menu_soft.frozen}" />      <c:out value = "${partner.menu_soft.status}"   /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "menu_normal_id"> <span style = "color: blue"> Current Menu (Normal): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <c:if test = "${partner.menu_normal == null}">
                        <input type = "hidden" id = "menu_normal_id" name = "menu_normal_id" value = "" />
                        <span> - </span> <br />
                      </c:if>

                      <c:if test = "${partner.menu_normal != null}">
                        <input type = "hidden" id = "menu_normal_id" name = "menu_normal_id" value = "${partner.menu_normal.id}" />
                        <span> Day:<c:out value = "${partner.menu_normal.seq_day}"   /> Time:<c:out value = "${partner.menu_normal.seq_time}" /> <c:out value = "${partner.menu_normal.name}" />
                               Frozen:<c:out value = "${partner.menu_normal.frozen}" />      <c:out value = "${partner.menu_normal.status}"   /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "provider_id"> <span style = "color: blue"> Pickup Location: </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <c:if test = "${partner.provider == null}">
                        <input type = "hidden" id = "provider_id" name = "provider_id" value = "" />
                        <span> - </span> <br />
                      </c:if>

                      <c:if test = "${partner.provider != null}">
                        <input type = "hidden" id = "provider_id" name = "provider_id" value = "${partner.provider.id}" />
                        <span> <c:out value = "${partner.provider.nric_uen}" /> <c:out value = "${partner.provider.names}" /> <c:out value = "${partner.provider.surname}" />
                               <c:out value = "${partner.provider.status}"   /> </span> <br />
                      </c:if>


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
