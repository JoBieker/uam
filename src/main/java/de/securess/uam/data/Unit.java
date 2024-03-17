package de.securess.uam.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "units", schema = "uam")
public class Unit extends AbstractEntity {

    @OneToMany(mappedBy = "unit")
    @Nullable
    private List<Role> roles = new LinkedList<>();

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "head_id")
    @Nullable
    private Long head_id;

    @Column(name = "parent_unit_id")
    @Nullable
    private Long parent_unit_id;

    @Column(name = "description")
    @Nullable
    private String description;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date date_created;

    @Column(name = "created_by")
    private String created_by;

    @Column(name = "date_updated")
    @Nullable
    @UpdateTimestamp
    private Date date_updated;

    @Column(name = "updated_by")
    @Nullable
    private String updated_by;


    @Nullable
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(@Nullable List<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public Long getHead_id() {
        return head_id;
    }

    public void setHead_id(@Nullable Long head_id) {
        this.head_id = head_id;
    }

    @Nullable
    public Long getParent_unit_id() {
        return parent_unit_id;
    }

    public void setParent_unit_id(@Nullable Long parent_unit_id) {
        this.parent_unit_id = parent_unit_id;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
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
}
