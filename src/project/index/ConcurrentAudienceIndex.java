//ConcurrentAudienceIndex.java

package project.index;

// import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import project.model.DeviceType;
import project.model.User;

public class ConcurrentAudienceIndex {
    private final Map<Long, User> userStore;
    private final Set<Long> premiumUsers;
    private final Map<String, Set<Long>> cityIndex;
    private final Map<DeviceType, Set<Long>> deviceIndex;

    public ConcurrentAudienceIndex(){
        userStore = new ConcurrentHashMap<>();
        premiumUsers = ConcurrentHashMap.newKeySet();
        cityIndex = new ConcurrentHashMap<>();
        deviceIndex = new ConcurrentHashMap<>();
    }

    public void addUser(User user){
        userStore.put(user.id(), user);

        if(user.isPremium()){
            premiumUsers.add(user.id());
        }

        cityIndex
            .computeIfAbsent(user.city(), k -> ConcurrentHashMap.newKeySet())
            .add(user.id());

        deviceIndex 
            .computeIfAbsent(user.deviceType(), k -> ConcurrentHashMap.newKeySet())
            .add(user.id());
    }

    public int getIndexedUsersCount(){
        return userStore.size();
    }
}
