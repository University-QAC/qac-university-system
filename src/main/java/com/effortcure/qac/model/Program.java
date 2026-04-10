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
@Table(name = "program")
public class Program {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String name;

    @ManyToOne
    @JoinColumn(name = "collage")
    private Collage collage;

    @OneToMany(mappedBy = "program")
    private Set<ProgramAccounts> programAccounts = new HashSet<>();

    @OneToMany(mappedBy = "program")
    private Set<ProgramRequirements> programRequirements = new HashSet<>();

    public Program() {
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

    public Collage getCollage() {
        return collage;
    }

    public void setCollage(Collage collage) {
        this.collage = collage;
    }

    public Set<ProgramAccounts> getProgramAccounts() {
        return programAccounts;
    }

    public void setProgramAccounts(Set<ProgramAccounts> programAccounts) {
        this.programAccounts = programAccounts;
    }

    public Set<ProgramRequirements> getProgramRequirements() {
        return programRequirements;
    }

    public void setProgramRequirements(Set<ProgramRequirements> programRequirements) {
        this.programRequirements = programRequirements;
    }

}
