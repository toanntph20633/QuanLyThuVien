package repositories;

import com.example.quanlythuvien.entities.LoanSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LoanSlipRepository extends JpaRepository<LoanSlip, UUID> {
}
