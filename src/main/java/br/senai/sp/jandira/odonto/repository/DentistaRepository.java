package br.senai.sp.jandira.odonto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.jandira.odonto.model.Dentista;

public interface DentistaRepository extends JpaRepository<Dentista, Long>{
	//é um banco de dados no contexto que estamos fazendo <generic> a classe é dentista e o indetificador é o Long
	// ele faz o crud insert dentro, ele identifica em qual tabela deve ser colocado o dado que for inserido
	
	Dentista findByNome(String nome);

}
