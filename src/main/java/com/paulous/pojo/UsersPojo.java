package com.paulous.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersPojo {
    private String username;
    private String firstName;
    private String lastName;
    private String userRole;
}
