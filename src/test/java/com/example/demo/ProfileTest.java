package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.Profile;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ProfileRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ProfileTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void testInsertMember()
    {
        IntStream.range(1, 101).forEach(i->{
            Member member = new Member();

            member.setUid("user " + i);
            member.setUpw("pw"+i);
            member.setUname("사용자"+i);
            memberRepository.save(member);
        });
    }

    @Test
    public void testInsertProfile()
    {
        Member member = new Member();
        member.setUid("user 1");

        for (int i = 1; i < 5; i++)
        {
            Profile profile1 = new Profile();
            profile1.setFname("face" + i + ".jpg");
            if (i == 1)
            {
                profile1.setCurrent(true);
            }

            profile1.setMember(member);
            profileRepository.save(profile1);
        }
    }

    @Test
    public void testFetchJoin1()
    {
        List<Object[]> result = memberRepository.getMemberWithProfileCount("user 1");
        result.forEach(arr-> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void testFetchJoin2()
    {
        List<Object[]> result = memberRepository.getMemberProfile("user 1");
        result.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }
}
