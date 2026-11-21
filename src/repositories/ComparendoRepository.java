package repositories;
import java.time.LocalDate;
import java.util.ArrayList;
import models.Comparendo;

public class ComparendoRepository {
    private ArrayList<Comparendo> comparendos = new ArrayList<>();

    public void add(Comparendo comparendo){
        comparendos.add(comparendo);
    }

    public Comparendo findById(int id){
        for(Comparendo comparendo : comparendos){
            if (comparendo.getId() == id) {
                return comparendo;
            }
        }
        return null;
    }

  
    public ArrayList<Comparendo> findByDate(LocalDate date) {
        ArrayList<Comparendo> compDate = new ArrayList<>();

        for (Comparendo comparendo : comparendos) {
            if (comparendo.getDate().equals(date)) {
                compDate.add(comparendo);
            }
        }

        return compDate;
    }

    public ArrayList<Comparendo> findByPaid(boolean paid) {
        ArrayList<Comparendo> compPaid = new ArrayList<>();

        for (Comparendo comparendo : comparendos) {
            if (comparendo.isPaid() == paid) {
                compPaid.add(comparendo);
            }
        }

        return compPaid;
    }

    
    public ArrayList<Comparendo> findByVehicleLicensePlate(String licensePlate) {
        ArrayList<Comparendo> compVehicle = new ArrayList<>();

        for (Comparendo comparendo : comparendos) {
            if (comparendo.getVehicleLicensePlate()
                    .equalsIgnoreCase(licensePlate.trim())) {
                compVehicle.add(comparendo);
            }
        }

        return compVehicle;
    }


    public ArrayList<Comparendo> findAll(){
        return comparendos;
    }
}
