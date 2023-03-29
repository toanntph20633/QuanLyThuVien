package repositories;

import com.example.quanlythuvien.entities.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BookDetailRepository extends JpaRepository<BookDetail,UUID> {
}
