function callApi(METHOD,URL,DATA,SUCCESS)
{
 var xhttp=new XMLHttpRequest();
 xhttp.open(METHOD,URL,true)
 xhttp.setRequestHeader('Content-Type','application/json');
 xhttp.onreadystatechange=function()
 {
  if(this.readyState == 4)
  {
   if(this.status == 200)
    SUCCESS(this.responseText);
   else
    alert("404: Service unavailable"); 
   
  }
  
 };
 xhttp.send(DATA);
 
}
function getCaptcha()
{
 var url="http://localhost:8080/captcha/cap/5";
 callApi("GET",url,"",loadCaptcha);
}
function loadCaptcha(res)
{
 IM.src="data:image/png;base64,"+res;
}