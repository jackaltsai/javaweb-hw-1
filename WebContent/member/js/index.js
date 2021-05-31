function loadData() {
	fetch("../getallmember")
		.then(resp => resp.json()
			.then(memberList => {
				console.log(memberList);
				memberList.forEach((item, i) => {
					var table = document.getElementById("memberTable");
					var newRow = table.insertRow(++i);
					var newCell = newRow.insertCell(0);
					newCell.innerHTML = "<button onclick='deleteMember(" + item.id + ")'>刪除</button>";
					newCell = newRow.insertCell(1);
					newCell.innerHTML = item.id;
					newCell = newRow.insertCell(2);
					newCell.innerHTML = item.account;
					newCell = newRow.insertCell(3);
					newCell.innerHTML = item.password;
					newCell = newRow.insertCell(4);
					newCell.innerHTML = item.nickname;
					newCell = newRow.insertCell(5);
					newCell.innerHTML = item.pass;
					newCell = newRow.insertCell(6);
					newCell.innerHTML = item.lastUpdateDate;
					newCell = newRow.insertCell(7);
					newCell.innerHTML = item.roleId;
				});
			}));
}

function deleteMember(id) {
	if (confirm("確定要刪除？")) {
		// POST方式
		fetch("../delete", {
			method: 'POST',
			body: JSON.stringify({ deleteId: id }),
			headers: {
				'Content-Type': 'application/json; charset=UTF-8'
			}
		})
			.then(resp => resp.json())
			.then(result => {
				//console.log(result);
				if (result.result_code == 1) {
					location.reload();
				}
			});
		/*
		// GET方式
		fetch("../delete?deleteId=" + id)
		.then(resp => resp.json())
		.then(memberList => {
			if (memberList != null) {
				location.reload();
			}
		});
		*/
	}
}