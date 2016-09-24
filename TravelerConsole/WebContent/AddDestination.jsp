<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="David & Yasen">
	
	<title>The Traveler Bulgaria</title>

	<link rel="shortcut icon" href="assets/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body onLoad="initialize()">
	<!-- Fixed navbar -->
	<%
   		response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   		response.addHeader("Pragma", "no-cache"); 
   		response.addDateHeader ("Expires", 0);
   		
  	%>
	<%@ include file="CheckIfLoggedIn.jsp" %>
	
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="index.jsp"><img src="assets/images/logo4.png" alt="The Traveler Bulgaria"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li><a href="index.jsp">Home</a></li>					
					<li><a href="AllDestinations.jsp">Destinations</a></li>
					<li><a class="btn" href="LogoutServlet">Logout</a></li>
					<li><a class="btn" href="profile.jsp">PROFILE</a></li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="index.jsp">Home</a></li>
			<li class="active">Add Destination</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-sm-9 maincontent">
				<header class="page-header">
					<h1 class="page-title">New Destination</h1>
				</header>
				
				<p>
					Share your experience and photos of a destination you have visited.
				</p>
				<br>
					<form action="AddDestinationServlet" method="POST" enctype="multipart/form-data">
						<div class="row">
							<div class="col-sm-4">
								<input name="name" class="form-control" type="text" placeholder="Name">
							</div>
							<div class="col-sm-4">
								<input id="lngbox" name="long" class="form-control" type="text" placeholder="Longitude">
							</div>
							<div class="col-sm-4">
								<input id="latbox" name="lat" class="form-control" type="text" placeholder="Latitude">
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-12">
								<textarea name="description" placeholder="Type your experience here" class="form-control" rows="9"></textarea>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-sm-6">
								<input name="picture" type="file" class="form-control" placeholder="Insert Picture">
							</div>
							<div class="col-sm-6 text-right">
								<input class="btn btn-action" type="submit" value="Add Destination">
							</div>
						</div>
						<div class="row" id="map" style="width:100% height:100%"></div>
					</form>
					
			</article>
			<!-- /Article -->
			

		</div>
	</div>	<!-- /container -->
	
	

	<footer id="footer" class="top-space">
			<div class="footer2">
            <div class="container">
                <div class="row">
                    
                    <div class="col-md-6 widget">
                        <div class="widget-body">
                            <p class="simplenav">
                                <a href="#">Home</a> | 
                                <a href="SectionUnderConstructionPage.html">About</a> |
                                <a href="SectionUnderConstructionPage.html">Sidebar</a> |
                                <a href="SectionUnderConstructionPage.html">Contact</a> |
                                <b><a href="signup.html">Sign up</a></b>
                            </p>
                        </div>
                    </div>

                    <div class="col-md-6 widget">
                        <div class="widget-body">
                            <p class="text-right">
                                Copyright &copy; 2016, The Traveler Bulgaria. Designed by David & Yasen</a> 
                            </p>
                        </div>
                    </div>

                </div> <!-- /row of widgets -->
            </div>
        </div>
		</footer>	
		




	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
	
	<!-- Google Maps -->
	
	

</body>

</html>

<cfoutput>
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBQKyIgPewrgRCgagA1sDItFSRZh5hZlL4&sensor=false"></script>
</cfoutput>

<script type="text/javascript">
//<![CDATA[

    // global "map" variable
    var map = null;
    var marker = null;

    // popup window for pin, if in use
    var infowindow = new google.maps.InfoWindow({ 
        size: new google.maps.Size(150,50)
        });

    // A function to create the marker and set up the event window function 
    function createMarker(latlng, name, html) {

    var contentString = html;

    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        zIndex: Math.round(latlng.lat()*-100000)<<5
        });

    google.maps.event.addListener(marker, 'click', function() {
        infowindow.setContent(contentString); 
        infowindow.open(map,marker);
        });

    google.maps.event.trigger(marker, 'click');    
    return marker;

}

function initialize() {

    
    var myLatlng = new google.maps.LatLng(42.70,23.33);

    
    var myOptions = {
        zoom: 8,
        center: myLatlng,
        mapTypeControl: true,
        mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
        navigationControl: true,
    }

    map = new google.maps.Map(document.getElementById("map"), myOptions);

    
      
    marker = new google.maps.Marker({
      position: myLatlng,
      map: map,
      
      
    });

    
    formlat = document.getElementById("latbox").value = myLatlng.lat();
    formlng = document.getElementById("lngbox").value = myLatlng.lng();

    
    google.maps.event.addListener(map, 'click', function() {
        infowindow.close();
        });

   
    google.maps.event.addListener(map, 'click', function(event) {
        
         if (marker) {
            marker.setMap(null);
            marker = null;
         }        

       
        var myLatLng = event.latLng ;
       
        marker = new google.maps.Marker({   
            position: myLatLng,
            map: map,       
           
        });
            marker.setMap(map);
        
        formlat = document.getElementById("latbox").value = event.latLng.lat();
        formlng = document.getElementById("lngbox").value = event.latLng.lng();

    });

}
//]]>

</script> 