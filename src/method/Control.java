package method;
//--------------------------------------------------------

import data.Doctor;
import menu.Menu;

import java.util.HashMap;

//--------------------------------------------------------

public class Control extends Menu<String> {
    HashMap<String, Doctor> doctorMap;

    public final Manager lib = new Manager();
    //--------------------------------------------------------
    static String[] menu = {"Add doctor", "Update doctor", "Delete doctor", "Search doctor", "EXIT (0)"};

    public Control() {
        super("\n----------!!Control System!!----------", menu);
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1 -> lib.addDoctor(doctorMap);
            case 2 -> lib.updateDoctor(doctorMap);
            case 3 -> lib.deleteDoctor(doctorMap);
            case 4 -> lib.searchDoctor(doctorMap);
        }
    }

}