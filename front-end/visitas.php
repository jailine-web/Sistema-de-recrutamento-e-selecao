<?php

include("conexao.php");

private $Id , $Ip, $Data, $Hora;

public function_construct(){
    $this->Id=0;
    $this->Ip= $_SERVER['REMOTE_ADDR'];
    $this->Data= date('y/m/d');
    $this->Hora=date('H:i')

}

public function verificaUsuario(){
    
}