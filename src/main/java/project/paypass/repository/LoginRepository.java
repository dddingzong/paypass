package project.paypass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.paypass.domain.Login;


@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
}
