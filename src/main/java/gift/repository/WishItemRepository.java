package gift.repository;

import gift.entity.Member;
import gift.entity.WishItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishItemRepository extends JpaRepository<WishItem, Long> {
    void deleteByIdAndMemberId(Long id, Long memberId);
}
