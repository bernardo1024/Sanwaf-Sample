var _table_ = document.createElement('table');
var _tr_ = document.createElement('tr');
var _th_ = document.createElement('th');
var _td_ = document.createElement('td');

function buildTable(json){
	var table = _table_.cloneNode();
	var columns = addColHeaders(json, table);
	for(var i = 0, maxi = json.length; i < maxi; ++i){
		var tr = _tr_.cloneNode();
		for(var j = 0, maxj = columns.length; j < maxj; ++j) {
			var td = _td_.cloneNode(); 
			cellValue = json[i][columns[j]];
			td.appendChild(document.createTextNode(json[i][columns[j]] || ''));
			tr.appendChild(td);
		}
		table.appendChild(tr);
	}
	return table;
}

function addColHeaders(json, table){
	var columnSet = []
	var tr = _tr_.cloneNode(false);
	for(var i = 0, l = json.length; i < l; i++){
		for(var key in json[i]){
			if(json[i].hasOwnProperty(key) && columnSet.indexOf(key) === -1){
				columnSet.push(key);
				var th = _th_.cloneNode(false);
				th.appendChild(document.createTextNode(key));
				tr.appendChild(th);
			}
		}
	}
	table.appendChild(tr);
	return columnSet;
}
