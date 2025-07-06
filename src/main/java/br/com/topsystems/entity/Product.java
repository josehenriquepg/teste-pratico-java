package br.com.topsystems.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	// Identificador único do produto
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	// Código único do produto no sistema
	@Size(min = 1, max = 20)
	@Column(name = "product_code", nullable = false, length = 20)
    private String productCode;

	// Nome/descrição do produto
	@Size(min = 1, max = 100)
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	// Porcentagem de comissão sobre vendas
	@Column(name = "percentage_commission", nullable = false, precision = 5, scale = 2)
	private BigDecimal percentageCommission;
	
	// Desconto máximo permitido
	@Column(name = "max_descount", precision = 5, scale = 2)
    private BigDecimal maxDescount;
    
    // Margem de lucro padrão
	@Column(name = "standard_margin", nullable = false, precision = 5, scale = 2)
	private BigDecimal standardMargin;
    
    // Letra de classificação do produto
    @Column(name = "product_letter", nullable = false)
    private String productLetter;
    
    // Cor do produto
    @Column(name = "product_color", nullable = false)
    private String productColor;
    
    // Tipo/categoria do produto
    @Column(name = "product_type", nullable = false)
    private String productType;
    
    // Data de cadastro do produto
    @Column(name = "registration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
 
    // Status ativo/inativo
    @Column(name = "active", nullable = false)
    private Boolean active;
    
    @PrePersist
    protected void onCreate() {
    	registrationDate = new Date();
    }
    
    /**
     * Construtor padrão
     */
    public Product() {}
    
    /**
     * Construtor com parâmetros principais
     */
    public Product(String productCode, String description, BigDecimal percentageCommission, 
                   BigDecimal maxDescount, BigDecimal standardMargin, 
                   String productLetter, String productColor, String productType) {
        this();
        this.productCode = productCode;
        this.description = description;
        this.percentageCommission = percentageCommission;
        this.maxDescount = maxDescount;
        this.standardMargin = standardMargin;
        this.productLetter = productLetter;
        this.productColor = productColor;
        this.productType = productType;
    }
 
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getProductCode() {
        return productCode;
    }
    
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getPercentageCommission() {
        return percentageCommission;
    }
    
    public void setPercentageCommission(BigDecimal percentageComission) {
        this.percentageCommission = percentageComission;
    }
    
    public BigDecimal getMaxDescount() {
        return maxDescount;
    }
    
    public void setMaxDescount(BigDecimal maxDescount) {
        this.maxDescount = maxDescount;
    }
    
    public BigDecimal getStandardMargin() {
        return standardMargin;
    }
    
    public void setStandardMargin(BigDecimal standardMargin) {
        this.standardMargin = standardMargin;
    }
    
    public String getProductLetter() {
        return productLetter;
    }
    
    public void setProductLetter(String productLetter) {
        this.productLetter = productLetter;
    }
    
    public String getproductColor() {
        return productColor;
    }
    
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + "]";
	}
}
