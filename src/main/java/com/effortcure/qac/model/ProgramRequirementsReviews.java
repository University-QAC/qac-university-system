package com.effortcure.qac.model;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "program_requirements_reviews")
public class ProgramRequirementsReviews {
    @EmbeddedId
    private ProgramRequirementsReviewsId programRequirementsReviewsId;

    @MapsId("programRequirementsId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "requirement_uuid"),
            @JoinColumn(name = "program_uuid")
    })
    private ProgramRequirements programRequirements;

    @MapsId("accountUuid")
    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Account account;

    private BigDecimal score;
    private String notes;

    public ProgramRequirementsReviews() {
    }

    public ProgramRequirementsReviewsId getProgramRequirementsReviewsId() {
        return programRequirementsReviewsId;
    }

    public void setProgramRequirementsReviewsId(ProgramRequirementsReviewsId programRequirementsReviewsId) {
        this.programRequirementsReviewsId = programRequirementsReviewsId;
    }

    public ProgramRequirements getProgramRequirements() {
        return programRequirements;
    }

    public void setProgramRequirements(ProgramRequirements programRequirements) {
        this.programRequirements = programRequirements;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
