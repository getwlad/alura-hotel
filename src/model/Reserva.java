package model;

import java.math.BigDecimal;
import java.util.Date;

public class Reserva {
	private int id;
	private Date dataEntrada;
	private Date dataSaida;
	private String formaPagamento;
	private BigDecimal valor;
	
	public Reserva(Date dataEntrada, Date dataSaida, String formaPagamento, BigDecimal valor) {
		super();
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.formaPagamento = formaPagamento;
		this.valor = valor;
	}
	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaidade) {
		this.dataSaida = dataSaidade;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
