package ie.atu.week5.service;

import ie.atu.week5.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final List<Passenger> store = new ArrayList<>();

    public List<Passenger> findAll(){
        return new ArrayList<>(store);
    }

    public Optional<Passenger> findById(String id){
        for (Passenger p : store){
            if (p.getPassengerId().equals(id)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Passenger create(Passenger p){
        if (findById(p.getPassengerId()).isPresent()){
            throw new IllegalArgumentException("PassengerId already exists");
        }
        store.add(p);
        return p;
    }

    public Optional<Passenger> updateName(String id,String name){
        for (int i = 0; i != store.size(); i++){
            if (store.get(i).getPassengerId().equals(id)){
                Passenger p = store.get(i);
                p.setName(name);
                store.set(i, p);
                return Optional.of(p);
            }
        }
        throw new IllegalArgumentException("PassengerId does not exisit");
    }

    public boolean delete(String id) {
        for (int i = 0; i != store.size(); i++){
            if (store.get(i).getPassengerId().equals(id)){
                store.remove(i);
                return true;
            }
        }
        return false;
    }
}

