package com.nixs.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {
    private Long id;
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
