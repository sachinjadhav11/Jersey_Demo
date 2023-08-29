/**
 * 
 */

var selectedRow = null
var deleteData = null;
var editId=null;

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
	 formData["dept_id"] = document.getElementById("dept_id").value;
	 
	
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
	cell5 = newRow.insertCell(4);
	cell5.innerHTML = data.address;
	cell6 = newRow.insertCell(5);
	cell6.innerHTML = data.gender;
	cell7 = newRow.insertCell(6);
	cell7.innerHTML = data.phoneNo;
	cell7 = newRow.insertCell(7);
	cell7.innerHTML = data.dept_id;
	cell4 = newRow.insertCell(8);
	cell4.innerHTML = `<a onClick="showForm(this)">Edit</a>
	                   <a onClick="onEdit(this)">Update</a>
                       <a onClick="onDelete(this)">Delete</a>`;

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
	document.getElementById("dept_id").value = "";
	
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
    document.getElementById("dept_id").value = selectedRow.cells[7].innerHTML;
	
/*	document.getElementById("male").value = selectedRow.cells[5].innerHTML;
	document.getElementById("female").value = selectedRow.cells[6].innerHTML;*/
	
}

async function updateRecord(formData)
 {

	const studentData = formData;
	studentData.id=editId;
	
	const student = {
        method: 'PUT',
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
		window.alert('please register first');
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
 
 /*function passwordValidate(data)
 {
	 const pass=document.getElementById("password");
	 if()
 }*/
 
  
     
     
     
     
     	/*const pass1=document.getElementById('password').value;
	if(pass1.length<8)
	{
		alert("please enter valid password");
	}
	else if(pass1.test(reg1))
	{
		alert("please enter valid password")
	}
	else
	{
	 formData["password"]=pass1	;
	}*/
     
     
     
     
     
     
     	/*document.getElementById("male").value = "";
	document.getElementById("female").value ="";
	*/
     
	
	/*let response=await fetch("http://localhost:9191/jerseyDemo/webapi/student/Login",{
		method: 'POST',
		headers : {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body : JSON.stringify(formData)
		
	})
	
	console.log(response);
	*/
	
	
	
	
/*	 email = document.getElementById("email").value ;
	 password = document.getElementById("password").value;
	 
	 signinData ={
		 email: email,
		 password: password
		 
	 };
	
	fetch('http://localhost:9191/jerseyDemo/webapi/student/Login',
		{
			method: 'POST',
			headers: {
				'Content-type': 'application/json'
			},
			body: JSON.stringify(signinData)
		})
		.then(res => console.log(res))
		
		window.location.href="index3.html"*/



/*  function SignIn()
  {
	  const apiUrl = 'http://localhost:9191/jerseyDemo/webapi/student/Login';
	  const loginData =
	   {
		  username: document.getElementById('email').value,
		  password: document.getElementById('password').value
	  };

	  // Create the request headers
	  const headers = new Headers();
	  headers.append('Content-Type', 'application/json');

	  // Create the request options
	  const requestOptions = {
		  method: 'POST',
		  headers: headers,
		  body: JSON.stringify(loginData)
	  };

	  // Perform the API call
	  fetch(apiUrl, requestOptions)
		   .then(
			   window.location.href='index3.html'
		   )
		  .catch(error =>
		   {
			  // Handle any errors that occurred during the API call
			  console.error('Error:', error);
		  });
  
  }*/
  
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
 


