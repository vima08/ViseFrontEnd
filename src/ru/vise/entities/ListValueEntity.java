package ru.vise.entities;

import javax.persistence.*; //TODO Max

@Entity
@Table(name = "listvalue", schema = "vise")
public class ListValueEntity {
    private int listValueId;
    private Integer number;
    private String value;
    private AttributeEntity attributesByAttrId; //TODO Max Просто attribute

    public void setListValueId(int listValueId) {
        this.listValueId = listValueId;
    }

    @Id
    @Column(name = "list_value_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getListValueId() {
        return listValueId;
    }

    @Basic
    @Column(name = "number", nullable = true)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
    public boolean equals(Object o) { //TODO Max В конец
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListValueEntity that = (ListValueEntity) o;

        if (listValueId != that.listValueId) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() { //TODO Max В конец
        int result = listValueId;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
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
