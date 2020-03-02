package br.senai.sp.jandira.odonto.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.jandira.odonto.model.Dentista;
import br.senai.sp.jandira.odonto.repository.DentistaRepository;

// é uma classe controller
@RestController //ctrl + space para importação
@RequestMapping("/odonto") // o que for colocado dentro das "" é o recurso que será chamado
public class DentistaResource {
	
	private static final String httpsStatus = null;
	@Autowired // 
	private DentistaRepository dentistaRepository;
	
	// mapear dentistas e retornar os dados em JSON 
	@GetMapping("/dentistas") //(caminho que executará o array)
	// método para retornar uma lista de dentistas
	public List<Dentista> getDentistas(){
		// retornar o array (lista) de dentistas (todos)
		return dentistaRepository.findAll();
	}
	
	@GetMapping("/dentistas/{codigo}") // chamando pelo id
	//o codigo deve ser passado como parametro para o java identificar o id do dado quando for chamado na url
	public ResponseEntity<?> getDentista(@PathVariable Long codigo){ // iniciando nulo,
		Optional<?> dentistaProcurado = dentistaRepository.findById(codigo);
		return dentistaProcurado.isPresent() ?
				ResponseEntity.ok(dentistaProcurado.get()) : //se existir o cadastro de determinado dentista
				ResponseEntity.notFound().build(); //se nao achar o dentista
	}
	
	@PostMapping("/dentistas")
	@ResponseStatus(HttpStatus.CREATED)
	//valid, para garantir se oq foi digitado esta de acordo com os padroes necessarios
	//(ex: digitar um nome entre 3 a 100 caracteres, 
	//caso nao corresponda com os valores necessarios, ele apontará o erro)
	public Dentista gravar(@Valid @RequestBody Dentista dentista) {
		return dentistaRepository.save(dentista);
	}
	
	@DeleteMapping("/dentistas/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		dentistaRepository.deleteById(codigo);
	}
	
	@PutMapping("/dentistas")
	public void atualizar(@Valid @RequestBody Dentista dentista) {
		dentistaRepository.save(dentista);
	}

}
