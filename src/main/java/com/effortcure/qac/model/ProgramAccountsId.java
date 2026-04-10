package com.effortcure.qac.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProgramAccountsId implements Serializable {
    private UUID accountUuid;
    private UUID programUuid;

    public ProgramAccountsId() {
    }

    public ProgramAccountsId(UUID accountUuid, UUID programUuid) {
        this.accountUuid = accountUuid;
        this.programUuid = programUuid;
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
    }

    public UUID getProgramUuid() {
        return programUuid;
    }

    public void setProgramUuid(UUID programUuid) {
        this.programUuid = programUuid;
    }

}
