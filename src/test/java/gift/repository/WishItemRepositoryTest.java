package gift.repository;

import gift.entity.Member;
import gift.entity.Product;
import gift.entity.WishItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class WishItemRepositoryTest {

    @Autowired
    private WishItemRepository wishItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    private Member member;
    private Product product;
    private WishItem wishItem;

    @BeforeEach
    void setUp() {
        // data.sql에 존재하는 member 조회
        member = memberRepository.findByEmail("test@test.com")
            .orElseThrow(() -> new RuntimeException("Member not found in data.sql"));

        product = productRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Product not found in data.sql"));

        wishItem = new WishItem(product, 2, member);
    }

    @Test
    void save() {
        WishItem savedWishItem = wishItemRepository.save(wishItem);

        assertThat(savedWishItem.getId()).isNotNull();
        assertThat(savedWishItem.getMember()).isEqualTo(member);
        assertThat(savedWishItem.getProduct()).isEqualTo(product);
        assertThat(savedWishItem.getQuantity()).isEqualTo(2);
    }

    @Test
    void findByMember() {
        wishItemRepository.save(wishItem);
        List<WishItem> wishItems = wishItemRepository.findByMember(member);

        assertThat(wishItems).hasSize(1);
        assertThat(wishItems.getFirst().getQuantity()).isEqualTo(2);
    }

    @Test
    void deleteByIdAndMemberId() {
        WishItem savedWishItem = wishItemRepository.save(wishItem);
        wishItemRepository.deleteByIdAndMemberId(savedWishItem.getId(), member.getId());
        List<WishItem> wishItems = wishItemRepository.findByMember(member);

        assertThat(wishItems).isEmpty();
    }
}