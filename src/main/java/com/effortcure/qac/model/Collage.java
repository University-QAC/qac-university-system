package com.effortcure.qac.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "collage")
public class Collage {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String name;
    private String logoPath;

    @ManyToOne
    @JoinColumn(name = "university")
    private University university;

    @OneToMany(mappedBy = "collage")
    private Set<Program> programs = new HashSet<>();

    @OneToMany(mappedBy = "collage")
    private Set<CollageAccounts> collageAccounts = new HashSet<>();

    @OneToMany(mappedBy = "collage")
    private Set<CollageRequirements> collageRequirements = new HashSet<>();

    public Collage() {
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    public Set<CollageAccounts> getCollageAccounts() {
        return collageAccounts;
    }

    public void setCollageAccounts(Set<CollageAccounts> collageAccounts) {
        this.collageAccounts = collageAccounts;
    }

    public Set<CollageRequirements> getCollageRequirements() {
        return collageRequirements;
    }

    public void setCollageRequirements(Set<CollageRequirements> collageRequirements) {
        this.collageRequirements = collageRequirements;
    }

}
