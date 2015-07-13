package it.av.apps.model.cargo;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.IndexedEmbedded;

import it.av.apps.model.BasicEntity;
import it.av.apps.model.City;
import it.av.apps.model.Country;

@Entity
@Table(name = "customers", indexes = { @Index(name = "idx_customers_companyName", columnList = "companyName") })
public class Customer extends BasicEntity {

    public static final String COMPANY_NAME  = "companyName";
    
    private static final long serialVersionUID = 1L;

    private String companyName;
    private String companyName2;
    private String address;
    @ManyToOne
    private Country country;
    @IndexedEmbedded
    @ManyToOne
    private City city;
    private String province;
    private String type;
    private String languague;
    private String currency;
    private String fiscalCode;
    private String vatCode;
    private String refFirstName;
    private String refLastName;
    private String refPhone1;
    private String refPhone2;
    private String refEmail;
    
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyName2() {
        return companyName2;
    }
    public void setCompanyName2(String companyName2) {
        this.companyName2 = companyName2;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLanguague() {
        return languague;
    }
    public void setLanguague(String languague) {
        this.languague = languague;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getFiscalCode() {
        return fiscalCode;
    }
    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }
    public String getVatCode() {
        return vatCode;
    }
    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }
    public String getRefFirstName() {
        return refFirstName;
    }
    public void setRefFirstName(String refFirstName) {
        this.refFirstName = refFirstName;
    }
    public String getRefLastName() {
        return refLastName;
    }
    public void setRefLastName(String refLastName) {
        this.refLastName = refLastName;
    }
    public String getRefPhone1() {
        return refPhone1;
    }
    public void setRefPhone1(String refPhone1) {
        this.refPhone1 = refPhone1;
    }
    public String getRefPhone2() {
        return refPhone2;
    }
    public void setRefPhone2(String refPhone2) {
        this.refPhone2 = refPhone2;
    }
    public String getRefEmail() {
        return refEmail;
    }
    public void setRefEmail(String refEmail) {
        this.refEmail = refEmail;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    

}
