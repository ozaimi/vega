package vega.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vega.model.User;

@Repository
public interface UserRepository  extends CrudRepository<User,Long> {
}
