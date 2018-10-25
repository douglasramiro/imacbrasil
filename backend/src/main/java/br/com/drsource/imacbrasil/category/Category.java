package br.com.drsource.imacbrasil.category;

import br.com.drsource.imacbrasil.contest.Contest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Short id;

    private String name;


    @Column(name = "contest_id", nullable = false)
    private Short contestID;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contest_id", insertable = false, updatable = false)
    private Contest contest;


    public void setContest(Contest contest) {
        this.contest = contest;
        this.contestID = contest != null ? contest.getId() : null;

    }
}
