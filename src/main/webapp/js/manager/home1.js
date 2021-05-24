window.onload = function(){
    getUserSession();
}

function getUserSession(){
    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = function(){

        if(xhttp.readyState == 4 && xhttp.status == 200)
        {
            let user = JSON.parse(xhttp.responseText);
            console.log(user);
            document.getElementById("welcomeHeader").innerText=`Welcome ${vill.name}`;
        }
    }

    xhttp.open("GET", "http://localhost:8080/ExpenseReimbursementSystem/getusersession.json");

    xhttp.send();
}