package ru.vise.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "object", schema = "vise")
public class ObjectEntity implements Serializable{
    private int objectId;
    private String name;
    private String description;
    private ObjectEntity objectsByParentId;

    @Id
    @Column(name = "object_id", nullable = false)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        ObjectEntity that = (ObjectEntity) o;

        if (objectId != that.objectId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = objectId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "object_id")
    public ObjectEntity getObjectsByParentId() {
        return objectsByParentId;
    }

    public void setObjectsByParentId(ObjectEntity objectsByParentId) {
        this.objectsByParentId = objectsByParentId;
    }

    private transient Set<ParamEntity> paramsOfObject = new HashSet<ParamEntity>();

    @OneToMany(mappedBy = "objectsByObjectId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    public Set<ParamEntity  > getParamsOfObject() {
        return paramsOfObject;
    }

    public void setParamsOfObject(Set<ParamEntity> paramsOfObject) {
        this.paramsOfObject = paramsOfObject;
    }

    @Override
    public String toString() {
        return "ObjectEntity{" +
                "objectId=" + objectId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
