package repositories;

import com.example.quanlythuvien.entities.LoanSlipDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LoanSlipDetailRepository extends JpaRepository<LoanSlipDetail, UUID> {
}
