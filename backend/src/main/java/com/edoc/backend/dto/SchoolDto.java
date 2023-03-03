package com.edoc.backend.dto;

import com.edoc.backend.enums.Level;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class SchoolDto {

    private Long diseCode;
    private String name;
    private Level level;
    private Long contactNo;
    private Boolean coEd;

}
