function buildTable(json) {
	if (!json || json.length == 0) {
		return "A non-parameter error was detected<br/><br/>";
	}
	var table = "<table><tr><td><b>Parameter</b></td><td><b>Value</b></td><td><b>Error Message</b></td></tr>";

	for (var i = 0; i < json.length; i++) {
		table = table + "<tr><td style='vertical-align:top;'>"
				+ json[i].display + "</td><td style='vertical-align:top;'>"
				+ prettifyValue(json[i].value, json[i].samplePoints)
				+ "</td><td style='vertical-align:top;'>"
				+ json[i].error + "</td></tr>";
	}
	table = table + "</table>";
	return table;
}

function prettifyValue(value, points) {
	if (points.length <= 0) {
		return value;
	}
	var offset = 0;
	var start = "";
	var mid = "";
	var end = "";
	for (var i = 0; i < points.length; i++) {
		var startPos = parseInt(points[i].start) + parseInt(offset);
		var endPos = parseInt(points[i].end) + parseInt(offset);
		var start = value.substring(0, startPos);
		var mid = value.substring(startPos, endPos);
		var end = value.substring(endPos, value.length + offset);
		value = start + "<mark>" + mid + "</mark>" + end;
		offset += "<mark></mark>".length;
	}
	return value;
}
