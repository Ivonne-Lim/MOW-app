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


  </head>


  <body>


    <div id = "root">


      <div id = "page-container">


        <div id = "content-wrap">


          <jsp:include page = "header.jsp" />


          <main id = "main">


            <div id = "breadcrumb">


              <a href = "/home">Home</a> > <a href = "/registration">Registration</a> > <a href = "/registration_member?username=${member.email}">Member</a> > <a href = "/registration_member_caregivers?member_id=${member.id}">Caregivers</a> > <span>Caregiver</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Registration (Caregiver) </u> </h1>


              <br />


              <form name = "retrieve" method = "get" action = "/registration_member_caregivers_caregiver_retrieval">


                <div style = "display: flex; gap: 20px;">
                
                  <span> NRIC: </span>

                  <input type = "text"   name = "nric_uen" required = "true" maxlength = "10" size = "10" pattern = "^[A-Za-z0-9]{0,10}$" />

                  <input type = "hidden" name = "member_id" value = "${member.id}" />
                  <input type = "submit" name = "submit" value = "Retrieve" style = "width: 100px" /> <br />

                </div>


              </form>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/registration_member_caregivers_caregiver" modelAttribute = "caregiver">


                <table style = "float: left">


                  <tr style = "background-color: lightgray">


                    <td style = "border: 1px solid black" colspan = "4">


                      <span> <b> Caregiver Details </b> </span> <br />


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
                      
                      <span> <c:out value = "${caregiver.id}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "nric_uen"> NRIC: </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <input type = "text" id = "nric_uen" name = "nric_uen" value = "${caregiver.nric_uen}" required = "true" maxlength = "10" size = "10" pattern = "^[A-Za-z0-9]{0,10}$" />


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


                      <form:hidden path = "role" value = "Caregiver" />
                      
                      <span> Caregiver </span> <br />


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


                      <input type = "text" id = "postal" name = "postal" value = "${caregiver.postal}" required = "true" maxlength = "6" size = "6" pattern = "^[0-9]{0,6}$" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "status"> Status: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${caregiver.status == null || caregiver.status == ''}">
                        <form:hidden path = "status" value = "Pending" />
                        <span> Pending </span> <br />
                      </c:if>

                      <c:if test = "${caregiver.status != null && caregiver.status != ''}">
                        <form:hidden path = "status" />
                        <span> <c:out value = "${caregiver.status}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "notes"> <span style = "color: blue"> Notes: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "notes" />

                      <c:if test = "${caregiver.notes == null || caregiver.notes == ''}"> <span> - </span> <br /> </c:if>

                      <c:if test = "${caregiver.notes != null && caregiver.notes != ''}"> <span> <c:out value = "${caregiver.notes}" /> </span> <br /> </c:if>


                    </td>


                  </tr>


                  <tr style = "height: 44px">


                    <td colspan = "4">


                      <p> </p>


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


                      <input type = "hidden" id = "member_id" name = "member_id" value = "${member.id}" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "gender"> Gender: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:radiobutton path = "gender" label = "Male"   value = "Male"   />
                      <form:radiobutton path = "gender" label = "Female" value = "Female" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "surname"> Surname: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <form:input type = "text" path = "surname" required = "true" maxlength = "40" size = "40" /> <br />


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


              <c:if test = "${not empty error}">
              <p> <span style = "color: red"> Error: </span> <c:out value = "${error}" /> </p>
              </c:if>


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
