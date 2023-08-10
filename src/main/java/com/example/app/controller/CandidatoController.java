package com.example.app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.app.DTO.CandidatoReduzido;
import com.example.app.model.entities.Candidato;
import com.example.app.projection.CandidaturasCandidatoProjection;
import com.example.app.repositories.CandidatoRepository;
import com.example.app.repositories.VagaRepository;
import com.example.app.services.CandidatoService;

@RestController
@RequestMapping(value="/hisig10/usuarios/candidatos")
public class CandidatoController {

	@Autowired
	private CandidatoService cs;
	
	@Autowired
	private VagaRepository vr;
	
	@Autowired
	private CandidatoRepository cr;
	
	@GetMapping
	public List<Candidato> buscarTodos(){
		List<Candidato> listaCandidatos = cs.buscarTodos();
		return listaCandidatos;
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Candidato> obterCandidato(@PathVariable Integer id){
		Optional<Candidato> candidatoOptional = cr.findById(id);
		
		if (candidatoOptional.isPresent()) {
			Candidato candidato = candidatoOptional.get();
			return ResponseEntity.ok(candidato);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Candidato> inserirCandidato(@RequestBody Candidato candidato){
		
		candidato = cs.inserirCandidato(candidato);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(candidato.getId()).toUri();
		return ResponseEntity.created(uri).body(candidato);
	}

	@DeleteMapping(value="/{id}")
	public ResponseEntity<Candidato> excluirCandidato(@PathVariable Integer id) {
		
		cs.excluirCandidato(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Candidato> atualizarCandidato(@PathVariable Integer id, @RequestBody Candidato candidato) {
		
		candidato = cs.atualizarCandidato(id, candidato);
		return ResponseEntity.ok().body(candidato);
	}
	
	@GetMapping(value="/{idCand}/candidaturas")
	public List<CandidaturasCandidatoProjection> buscarCandidaturas(@PathVariable Integer idCand){
		
		List<CandidaturasCandidatoProjection> vagas = vr.buscarCandidaturas(idCand);
		return vagas;
	}
	
	@PostMapping("/{id}/curriculo")
    public ResponseEntity<String> adicionarCurriculo(@PathVariable Integer id, @RequestParam("curriculo") MultipartFile curriculo) {
        try {
            cs.inserirCurriculo(id, curriculo);
            return ResponseEntity.ok("Currículo enviado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar o currículo.");
        }
    }
	
	@GetMapping("/{id}/curriculo")
	public ResponseEntity<byte[]> obterCurriculo(@PathVariable Integer id) {
		Candidato candidato = cs.buscarPorId(id);
		if (candidato == null || candidato.getCurriculo() == null) {
			return ResponseEntity.notFound().build();
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDisposition(ContentDisposition.builder("inline").filename("Curriculo.pdf").build());
		
		return new ResponseEntity<>(candidato.getCurriculo(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/curriculos")
	public ResponseEntity<List<CandidatoReduzido>> obterCurriculoPorVaga(@RequestParam("idVaga") Integer idVaga){
		List<Candidato> candidatos = cs.buscarCandidatosPorVaga(idVaga);
		
		if (candidatos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		List<CandidatoReduzido> curriculosDTO = new ArrayList<>();
		
		for (Candidato candidato : candidatos) {
			byte[] curriculo = candidato.getCurriculo();
			if (curriculo != null) {
				curriculosDTO.add(new CandidatoReduzido(candidato));
			}
		}
		
		if (curriculosDTO.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(curriculosDTO);
	}
}
