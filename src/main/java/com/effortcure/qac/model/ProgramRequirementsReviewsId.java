package com.effortcure.qac.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class ProgramRequirementsReviewsId implements Serializable {
    @Embedded
    private ProgramRequirementsId programRequirementsId;
    private UUID accountUuid;

    public ProgramRequirementsReviewsId() {
    }

    public ProgramRequirementsReviewsId(ProgramRequirementsId programRequirementsId, UUID accountUuid) {
        this.programRequirementsId = programRequirementsId;
        this.accountUuid = accountUuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((programRequirementsId == null) ? 0 : programRequirementsId.hashCode());
        result = prime * result + ((accountUuid == null) ? 0 : accountUuid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProgramRequirementsReviewsId other = (ProgramRequirementsReviewsId) obj;
        if (programRequirementsId == null) {
            if (other.programRequirementsId != null)
                return false;
        } else if (!programRequirementsId.equals(other.programRequirementsId))
            return false;
        if (accountUuid == null) {
            if (other.accountUuid != null)
                return false;
        } else if (!accountUuid.equals(other.accountUuid))
            return false;
        return true;
    }

    public ProgramRequirementsId getProgramRequirementsId() {
        return programRequirementsId;
    }

    public void setProgramRequirementsId(ProgramRequirementsId programRequirementsId) {
        this.programRequirementsId = programRequirementsId;
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
    }

}
