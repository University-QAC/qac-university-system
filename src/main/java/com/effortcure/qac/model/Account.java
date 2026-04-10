package com.effortcure.qac.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String email;
    private String username;
    private String password;
    private String imagePath;
    private Boolean isVerified;
    private String verificationCode;
    private LocalDateTime codeExpiredAt;
    private LocalDateTime activatedUntil;

    @OneToMany(mappedBy = "account")
    private Set<UniversityAccounts> universityAccounts = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<CollageAccounts> collageAccounts = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<ProgramAccounts> programAccounts = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<CollageRequirementsReviews> collageRequirementsReviews = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<ProgramRequirementsReviews> programRequirementsReviews = new HashSet<>();

    public Account() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public LocalDateTime getCodeExpiredAt() {
        return codeExpiredAt;
    }

    public void setCodeExpiredAt(LocalDateTime codeExpiredAt) {
        this.codeExpiredAt = codeExpiredAt;
    }

    public LocalDateTime getActivatedUntil() {
        return activatedUntil;
    }

    public void setActivatedUntil(LocalDateTime activatedUntil) {
        this.activatedUntil = activatedUntil;
    }

    public Set<UniversityAccounts> getUniversityAccounts() {
        return universityAccounts;
    }

    public void setUniversityAccounts(Set<UniversityAccounts> universityAccounts) {
        this.universityAccounts = universityAccounts;
    }

    public Set<CollageAccounts> getCollageAccounts() {
        return collageAccounts;
    }

    public void setCollageAccounts(Set<CollageAccounts> collageAccounts) {
        this.collageAccounts = collageAccounts;
    }

    public Set<ProgramAccounts> getProgramAccounts() {
        return programAccounts;
    }

    public void setProgramAccounts(Set<ProgramAccounts> programAccounts) {
        this.programAccounts = programAccounts;
    }

    public Set<CollageRequirementsReviews> getCollageRequirementsReviews() {
        return collageRequirementsReviews;
    }

    public void setCollageRequirementsReviews(Set<CollageRequirementsReviews> collageRequirementsReviews) {
        this.collageRequirementsReviews = collageRequirementsReviews;
    }

    public Set<ProgramRequirementsReviews> getProgramRequirementsReviews() {
        return programRequirementsReviews;
    }

    public void setProgramRequirementsReviews(Set<ProgramRequirementsReviews> programRequirementsReviews) {
        this.programRequirementsReviews = programRequirementsReviews;
    }

}
