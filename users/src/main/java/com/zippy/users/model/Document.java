package com.zippy.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "document_generator")
    @SequenceGenerator(name = "document_generator", sequenceName = "document_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "document_type_id")
    private Integer typeId;

    @Column(name = "document_number")
    private String number;

    @Column(name = "front_image")
    private String frontImage;

    @Column(name = "back_image")
    private String backImage;

    @JsonIgnore
    @ManyToOne(targetEntity = DocumentType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "document_type_id", insertable = false, updatable = false)
    private DocumentType type;

}
