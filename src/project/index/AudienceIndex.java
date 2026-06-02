//AudienceIndex.java

package project.index;

// import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import project.model.DeviceType;
import project.model.User;

public class AudienceIndex {

    private final Map<Long, User> userStore;
    private final Set<Long> premiumUsers;
    private final Map<String, Set<Long>> cityIndex;
    private final Map<DeviceType, Set<Long>> deviceIndex;

    public AudienceIndex(){
        userStore = new HashMap<>();
        premiumUsers = new HashSet<>();
        cityIndex = new HashMap<>();
        deviceIndex = new HashMap<>();
    }

    public void addUser(User user){
        //adding user to the userStore
        userStore.put(user.id(), user);

        //if premium user then adding user to the userStore
        if(user.isPremium()){
            premiumUsers.add(user.id());
        }

        //updating cityIndex with user id
        cityIndex
            .computeIfAbsent(user.city(), k -> new HashSet<>())
            .add(user.id());

        //updating deviceIndex with user id
        deviceIndex
            .computeIfAbsent(user.deviceType(), k -> new HashSet<>())
            .add(user.id());
    }

    public Set<Long> getPremiumUsers(){
        return new HashSet<>(premiumUsers);
    }

    public Set<Long> getUsersByCity(String city){
        return new HashSet<>(getSafeSet(cityIndex.get(city)));
    }

    public Set<Long> getUsersByDeviceType(DeviceType deviceType){
        return new HashSet<>(getSafeSet(deviceIndex.get(deviceType)));
    }
    
    public Set<Long> getPremiumAndroidUsers(){
        Set<Long> premiumAndroidUsers = new HashSet<>(getSafeSet(deviceIndex.get(DeviceType.ANDROID)));
        premiumAndroidUsers.retainAll(getSafeSet(premiumUsers));
        return getSafeSet(premiumAndroidUsers);
    }

    public Set<Long> getPremiumUsersByCity(String city){
        Set<Long> premiumByCity = new HashSet<>(getSafeSet(cityIndex.get(city)));
        premiumByCity.retainAll(getSafeSet(premiumUsers));
        return premiumByCity;
    }

    public Set<Long> getUsersByCityAndDevice(String city, DeviceType deviceType){
        Set<Long> users = new HashSet<>(getSafeSet(cityIndex.get(city)));
        users.retainAll(getSafeSet(deviceIndex.get(deviceType)));
        return users;
    }

    public Set<Long> getPremiumUsersByCityAndDevice(String city, DeviceType deviceType){
        Set<Long> users = new HashSet<>(getSafeSet(cityIndex.get(city)));
        users.retainAll(getSafeSet(deviceIndex.get(deviceType)));
        users.retainAll(getSafeSet(premiumUsers));
        return users;
    }
    
    //takes a set that may be filled, null or empty. 
    //if filled then returns set, if null / empty then returns Collections.emptySet()
    private Set<Long> getSafeSet(Set<Long> safeSet){
        return (safeSet==null || safeSet.isEmpty()) ? Collections.emptySet() : safeSet; 
    }
}
