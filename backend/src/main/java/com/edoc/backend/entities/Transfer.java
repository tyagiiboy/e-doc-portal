package com.edoc.backend.entities;

import com.edoc.backend.enums.SchoolClass;
import com.edoc.backend.enums.Stream;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "applicant_id")
    @MapsId
    private User user;

    @ManyToOne
    @JoinColumn(name = "respondant")
    @EqualsAndHashCode.Exclude
    private School respondant;

    @Column(name = "class")
    @Enumerated(EnumType.STRING)
    private SchoolClass schoolClass;

    @Column(name = "stream")
    @Enumerated(EnumType.STRING)
    private Stream stream;

}
