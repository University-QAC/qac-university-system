package com.effortcure.qac.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "university")
public class University {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String name;
    private String logoPath;

    @OneToMany(mappedBy = "university")
    private Set<Collage> collages = new HashSet<>();

    @OneToMany(mappedBy = "university")
    private Set<UniversityAccounts> universityAccounts = new HashSet<>();

    public University() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Set<Collage> getCollages() {
        return collages;
    }

    public void setCollages(Set<Collage> collages) {
        this.collages = collages;
    }

    public Set<UniversityAccounts> getUniversityAccounts() {
        return universityAccounts;
    }

    public void setUniversityAccounts(Set<UniversityAccounts> universityAccounts) {
        this.universityAccounts = universityAccounts;
    }
}
