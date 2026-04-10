package com.effortcure.qac.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProgramRequirementsId implements Serializable {
    private UUID requirementUuid;
    private UUID programUuid;

    public ProgramRequirementsId() {
    }

    public ProgramRequirementsId(UUID requirementUuid, UUID programUuid) {
        this.requirementUuid = requirementUuid;
        this.programUuid = programUuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((requirementUuid == null) ? 0 : requirementUuid.hashCode());
        result = prime * result + ((programUuid == null) ? 0 : programUuid.hashCode());
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
        ProgramRequirementsId other = (ProgramRequirementsId) obj;
        if (requirementUuid == null) {
            if (other.requirementUuid != null)
                return false;
        } else if (!requirementUuid.equals(other.requirementUuid))
            return false;
        if (programUuid == null) {
            if (other.programUuid != null)
                return false;
        } else if (!programUuid.equals(other.programUuid))
            return false;
        return true;
    }

    public UUID getRequirementUuid() {
        return requirementUuid;
    }

    public void setRequirementUuid(UUID requirementUuid) {
        this.requirementUuid = requirementUuid;
    }

    public UUID getProgramUuid() {
        return programUuid;
    }

    public void setProgramUuid(UUID programUuid) {
        this.programUuid = programUuid;
    }

}
