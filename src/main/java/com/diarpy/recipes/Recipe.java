package com.diarpy.recipes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 8/16/2021
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private String name;
    private String description;
    private String ingredients;
    private String directions;
}
