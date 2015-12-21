function addFeature() {
	var countinput = document.getElementById("featureCount");
	var count = parseInt(countinput.value);
	var inputField = document.getElementById("f"+(count+1));
	if(inputField.value != "")
	{
		countinput.value = parseInt(countinput.value) + 1;
		count++;
		var newDiv = "";
		var fname;
		for (var i = 1; i <= count; i++) {
			fname = 'f' + i;
			newDiv = newDiv
					+ "<div class='row'><div class='col-sm-10'><input type='text' class='form-control' readonly name="
					+ fname + " id=" + fname + " value='"
					+ document.getElementById("f" + i).value + "'></div><div class='com-sm-2'>"
					+ "<button type='button' class='btn btn-warning' id='b"+i+"' onclick='removeFeature("+i+")'>" +
							"<span class='glyphicon glyphicon-remove'></span></button></div></div><br/>";
		}
		count++;
		fname = 'f' + count;
		newDiv = newDiv
				+ "<input type='text' class='form-control' placeholder='Feature Name' name="
				+ fname + " id=" + fname + " value=''>";
		var div = document.getElementById("featureDiv");
		div.innerHTML = newDiv;
	}
}

function removeFeature(id)
{
	var element = document.getElementById("f"+id);
	element.parentNode.removeChild(element);
	element = document.getElementById("b"+id);
	element.parentNode.removeChild(element);
	var countinput = document.getElementById("featureCount");
	var count = parseInt(countinput.value);
	for(var i=id+1;i<=count;i++)
	{
		document.getElementById("f"+i).name = "f"+(i-1);
		document.getElementById("f"+i).id = "f"+(i-1);
		document.getElementById("b"+i).setAttribute('onclick',"removeFeature("+(i-1)+")");
		document.getElementById("b"+i).id = "b"+(i-1);
	}
	document.getElementById("f"+(count+1)).name = "f"+(count);
	document.getElementById("f"+(count+1)).id = "f"+(count);
	document.getElementById("featureCount").value = parseInt(document.getElementById("featureCount").value) -1 ; 
}

function changeRoom(id,value)
{
	value = parseInt(value);
	var input = document.getElementById(id);
	numOfRooms = parseInt(input.value);
	maxValue = input.max;
	var newValue = numOfRooms+value;
	if(newValue > 0 && newValue <= maxValue)
		input.value = newValue;
}




