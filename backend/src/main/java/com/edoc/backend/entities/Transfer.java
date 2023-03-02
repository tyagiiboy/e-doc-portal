package com.edoc.backend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "transfers")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class Transfer {

    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "applicant_id")
    @MapsId
    private User user;

    @ManyToOne
    private School respondant;

}
