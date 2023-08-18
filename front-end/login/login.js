//Função que permite que o JS acesse elementos do HTML

const formularioCadastro = document.querySelector("form");
const formularioLogin = document.querySelector("form");

const Iusuario = document.querySelector("#usuario");
const Iemail = document.querySelector("#email");
const Isenha = document.querySelector("#senha");
const Iregras = document.querySelector("#adm");

function logar(){
    fetch("http://localhost:8080/hisig10/auth/logar", 
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            usuario: Iusuario.value,
            senha: Isenha.value,
           
        })
    })
    .then(function(res){console.log(res)})
    .catch(function (res) {console.log(res)})
}

//Função de conexão do front-end com o back-end
function cadastrar(){
    fetch("http://localhost:8080/hisig10/auth/cadastro", 
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
            adm: Iregras.value
        })
    })
    .then(function(res){console.log(res)})
    .catch(function (res) {console.log(res)})
}

function limpar(){
        Iusuario.value = "";
        Iemail.value = "";
        Isenha.value = "";
}

formularioCadastro.addEventListener('submit', function(event){
    event.preventDefault();
    cadastrar();
    logar();
    limpar();
});


