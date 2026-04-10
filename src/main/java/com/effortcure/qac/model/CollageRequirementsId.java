package com.effortcure.qac.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class CollageRequirementsId implements Serializable {
    private UUID requirementUuid;
    private UUID collageUuid;

    public CollageRequirementsId() {
    }

    public CollageRequirementsId(UUID requirementUuid, UUID collageUuid) {
        this.requirementUuid = requirementUuid;
        this.collageUuid = collageUuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((requirementUuid == null) ? 0 : requirementUuid.hashCode());
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
        CollageRequirementsId other = (CollageRequirementsId) obj;
        if (requirementUuid == null) {
            if (other.requirementUuid != null)
                return false;
        } else if (!requirementUuid.equals(other.requirementUuid))
            return false;
        if (collageUuid == null) {
            if (other.collageUuid != null)
                return false;
        } else if (!collageUuid.equals(other.collageUuid))
            return false;
        return true;
    }

    public UUID getRequirementUuid() {
        return requirementUuid;
    }

    public void setRequirementUuid(UUID requirementUuid) {
        this.requirementUuid = requirementUuid;
    }

    public UUID getCollageUuid() {
        return collageUuid;
    }

    public void setCollageUuid(UUID collageUuid) {
        this.collageUuid = collageUuid;
    }

}
