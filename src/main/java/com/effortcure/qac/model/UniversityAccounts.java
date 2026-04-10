package com.effortcure.qac.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "university_accounts")
public class UniversityAccounts {
    @EmbeddedId
    private UniversityAccountsId universityAccountsId;

    @MapsId("accountUuid")
    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Account account;

    @MapsId("universityUuid")
    @ManyToOne
    @JoinColumn(name = "university_uuid")
    private University university;

    public UniversityAccounts() {
    }

    public UniversityAccountsId getUniversityAccountsId() {
        return universityAccountsId;
    }

    public void setUniversityAccountsId(UniversityAccountsId universityAccountsId) {
        this.universityAccountsId = universityAccountsId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

}
