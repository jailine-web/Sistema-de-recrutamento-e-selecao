<?php

    $hostname = "localhost";
    $bancodedados = "project";
    $usuario = "root";
    $senha = "jailine96";

    $msqli = new mysqli($hostname, $usuario, $senha, $bancodedados);
    if($msqli->connect_errno){
        echo "falha ao conectar: (" . $msqli->connect_errno . ") " . $msqli->connect_error;
    }
    else
        echo "Conectado ao bd: " . $bancodedados;
        return $msqli
 
?>