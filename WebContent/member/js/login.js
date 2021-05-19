const btnsignin = document.getElementById('btnsignin');
btnsignin.onclick = () => {
    fetch("../signin")
	.then(resp => resp.json())
	.then(memberList => {
		console.log(memberList);
	});
};

