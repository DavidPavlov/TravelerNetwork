<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <link rel="stylesheet" href="PagesResourses/common-files/css/bootstrap.min.css">
  <link rel="stylesheet" href="PagesResourses/common-files/css/fonts-v3.css">
  <link rel="stylesheet" href="PagesResourses/common-files/css/base-v4.css">
  <link rel="stylesheet" href="PagesResourses/styles/properties-2643c23c-dca1-d47a-2b01-e417a58b4b82.css">
</head>

<body>

  <div id="_1">

    <div id="_2">

      <div id="_3" class="nm-label">
        <span id="_4">
            <br/>
        </span>
      </div>
    </div>

    <div id="_5" class="nm-label">
      <p id="_6">
        Популярни места
        <br/>
      </p>
    </div>

    <i id="_7"></i>

    

    <i id="_17" data-pagelink="Homepage"></i>

    <div id="_18" class="nm-label">
      <span id="_19">
             Панорама „Плевенска епопея 1877 г.” <br/>
        </span>
    </div>

    <div id="_20">

      <div id="_21" class="nm-label">
        <span id="_22">
            Открийте нови места и споделете изживяването си с останалите<br/>
        </span>
      </div>

      <div id="_23" class="nm-label">
        <span id="_24">
            The Traveller Bulgaria<br/>
        </span>
      </div>

      <div id="_25" class="nm-label">
        <span id="_26">
            Първата по рода си социална мрежа за пътешественици<br/>
        </span>
      </div>

      <div id="_27">
        <svg id="_28">
          <line id="_29" x1="0" x2="492" y1="" y2="0" />
        </svg>
      </div>
    </div>

    <div id="_30">

      <i id="_31"></i>

      <i id="_32"></i>
    </div>

    <i id="_33"></i>





     <div id="_34">

      <div id="_35" class="btn-group">
      <%if(request.getSession().getAttribute("user") == null){ %>
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="_36">
          Профил <span class="caret"></span>
        </button>
        <ul id="_37" class="dropdown-menu" role="menu">
          <li href="#">

            <div id="_38" data-pagelink="RegistrationPage" class="nm-label">
              <a id="_39" href="#">
            Нова регистрация<br/>
        </a>
            </div>
          </li>
          <li href="#">

            <div id="_40" data-pagelink="LoginPage" class="nm-label">
              <a id="_41" href="#">
            Влизане в профил<br/>
        </a>
            </div>
          </li>
          <li class="divider">

          </li>
          
        </ul>
        <%}else{ %>
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="_36">
          Профил <span class="caret"></span>
          </button>
          <ul id="_37" class="dropdown-menu" role="menu">
          <li href="#">

            <div id="_38" data-pagelink="ProfilePage.jsp" class="nm-label">
              <a id="_39" href="#">
            Профил<br/>
        </a>
            </div>
          </li>
          <li href="#">

            <div id="_40" data-pagelink="AddDestinationPage.jsp" class="nm-label">
              <a id="_41" href="#">
            Добавяне на място<br/>
        </a>
            </div>
          </li>
        
        <%} %>
      </div>

      <i id="_44"></i>
    </div>





    <div id="_45">
      <div id="_46">
        <div class="form-group" id="_47">
          <input type="text" id="_48" placeholder="Търсене на абонат..." class="form-control" />

          <i id="_49">
        
    </i>
        </div>
      </div>

      <button id="_50" class="btn btn-default">
        Търсене

      </button>
      <div id="_51">
        <div class="form-group" id="_52">
          <input type="text" id="_53" placeholder="Търсене на обект..." class="form-control" />

          <i id="_54">
        
    </i>
        </div>
      </div>

      <button id="_55" class="btn btn-default">
        Търсене

      </button>
    </div>

  <script type="text/javascript" src="PagesResourses/common-files/js/require.min.js" data-main="PagesResourses/scripts/startup"></script>
</body>

</html>