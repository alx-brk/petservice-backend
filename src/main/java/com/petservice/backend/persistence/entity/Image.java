package com.petservice.backend.persistence.entity;

import com.google.common.base.Objects;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;

    @Column(nullable = false)
    private String type;

    @Lob
    private byte[] picture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return Objects.equal(id, image.id) &&
                Objects.equal(fileName, image.fileName) &&
                Objects.equal(type, image.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, fileName, type);
    }
}
