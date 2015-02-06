package com.infonal.business;

import com.infonal.db.BaseRepository;
import com.infonal.db.IUsersRepo;
import com.infonal.entities.Users;
import java.util.List;

/**
 *
 * @author mkilic
 */
public class UsersRepository extends BaseRepository<Users> implements IUsersRepo {

    public UsersRepository(String persistenceUnitName) {
        super(persistenceUnitName, Users.class);
    }

}
