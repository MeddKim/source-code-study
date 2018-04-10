package wang.willard.boot.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class VendorBusinessCashDeposit {
    private String id;
    private String vendorCompanyId;
    private String businessTaskId;
    private BigDecimal preAmount;
    private BigDecimal amount;
    private Integer businessType;
    private String businessDesc;
    private Integer type;
    private String reason;
    private Integer status;
    private String bankTransaction;
    private String auditor;
    private Timestamp auditTime;
    private String proposer;
    private Timestamp createAt;
    private Boolean isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendorCompanyId() {
        return vendorCompanyId;
    }

    public void setVendorCompanyId(String vendorCompanyId) {
        this.vendorCompanyId = vendorCompanyId;
    }

    public String getBusinessTaskId() {
        return businessTaskId;
    }

    public void setBusinessTaskId(String businessTaskId) {
        this.businessTaskId = businessTaskId;
    }

    public BigDecimal getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(BigDecimal preAmount) {
        this.preAmount = preAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getBusinessDesc() {
        return businessDesc;
    }

    public void setBusinessDesc(String businessDesc) {
        this.businessDesc = businessDesc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBankTransaction() {
        return bankTransaction;
    }

    public void setBankTransaction(String bankTransaction) {
        this.bankTransaction = bankTransaction;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Timestamp getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Timestamp auditTime) {
        this.auditTime = auditTime;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
