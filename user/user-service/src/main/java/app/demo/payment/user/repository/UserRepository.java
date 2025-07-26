package app.demo.payment.user.repository;

import app.demo.payment.user.domain.model.User;

public interface UserRepository {

    User findByUser(String user);

    User save(User user);
}
