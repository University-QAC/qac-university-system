package com.effortcure.qac.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class CollageAccountsId implements Serializable {
    private UUID accountUuid;
    private UUID collageUuid;

    public CollageAccountsId() {
    }

    public CollageAccountsId(UUID accountUuid, UUID collageUuid) {
        this.accountUuid = accountUuid;
        this.collageUuid = collageUuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountUuid == null) ? 0 : accountUuid.hashCode());
        result = prime * result + ((collageUuid == null) ? 0 : collageUuid.hashCode());
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
        CollageAccountsId other = (CollageAccountsId) obj;
        if (accountUuid == null) {
            if (other.accountUuid != null)
                return false;
        } else if (!accountUuid.equals(other.accountUuid))
            return false;
        if (collageUuid == null) {
            if (other.collageUuid != null)
                return false;
        } else if (!collageUuid.equals(other.collageUuid))
            return false;
        return true;
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
    }

    public UUID getCollageUuid() {
        return collageUuid;
    }

    public void setCollageUuid(UUID collageUuid) {
        this.collageUuid = collageUuid;
    }

}
