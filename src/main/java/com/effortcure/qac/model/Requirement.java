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
@Table(name = "requirement")
public class Requirement {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String name;
    private String description;

    @OneToMany(mappedBy = "requirement")
    private Set<Template> templates = new HashSet<>();

    @OneToMany(mappedBy = "requirement")
    private Set<CollageRequirements> collageRequirements = new HashSet<>();

    @OneToMany(mappedBy = "requirement")
    private Set<ProgramRequirements> programRequirements = new HashSet<>();

    public Requirement() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }

    public Set<CollageRequirements> getCollageRequirements() {
        return collageRequirements;
    }

    public void setCollageRequirements(Set<CollageRequirements> collageRequirements) {
        this.collageRequirements = collageRequirements;
    }

    public Set<ProgramRequirements> getProgramRequirements() {
        return programRequirements;
    }

    public void setProgramRequirements(Set<ProgramRequirements> programRequirements) {
        this.programRequirements = programRequirements;
    }

}
