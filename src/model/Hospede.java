package model;

import java.util.Date;

public class Hospede {
	private int id;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private double telefone;
	private String nacionalidade;
	private int idReserva;
	
	public Hospede (String nome, String sobrenome, Date dataNascimento, double telefone, String nacionalidade,
			int idReserva) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.nacionalidade = nacionalidade;
		this.idReserva = idReserva;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public double getTelefone() {
		return telefone;
	}
	public void setTelefone(Double telefone) {
		this.telefone = telefone;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	
}
