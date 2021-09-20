package com.diarpy.recipes.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.diarpy.recipes.businessLayer.UserRole;

/**
 * @author Mack_TB
 * @version 1.0.7
 * @since 9/9/2021
 */

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
