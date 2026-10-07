package pnu.cse.pbp;

import jakarta.persistence.*;

public class UserJpaDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UserClassLabPU");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            UserEntity newUser =
                    new UserEntity("박진성", "mister124@pnu.ac.kr");

            em.persist(newUser);

            UserEntity foundUser =
                    em.find(UserEntity.class, 1L);

            System.out.println(
                    "DB에서 조회한 User: " + foundUser
            );

            foundUser.setName("박진성_수정");

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        emf.close();
    }
}