package com.effortcure.qac.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class UniversityAccountsId implements Serializable {
    private UUID accountUuid;
    private UUID universityUuid;

    public UniversityAccountsId() {
    }

    public UniversityAccountsId(UUID accountUuid, UUID universityUuid) {
        this.accountUuid = accountUuid;
        this.universityUuid = universityUuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountUuid == null) ? 0 : accountUuid.hashCode());
        result = prime * result + ((universityUuid == null) ? 0 : universityUuid.hashCode());
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
        UniversityAccountsId other = (UniversityAccountsId) obj;
        if (accountUuid == null) {
            if (other.accountUuid != null)
                return false;
        } else if (!accountUuid.equals(other.accountUuid))
            return false;
        if (universityUuid == null) {
            if (other.universityUuid != null)
                return false;
        } else if (!universityUuid.equals(other.universityUuid))
            return false;
        return true;
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
    }

    public UUID getUniversityUuid() {
        return universityUuid;
    }

    public void setUniversityUuid(UUID universityUuid) {
        this.universityUuid = universityUuid;
    }

}
