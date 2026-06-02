//AudienceAnalytics.java

package project.analytics;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import project.model.DeviceType;
import project.model.User;

public class AudienceAnalytics {
    private final Map<String, Integer> cityCounts; //ideally hashmap but using treemap here to learn concepts of treemap
    private final Map<DeviceType, Integer> deviceCounts;
    private long totalUsers;
    private long premiumUsers;

    public AudienceAnalytics(){
        cityCounts = new TreeMap<>();
        deviceCounts = new EnumMap<>(DeviceType.class);
        totalUsers = 0;
        premiumUsers = 0;
    }

    public void recordUser(User user){
        totalUsers++;
        if(user.isPremium()){
            premiumUsers++;
        }

        cityCounts.merge(user.city(),1,Integer::sum);

        deviceCounts.merge(user.deviceType(), 1, Integer::sum);
    }

    public long getTotalUserCount(){
        return totalUsers;
    }

    public long getPremiumUserCount(){
        return premiumUsers;
    }

    public double getPremiumUserPercentage(){
        return (premiumUsers*100) / totalUsers;
    }

    public Map<String, Integer> getCityCounts(){
        return new TreeMap<>(cityCounts);
    }

    public Map<DeviceType, Integer> getDeviceTypeCounts(){
        return new EnumMap<>(deviceCounts);
    }
}
