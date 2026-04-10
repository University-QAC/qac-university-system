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
@Table(name = "program_requirements")
public class ProgramRequirements {
    @EmbeddedId
    private ProgramRequirementsId programRequirementsId;

    @MapsId("programUuid")
    @ManyToOne
    @JoinColumn(name = "program_uuid")
    private Program program;

    @MapsId("requirementUuid")
    @ManyToOne
    @JoinColumn(name = "requirement_uuid")
    private Requirement requirement;

    private LocalDateTime deadlineFrom;
    private LocalDateTime deadlineTo;
    private Boolean isFulfilled;

    @OneToMany(mappedBy = "programRequirements")
    private Set<ProgramRequirementsReviews> programRequirementsReviews = new HashSet<>();

    public ProgramRequirements() {
    }

    public ProgramRequirementsId getProgramRequirementsId() {
        return programRequirementsId;
    }

    public void setProgramRequirementsId(ProgramRequirementsId programRequirementsId) {
        this.programRequirementsId = programRequirementsId;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
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

    public Set<ProgramRequirementsReviews> getProgramRequirementsReviews() {
        return programRequirementsReviews;
    }

    public void setProgramRequirementsReviews(Set<ProgramRequirementsReviews> programRequirementsReviews) {
        this.programRequirementsReviews = programRequirementsReviews;
    }

}
