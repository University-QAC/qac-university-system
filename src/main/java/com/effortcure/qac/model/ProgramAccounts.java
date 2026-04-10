package com.effortcure.qac.model;

import com.effortcure.qac.enums.ProgramRoles;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "program_accounts")
public class ProgramAccounts {
    @EmbeddedId
    private ProgramAccountsId programAccountsId;

    @MapsId("accountUuid")
    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Account account;

    @MapsId("programUuid")
    @ManyToOne
    @JoinColumn(name = "program_uuid")
    private Program program;

    @Enumerated(EnumType.STRING)
    private ProgramRoles role;

    public ProgramAccounts() {
    }

    public ProgramAccountsId getProgramAccountsId() {
        return programAccountsId;
    }

    public void setProgramAccountsId(ProgramAccountsId programAccountsId) {
        this.programAccountsId = programAccountsId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public ProgramRoles getRole() {
        return role;
    }

    public void setRole(ProgramRoles role) {
        this.role = role;
    }

}
