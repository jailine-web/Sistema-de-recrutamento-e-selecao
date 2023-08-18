//Função que permite que o JS acesse elementos do HTML

const formulario = document.querySelector("form");

const Inome = document.querySelector("#nome");
const Iemail = document.querySelector("#email");
const Isenha = document.querySelector("#senha");

//Função de conexão do front-end com o back-end
function cadastrar(){
    fetch("http://localhost:8080/hisig10/login", 
    {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            email: Iemail.value,
            usuario: Inome.value,
            senha: Isenha.value
        })
    })
    .then(function(res){console.log(res)})
    .catch(function (res) {console.log(res)})
}

function limpar(){
    
        Inome.value = "";
        Iemail.value = "";
        Isenha.value = "";
}

formulario.addEventListener('submit', function(event){
    event.preventDefault();
    cadastrar();
    limpar();
});
