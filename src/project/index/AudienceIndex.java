//AudienceIndex.java

package project.index;

// import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import project.model.DeviceType;
import project.model.User;

public class AudienceIndex {
    private final HashSet<Long> premiumUsers;
    private final HashMap<String, HashSet<Long>> cityIndex;
    private final HashMap<DeviceType, HashSet<Long>> deviceIndex;

    public AudienceIndex(){
        premiumUsers = new HashSet<>();
        cityIndex = new HashMap<>();
        deviceIndex = new HashMap<>();
    }

    public void addUser(User user){
        if(user.isPremium()){
            premiumUsers.add(user.id());
        }

        cityIndex
            .computeIfAbsent(user.city(), k -> new HashSet<>())
            .add(user.id());

        deviceIndex
            .computeIfAbsent(user.deviceType(), k -> new HashSet<>())
            .add(user.id());
    }

    public Set<Long> getAllPremiumUsers(){
        return new HashSet<>(premiumUsers);
    }

    public Set<Long> getUsersByCity(String city){
        return new HashSet<>(getSafeSet(cityIndex.get(city)));
    }

    public Set<Long> getUsersByDeviceType(DeviceType deviceType){
        return new HashSet<>(getSafeSet(deviceIndex.get(deviceType)));
    }  
    
    //takes a set that may be filled, null or empty. 
    //if filled then returns set, if null / empty then returns Collections.emptySet()
    private Set<Long> getSafeSet(Set<Long> safeSet){
        return (safeSet==null || safeSet.isEmpty()) ? Collections.emptySet() : safeSet; 
    }
}
