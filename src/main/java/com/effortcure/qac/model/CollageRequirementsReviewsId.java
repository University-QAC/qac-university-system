package com.effortcure.qac.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class CollageRequirementsReviewsId implements Serializable {
    @Embedded
    private CollageRequirementsId collageRequirementsId;
    private UUID accountUuid;

    public CollageRequirementsReviewsId() {
    }

    public CollageRequirementsReviewsId(CollageRequirementsId collageRequirementsId, UUID accountUuid) {
        this.collageRequirementsId = collageRequirementsId;
        this.accountUuid = accountUuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((collageRequirementsId == null) ? 0 : collageRequirementsId.hashCode());
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
        CollageRequirementsReviewsId other = (CollageRequirementsReviewsId) obj;
        if (collageRequirementsId == null) {
            if (other.collageRequirementsId != null)
                return false;
        } else if (!collageRequirementsId.equals(other.collageRequirementsId))
            return false;
        if (accountUuid == null) {
            if (other.accountUuid != null)
                return false;
        } else if (!accountUuid.equals(other.accountUuid))
            return false;
        return true;
    }

    public CollageRequirementsId getCollageRequirementsId() {
        return collageRequirementsId;
    }

    public void setCollageRequirementsId(CollageRequirementsId collageRequirementsId) {
        this.collageRequirementsId = collageRequirementsId;
    }

    public UUID getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(UUID accountUuid) {
        this.accountUuid = accountUuid;
    }

}
