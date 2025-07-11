package br.com.topsystems.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsystems.dao.ProdutoDAO;
import br.com.topsystems.entity.Produto;
import br.com.topsystems.util.FacesUtil;

@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoBean {
	private Produto produto;
	private List<Produto> listaProduto;
	private List<Produto> listaProdutoFiltrados;
	private String acao;
	private Long id;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public List<Produto> getListaProdutoFiltrados() {
		return listaProdutoFiltrados;
	}

	public void setListaProdutoFiltrados(List<Produto> listaProdutoFiltrados) {
		this.listaProdutoFiltrados = listaProdutoFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void novo() {
		produto = new Produto();
	}

	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produto);
			novo();
			FacesUtil.adicionarMensagemInfo("Produto Salvo com Sucesso");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMensagemErro("Erro ao tentar incluir um Produto: " + ex.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProduto = produtoDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMensagemErro("Erro ao Listar os Produtos: " + ex.getMessage());
		}
	}

	public void excluir() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			FacesUtil.adicionarMensagemInfo("Produto Removido com Sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar remover um Produto: " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.editar(produto);
			FacesUtil.adicionarMensagemInfo("Produto Editado com Sucesso");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMensagemErro("Erro ao tentar editar um Produto: " + ex.getMessage());
		}
	}
}
