/**
 * 
 */
 
//  console.log("In JS");
window.onload = function(){
    getUserSession();
   getUserReimbursement();
}

function getUserSession(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){

        if(xhttp.readyState == 4 && xhttp.status == 200)
        {
            let user = JSON.parse(xhttp.responseText);
            // console.log(user);
            document.getElementById("welcomeHeader").innerText=`Welcome ${user.first_name}`;
        }
    }

    xhttp.open("GET", "http://localhost:8080/ExpenseReimbursementSystem/getusersession.json");

    xhttp.send();
}

var btn = document.getElementById("viewer");
btn.addEventListener("click", getUserReimbursement);

function getUserReimbursement()
{    
    //var data;
    $.ajax({
        method:'GET',
        url:'http://localhost:8080/ExpenseReimbursementSystem/getexpenselist.json',
        success:function(response){
            data = JSON.parse(response);
            // =response;
            //console.log(data);
            buildTable(data);
        }
    })

    function buildTable(data)
    {
        //console.log(`Data: ${data[0].id}`);
        let table = document.getElementById('myTable');
        var status = document.getElementById("status");
        var row = "";
        for(let i =0; i<data.length; i++)
        {
            

            let resolvedTime =data[i].reimb_resolved;
            let submittedTime =data[i].reimb_submitted;

            let formattedResolvedTime = new Date(resolvedTime )

            var first_name = data[i].reimbResolver.first_name;
            var last_name = data[i].reimbResolver.last_name;

            if(!first_name && !last_name)
            {
                first_name = " ";
                last_name = " ";
            }

            if(status.value === "All")
            {
                row +=   `<tr>
                            
                                <td>${data[i].reimbAuthor.first_name} ${data[i].reimbAuthor.last_name}</td>
                                <td>${data[i].reimbAuthor.email}</td>
                                <td>${convertTime(submittedTime)}</td>
                                <td>${data[i].reimbType.type}</td>
                                <td>${data[i].reimb_amount}</td>
                                <td>${data[i].reimb_description}</td>
                                <td>${first_name} ${last_name}</td>
                                <td>${convertTime(resolvedTime)}</td>
                                <td>${data[i].reimbStatus.status}</td>
                                <td>
                                    <form method="post" action="/ExpenseReimbursementSystem/approve.change">
                                        <input type="hidden" name = "reimb_id" value="${data[i].id}">
                                        <input type="hidden" name = "reimb_status" value="approve">
                                        <button name"approveBtn" type="submit" value="approve" class="btn btn-light">Approve</button>
                                    </form>
                                    <form method="post" action="/ExpenseReimbursementSystem/approve.change">
                                        <input type="hidden" name = "reimb_id" value="${data[i].id}">
                                        <input type="hidden" name = "reimb_status" value="deny">
                                        <button name"approveBtn" type="submit" value="deny" class="btn btn-light">Deny</button>
                                    </form>
                                </td>
                        </tr>`
                        
            }else if(status.value === data[i].reimbStatus.status)
            {
                row +=   `<tr>
                            
                            <td>${data[i].reimbAuthor.first_name} ${data[i].reimbAuthor.last_name}</td>
                            <td>${data[i].reimbAuthor.email}</td>
                            <td>${convertTime(submittedTime)}</td>
                            <td>${data[i].reimbType.type}</td>
                            <td>${data[i].reimb_amount}</td>
                            <td>${data[i].reimb_description}</td>
                            <td>${first_name} ${last_name}</td>
                            <td>${convertTime(resolvedTime)}</td>
                            <td>${data[i].reimbStatus.status}</td>
                            <td>
                                <form method="post" action="/ExpenseReimbursementSystem/approve.change">
                                    <input type="hidden" name = "reimb_id" value="${data[i].id}">
                                    <input type="hidden" name = "reimb_status" value="approve">
                                    <button name"approveBtn" type="submit" value="approve" class="btn btn-light">Approve</button>
                                </form>
                                <form method="post" action="/ExpenseReimbursementSystem/approve.change">
                                    <input type="hidden" name = "reimb_id" value="${data[i].id}">
                                    <input type="hidden" name = "reimb_status" value="deny">
                                    <button name"approveBtn" type="submit" value="deny" class="btn btn-light">Deny</button>
                                </form>
                            </td>
                        </tr>`
            }
            
            table.innerHTML=row;
            
        }
    }

    function convertTime(timeStamp)
    {
        if(timeStamp == null)
        {
            return " ";
        }
        else
        {
            let myDate = new Date(timeStamp);
            let date = myDate.toLocaleDateString();
            let time = myDate.toLocaleTimeString();
            let time1 = date+" "+time;
            return time1;
        }
        
    }
}
    