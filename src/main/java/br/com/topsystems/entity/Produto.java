package br.com.topsystems.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produtos")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_produto")
	private Long id;

	// Código único do produto
	@Size(min = 1, max = 20)
	@Column(name = "codigo", length = 20, nullable = false)
	private String codigo;
	
	// Descrição do produto
	@Size( min = 3, max = 100)
	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;
	
	// Porcentagem de comissão sobre vendas
	@Column(name = "comissao", precision = 5, scale = 2)
	private BigDecimal comissao;
		
	// Desconto máximo permitido
	@Column(name = "desconto_max", precision = 5, scale = 2)
    private BigDecimal descontoMax;
	    
	// Margem de lucro padrão
	@Column(name = "margem_lucro", precision = 5, scale = 2)
	private BigDecimal margemLucro;

	public Produto() {
		
	}
	
	public Produto(String codigo, String descricao, BigDecimal comissao, BigDecimal descontoMax, BigDecimal margemLucro) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.comissao = comissao;
		this.descontoMax = descontoMax;
		this.margemLucro = margemLucro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public BigDecimal getDescontoMax() {
		return descontoMax;
	}

	public void setDescontoMax(BigDecimal descontoMax) {
		this.descontoMax = descontoMax;
	}

	public BigDecimal getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(BigDecimal margemLucro) {
		this.margemLucro = margemLucro;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", comissao=" + comissao
				+ ", descontoMax=" + descontoMax + ", margemLucro=" + margemLucro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
