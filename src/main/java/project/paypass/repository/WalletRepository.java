package project.paypass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.paypass.domain.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
