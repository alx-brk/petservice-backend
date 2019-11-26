package com.petservice.backend.persistence.entity;

import com.google.common.base.Objects;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "petsitter", nullable = false)
    private User petsitter;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @Column(nullable = false, precision = 2, scale = 1)
    private Double mark;

    private String text;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback)) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equal(id, feedback.id) &&
                Objects.equal(petsitter, feedback.petsitter) &&
                Objects.equal(author, feedback.author) &&
                Objects.equal(mark, feedback.mark) &&
                Objects.equal(text, feedback.text) &&
                Objects.equal(creationDate, feedback.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, petsitter, author, mark, text, creationDate);
    }
}
