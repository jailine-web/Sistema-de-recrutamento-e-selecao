const url ="http://localhost:8080/hisig10/vagas";

const vaga = {
    codigo:"xx01",
    titulo:"Design",
    descricao:"Desenvolver programas na linguagem C#",
    requisitos:"Conhecimento em C# e Banco de dados",
    localizacao:"Bahia",
    formato:"Home office",
    categoria:"Front-end",
    dataAbertura:"2023-09-03T13:30:26"
}

function obterVagas(){
    axios.get(url, vaga)
    .then(response =>{
        const data = response.data
        vagas.textContent = JSON.stringify(data)
    })
    .catch(error => console.log(error))
}

function limpar(){
    vagas.textContent= "";
}

function novaVaga(){
    axios.post(url, vaga)
    .then(response =>{
        alert(response.alert)
     })
     .catch(error => console.log(error))
}

novaVaga()