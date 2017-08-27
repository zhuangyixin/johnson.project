<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Theme -->
<script src="bootstrap/css/bootstrap-theme.min.css"></script>
<!-- jQuery -->
<script src="jquery/jquery.min.js"></script>
<!-- Bootstrap JavaScript-->
<script src="bootstrap/js/bootstrap.min.js"></script>
<title>File download and upload</title>
</head>
<body>
	<div class="container col-md-8 col-md-offset-2">
		<div class="row">
			<h1>File download and upload</h1>
			<hr>
		</div>
		<div class="row">
			<form role='form' method="POST" class="form-inline"
				enctype="multipart/form-data">
				<div class="form-group">
					<label class="src-only">Download:
						<div class="btn-group">
							<input type="submit" value="Request Entity"
								formaction="file/request-entity" class="btn btn-primary" /> <input
								type="submit" value="Servlet Download"
								formaction="file/servlet-download" class="btn btn-primary" />
						</div>
					</label>
				</div>
			</form>
		</div>
		<div class="row">
			<form role='form' method="POST" class="form-inline"
				enctype="multipart/form-data">
				<div class="form-group">
					<label class="src-only">Upload:
					<input type="file" name="multipartfile" class="file form-control" />
						<div class="btn-group">
							<input type="submit" class="btn btn-primary"
								value="Multipart File" 
								formaction="file/multipart-file" /> 
							<input type="submit" class="btn btn-primary" 
								value="Servlet Upload"
								formaction="file/servlet-upload" />
						</div>
					</label>
				</div>
			</form>
		</div>
	</div>
</body>
</html>