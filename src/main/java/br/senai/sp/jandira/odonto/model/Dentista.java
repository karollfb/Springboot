package br.senai.sp.jandira.odonto.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_dentista") // ele procurará um atabela com esse nome, caso nao encontre, ele ira criar um table com esse nome
public class Dentista {

	@Id //ele só funciona tendo um comentario com "@" em cima do codigo que será feito
	@GeneratedValue(strategy = GenerationType.IDENTITY)// auto increment do bd
	private Long codigo; // nosso id e auto_increment, já que o @id esta
	
	//anotações, definindo o tamanho min e max, e se o campo poderá ser vazio ou nao
	@NotNull
	@Size(min = 3, max = 100, message = "deve ter entre 3 a 100 caracteres, grrrr >:c")
	private String nome;
	
	private String cro;
	private String email;
	private String telefone;
	
	//RELACIONANDO TABELAS
	
	//joinColumns -> referenciar as colunas
	//@JoinColumn -> especificar a coluna que esta sendo referenciada
	//associação, ela liga duas tabelas 
	@ManyToMany
	@JoinTable(name = "tbl_dentista_especialidade",
	//referenciando as tabelas, em seguida referenciando o campo codigo da tabela, e passando como referencia o nome do campo
				joinColumns = @JoinColumn(name = "dentista_codigo", referencedColumnName = "codigo"), 
				// é a "outra tabela", o inverse pede qual será o campo a ser relacionado (como InnerJoin, que relaciona as tabelas)
				inverseJoinColumns = @JoinColumn(name = "especialidade_codigo", referencedColumnName = "codigo")
			)
	//lista(array) das especialidades (classe Especialidade) de cada dentista
	private List<Especialidade> especialidades;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCro() {
		return cro;
	}
	public void setCro(String cro) {
		this.cro = cro;
	}
	public String getEmail() {
		return email;
	}
	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String toString() {
		return "Dentista [codigo=" + codigo + ", nome=" + nome + ", cro=" + cro + ", email=" + email + ", telefone="
				+ telefone + "]";
	}
	
	
	
	
}
