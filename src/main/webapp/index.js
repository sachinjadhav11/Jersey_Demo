/**
 * 
 */

var selectedRow = null
var deleteData = null;

function onFormSubmit(e)
 {
	event.preventDefault();
	var formData = readFormData();
	if (selectedRow == null)
		addStudent(formData);
	else
		updateRecord(formData);
	resetForm();

}

function addStudent(formData1)
 {
	const studentData = formData1;

	fetch('http://localhost:9191/jerseyDemo/webapi/student',
		{
			method: 'POST',
			headers: {
				'Content-type': 'application/json'
			},
			body: JSON.stringify(studentData)
		})
}

function readFormData()
 {
	var formData = {};
	formData["id"] = document.getElementById("id").value;
	formData["name"] = document.getElementById("name").value;
	formData["email"] = document.getElementById("email").value;
	formData["password"] = document.getElementById("password").value;
	formData["address"] = document.getElementById("address").value;
	formData["gender"] = document.getElementById("gender").value;

	return formData;
}

function viewStudent()
 {
	fetch("http://localhost:9191/jerseyDemo/webapi/student")
		.then(res => res.json())
		.then(data =>
		 {


			for (let k in data)
			 {
				insertNewRecord(data[k])
			 }
		}
		)
}

function insertNewRecord(data)
 {
	var table = document.getElementById("studentList").getElementsByTagName('tbody')[0];
	var newRow = table.insertRow(table.length);
	cell1 = newRow.insertCell(0);
	cell1.innerHTML = data.id;
	cell2 = newRow.insertCell(1);
	cell2.innerHTML = data.name;
	cell3 = newRow.insertCell(2);
	cell3.innerHTML = data.email;
	cell4 = newRow.insertCell(3);
	cell4.innerHTML = data.password;
	cell1 = newRow.insertCell(4);
	cell1.innerHTML = data.address;
	cell1 = newRow.insertCell(5);
	cell1.innerHTML = data.gender;
	cell4 = newRow.insertCell(6);
	cell4.innerHTML = `<a onClick="onEdit(this)">Edit</a>
                       <a onClick="onDelete(this)">Delete</a>`;

}

function resetForm()
 {
	document.getElementById("id").value = "";
	document.getElementById("name").value = "";
	document.getElementById("email").value = "";
	document.getElementById("password").value = "";
	document.getElementById("address").value = "";
	document.getElementById("gender").value = "";
	selectedRow = null;
}

function onEdit(td)
 {
	selectedRow = td.parentElement.parentElement;
	document.getElementById("id").value = selectedRow.cells[0].innerHTML;
	document.getElementById("name").value = selectedRow.cells[1].innerHTML;
	document.getElementById("email").value = selectedRow.cells[2].innerHTML;
	document.getElementById("password").value = selectedRow.cells[3].innerHTML;
	document.getElementById("address").value = selectedRow.cells[4].innerHTML;
	document.getElementById("gender").value = selectedRow.cells[5].innerHTML;
}

function updateRecord(formData)
 {

	const studentData = formData;
	fetch('http://localhost:9191/jerseyDemo/webapi/student',
		{
			method: 'PUT',
			headers: {
				'Content-type': 'application/json'
			},
			body: JSON.stringify(studentData)
		})
}

function onDelete(td)
 {
	if (confirm('Are you sure to delete this record ?')) {
		deleteData = td.parentElement.parentElement;
		deleteRow1();
		resetForm();
	}
}
function deleteRow1()
 {
	var id = deleteData.cells[0].innerHTML;
	fetch('http://localhost:9191/jerseyDemo/webapi/student/' + id,
		{
			method: 'DELETE',
		})
		.then(res => res.json())
		.then(res => console.log(res))
}

function validate()
 {
	isValid = true;
	if (document.getElementById("id").value == "") {
		isValid = false;
		document.getElementById("fullNameValidationError").classList.remove("hide");
	} else {
		isValid = true;
		if (!document.getElementById("fullNameValidationError").classList.contains("hide"))
			document.getElementById("fullNameValidationError").classList.add("hide");
	}
	return isValid;
}