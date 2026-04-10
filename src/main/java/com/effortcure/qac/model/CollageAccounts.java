package com.effortcure.qac.model;

import com.effortcure.qac.enums.CollageRoles;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "collage_accounts")
public class CollageAccounts {

    @EmbeddedId
    private CollageAccountsId collageAccountsId;

    @MapsId("collageUuid")
    @ManyToOne
    @JoinColumn(name = "collage_uuid")
    private Collage collage;

    @MapsId("accountUuid")
    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Account account;

    @Enumerated(EnumType.STRING)
    private CollageRoles role;

    public CollageAccounts() {
    }

    public CollageAccountsId getCollageAccountsId() {
        return collageAccountsId;
    }

    public void setCollageAccountsId(CollageAccountsId collageAccountsId) {
        this.collageAccountsId = collageAccountsId;
    }

    public Collage getCollage() {
        return collage;
    }

    public void setCollage(Collage collage) {
        this.collage = collage;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public CollageRoles getRole() {
        return role;
    }

    public void setRole(CollageRoles role) {
        this.role = role;
    }

}
