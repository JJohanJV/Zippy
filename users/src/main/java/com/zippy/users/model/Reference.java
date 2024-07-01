package com.zippy.users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Entity
@Builder
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reference")
public class Reference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reference_generator")
    @SequenceGenerator(name = "reference_generator", sequenceName = "reference_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_names")
    private String firstNames;

    @Column(name = "last_names")
    private String lastNames;

    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @OneToOne(targetEntity = Document.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", insertable = false, updatable = false)
    private Document document;
}
