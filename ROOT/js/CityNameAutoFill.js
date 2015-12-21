function loadJSON()
{
	var inputField = document.getElementById("cityName");
	var data_file = "cityName?cityName="+inputField.value;
	var http_request = new XMLHttpRequest();
	try{
	   http_request = new XMLHttpRequest();
	}
	catch (e)
	{
	   try{
	      http_request = new ActiveXObject("Msxml2.XMLHTTP");
			
	   }
	   catch (e) 
	   {
	      try
	      {
	         http_request = new ActiveXObject("Microsoft.XMLHTTP");
	      }
	      catch (e)
	      {
	    	  return false;
	      }
	   }
	}
	http_request.onreadystatechange = function()
	{
	   if (http_request.readyState == 4  )
	   {
		   var jsonObj = JSON.parse(http_request.responseText);
		   var options;
		   for(var i = 0; i < jsonObj.length; i++)
			    options += '<option value="'+jsonObj[i]+'" />';
		   document.getElementById('cityNameList').innerHTML = options;

	   }
	}
	http_request.open("GET", data_file, true);
	http_request.send();
}