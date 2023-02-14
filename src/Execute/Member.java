package Execute;

public class Member {
    private String email;
    private String uCode;
    private long funds;
    public Member(String email, String uCode, long funds) {
        this.email = email;
        this.uCode = uCode;
        this.funds = funds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuCode() {
        return uCode;
    }

    public void setuCode(String uCode) {
        this.uCode = uCode;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(long funds) {
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", uCode='" + uCode + '\'' +
                ", funds=" + funds +
                '}';
    }
}
