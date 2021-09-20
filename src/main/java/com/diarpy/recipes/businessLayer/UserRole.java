package com.diarpy.recipes.businessLayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 9/9/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "\\w{3,}@[a-z]+\\.[a-z]{2,3}")
    @Column(name = "username", nullable = false, unique = true)
    private String email;

    @Column(name = "role")
    private String role;
}
