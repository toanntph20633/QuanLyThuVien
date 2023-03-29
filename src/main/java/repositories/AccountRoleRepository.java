package repositories;

import com.example.quanlythuvien.entities.RolesAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AccountRoleRepository extends JpaRepository<RolesAccounts, UUID> {
}
