


let fetchData=document.getElementById("fetch");
fetchData.addEventListener("click",listen);

async function listen(){

    const xhr=new XMLHttpRequest();

    xhr.open('POST','http://localhost:8080/project1/LoginVerify',true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");


    
    xhr.onprogress=function(){
        console.log("In Progress");
    }

    xhr.onreadystatechange=function(){
        console.log(this.readyState); // 0 to 4
    }

    xhr.onload=function(){
        if(this.status==200){
            console.log(this.responseText);
            fetchData.remove();
            document.getElementById("target").innerHTML=this.responseText;
        }else{
            console.log("some error occured");
        }
    }

    
    params='useremail=sujith@gmail.com&userpassword=sujith';
    xhr.send(params);
// -------------------------------------------------
    // fetch('https://gorest.co.in/public/v1/users').then(
    //     function(response){
    //         console.log(response.json().then(
    //             function(val){
    //                 console.log(val['data']);
    //             }
    //         ));
    //     }
    // );
// ---------------------------------------------------

// fetch('http://localhost:8080/project1/LoginVerify').then(
//         function(response){
//             console.log(response);
// })
}