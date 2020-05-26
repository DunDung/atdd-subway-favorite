package wooteco.subway.service.favorite;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static wooteco.subway.service.member.MemberServiceTest.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import wooteco.subway.domain.member.Member;
import wooteco.subway.service.favorite.dto.FavoriteRequest;
import wooteco.subway.service.member.MemberService;

@ExtendWith(SpringExtension.class)
public class FavoriteServiceTest {

    @MockBean
    private MemberService memberService;

    private FavoriteService favoriteService;
    private Member member;
    private FavoriteRequest request1;
    private FavoriteRequest request2;

    @BeforeEach
    void setUp() {
        favoriteService = new FavoriteService(memberService);
        member = new Member(1L, TEST_USER_EMAIL, TEST_USER_NAME, TEST_USER_PASSWORD);
        request1 = new FavoriteRequest(1L, 2L);
        request2 = new FavoriteRequest(1L, 4L);
    }

    @Test
    void addToMember() {
        given(memberService.save(any())).willReturn(member);

        favoriteService.addToMember(member, request1);
        favoriteService.addToMember(member, request2);
        assertThat(member.getFavorites()).hasSize(2);
    }

    @Test
    void deleteById() {
        given(memberService.save(any())).willReturn(member);

        favoriteService.addToMember(member, request1);

        favoriteService.deleteById(member, null);
        assertThat(member.getFavorites()).hasSize(0);
    }
}
