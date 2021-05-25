function checkUserid(){
	var ueridObj=document.getElementById("floatingInput");
	var floatingInput=trim(ueridObj.value);
	var useridRegex= /^\w{5,50}$/;
	var msg ="   <a style='width:12px'>X</a>";
	if(!floatingInput)
		msg ="<font color='red'>帳號不能為空！</font>";
	else if(!useridRegex.test(floatingInput))
		msg ="<font color='red'>帳號長度不對！</font>";
	var span = document.getElementById("useridSpan");
	span.innerHTML = msg;
	return msg == " O";
}

function trim(s){
   return s.replace(/^\s+|\s+$/g,"");
}

