<%@page import="functionality.ProjectManager"%>
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


     <% ProjectManager.getInstance(); %>
<body class="home">
	<%
   		response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   		response.addHeader("Pragma", "no-cache"); 
   		response.addDateHeader ("Expires", 0);
  	%>
	<!-- Fixed navbar -->
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
							
					<%if(request.getSession().getAttribute("user") == null){ %>
						<li><a class="btn" href="SignIn.jsp">SIGN IN / SIGN UP</a></li>
					<%}else{ %>
						<li><a class="btn" href="LogoutServlet">Logout</a></li>
						<li><a class="btn" href="profile.jsp">PROFILE</a></li>
					<%} %>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->

	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="row">
				<h1 class="lead">The Traveler Bulgaria</h1>
				
				<p><a class="btn btn-default btn-lg" href="SectionUnderConstructionPage.html">View Top Destinations</a> 
				<a class="btn btn-action btn-lg" href="AllDestinations.jsp" >View All Destinations</a></p>

			</div>
		</div>
	</header>
	<!-- /Header -->

	<!-- Intro -->
	<div class="container text-center">
		<br> <br>
		<h2 class="thin">The best place to tell people where you've been on your last travels and how awesome your experience was!</h2>
		<p class="text-muted">
			The difference between involvement and commitment is like an eggs-and-ham breakfast:<br> 
			the chicken was involved; the pig was committed. Be truly committed to what you do!
		</p>
	</div>
	<!-- /Intro-->
		
	<!-- Highlights - jumbotron -->
	<div class="jumbotron top-space">
		<div class="container">
			
			<h3 class="text-center thin">Reasons to use our site</h3>
			
			<div class="row">
				<div class="col-md-3 col-sm-6 highlight">
					<div class="h-caption"><h4><i class="fa fa-cogs fa-5"></i>User-powered content</h4></div>
					<div class="h-body text-center">
						<p>All the content in the site is inspired, generated and intended for our users! Our goal is to help you make the perfect Traveler social network and connect with other travelers like you. </p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<div class="h-caption"><h4><i class="fa fa-flash fa-5"></i>Burning calories while having fun</h4></div>
					<div class="h-body text-center">
						<p>Get in your best shape by traveling all over Bulgaria, finding new places, and sharing your experience with your friends! Being fit has never been easier and more enjoyable!</p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<div class="h-caption"><h4><i class="fa fa-heart fa-5"></i>Beautiful destinations</h4></div>
					<div class="h-body text-center">
						<p>Reach out and explore an amazing new world you have never seen before! Explore the most breathtaking destinations and live to tell the story!</p>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<div class="h-caption"><h4><i class="fa fa-smile-o fa-5"></i>Finding new places and friends</h4></div>
					<div class="h-body text-center">
						<p>Bond with people with the same travel interests as yours and keep all your memories of going on new adventures!</p>
					</div>
				</div>
			</div> <!-- /row  -->
		
		</div>
	</div>
	<!-- /Highlights -->

	<!-- container -->
	<div class="container">

		<h2 class="text-center top-space">Frequently Asked Questions</h2>
		<br>

		<div class="row">
			<div class="col-sm-6">
				<h3>What does it cost to sign up?</h3>
				<p>- The registration and all the features in the site are FREE! But registering will cost you about 1 minute and a lifetime of adventures!</p>
			</div>
			<div class="col-sm-6">
				<h3>What can I do when I register?</h3>
				<p>- You can add new destinations, view already created ones and add them to you list of destinations. You can also add friends, message them, comment on your friends' posts and like them. Just don't forget to have fun!</p>
			</div>
		</div> <!-- /row -->

		<div class="row">
			<div class="col-sm-6">
				<h3>Can I invite a friend to join from another social network?</h3>
				<p>- The feature for logging in from other social medias or inviting a friend there is currently in development. But while you wait, you can like our Facebook page and tell your friends about us! Let's  add more people to our community!</p>
			</div>
			<div class="col-sm-6">
				<h3>Can you tell me more about the Site?</h3>
				<p>- You can click on the About icon on the bottom of the page to learn more about the project and all the information you need! Or you can write to us by clicking on the Contact icon! We'll be more than happy to answer you questions!</p>
			</div>
		</div> <!-- /row -->

		<div class="jumbotron top-space">
			<h4>The Traveler Bulgaria supports the Keep Inspiring Initiative. To learn more about it click on the button!</h4>
     		<p class="text-right"><a class="btn btn-primary btn-large" href="SectionUnderConstructionPage.html">Learn more »</a></p>
  		</div>

</div>	<!-- /container -->
	
	<!-- Social links. @TODO: replace by link/instructions in template -->
	<section id="social">
		<div class="container">
			<div class="wrapper clearfix">
				<!-- AddThis Button BEGIN -->
				<div class="addthis_toolbox addthis_default_style">
				<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
				<a class="addthis_button_tweet"></a>
				<a class="addthis_button_linkedin_counter"></a>
				<a class="addthis_button_google_plusone" g:plusone:size="medium"></a>
				</div>
				<!-- AddThis Button END -->
			</div>
		</div>
	</section>
	<!-- /social links -->


	<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">
					
					<div class="col-md-3 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p><br>
								<a href="mailto:#">thetravellerbulgaria@gmail.com</a><br>
								<br>
								Infinity Tower, Sofia City, Bulgaria
							</p>	
						</div>
					</div>

					<div class="col-md-3 widget">
						<h3 class="widget-title">Follow us</h3>
						<div class="widget-body">
							<p class="follow-me-icons">
								<a href=""><i class="fa fa-twitter fa-2"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a>
								<a href=""><i class="fa fa-github fa-2"></i></a>
								<a href="https://www.facebook.com/TheTravellerBulgaria/"><i class="fa fa-facebook fa-2"></i></a>
							</p>	
						</div>
					</div>

					<div class="col-md-6 widget">
						<h3 class="widget-title">Our motto</h3>
						<div class="widget-body">
							<p>"The whole object of travel is not to set foot on foreign land; it is to set foot on one's own country as on a foreign land." - G.K. Chesterton</p>
							<p>So keep moving! But don't forget to take time to stop and smell the roses!</p>
						</div>
					</div>

				</div> <!-- /row of widgets -->
			</div>
		</div>

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
</body>
</html>