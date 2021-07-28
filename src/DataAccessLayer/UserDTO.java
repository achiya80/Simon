package DataAccessLayer;

public class UserDTO extends DTO implements Comparable<UserDTO>{

    public static final String NicknameColumnName = "Nickname";
    public static final String SequenceColumnName = "Sequence";

    public final String nickname;
    public final int sequence;

    public UserDTO(String nickname, int sequence) {
        super(new UserControllerDTO());
        this.nickname = nickname;
        this.sequence = sequence;
        Insert();
    }


    @Override
    public int compareTo(UserDTO o) {
        return ((Integer)o.sequence).compareTo(sequence);
    }
}
