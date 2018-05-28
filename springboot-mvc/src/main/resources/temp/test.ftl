<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	<script src="https://cdn.bootcss.com/jquery/2.1.0/jquery.min.js"></script>
	<script type="text/javascript">
		function crosRequest() {
			$.ajax({
				url: 'http://localhost:9999/crosRequest',
				type: 'POST',
				dataType: 'json',
				success: function (data) {
				}
			});
		}
	</script>
</head>
<body>
<button onclick="crosRequest()">请求跨域资源</button>
</body>
</html>