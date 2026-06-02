//UserGenerator.java

package project.generator;

import java.util.Random;

import project.model.DeviceType;
import project.model.User;

public class UserGenerator {
    private static final String[] cities = {
        "New York",
        "Los Angeles",
        "Tokyo",
        "Seattle",
        "Chicago",
        "London",
        "Melbourne",
        "Berlin",
        "Mumbai"
    };

    private final Random random;

    public UserGenerator(){
        this.random =  new Random();
    }

    public User generateUser(long id){
        String name = "User" + id;
        String city = cities[random.nextInt(cities.length)];
        DeviceType [] types = DeviceType.values();
        DeviceType deviceType = types[random.nextInt(types.length)];
        boolean isPremium = (random.nextInt(100) < 20) ? true : false; //20% premium customers

        return new User(id, name, city, deviceType, isPremium);
    }
}
