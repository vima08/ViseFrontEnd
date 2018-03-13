package ru.vise.entities;

import javax.persistence.*;

@Entity
@Table(name = "param", schema = "vise")
public class ParamEntity {
    private int paramId;
    private String value;
    private ObjectEntity objectsByObjectId;
    private AttributeEntity attributesByAttrId;

    @Id
    @Column(name = "param_id", nullable = false)
    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 80)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParamEntity that = (ParamEntity) o;

        if (paramId != that.paramId) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paramId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "object_id", referencedColumnName = "object_id")
    public ObjectEntity getObjectsByObjectId() {
        return objectsByObjectId;
    }

    public void setObjectsByObjectId(ObjectEntity objectsByObjectId) {
        this.objectsByObjectId = objectsByObjectId;
    }

    @ManyToOne
    @JoinColumn(name = "attr_id", referencedColumnName = "attr_id")
    public AttributeEntity getAttributesByAttrId() {
        return attributesByAttrId;
    }

    public void setAttributesByAttrId(AttributeEntity attributesByAttrId) {
        this.attributesByAttrId = attributesByAttrId;
    }
}
