

INSERT INTO Candidato (nome, telefone, email, recrutador, curriculo, img, semestre_vigente, curso, termino, instituicao) VALUES('Julia Campos', '71 99097845' , 'julia@gmail.com', false, 'Currículo', 'img', '4°', 'Análise', '09/2024', 'If baiano');
INSERT INTO Candidato (nome, telefone, email, recrutador, curriculo, img, semestre_vigente, curso, termino, instituicao) values('Paula Souza', '75 98976394', 'paulo@gmail.com', false, 'Currículo', 'img', '4°', 'Análise', '10/2030', 'Estácio');
INSERT INTO Candidato (nome, telefone, email, recrutador, curriculo, img, semestre_vigente, curso, termino, instituicao) values('João Souza', '21 98576394', 'joao@gmail.com', false, 'Currículo', 'img', '4°', 'Técnico', '01/2025', 'If ');

INSERT INTO Recrutador (nome, email, recrutador, curriculo, img, time) VALUES ('José Alves Campos','jose@gmail.com', false, 'Currículo', 'img', 'Devs');
INSERT INTO Recrutador (nome, email, recrutador, curriculo, img, time) VALUES ('Júlia Porto','ju@gmail.com', false, 'Currículo', 'img', 'Devs');
INSERT INTO Recrutador (nome, email, recrutador, curriculo, img, time) VALUES ('Pedro Henrique ','pedro@gmail.com', false, 'Currículo', 'img', 'Devs');

INSERT INTO Vaga (nome, descricao, requisitos, localizacao, formato) VALUES ('Estágio em desenvolvimento', 'Estágio das 08:00 às 12 ','linguagens: Java e python', 'Espirito santo', 'Home office');
INSERT INTO Vaga (nome, descricao, requisitos, localizacao, formato) VALUES ('Estágio em redação', 'Estágio das 14:30 às 18:30 realizar ....',': Java e python', 'São Paulo', 'Presencial');
INSERT INTO Vaga (nome, descricao, requisitos, localizacao, formato) VALUES ('Progamador Jr', 'Tarefas: Desenvolver programas em C','linguagens: Java e C', 'Bahia', 'Home office');

insert into Candidato_vaga(candidato_id, vaga_id) Values(1,1);
insert into Candidato_vaga(candidato_id, vaga_id) Values(1,2);
insert into Candidato_vaga(candidato_id, vaga_id) Values(2,3);