package method;
//--------------------------------------------------------

import menu.Menu;
import data.*;
import java.util.ArrayList;
import java.util.*;
//--------------------------------------------------------

public class Control extends Menu<String> {

    ArrayList<Doctor> ld = new ArrayList<>();
    public final Manager lib = new Manager();
    //--------------------------------------------------------
    static String[] menu = {" Add doctor", "Update doctor", "Delete doctor", "Search doctor","EXIT (0)"};

    public Control() {
        super("\n----------!!Control System!!----------", menu);



    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1 -> lib.addDoctor(ld);
            case 2 -> lib.updateDoctor(ld);
            case 3 -> lib.deleteDoctor(ld);
            case 4 -> lib.searchDoctor(ld);
        }
    }
}