package ru.vise.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attribute", schema = "vise")
public class AttributeEntity {
    private int attrId;
    private String attrName;
    private Integer attrType;
    private String description;

    @Id
    @Column(name = "attr_id", nullable = false)
    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    @Basic
    @Column(name = "attr_name", nullable = true, length = 40)
    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    @Basic
    @Column(name = "attr_type", nullable = true)
    public Integer getAttrType() {
        return attrType;
    }

    public void setAttrType(Integer attrType) {
        this.attrType = attrType;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 40)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeEntity that = (AttributeEntity) o;

        if (attrId != that.attrId) return false;
        if (attrName != null ? !attrName.equals(that.attrName) : that.attrName != null) return false;
        if (attrType != null ? !attrType.equals(that.attrType) : that.attrType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = attrId;
        result = 31 * result + (attrName != null ? attrName.hashCode() : 0);
        result = 31 * result + (attrType != null ? attrType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    private Set<ListValueEntity> listValueEntities = new HashSet<ListValueEntity>();

    @OneToMany(mappedBy = "attributesByAttrId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<ListValueEntity> getListValueEntities() {
        return listValueEntities;
    }

    public void setListValueEntities(Set<ListValueEntity> listValueEntities) {
        this.listValueEntities = listValueEntities;
    }

    private Set<ParamEntity> paramEntities =  new HashSet<ParamEntity>();

    @OneToMany(mappedBy = "attributesByAttrId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<ParamEntity> getParamEntities() {
        return paramEntities;
    }

    public void setParamEntities(Set<ParamEntity> paramEntities) {
        this.paramEntities = paramEntities;
    }

    @Override
    public String toString() {
        return "AttributeEntity{" +
                "attrId=" + attrId +
                ", attrName='" + attrName + '\'' +
                ", attrType=" + attrType +
                ", description='" + description + '\'' +
//                ", listValueEntities=" + listValueEntities +
//                ", paramEntities=" + paramEntities +
                '}';
    }
}
