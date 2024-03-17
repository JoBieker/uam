package de.securess.uam.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.Nullable;
import java.util.Date;
@Entity
@Table(name = "roles", schema = "uam")
public class Role extends AbstractEntity {

    @NotEmpty
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    @Nullable
    private String description;

    @Column(name = "targets")
    @Nullable
    private String targets;

    @Column(name = "tasks")
    @Nullable
    private String tasks;

    @Column(name = "responsibilities")
    @Nullable
    private String responsibilities;

    @Column(name = "notes")
    @Nullable
    private String notes;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date date_created;

    @Column(name = "date_approved")
    @Nullable
    private Date date_approved;

    @Column(name = "approved_by")
    @Nullable
    private String approved_by;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "date_updated")
    @UpdateTimestamp
    @Nullable
    private Date date_updated;

    @Column(name = "updated_by")
    @Nullable
    private String updated_by;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "unit_id")
    @JsonIgnoreProperties("employees")
    private Unit unit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getTargets() {
        return targets;
    }

    public void setTargets(@Nullable String targets) {
        this.targets = targets;
    }

    @Nullable
    public String getTasks() {
        return tasks;
    }

    public void setTasks(@Nullable String tasks) {
        this.tasks = tasks;
    }

    @Nullable
    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(@Nullable String responsibilities) {
        this.responsibilities = responsibilities;
    }

    @Nullable
    public String getNotes() {
        return notes;
    }

    public void setNotes(@Nullable String notes) {
        this.notes = notes;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @Nullable
    public Date getDate_approved() {
        return date_approved;
    }

    public void setDate_approved(@Nullable Date date_approved) {
        this.date_approved = date_approved;
    }

    @Nullable
    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(@Nullable String approved_by) {
        this.approved_by = approved_by;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    @Nullable
    public Date getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(@Nullable Date date_updated) {
        this.date_updated = date_updated;
    }

    @Nullable
    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(@Nullable String updated_by) {
        this.updated_by = updated_by;
    }

    @Nullable
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(@Nullable Unit unit) {
        this.unit = unit;
    }
}
