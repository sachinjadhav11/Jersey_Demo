/**
 * 
 */

var selectedRow = null
var deleteData = null;
var editId=null;
/*var employeeId=null;
var empName=null;
var email=null;
var url=null;*/
//applyLeave(tableData);

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

async function addStudent(formData1)
 {
    const studentData = formData1;

    const student = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(studentData),
    };

    try {
        const response = await fetch(
            `http://localhost:9191/jerseyDemo/webapi/student`, student);

        if (response.status === 200)
         {
            alert('Registration Successful');
         } 
        else if (response.status === 304)
         {
            alert('Email id already Exist');
         }
         else
          {
            alert('Something went wrong');
          }
    } 
    catch (error)
     {
        console.error('Error:', error);
      }
}






/*function addStudent(formData1)
 {
	const studentData = formData1;
	
	  const student = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body : JSON.stringify(studentData)
    }
    try
    {
		const response = fetch(
			`http://localhost:9191/jerseyDemo/webapi/student`, student);
			
			if(response.status=='200')
			{
				alert("Registration Successful");
			}
			else if(response.status=='304')
			{
				alert("Email id already Exist");
			}	
	}
	catch(e)
	{
		return e;
	}
}
	*/
	
	

/*
	const response=fetch('http://localhost:9191/jerseyDemo/webapi/student',
		{
			method: 'POST',
			headers: {
				'Content-type': 'application/json'
			},
			body: JSON.stringify(studentData)
		}).then((res) =>
		{
			if(response.status=='200')
			{
				alert("Registration Successfully");
			}
			else if(response.status=='304')
			{
				alert("Email already Exist..");
			}
		})	
*/
function readFormData()
 {
	var formData = {};

	formData["name"] = document.getElementById("name").value;
	formData["email"] = document.getElementById("email").value;
	formData["password"] = document.getElementById("password").value;
	formData["address"] = document.getElementById("address").value;
	if (document.getElementById("male").checked)
	 {
		formData["gender"] = document.getElementById("male").value;
	 } 
	else if (document.getElementById("female").checked)
	 {
		formData["gender"] = document.getElementById("female").value;
	 }
	 formData["phoneNo"] = document.getElementById("phoneNo").value;
	 formData["deptName"] = document.getElementById("deptName").value;
	 
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
	cell1.innerHTML = data.employeeId;
	cell2 = newRow.insertCell(1);
	cell2.innerHTML = data.name;
	cell3 = newRow.insertCell(2);
	cell3.innerHTML = data.email;
	cell4 = newRow.insertCell(3);
	cell4.innerHTML = data.password;
	cell5 = newRow.insertCell(4);
	cell5.innerHTML = data.address;
	cell6 = newRow.insertCell(5);
	cell6.innerHTML = data.gender;
	cell7 = newRow.insertCell(6);
	cell7.innerHTML = data.phoneNo;
	cell8 = newRow.insertCell(7);
	cell8.innerHTML = data.deptId;
	cell9 = newRow.insertCell(8);
	cell9.innerHTML = data.deptName;
	cell10 = newRow.insertCell(9);
	cell10.innerHTML = `<a onClick="showForm(this)">Edit</a>
	                   <a onClick="onEdit(this)">Update</a>
                       <a onClick="onDelete(this)">Delete</a>`;
    cell11 = newRow.insertCell(10); 
    cell11.innerHTML = `<a onClick="applyLeave(this)">ApplyLeave</a>`;                  

}

function applyLeave(td) 
{
    alert("Are you sure to Apply For Leave");
    
    // Get the values from the selected row
    selectedRow = td.parentElement.parentElement;
    employeeId = selectedRow.cells[0].innerHTML;
    empName = selectedRow.cells[1].innerHTML;
    email = selectedRow.cells[2].innerHTML;
    
    console.log(employeeId);
    console.log(empName);
    console.log(email);

    // Construct the URL with parameters and navigate to it
     url = "leave.html?employeeId=" + encodeURIComponent(employeeId) +
              "&name=" + encodeURIComponent(empName) +
              "&email=" + encodeURIComponent(email);
    document.location.href = url;
    
    console.log(employeeId);
    console.log(empName);
    console.log(email);
}

window.onload = function ()
 {
    
    if (document.location.search)
     {
        params = document.location.search.substring(1).split('&');
       let decodedUrl= decodeURIComponent(document.location.search)
       params = decodedUrl.substring(1).split('&');
        
        data = {};
        for (var i = 0, l = params.length; i < l; i++) 
        {
            tmp = params[i].split('=');
            data[tmp[0]] = tmp[1];
        }
         console.log(data.employeeId);
     //   document.getElementById('employeeId').innerHTML = data.employeeId;
      var empId=data.employeeId;
      var Name=data.name; 
      var mail=data.email;
      
      console.log(empId);
      console.log(Name);
      console.log(mail);
      
      document.getElementById('employeeId').value = empId;
      document.getElementById('name').value = Name;
      document.getElementById('email').value = mail;
         
    }
}

async function onLeave()
{
	var leaveData = {};
	leaveData["employeeId"] = document.getElementById('employeeId').value;
	leaveData["empName"] = document.getElementById('name').value;
	leaveData["empMail"] = document.getElementById('email').value;
	leaveData["date"] = document.getElementById('date').value;
	leaveData["description"] = document.getElementById('description').value;
	
	const data = leaveData;
	
	const leave = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };
    
    try
    {
		const responce = await fetch (
			`http://localhost:9191/jerseyDemo/webapi/student/leave`, leave);
			
			if(response.status === 200)
			{
				alert("apply successfully");
			}
			else if(response.status === 304)
			{
				alert("something went wrong");
			}	
	}
	catch (error)
     {
        console.error('Error:', error);
     }	
}

function onReset()
{
	document.getElementById('myForm').reset();
}

function ViewLeaves()
 {
	 document.location.href = "viewLeave.html";	 
}

 function viewEmpLeave()
{
	fetch("http://localhost:9191/jerseyDemo/webapi/student/leave").then(
		res => {
			res.json().then(
				data => {
					console.log(data);
					if (data.length > 0) {

						var temp = "";
						data.forEach((itemData) => {
							temp += "<tr>";
							temp += "<td>" + itemData.employeeId + "</td>";
							temp += "<td>" + itemData.empName + "</td>";
							temp += "<td>" + itemData.empMail + "</td>";
							temp += "<td>" + itemData.leaveBalance + "</td>";
							temp += "<td>" + itemData.date + "</td>";
							temp += "<td>" + itemData.description + "</td>";
							temp += "<td>" + itemData.leave_count + "</td>";
							temp += "<td>" + itemData.remainingLeave + "</td>";
						});
						document.getElementById('data').innerHTML = temp;
					}
				}
			)
		}
	)
}



function showForm(td)
{
	alert("Are you sure to Edit this record! click on Update");
	document.querySelector('.Hide-form-content').style.display = 'block';
}

function resetForm()
 {
	/*document.getElementById("id").value = "";*/
	editId=null;
	document.getElementById("name").value = "";
	document.getElementById("email").value = "";
	document.getElementById("password").value = "";
	document.getElementById("address").value = "";
	if(document.getElementById("male").checked==true)
	{
	 document.getElementById("male").value = "";
	}
	else if(document.getElementById("female").checked==true)
	{
	  document.getElementById("female").value = "";
	}
	document.getElementById("phoneNo").value = "";
	document.getElementById("deptName").value = "";
	
	selectedRow = null;
}

function onEdit(td)
 {
	selectedRow = td.parentElement.parentElement;
     editId = selectedRow.cells[0].innerHTML;	
    document.getElementById("name").value = selectedRow.cells[1].innerHTML;
	document.getElementById("email").value = selectedRow.cells[2].innerHTML;
	document.getElementById("password").value = selectedRow.cells[3].innerHTML;
	document.getElementById("address").value = selectedRow.cells[4].innerHTML;
	if(selectedRow.cells[5].innerHTML=="Male")
	{
		document.getElementById("male").checked=true;
	}
	else if(selectedRow.cells[5].innerHTML=="Female")
	{
		document.getElementById("female").checked=true;
	}
    document.getElementById("phoneNo").value = selectedRow.cells[6].innerHTML;
    document.getElementById("deptName").value = selectedRow.cells[8].innerHTML;
	
/*	document.getElementById("male").value = selectedRow.cells[5].innerHTML;
	document.getElementById("female").value = selectedRow.cells[6].innerHTML;*/
	
}

async function updateRecord(formData)
 {

	const studentData = formData;
	studentData.employeeId=editId;
	
	const student = {
        method: 'PUT',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(studentData)
    };
    
    try {
        const response = await fetch(
            `http://localhost:9191/jerseyDemo/webapi/student`, student);

        if (response.status === 200)
         {
            alert('Update Successful');
         } 
        else if (response.status === 304)
         {
            alert('Email id already Exist!');
         }
         else
          {
            alert('Something went wrong');
          }
        }
        catch (error)
        {
         console.error('Error:', error);
        } 
}	


function onDelete(td)
 {
	if (confirm('Are you sure to delete this record ?'))
	 {
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

function SignUp()
 {
	window.location.href = "index3.html";
 }


signIn = async ()=>
{
	event.preventDefault();
	let formData = onSignIn();
	
	   const settings = {
        method: 'POST',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        },
        body : JSON.stringify(formData)
    };
    try {
        const fetchResponse = await fetch(
			`http://localhost:9191/jerseyDemo/webapi/student/Login`, settings);
       
    if(fetchResponse.status=='200')
    {
		window.location.href='view.html';
	}
	else if(fetchResponse.status=='304')
	{
		var div = document.getElementById('emailValidate');

        div.innerHTML = 'Email Or Password Wrong';
		
	}
        console.log(fetchResponse);
    } 
    catch (e)
     {
        return e;
     } 
     
 }     
     
function onSignIn()
{
	var formData={};
		formData["email"] = document.getElementById("email").value;
	    formData["password"] = document.getElementById("password").value;
	    return formData;
}

function validation()
{
	const pass=document.getElementById("password").value;
	
	if(pass.length()<8)
	{
		alert("enter valid password");
	}
 }
 
function validate()
 {
	isValid = true;
	if (document.getElementById("id").value == "")
	 {
		isValid = false;
		document.getElementById("fullNameValidationError").classList.remove("hide");
	} else
	 {
		isValid = true;
		if (!document.getElementById("fullNameValidationError").classList.contains("hide"))
			document.getElementById("fullNameValidationError").classList.add("hide");
	}
	return isValid;
 }
 


