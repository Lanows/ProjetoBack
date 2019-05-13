package projeto.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;
import projeto.infrastructure.IObjectPersistent;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "tb_genre")
@JsonIgnoreProperties({"programs"})
public class Genre implements IObjectPersistent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cl_idTmbd")
    private int idTmdb;

    @Size(min = 1, max = 30)
    @Column(name = "cl_description")
    private String description;

    @ManyToMany(mappedBy = "genres", targetEntity = Program.class, fetch = FetchType.LAZY)
    private Set<Program> programs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id;
    }

    public Genre() {
    }

    public Genre(Long id, int idTmdb, @Size(min = 1, max = 30) String description, Set<Program> programs) {
        this.id = id;
        this.idTmdb = idTmdb;
        this.description = description;
        this.programs = programs;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    public int idTmdb() {
        return idTmdb;
    }

    public void setIdTmdb(int idTmdb) {
        this.idTmdb = idTmdb;
    }

}
