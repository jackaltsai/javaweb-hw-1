
    
const submit = document.getElementById('update');
var nickname2 = document.getElementById('float_nickname')
var password2 = document.getElementById('float_password')
var checkPassword2 = document.getElementById('float_check_password');
var text_holder = document.getElementById('float_text_holder');
var account2 = document.getElementById('float_account');


function clickBtn() {

    var nickname = nickname2.value;
    var password = password2.value;
    var checkPassword = checkPassword2.value;
    
    var account = account2.valuel;
    
    var nicknameisok = false;
    var passwordisok = false;
    var data_json;

    console.log(account);
    console.log(nickname);
    console.log(password);

    if(password != checkPassword){
        text_holder.innerHTML = '密碼不相同';
    }else if(password.length < 6 || password.length > 12){
        text_holder.innerHTML = '密碼: ⻑度6~12';
    }else if(/\s/.test(password)){
        // (/\s/.test(變數))檢查是否有空白
        text_holder.innerHTML = '密碼不能有空白';
    }else{
        passwordisok = true;
    }

    if(nickname <1 || nickname >20){
        text_holder.innerHTML = '暱稱: ⻑度1~20';
    }else if(/\s/.test(nickname)){
        text_holder.innerHTML ='暱稱不能有空白'
    }else{
        nicknameisok = true;
    }

    if(passwordisok && nicknameisok){
        const data = {
            nickname : nickname,
            password : password};
        data_json = data;
    }else if(passwordisok){
        const data = {
             password : password};
        data_json = data;
    }else if(nicknameisok){
        const data = {
            nickname : nickname};
        data_json = data;
    }
        
    
    fetchJson(data_json);
        
    
}


function fetchJson(data){
    fetch("../update",{
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json; charset=UTF-8'
        },
        body : JSON.stringify(data),
    })
    .then(response => {
        console.log(response);
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json()
    })
    .then(data => {
        console.log('Success' , data);
    })
    .catch(function (error) {
        console.log(error);
    })
}