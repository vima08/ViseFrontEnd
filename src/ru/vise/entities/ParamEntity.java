package ru.vise.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "param", schema = "vise")
public class ParamEntity implements Serializable{
    private Long paramId;
    private String value;
    private ObjectEntity objectsByObjectId;
    private AttributeEntity attributesByAttrId;

    @Id
    @Column(name = "param_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
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
        Long result = paramId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result.intValue();
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

    @Override
    public String toString() {
        return "ParamEntity{" +
                "paramId=" + paramId +
                ", value='" + value + '\'' +
                ", objectsByObjectId=" + objectsByObjectId +
                ", attributesByAttrId=" + attributesByAttrId +
                '}';
    }
}
