package com.effortcure.qac.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "collage_requirements")
public class CollageRequirements {
    @EmbeddedId
    private CollageRequirementsId collageRequirementsId;

    @MapsId("collageUuid")
    @ManyToOne
    @JoinColumn(name = "collage_uuid")
    private Collage collage;

    @MapsId("requirementUuid")
    @ManyToOne
    @JoinColumn(name = "requirement_uuid")
    private Requirement requirement;

    private LocalDateTime deadlineFrom;
    private LocalDateTime deadlineTo;
    private Boolean isFulfilled;

    @OneToMany(mappedBy = "collageRequirements")
    private Set<CollageRequirementsReviews> collageRequirementsReviews = new HashSet<>();

    public CollageRequirements() {
    }

    public CollageRequirementsId getCollageRequirementsId() {
        return collageRequirementsId;
    }

    public void setCollageRequirementsId(CollageRequirementsId collageRequirementsId) {
        this.collageRequirementsId = collageRequirementsId;
    }

    public Collage getCollage() {
        return collage;
    }

    public void setCollage(Collage collage) {
        this.collage = collage;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    public LocalDateTime getDeadlineFrom() {
        return deadlineFrom;
    }

    public void setDeadlineFrom(LocalDateTime deadlineFrom) {
        this.deadlineFrom = deadlineFrom;
    }

    public LocalDateTime getDeadlineTo() {
        return deadlineTo;
    }

    public void setDeadlineTo(LocalDateTime deadlineTo) {
        this.deadlineTo = deadlineTo;
    }

    public Boolean getIsFulfilled() {
        return isFulfilled;
    }

    public void setIsFulfilled(Boolean isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

    public Set<CollageRequirementsReviews> getCollageRequirementsReviews() {
        return collageRequirementsReviews;
    }

    public void setCollageRequirementsReviews(Set<CollageRequirementsReviews> collageRequirementsReviews) {
        this.collageRequirementsReviews = collageRequirementsReviews;
    }

}
