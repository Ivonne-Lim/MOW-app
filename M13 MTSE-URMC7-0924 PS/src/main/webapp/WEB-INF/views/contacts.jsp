<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"            prefix = "c"   %>

<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>


<!DOCTYPE html>


<html lang = "en">


  <head>


    <meta charset = "UTF-8">


    <meta name = "viewport"    content = "initial-scale = 1.0, width = device-width" />

    <meta name = "description" content = "MOW Application" />
    <meta name = "author"      content = "Ivonne Lim" />


    <title> Meals on Wheels (MOW) </title>


    <link href = "/images/logo-mow.png" rel = "icon"       type = "image/x-icon" />


    <link href = "/css/jquery-ui.css"   rel = "stylesheet" type = "text/css" />

    <link href = "/css/styles.css"      rel = "stylesheet" type = "text/css" />


  </head>


  <body>


    <div id = "root">


      <div id = "page-container">


        <div id = "content-wrap">


          <jsp:include page = "header.jsp" />


          <main id = "main-contacts">


            <div id = "breadcrumb">


              <a href = "/home">Home</a> > <span>Contacts</span>


              <br />


            </div>


            <div id = "content">


              <h1> <u> Contacts </u> </h1>


              <br />


              <div id = "sidebar">


                <iframe width = "600" height = "450"
                  src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3988.760036301837!2d103.88991727585685!3d1.3196895986677912!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31da18188d9f92a7%3A0xab3f543103de23b7!2s11%20Eunos%20Rd%208%2C%20Lifelong%20Learning%20Institute%2C%20Singapore%20408601!5e0!3m2!1sen!2ssg!4v1748907974896!5m2!1sen!2ssg"
                  title = "MerryMeal"> </iframe>


              </div>


              <span> <b> <u> MerryMeal </u> </b> </span> <br />


              <br />


              <span> <b> Email: </b> </span> <br />
              <span>     mow.merrymeal@gmail.com </span> <br />


              <br />


              <span> <b> Phone: </b> </span> <br />
              <span>     6324 9730 </span> <br />


              <br />


              <span> <b> Address: </b> </span> <br />
              <span>     11 Eunos Road 8 #07-02 </span> <br />
              <span>     Singapore 408601 <br />


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
