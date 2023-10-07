package method;

import data.Doctor;
import data.Validate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//--------------------------------------------------------
public class Manager {
    private Validate val = new Validate();
    protected Map<String, Doctor> doctorMap = new HashMap<>();

    //--------------------------------------------------------
    public void addDoctor(HashMap<String, Doctor> doctorMap) {
        // Generate the next available code
        int nextCode = generateNextDoctorId(doctorMap);

        // Check if code exists in the map
        String code = String.valueOf(nextCode);
        if (doctorMap.containsKey(code)) {
            System.err.println("Code exists.");
            return;
        }

        System.out.print("Enter name: ");
        String name = val.checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = val.checkInputString();
        System.out.print("Enter availability: ");
        int availability = val.checkInputInt();

        Doctor newDoctor = new Doctor(code, name, specialization, availability);
        doctorMap.put(code, newDoctor);
        System.err.println("Add successful.");
    }

    public int generateNextDoctorId(HashMap<String, Doctor> doctorMap) {
        int lastCode = 0;
        for (String code : doctorMap.keySet()) {
            int codeValue = Integer.parseInt(code);
            if (codeValue > lastCode) {
                lastCode = codeValue;
            }
        }
        return lastCode + 1;
    }
    //--------------------------------------------------------

    public void updateDoctor(HashMap<String, Doctor> doctorMap) {
        System.out.print("Enter code: ");
        String code = val.checkInputString();


        if (!doctorMap.containsKey(code)) {
            System.err.println("Doctor not found");
            return;
        }

        System.out.print("Enter update code: ");
        String codeUpdate = val.checkInputString();
        Doctor doctor = doctorMap.get(code);

        System.out.print("Enter name: ");
        String name = val.checkInputString();
        System.out.print("Enter specialization: ");
        String specialization = val.checkInputString();
        System.out.print("Enter availability: ");
        int availability = val.checkInputInt();

        // check if user have changed data
        if (!val.checkChangeInfo(doctor, code, name, specialization, availability)) {
            System.err.println("No change");
            return;
        }

        // update Doctor Info
        doctor.setCode(codeUpdate);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setAvailability(availability);

        // update HashMap with new key
        doctorMap.remove(code);
        doctorMap.put(codeUpdate, doctor);

        System.err.println("Update successful");
    }


    //--------------------------------------------------------
    public void deleteDoctor(HashMap<String, Doctor> doctorMap) {
        System.out.print("Enter code: ");
        String code = val.checkInputString();


        if (!doctorMap.containsKey(code)) {
            System.err.println("Doctor not found.");
            return;
        }


        doctorMap.remove(code);
        System.err.println("Delete successful.");
    }

    //--------------------------------------------------------
    public void searchDoctor(HashMap<String, Doctor> doctorMap) {
        System.out.print("Name: ");
        String nameSearch = val.checkInputString();

        ArrayList<Doctor> foundDoctors = new ArrayList<>();

        for (Doctor doctor : doctorMap.values()) {
            if (doctor.getName().equalsIgnoreCase(nameSearch)) {
                foundDoctors.add(doctor);
            }
        }

        if (foundDoctors.isEmpty()) {
            System.err.println("Doctor not found.");
        } else {
            System.out.printf("%-10s%-15s%-25s%-20s\n", "Code", "Name",
                    "Specialization", "Availability");
            for (Doctor doctor : foundDoctors) {
                System.out.printf("%-10s%-15s%-25s%-20d\n", doctor.getCode(),
                        doctor.getName(), doctor.getSpecialization(),
                        doctor.getAvailability());
            }
        }
    }
    //--------------------------------------------------------
}
