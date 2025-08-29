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


              <a href = "/home">Home</a> > <a href = "/evaluation">Evaluation</a> > <a href = "/evaluation_members">Members</a> > <span>Member</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Evaluation (Member) </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/evaluation_members_member" modelAttribute = "member">


                <table style = "float: left">


                  <tr style = "background-color: lightgray">


                    <td style = "border: 1px solid black" colspan = "4">


                      <span> <b> Member Details </b> </span> <br />


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
                      
                      <span> <c:out value = "${member.id}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "nric_uen"> NRIC: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "nric_uen" />

                      <span> <c:out value = "${member.nric_uen}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "names"> Name(s): </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "names" />

                      <span> <c:out value = "${member.names}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "role"> Role: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "role" value = "Member" />
                      
                      <span> Member </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "email"> Email: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "email" />

                      <span> <c:out value = "${member.email}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "phone"> Phone: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "phone" />

                      <span> <c:out value = "${member.phone}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "address"> Address: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "address" />

                      <span> <c:out value = "${member.address}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "unit"> <span style = "color: blue"> Unit: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "unit" />

                      <span> <c:out value = "${member.unit}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "postal"> Postal: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "postal" />

                      <span> <c:out value = "${member.postal}" /> </span> <br />


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


                      <input type = "password" id = "password" name = "password" value = "${member.password}" required = "true" maxlength = "64" size = "20" /> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <label for = "password_confirm"> Password (Confirm): </label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <input type = "password" id = "password_confirm" name = "password_confirm" value = "${member.password}" required = "true" maxlength = "64" size = "20" /> <br />


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


                      <form:hidden path = "surname" />

                      <span> <c:out value = "${member.surname}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "dob"> Date of Birth: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "dob" />

                      <span> <c:out value = "${member.dob}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "disabilities"> Disabilities: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <form:hidden path = "disabilities" />

                      <span> <c:out value = "${member.disabilities}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "income"> Monthly Income (SGD): </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <form:hidden path = "income" />

                      <span> <c:out value = "${member.income}" /> </span> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <form:label path = "household"> Household Count: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <form:hidden path = "household" />

                      <span> <c:out value = "${member.household}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "file1"> <span style = "color: blue"> Attachment (NRIC): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <input  type = "hidden" id = "file1" name = "file1" value = "${member.file1}" />

                      <span id = "link1">
                      <c:if test = "${not empty member.file1}">
                        Server: <a href = "http://${upload_url}/assets/${member.file1}" target = "blank"> <c:out value = "${member.file1}" /> </a>
                      </c:if>
                      </span>

                      <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "file2"> <span style = "color: blue"> Attachment (Medical): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <input  type = "hidden" id = "file2" name = "file2" value = "${member.file2}" />

                      <span id = "link2">
                      <c:if test = "${not empty member.file2}">
                        Server: <a href = "http://${upload_url}/assets/${member.file2}" target = "blank"> <c:out value = "${member.file2}" /> </a>
                      </c:if>
                      </span>

                      <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "file3"> <span style = "color: blue"> Attachment (Financial): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <input  type = "hidden" id = "file3" name = "file3" value = "${member.file3}" />

                      <span id = "link3">
                      <c:if test = "${not empty member.file3}">
                        Server: <a href = "http://${upload_url}/assets/${member.file3}" target = "blank"> <c:out value = "${member.file3}" /> </a>
                      </c:if>
                      </span>

                      <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "diet"> Diet: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "diet" />

                      <span> <c:out value = "${member.diet}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "allergies"> <span style = "color: blue"> Allergies: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black; width: 600px;" colspan = "3">


                      <form:hidden path = "allergies" />

                      <span> <c:out value = "${member.allergies}" /> </span> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "scheduled"> <span style = "color: blue"> Next Evaluation: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <form:input type = "datetime-local" path = "scheduled" maxlength = "20" size = "20" /> <br />


                    <td>


                      <p> </p>


                    </td>


                    <td>


                      <div style = "display: flex; gap: 20px;">

                        <input type = "submit" name = "submit" value = "Caregivers" style = "width: 100px" formethod = "post" formaction = "/evaluation_members_member_caregivers" />

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
