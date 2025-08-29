<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"    %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"             prefix = "fmt"  %>

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


              <a href = "/home">Home</a> > <a href = "/registration">Registration</a> > <span>Member</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Registration (Member) </u> </h1>


              <br />


              <form:form id = "form" name = "form" method = "post" action = "/registration_member" modelAttribute = "member">


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


                      <label for = "nric_uen"> NRIC: </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <input type = "text" id = "nric_uen" name = "nric_uen" value = "${member.nric_uen}" required = "true" maxlength = "10" size = "10" pattern = "^[A-Za-z0-9]{0,10}$" /> <br />


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


                      <form:hidden path = "role" value = "Member" />
                      
                      <span> Member </span> <br />


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


                      <input type = "text" id = "postal" name = "postal" value = "${member.postal}" required = "true" maxlength = "6" size = "6" pattern = "^[0-9]{0,6}$" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "status"> Status: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <c:if test = "${member.status == null || member.status == ''}">
                        <form:hidden path = "status" value = "Pending" />
                        <span> Pending </span> <br />
                      </c:if>

                      <c:if test = "${member.status != null && member.status != ''}">
                        <form:hidden path = "status" />
                        <span> <c:out value = "${member.status}" /> </span> <br />
                      </c:if>


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "notes"> <span style = "color: blue"> Notes: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:hidden path = "notes" />

                      <c:if test = "${member.notes == null || member.notes == ''}"> <span> - </span> <br /> </c:if>

                      <c:if test = "${member.notes != null && member.notes != ''}"> <span> <c:out value = "${member.notes}" /> </span> <br /> </c:if>


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


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "surname" required = "true" maxlength = "40" size = "40" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "dob"> Date of Birth: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "date" path = "dob" required = "true" maxlength = "10" size = "10" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "disabilities"> Disabilities: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "disabilities" required = "true" maxlength = "255" size = "80" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "income"> Monthly Income (SGD): </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <form:input type = "number" path = "income" required = "true" maxlength = "6" size = "6" min = "0" max = "999999" /> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <form:label path = "household"> Household Count: </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <form:input type = "number" path = "household" required = "true" maxlength = "2" size = "6" min = "1" max = "99" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <label for = "file1"> <span style = "color: blue"> Attachment (NRIC): </span> </label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <button type = "button" id = "reset1"> Reset File </button>

                      <input  type = "file"   id = "upload1" />

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


                      <button type = "button" id = "reset2"> Reset File </button>

                      <input  type = "file"   id = "upload2" />

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


                      <button type = "button" id = "reset3"> Reset File </button>

                      <input  type = "file"   id = "upload3" />

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


                      <form:select path = "diet">
                        <form:option label = "Halal"      value = "Halal"      />
                        <form:option label = "Vegetarian" value = "Vegetarian" />
                        <form:option label = "Soft"       value = "Soft"       />
                        <form:option label = "Normal"     value = "Normal"     />
                      </form:select> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "allergies"> <span style = "color: blue"> Allergies: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black" colspan = "3">


                      <form:input type = "text" path = "allergies" maxlength = "255" size = "80" /> <br />


                    </td>


                  </tr>


                  <tr>


                    <td style = "border: 1px solid black">


                      <form:label path = "scheduled"> <span style = "color: blue"> Next Evaluation: </span> </form:label> <br />


                    </td>


                    <td style = "border: 1px solid black">


                      <form:hidden path = "scheduled" />

                      <c:if test = "${member.scheduled == null || member.scheduled == ''}"> <span> - </span> <br /> </c:if>

                      <c:if test = "${member.scheduled != null && member.scheduled != ''}"> <span>
                        <fmt:parseDate  value = "${member.scheduled}" pattern = "yyyy-MM-dd'T'HH:mm" var = "parseScheduled" type = "both" />
                        <fmt:formatDate value = "${parseScheduled}"   pattern = "yyyy-MM-dd HH:mm" /> </span> <br /> </c:if>


                    <td>


                      <p> </p>


                    </td>


                    <td>


                      <div style = "display: flex; gap: 20px;">

                        <input type = "submit" name = "submit" value = "Caregivers" style = "width: 100px" formethod = "post" formaction = "/registration_member_caregivers" />

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

        <div id = "alert-upload"   title = "File Upload"> Upload failed. </div>

      </div>


      <noscript> Error: JavaScript is blocked! </noscript>

      <script src = "/js/jquery.js"    type = "text/javascript"> </script>
      <script src = "/js/jquery-ui.js" type = "text/javascript"> </script>

      <script src = "/js/scripts.js"   type = "text/javascript"> </script>

      <script nonce = "${random-value}">


        /* Migrated event handlers to enable Content Security Policy (CSP) */

        document.getElementById("form").addEventListener("submit", (listener) => validatePassword(event));

        document.getElementById("reset1").addEventListener("click", (listener) => resetFile('upload1','file1','link1'));
        document.getElementById("reset2").addEventListener("click", (listener) => resetFile('upload2','file2','link2'));
        document.getElementById("reset3").addEventListener("click", (listener) => resetFile('upload3','file3','link3'));

        document.getElementById("upload1").addEventListener("change", (listener) => uploadFile('upload1','file1'));
        document.getElementById("upload2").addEventListener("change", (listener) => uploadFile('upload2','file2'));
        document.getElementById("upload3").addEventListener("change", (listener) => uploadFile('upload3','file3'));


        /* Activation of jQuery Widget(s) */

        $(document).ready(function() {

          $("#alert-password").dialog({
            autoOpen: false
          });

          $("#alert-upload").dialog({
            autoOpen: false
          });

        });


        /* Remove 'upload' file and reset 'file' parameter */

        function resetFile(upload, file, link) {

          const uploadInput  = document.getElementById(upload);
          const fileInput    = document.getElementById(file);
          const linkSpan     = document.getElementById(link);

          uploadInput.value  = null;
          fileInput.value    = null;
          linkSpan.innerHTML = "";

        }


        /* Fetch 'upload' file to server and set 'file' parameter */

        async function uploadFile(upload, file) {

          const uploadInput = document.getElementById(upload);

          if (uploadInput.files[0]) {

            const formData = new FormData();

            // Note: Added putting current date and time prefix to filename so prevent overriding other's file.
            
            const currentDateTime = new Date();

            var currentYear   = currentDateTime.getFullYear();
            var currentMonth  = currentDateTime.getMonth() + 1;
            var currentDay    = currentDateTime.getDate();
            var currentHour   = currentDateTime.getHours();
            var currentMinute = currentDateTime.getMinutes();
            var currentSecond = currentDateTime.getSeconds();

            if (currentMonth.toString().length  == 1) {
              currentMonth  = '0' + currentMonth;
            }
            if (currentDay.toString().length    == 1) {
              currentDay    = '0' + currentDay;
            }
            if (currentHour.toString().length   == 1) {
              currentHour   = '0' + currentHour;
            }
            if (currentMinute.toString().length == 1) {
              currentMinute = '0' + currentMinute;
            }
            if (currentSecond.toString().length == 1) {
              currentSecond = '0' + currentSecond;
            }

            formData.append('file', uploadInput.files[0],
                            currentYear + currentMonth  + currentDay +
                            currentHour + currentMinute + currentSecond +
                            ' - ' + uploadInput.files[0].name);

            try {

              const response = await fetch('https://${upload_url}/file-upload', {
                                             method: 'POST',
                                             body:   formData,
                                             header: {
                                               'content-type': 'multipart/form-data'
                                             }
                                           });
              if (response.ok) {

                const fileInput = document.getElementById(file);

                fileInput.value = currentYear + currentMonth  + currentDay
                                + currentHour + currentMinute + currentSecond
                                + ' - ' + uploadInput.files[0].name;

              } else {

                $("#alert-upload").dialog("open");
                  // alert('Upload failed.');
                  // Note: Switched to jQuery widget

              }

            } catch (error) {

              $("#alert-upload").dialog("open");
                // alert('Upload failed.');
                // Note: Switched to jQuery widget

            }

          }

        }


      </script>


    </div>


  </body>


</html>
