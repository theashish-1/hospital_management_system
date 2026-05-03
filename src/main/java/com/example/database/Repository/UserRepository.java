package com.example.database.Repository;

import com.example.database.Entity.User;
<<<<<<< HEAD
import com.example.database.Entity.type.AuthProviderType;
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
<<<<<<< HEAD

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
=======
>>>>>>> 46cbaac4155ca17de5d9faae764d05bf320feb38
}
