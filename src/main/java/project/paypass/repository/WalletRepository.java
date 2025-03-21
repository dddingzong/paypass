package project.paypass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.paypass.domain.Wallet;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findByMainId(@Param("mainId")String mainId);

    @Modifying
    @Query("update Wallet w set w.account = w.account + :amount where w.mainId = :mainId")
    int updateWalletBalance(@Param("mainId") String mainId, @Param("amount") int amount);

}
