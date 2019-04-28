package com.mzkj.vo.process;

import com.mzkj.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 代理记账查询vo
 * 创建人：CDCXH
 * 创建时间：2019-04-22
 */
@ApiModel(value = "Tally查询对象", description = "Tally查询")
public class TallyQueryVo extends BaseVo {
    private String tallyId;
    private String companyName;
    private String companyLandline;
    private String companyType;
    private String legalRepresentative;
    private String legalRepresentativeTel;
    private String supervisor;
    private String supervisorTel;
    private String shareholders;
    private String emergencyContact;
    private String emergencyTel;
    private String registerArea;
    private String registerAddress;
    private String beginDate;
    private String realAddress;
    private String addressType;
    private String scopeBusiness;
    private Double registeredCapital;
    private String dueDate;
    private String isPaid;
    private String einNo;
    private String nationalTaxPassword;
    private String landTaxPassword;
    private String nationalTaxManager;
    private String nationalTaxManagerTel;
    private String landTaxManager;
    private String landTaxManagerTel;
    private String annualReportCode;
    private Integer stampCount;
    private String account;
    private String initialBank;
    private String broughtToAccount;
    private String returnPrintingMethod;
    private String customerSource;
    private String saleDepartment;
    private String signPerson;
    private String accountingDepartment;
    private String bookkeeper;
    private Double unitPrice;
    private Double booksYearCharge;
    private String chargeCycle;
    private Double otherServiceCharges;
    private String contractPeriod;
    private String contractCode;
    private String ensureDelegate;
    private Double price;
    private String customerFocusProblem;
    private String customerPersonalityDescription;
    private String industry;
    private String customerSegmentation;
    private String vatType;
    private String drawerDepartment;
    private String drawer;
    private String contractDate;
    private String note;
    private String businessTypes;
    private Double paymentAmount;
    private String paymentMethods;
    private String collectionCustomer;
    private String code;
    private String potentialDemand;
    private String cUser;
    private String mDate;
    private String mUser;
    private String cDate;
    private String customerRepresentative;
    private String procInstId;
    private String ensureDisc;
    private String personTaxPassword;
    private String backlogThings;
    private String tax;
    private String collectionMethods;
    private String legalRepresentativeIdcard;
    private String isBooksCharge;
    private Integer amountMonthsDiscounts;
    private String tenantId;

    public String getTallyId() {
        return tallyId;
    }

    public void setTallyId(String tallyId) {
        this.tallyId = tallyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLandline() {
        return companyLandline;
    }

    public void setCompanyLandline(String companyLandline) {
        this.companyLandline = companyLandline;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getLegalRepresentativeTel() {
        return legalRepresentativeTel;
    }

    public void setLegalRepresentativeTel(String legalRepresentativeTel) {
        this.legalRepresentativeTel = legalRepresentativeTel;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorTel() {
        return supervisorTel;
    }

    public void setSupervisorTel(String supervisorTel) {
        this.supervisorTel = supervisorTel;
    }

    public String getShareholders() {
        return shareholders;
    }

    public void setShareholders(String shareholders) {
        this.shareholders = shareholders;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyTel() {
        return emergencyTel;
    }

    public void setEmergencyTel(String emergencyTel) {
        this.emergencyTel = emergencyTel;
    }

    public String getRegisterArea() {
        return registerArea;
    }

    public void setRegisterArea(String registerArea) {
        this.registerArea = registerArea;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getRealAddress() {
        return realAddress;
    }

    public void setRealAddress(String realAddress) {
        this.realAddress = realAddress;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getScopeBusiness() {
        return scopeBusiness;
    }

    public void setScopeBusiness(String scopeBusiness) {
        this.scopeBusiness = scopeBusiness;
    }

    public Double getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(Double registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid = isPaid;
    }

    public String getEinNo() {
        return einNo;
    }

    public void setEinNo(String einNo) {
        this.einNo = einNo;
    }

    public String getNationalTaxPassword() {
        return nationalTaxPassword;
    }

    public void setNationalTaxPassword(String nationalTaxPassword) {
        this.nationalTaxPassword = nationalTaxPassword;
    }

    public String getLandTaxPassword() {
        return landTaxPassword;
    }

    public void setLandTaxPassword(String landTaxPassword) {
        this.landTaxPassword = landTaxPassword;
    }

    public String getNationalTaxManager() {
        return nationalTaxManager;
    }

    public void setNationalTaxManager(String nationalTaxManager) {
        this.nationalTaxManager = nationalTaxManager;
    }

    public String getNationalTaxManagerTel() {
        return nationalTaxManagerTel;
    }

    public void setNationalTaxManagerTel(String nationalTaxManagerTel) {
        this.nationalTaxManagerTel = nationalTaxManagerTel;
    }

    public String getLandTaxManager() {
        return landTaxManager;
    }

    public void setLandTaxManager(String landTaxManager) {
        this.landTaxManager = landTaxManager;
    }

    public String getLandTaxManagerTel() {
        return landTaxManagerTel;
    }

    public void setLandTaxManagerTel(String landTaxManagerTel) {
        this.landTaxManagerTel = landTaxManagerTel;
    }

    public String getAnnualReportCode() {
        return annualReportCode;
    }

    public void setAnnualReportCode(String annualReportCode) {
        this.annualReportCode = annualReportCode;
    }

    public Integer getStampCount() {
        return stampCount;
    }

    public void setStampCount(Integer stampCount) {
        this.stampCount = stampCount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getInitialBank() {
        return initialBank;
    }

    public void setInitialBank(String initialBank) {
        this.initialBank = initialBank;
    }

    public String getBroughtToAccount() {
        return broughtToAccount;
    }

    public void setBroughtToAccount(String broughtToAccount) {
        this.broughtToAccount = broughtToAccount;
    }

    public String getReturnPrintingMethod() {
        return returnPrintingMethod;
    }

    public void setReturnPrintingMethod(String returnPrintingMethod) {
        this.returnPrintingMethod = returnPrintingMethod;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getSaleDepartment() {
        return saleDepartment;
    }

    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    public String getSignPerson() {
        return signPerson;
    }

    public void setSignPerson(String signPerson) {
        this.signPerson = signPerson;
    }

    public String getAccountingDepartment() {
        return accountingDepartment;
    }

    public void setAccountingDepartment(String accountingDepartment) {
        this.accountingDepartment = accountingDepartment;
    }

    public String getBookkeeper() {
        return bookkeeper;
    }

    public void setBookkeeper(String bookkeeper) {
        this.bookkeeper = bookkeeper;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getBooksYearCharge() {
        return booksYearCharge;
    }

    public void setBooksYearCharge(Double booksYearCharge) {
        this.booksYearCharge = booksYearCharge;
    }

    public String getChargeCycle() {
        return chargeCycle;
    }

    public void setChargeCycle(String chargeCycle) {
        this.chargeCycle = chargeCycle;
    }

    public Double getOtherServiceCharges() {
        return otherServiceCharges;
    }

    public void setOtherServiceCharges(Double otherServiceCharges) {
        this.otherServiceCharges = otherServiceCharges;
    }

    public String getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(String contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getEnsureDelegate() {
        return ensureDelegate;
    }

    public void setEnsureDelegate(String ensureDelegate) {
        this.ensureDelegate = ensureDelegate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCustomerFocusProblem() {
        return customerFocusProblem;
    }

    public void setCustomerFocusProblem(String customerFocusProblem) {
        this.customerFocusProblem = customerFocusProblem;
    }

    public String getCustomerPersonalityDescription() {
        return customerPersonalityDescription;
    }

    public void setCustomerPersonalityDescription(String customerPersonalityDescription) {
        this.customerPersonalityDescription = customerPersonalityDescription;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCustomerSegmentation() {
        return customerSegmentation;
    }

    public void setCustomerSegmentation(String customerSegmentation) {
        this.customerSegmentation = customerSegmentation;
    }

    public String getVatType() {
        return vatType;
    }

    public void setVatType(String vatType) {
        this.vatType = vatType;
    }

    public String getDrawerDepartment() {
        return drawerDepartment;
    }

    public void setDrawerDepartment(String drawerDepartment) {
        this.drawerDepartment = drawerDepartment;
    }

    public String getDrawer() {
        return drawer;
    }

    public void setDrawer(String drawer) {
        this.drawer = drawer;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(String businessTypes) {
        this.businessTypes = businessTypes;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(String paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String getCollectionCustomer() {
        return collectionCustomer;
    }

    public void setCollectionCustomer(String collectionCustomer) {
        this.collectionCustomer = collectionCustomer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPotentialDemand() {
        return potentialDemand;
    }

    public void setPotentialDemand(String potentialDemand) {
        this.potentialDemand = potentialDemand;
    }

    public String getcUser() {
        return cUser;
    }

    public void setcUser(String cUser) {
        this.cUser = cUser;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getCustomerRepresentative() {
        return customerRepresentative;
    }

    public void setCustomerRepresentative(String customerRepresentative) {
        this.customerRepresentative = customerRepresentative;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getEnsureDisc() {
        return ensureDisc;
    }

    public void setEnsureDisc(String ensureDisc) {
        this.ensureDisc = ensureDisc;
    }

    public String getPersonTaxPassword() {
        return personTaxPassword;
    }

    public void setPersonTaxPassword(String personTaxPassword) {
        this.personTaxPassword = personTaxPassword;
    }

    public String getBacklogThings() {
        return backlogThings;
    }

    public void setBacklogThings(String backlogThings) {
        this.backlogThings = backlogThings;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getCollectionMethods() {
        return collectionMethods;
    }

    public void setCollectionMethods(String collectionMethods) {
        this.collectionMethods = collectionMethods;
    }

    public String getLegalRepresentativeIdcard() {
        return legalRepresentativeIdcard;
    }

    public void setLegalRepresentativeIdcard(String legalRepresentativeIdcard) {
        this.legalRepresentativeIdcard = legalRepresentativeIdcard;
    }

    public String getIsBooksCharge() {
        return isBooksCharge;
    }

    public void setIsBooksCharge(String isBooksCharge) {
        this.isBooksCharge = isBooksCharge;
    }

    public Integer getAmountMonthsDiscounts() {
        return amountMonthsDiscounts;
    }

    public void setAmountMonthsDiscounts(Integer amountMonthsDiscounts) {
        this.amountMonthsDiscounts = amountMonthsDiscounts;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }


}

