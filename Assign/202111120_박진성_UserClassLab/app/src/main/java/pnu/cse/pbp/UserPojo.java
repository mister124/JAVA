package pnu.cse.pbp;

// import java.util.Objects;

import java.util.Objects;


public class UserPojo {
    private Long id;
    private String name;
    private String email;

    // public UserPojo(Long id, String name, String email) {
    //     this.id = id;
    //     this.name = name;
    //     this.email = email;
    // }

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // @Override
    // public String toString() {
    //     return "UserPojo{" +
    //             "id=" + id +
    //             ", name='" + name + '\'' +
    //             ", email='" + email + '\'' +
    //             '}';
    // }

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;

    //     UserPojo userPojo = (UserPojo) o;

    //     return Objects.equals(id, userPojo.id)
    //             && Objects.equals(name, userPojo.name)
    //             && Objects.equals(email, userPojo.email);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(id, name, email);
    // }

    public UserPojo(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserPojo other = (UserPojo) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserPojo{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }
}