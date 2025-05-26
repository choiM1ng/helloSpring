package com.example.spring.service;

import com.example.spring.domain.Member;
import com.example.spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

// import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        // 각 테스트 후 저장소 초기화
        // 테스트 간 데이터 충돌을 방지
        memberRepository.clearStore();
    }

    @Test
    void 회원가입(){
        // given: 새로운 회원 객체 준비
        Member member = new Member();
        member.setName("spring");

        // when: 회원가입 실행
        Long saveId = memberService.join(member);

        // then: 저장된 회원을 ID로 조회해서 이름이 같은지 검증
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given: 같은 이름을 가진 회원 두 명 준비
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when: 첫 번째 회원은 정상 가입
        memberService.join(member1);

        // then: 예외가 발생해야 하며, 예외 메시지가 예상한 내용과 같은지 확인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}