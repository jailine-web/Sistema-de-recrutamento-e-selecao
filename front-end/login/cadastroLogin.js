
const formulario = document.querySelector("form");

const Iemail = document.querySelector("#email");
const Iusuario = document.querySelector("#usuario");
const Isenha = document.querySelector("#senha");
const Iregras = document.querySelector("#regras");

const url = "http://localhost:8080/hisig10/auth/cadastro";

function cadastrar(){
    fetch( url, 
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
    },
        method: "POST",
        body: JSON.stringify({
            email: Iemail.value,
            usuario: Iusuario.value,
            senha: Isenha.value,
            regras: Iregras.value
        })
    })
    .then(function(res){console.log(res)})
    .catch(function (res) {console.log(res)})
}

function limpar(){
    
        Iemail.value = "";
        Iusuario.value = "";
        Isenha.value = "";
        Iregras.value= "";
    
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();
    limpar();
});




