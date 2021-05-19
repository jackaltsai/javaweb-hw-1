const btnregister = document.getElementById('btnregister');
btnregister.onclick = () => {
    fetch("../register")
	.then(resp => resp.json())
	.then(memberList => {
		console.log(memberList);
	});
};


