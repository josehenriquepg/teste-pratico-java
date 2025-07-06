package br.com.topsystems.bean;

import br.com.topsystems.dao.ProductDAO;
import br.com.topsystems.entity.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ProductBean implements Serializable {
	
	@Inject
    private ProductDAO productDAO;
	
	private Product product;
    private List<Product> products;
    private List<Product> filteredProducts;
    private Product selectedProduct;
    
    // Filtros
    private String filterDescription;
    private String filterProductCode;
    
    // Flags de controle
    private boolean displayDialogFilter = false;
    private boolean editing = false;
    
    @PostConstruct
    public void init() {
        product = new Product();
        loadingProducts();
    }
    
    public void loadingProducts() {
        try {
            products = productDAO.listAllProducts();
            filteredProducts = new ArrayList<>(products);
        } catch (Exception e) {
        	addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao carregar produtos: " + e.getMessage());
        }
    }

    public void newProduct() {
        product = new Product();
        editing = false;
    }
    
    public void editProduct() {
        if (selectedProduct != null) {
            product = new Product();
            product.setId(selectedProduct.getId());
            product.setProductCode(selectedProduct.getProductCode());
            product.setDescription(selectedProduct.getDescription());
            product.setRegistrationDate(selectedProduct.getRegistrationDate());
            editing = true;
        } else {
        	addMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione um produto para editar.");
        }
    }
    
    public void saveProduct() {
        try {
            if (product.getProductCode() == null || product.getProductCode().trim().isEmpty()) {
            	addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Código é obrigatório.");
                return;
            }
            
            if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
            	addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Descrição é obrigatória.");
                return;
            }
            
            productDAO.save(product);
            
            String message = editing ? "Produto atualizado com sucesso!" : "Produto cadastrado com sucesso!";
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", message);
            
            loadingProducts();
            product = new Product();
            
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao salvar produto: " + e.getMessage());
        }
    }
    
    public void deleteProduct() {
        if (selectedProduct != null) {
            try {
                productDAO.delete(selectedProduct);
                addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Produto excluído com sucesso!");
                loadingProducts();
                selectedProduct = null;
            } catch (Exception e) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao excluir produto: " + e.getMessage());
            }
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Atenção", "Selecione um produto para excluir.");
        }
    }
    
    public void openFilter() {
        displayDialogFilter = true;
    }
    
    public void aplicarFiltro() {
        try {
            if ((filterDescription == null || filterDescription.trim().isEmpty()) && 
                (filterProductCode == null || filterProductCode.trim().isEmpty())) {
                filteredProducts = new ArrayList<>(products);
            } else {
                filteredProducts = productDAO.searchWithFilters(filterDescription, filterProductCode);
            }
            displayDialogFilter = false;
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Filtro aplicado com sucesso!");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao aplicar filtro: " + e.getMessage());
        }
    }
    
    public void limparFiltro() {
        filterDescription = null;
        filterProductCode = null;
        filteredProducts = new ArrayList<>(products);
        displayDialogFilter = false;
        addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Filtro limpo com sucesso!");
    }
    
    public void searchByDescription() {
        if (filterDescription != null && !filterDescription.trim().isEmpty()) {
            try {
                filteredProducts = productDAO.searchByName(filterDescription);
                addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Busca realizada com sucesso!");
            } catch (Exception e) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao buscar produtos: " + e.getMessage());
            }
        } else {
            loadingProducts();
        }
    }
    
    public void cancelEdit() {
        product = new Product();
        displayDialogFilter = false;
    }
    
    public List<String> getProductCode() {
        try {
            return productDAO.listByCode();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(severity, summary, detail));
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Product> getFilteredProducts() {
		return filteredProducts;
	}

	public void setFilteredProducts(List<Product> filteredProducts) {
		this.filteredProducts = filteredProducts;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public String getFilterDescription() {
		return filterDescription;
	}

	public void setFilterDescription(String filterDescription) {
		this.filterDescription = filterDescription;
	}

	public String getFilterProductCode() {
		return filterProductCode;
	}

	public void setFilterProductCode(String filterProductCode) {
		this.filterProductCode = filterProductCode;
	}

	public boolean isDisplayDialogFilter() {
		return displayDialogFilter;
	}

	public void setDisplayDialogFilter(boolean displayDialogFilter) {
		this.displayDialogFilter = displayDialogFilter;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}
    
}
