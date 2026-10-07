package pnu.cse.pbp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FilePersistenceDemo {
    public static void main(String[] args) throws Exception {

        UserSerializable userToSave =
                new UserSerializable(
                        202111120L,
                        "박진성",
                        "mister124@pnu.ac.kr"
                );

        File file = new File("user.dat");

        try (
            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(file)
                    )
        ) {
            oos.writeObject(userToSave);

            System.out.println(
                    "객체를 파일에 저장했습니다: "
                    + userToSave
            );
        }

        try (
            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file)
                    )
        ) {
            UserSerializable userFromFile =
                    (UserSerializable) ois.readObject();

            System.out.println(
                    "파일에서 객체를 읽었습니다: "
                    + userFromFile
            );
        }
    }
}