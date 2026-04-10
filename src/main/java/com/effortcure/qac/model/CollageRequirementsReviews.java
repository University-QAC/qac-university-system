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
@Table(name = "collage_requirements_reviews")
public class CollageRequirementsReviews {
    @EmbeddedId
    private CollageRequirementsReviewsId collageRequirementsReviewsId;

    @MapsId("collageRequirementsId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "requirement_uuid"),
            @JoinColumn(name = "collage_uuid")
    })
    private CollageRequirements collageRequirements;

    @MapsId("accountUuid")
    @ManyToOne
    @JoinColumn(name = "account_uuid")
    private Account account;

    private BigDecimal score;
    private String notes;

    public CollageRequirementsReviews() {
    }

    public CollageRequirementsReviewsId getCollageRequirementsReviewsId() {
        return collageRequirementsReviewsId;
    }

    public void setCollageRequirementsReviewsId(CollageRequirementsReviewsId collageRequirementsReviewsId) {
        this.collageRequirementsReviewsId = collageRequirementsReviewsId;
    }

    public CollageRequirements getCollageRequirements() {
        return collageRequirements;
    }

    public void setCollageRequirements(CollageRequirements collageRequirements) {
        this.collageRequirements = collageRequirements;
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
