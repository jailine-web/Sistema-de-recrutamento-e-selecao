
const form = document.querySelector("form");


const url = "http://localhost:8080/hisig10/auth/logar";

function logar(){
    const Usuario = document.querySelector("#usuario");
    const Senha = document.querySelector("#senha");
    fetch(url, 
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
    },
        method: "POST",
        body: JSON.stringify({
            usuario: Usuario.value,
            senha: Senha.value,
            
        })
    })
    .then(function(res){console.log(res)})
    .catch(function (res) {console.log(res)})
}

function limpar(){

        Usuario.value = "";
        Senha.value = "";
}





