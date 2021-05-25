const submit = document.getElementById('submit');
var account = document.getElementById('floatingInput');
var password = document.getElementById('floatingPassword');

console.log(account);
console.log(password);

// submit.onclick = () => {
// 	fetch("../login")
// 	.then(res => {
// 		return res.json();
// 	}).then(result => {
// 		var account = document.getElementById("email").value;
// 		var password = document.getElementById("password").value;
// 		console.log('${account} ${password}');
// 	});
// };

function clickBtn() {

    var accountValue = account.value;
    var passwordValue = password.value;
    console.log(accountValue);
    console.log(passwordValue);

    fetch("../login",{
        method : 'POST',
        body : JSON.stringify({
            account : accountValue,
            password : passwordValue
        }),
        headers : {
            'Content-Type' : 'application/json; charset=UTF-8'
        }
    })
    .then(response => {
        console.log(response);
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json()
    })
    .then(result => {
        console.log(result);
        if (result.status == true) {
            alert("驗證成功");
        } else {
            alert("驗證失敗");
        }
    })
    .catch(function (error) {
        console.log(error);
    })
    ;
}