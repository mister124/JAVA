package pnu.cse.pbp;

public class MainStep3 {
    public static void main(String[] args) {
        UserLombok user =
                new UserLombok(202111120L, "박진성", "mister124@pnu.ac.kr");

        System.out.println("Name: " + user.getName());

        user.setEmail("mister124@pnu.ac.kr");

        System.out.println("객체 정보: " + user);
    }
}