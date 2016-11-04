package com.mprtcz.tictactoeultimate.repository;

import com.mprtcz.tictactoeultimate.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Azet on 2016-11-03.
 */
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
}