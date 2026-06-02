//SortedAudienceDiscovery.java

package project.discovery;

import java.util.NavigableSet;
import java.util.Set;
// import java.util.SortedSet;
import java.util.TreeSet;

import project.model.User;

public class SortedAudienceDiscovery {
    private final NavigableSet<Long> allUsers;
    private final NavigableSet<Long> premiumUsers;

    public SortedAudienceDiscovery(){
        allUsers = new TreeSet<>();
        premiumUsers = new TreeSet<>();
    }

    public void recordUser(User user){
        allUsers.add(user.id());

        if(user.isPremium()){
            premiumUsers.add(user.id());
        }
    }

    public Set<Long> getAllUsersSorted(){
        return new TreeSet<>(allUsers);
    }

    public Set<Long> getPremiumUsersSorted(){
        return new TreeSet<>(premiumUsers);
    }

    public Long getFirstUserId(){
        return allUsers.first();
    }

    public Long getLastUserId(){
        return allUsers.last();
    }

    public Set<Long> getUsersInRange(long from, long to){
        return new TreeSet<>(allUsers.subSet(from, true, to, true));
    }

    public Set<Long> getFirstNUsers(int n){
        Set<Long> result = new TreeSet<>();
        int count = 0;
        for(Long uid:allUsers){
            if(count>=n){
                break;
            }
            result.add(uid);
            count++;
        }
        return result;
    }
}
