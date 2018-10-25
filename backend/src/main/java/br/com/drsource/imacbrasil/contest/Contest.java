package br.com.drsource.imacbrasil.contest;

import br.com.drsource.imacbrasil.category.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Short id;

    @Column(nullable = false)
    private String name;

    @Future(message = "The begin date must be in future")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd hh:mm:ss")
    private Date beginDate;


    @Future(message = "The end date must be in future")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endDate;

    @OneToMany(mappedBy = "contestID")
    private List<Category> categories;

    public List<Category> getCategories() {
        if (categories == null)
            categories = new ArrayList<>();
        return categories;
    }
}
