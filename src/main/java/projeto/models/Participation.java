package projeto.models;

import com.fasterxml.jackson.annotation.*;
import projeto.infrastructure.IObjectPersistent;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_participation")
public class Participation implements IObjectPersistent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String department;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cl_program_id")
    private Program program;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cl_person_id")
    private Person person;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participation that = (Participation) o;
        return Objects.equals(id, that.id);
    }

    public Participation() {
    }

    public Participation(Long id, String department, Program program, Person person) {
        this.id = id;
        this.department = department;
        this.program = program;
        this.person = person;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
