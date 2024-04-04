package vumc.org.springreact.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bourbon_distillery")
public class BourbonDistillery {
    private Long id;
    private String name;

    public BourbonDistillery(String name) {
        this.name = name;
    }

    public BourbonDistillery() {

    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BourbonDistillery person = (BourbonDistillery) o;
        return id.equals(person.id) && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BourbonDistillery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
