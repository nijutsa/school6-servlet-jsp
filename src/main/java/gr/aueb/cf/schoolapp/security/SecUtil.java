package gr.aueb.cf.schoolapp.security;

import org.mindrot.jbcrypt.BCrypt;

public class SecUtil {

    private SecUtil(){

    }

    public static String hashPassword(String inputPassword){
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(inputPassword, salt);
    }

    public static boolean isPasswordValid(String inputPassword, String hashedPassword) {
        return BCrypt.checkpw(inputPassword, hashedPassword);

    }
}
