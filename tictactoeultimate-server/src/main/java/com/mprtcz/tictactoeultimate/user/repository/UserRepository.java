package com.mprtcz.tictactoeultimate.user.repository;

import com.mprtcz.tictactoeultimate.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
}