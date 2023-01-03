<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visitor Count</title>
</head>
<body>

	<h2 align="center">Upload the CSV file to get the count of users
		visited your webpage</h2>
	<div align="center">
		<form action="/viewcount" method="POST" enctype="multipart/form-data">
			<input type="file" name="visitorfile" id="visitorfile"> <input
				type="submit" id="submit" value="UPLOAD">
		</form>
	</div>

	<script>
		document.getElementById("submit").onclick = function(e) {
			if (document.getElementById("visitorfile").value == "") {
				e.preventDefault();
				alert("Please select a file.");
				return false;
			}
			var allowedExtensions = /(\.csv)$/i;
			if (!allowedExtensions
					.exec(document.getElementById("visitorfile").value)) {
				alert('Please upload file having extensions .csv only.');
				return false;
			}
		}
	</script>
</body>
</html>