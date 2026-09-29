package pnu.cse.pbp;

public class MainStep4 {
    public static void main(String[] args) {
        UserRecord user =
                new UserRecord(
                        202111120L,
                        "박진성",
                        "mister124@pnu.ac.kr"
                );

        System.out.println("ID: " + user.id());
        System.out.println("Name: " + user.name());
        System.out.println("객체 정보: " + user.toString());

        // user.setName("다른 이름");
        // ↑ record에는 setter가 없어서 컴파일 오류
    }
}