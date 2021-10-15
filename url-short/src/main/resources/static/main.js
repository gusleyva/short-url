

$(document).ready(function() {
	$("button").click(function() {
		$.ajax({
			type : 'POST',
			url : 'http://localhost:8080/api/shorturl/v1/shortUrl',
			data : JSON.stringify({
				"fullUrl" : $("#urlinput").val(),
                "webHook" : $("#webhook").val(),
                "expiresDate" : $("#expirationdate").val()
			}),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#shorturltext").val(data.shortUrl);
                document.getElementById('webhook').readOnly = true;
                document.getElementById('expirationdate').readOnly = true;
			}
		});
	});
});