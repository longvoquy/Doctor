
import data.Doctor;
import method.Control;

import java.util.HashMap;
import java.util.Map;


public class Test {


    public static Control con = new Control() {
    };

    public static void main(String[] args) throws Exception {
        Map<String, Doctor> doctorMap = new HashMap<>();

        con.run();
    }

}