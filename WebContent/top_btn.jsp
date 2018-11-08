<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous">
</script>

<link rel="stylesheet" type="text/css" href="/css/top_btn.css" />

</head>
<body>

	<a href="#" id="back_to_top"> <img src="/img/top.png" />
	</a>
	<script>
		$(function() {
			$(window).scroll(function() {
				if ($(this).scrollTop() > 500) {
					$('#back_to_top').fadeIn();
				} else {
					$('#back_to_top').fadeOut();
				}
			});

			$("#back_to_top").click(function() {
				$('html, body').animate({
					scrollTop : 0
				}, 400);
				return false;
			});
		});
	</script>


</body>
</html>