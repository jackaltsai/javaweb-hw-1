// const btnregister = document.getElementById('btnregister');
// btnregister.onclick = () => {
//     fetch("../register")
// 	.then(resp => resp.json())
// 	.then(memberList => {
// 		console.log(memberList);
// 	});
// };

var account = document.getElementById('floatingInput');
var password = document.getElementById('floatingPassword');
var repassword = document.getElementById('floatingRepassword');
var nickname = document.getElementById('floatingNickname');

console.log(account);
console.log(password);
console.log(repassword);
console.log(nickname);

function register_click(){

	var accountValue = account.value;
	var passwordValue = password.value;
	var repasswordValue = repassword.value;
	var nicknameValue = nickname.value;

	console.log(accountValue);
	console.log(passwordValue);
	console.log(repasswordValue);
	console.log(nicknameValue);

	if((accountValue.length == 0)||(passwordValue.length == 0)||(repasswordValue.length == 0)||(nicknameValue.length == 0)){
		alert('不可有欄位為空！！！')
		return;
	}
	if(repasswordValue != passwordValue){
		alert('重複密碼錯誤');
		return ;
	}

	if((passwordValue.length<=5)||(passwordValue.length>=20)){
		alert('密碼長度不符合');
		return ;
	}

	fetch("../register",{
        method : 'POST',
        body : JSON.stringify({
            account : accountValue,
						password : passwordValue,
						repassword : repasswordValue,
						nickname : nicknameValue

        }),
        headers : {
            'Content-Type' : 'application/json; charset=UTF-8'
        }
	})
	.then(response =>{
		console.log(response);
		if(!response.ok){
			throw new Error(response.statusText);
		}
		return response.json();
	})
	.then(result =>{
		console.log(result);
		if(result.repassword != result.password){
			alert("第二次密碼輸入不相符！！！")
		}
		if (result.errorCode = "帳號已經存在") {
			alert("帳號已經存在！！！")
			return;
		}
	})
	.catch(function (error) {
        console.log(error);
    });
}
