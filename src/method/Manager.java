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
    //allow user add doctor

    public void addDoctor(ArrayList<Doctor> ld) {
        System.out.print("Enter code: ");
        String code = val.checkInputString();

        // Check if code exists in the map
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

        // Check if the doctor with the same details already exists
        Doctor newDoctor = new Doctor(code, name, specialization, availability);
        for (Doctor existingDoctor : ld) {
            if (existingDoctor.equals(newDoctor)) {
                System.err.println("Duplicate.");
                return;
            }
        }

        // Add the doctor to the map and the list
        doctorMap.put(code, newDoctor);
        ld.add(newDoctor);
        System.err.println("Add successful.");
    }

    //--------------------------------------------------------
    //allow user update doctor
    public void updateDoctor(ArrayList<Doctor> ld) {
        System.out.print("Enter code: ");
        String code = val.checkInputString();
        //check code exist or not
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
        //check user change infomation or not
        assert doctor != null;
        if (!val.checkChangeInfo(doctor, code, name, specialization, availability)) {
            System.err.println("No change");
            return;
        }
        // Update the doctor's information
        doctor.setCode(codeUpdate);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setAvailability(availability);

        // Update the HashMap with the new code as the key
        doctorMap.remove(code);
        doctorMap.put(codeUpdate, doctor);

        System.err.println("Update successful");
    }

    //--------------------------------------------------------
    //allow user delete doctor
    public void deleteDoctor(ArrayList<Doctor> ld) {
        System.out.print("Enter code: ");
        String code = val.checkInputString();
        // Check if the code exists in the HashMap
        if (!doctorMap.containsKey(code)) {
            System.err.println("Doctor not found.");
            return;
        }
        // Remove the doctor from the HashMap
        Doctor doctor = doctorMap.remove(code);
        // Also remove the doctor from the ArrayList, if it's present
        ld.remove(doctor);
        System.err.println("Delete successful.");
    }

    //--------------------------------------------------------
    public void searchDoctor(ArrayList<Doctor> ld) {
        System.out.print("Name: ");
        String nameSearch = val.checkInputString();

        //found by name
        ArrayList<Doctor> foundDoctors = new ArrayList<>();
        for (Doctor doctor : ld) {
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
