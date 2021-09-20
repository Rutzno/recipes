package com.diarpy.recipes.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.diarpy.recipes.businessLayer.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
