package com.example.CrudBanking2;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccountRepository extends JpaRepository<AccountDetail, Long> {
    List<AccountDetail> findByActive(boolean active);
}
