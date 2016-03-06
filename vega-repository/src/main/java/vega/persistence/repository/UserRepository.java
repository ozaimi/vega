package vega.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vega.model.User;

@Repository
public interface UserRepository  extends CrudRepository<User,Long> {
    User findByUserName(String userName);
}
