package gift.repository;

import gift.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("test1@test.com", "password123", "USER");
    }

    @Test
    void save() {
        Member savedMember = memberRepository.save(member);

        assertThat(savedMember.getId()).isNotNull();
        assertThat(savedMember.getEmail()).isEqualTo("test1@test.com");
        assertThat(savedMember.getPassword()).isEqualTo("password123");
        assertThat(savedMember.getRole()).isEqualTo("USER");
    }

    @Test
    void findByEmail() {
        memberRepository.save(member);
        Optional<Member> foundMember = memberRepository.findByEmail("test1@test.com");

        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getEmail()).isEqualTo("test1@test.com");
    }

    @Test
    void deleteById() {
        Member savedMember = memberRepository.save(member);
        memberRepository.deleteById(savedMember.getId());
        Optional<Member> deletedMember = memberRepository.findById(savedMember.getId());

        assertThat(deletedMember).isEmpty();
    }
}