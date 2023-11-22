package org.example.springboot.service.Users;

import junit.framework.TestCase;
import org.example.springboot.domain.users.Users;
import org.example.springboot.domain.users.UsersRepository;
import org.example.springboot.web.dto.DrivingRecordDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest extends TestCase {

    @Autowired
    UsersService usersService;

    @Test
    public void 회원가입테스트() {
        DrivingRecordDto drivingRecordDto = DrivingRecordDto.builder()
                .userId("qwer")
                .mileage(1200L)
                .drivingDistance(12)
                .sharpSpeedingNum(1)
                .sharpBrakingNum(0)
                .sharpCurvingNum(0)
                .speedingNum(0)
                .accidentNum(1)
                .build();
        //given
        Users users = new Users();

        //when
        String Id = usersService.join(users);

        //then
        Users findUsers = usersService.findByUserId(Id).get();
        assertThat(findUsers.getUserId()).isEqualTo(findUsers.getUserId());
    }

    @Test
    public void 중복회원예외테스트() {
        //given
        Users user1 = new Users();
        user1.setUserId("qwer");
        Users user2 = new Users();
        user2.setUserId("qwer");
        //when
        usersService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> usersService.join(user2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 이름입니다.");
    }

    @Test
    public void 회원탈퇴테스트() {
        Long deleteId = 32L; //삭제할 유저 ID
        usersService.deleteUser(deleteId);
    }

    @Test
    public void 로그인테스트() {
        String loginId = "qwer";
        String loginPassword = "1234";

        Users loginUser = usersService.login(loginId, loginPassword);

        assertThat(loginUser).isNotNull();
    }

    @Test
    public void 유저아이디찾기테스트() {
        Users users = new Users();
        users.setUserId("testId");

        usersService.join(users);

        Users findUsersId = usersService.findByUserId("testId").get();
        assertThat(findUsersId).isEqualTo(users);
    }

    @Test
    public void 전체유저찾기테스트() {
        Users users = new Users();
        users.setUserId("한명만 가입");

        usersService.join(users);

        List<Users> result = usersService.findUsers();
        assertThat(result.size()).isEqualTo(1);
    }
}
