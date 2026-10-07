package pnu.cse.pbp;

import java.util.HashSet;
import java.util.Set;

public class MainStep1 {
    public static void main(String[] args) {
        UserPojo user = new UserPojo(202111120L, "박진성", "mister124@pnu.ac.kr");

        System.out.println("ID: " + user.getId());

        user.setName("박진성");
        System.out.println("Updated Name: " + user.getName());

        System.out.println("객체 정보: " + user.toString());

        UserPojo user1 =
                new UserPojo(202111120L, "박진성", "mister124@pnu.ac.kr");

        UserPojo user2 =
                new UserPojo(202111120L, "박진성", "mister124@pnu.ac.kr");

        System.out.println(
                "user1과 user2는 같은 객체인가? "
                        + (user1 == user2)
        );

        System.out.println(
                "user1과 user2는 동등한가? "
                        + user1.equals(user2)
        );

        Set<UserPojo> userSet = new HashSet<>();

        userSet.add(user1);
        userSet.add(user2);

        System.out.println(
                "Set에 들어있는 User의 수: "
                        + userSet.size()
        );
    }
}