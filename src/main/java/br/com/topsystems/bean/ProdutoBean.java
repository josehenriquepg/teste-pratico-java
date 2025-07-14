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
	private Produto produtoSelecionado;
	private String termoPesquisa;
	private Filtro filtro;
	private List<Produto> listaProduto;
	private List<Produto> listaProdutoFiltrados;
	private boolean exibirFormulario;
	private boolean modoEdicao;
	private ProdutoDAO produtoDAO;

	public void novo() {
		produto = new Produto();
	}

	public void pesquisar() {
		try {
			if (termoPesquisa != null && !termoPesquisa.trim().isEmpty()) {
				listaProduto = produtoDAO.pesquisarDescricao(termoPesquisa.trim());
				FacesUtil.adicionarMensagemInfo("Pesquisa realizada");
			} else {
				carregarProdutos();
			}
		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro na pesquisa: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void limparPesquisa() {
		this.termoPesquisa = "";
		carregarProdutos();
		FacesUtil.adicionarMensagemInfo("Pesquisa limpa");
	}

	private void carregarProdutos() {
		try {
			listaProduto = produtoDAO.listar();
		} catch (Exception e) {
			FacesUtil.adicionarMensagemErro("Erro ao carregar produtos: " + e.getMessage());
			e.printStackTrace();
		}
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

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}

	public boolean isExibirFormulario() {
		return exibirFormulario;
	}

	public void setExibirFormulario(boolean exibirFormulario) {
		this.exibirFormulario = exibirFormulario;
	}

	public boolean isModoEdicao() {
		return modoEdicao;
	}

	public void setModoEdicao(boolean modoEdicao) {
		this.modoEdicao = modoEdicao;
	}

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

	public static class Filtro {
		private String campo;
		private String comparacao;
		private String valor;

		public Filtro() {
			this.campo = "";
			this.comparacao = "";
			this.valor = "";
		}

		// Getters e Setters
		public String getCampo() {
			return campo;
		}

		public void setCampo(String campo) {
			this.campo = campo;
		}

		public String getComparacao() {
			return comparacao;
		}

		public void setComparacao(String comparacao) {
			this.comparacao = comparacao;
		}

		public String getValor() {
			return valor;
		}

		public void setValor(String valor) {
			this.valor = valor;
		}
	}
}
