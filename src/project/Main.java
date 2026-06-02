//Main.java
package project;

import project.index.AudienceIndex;
import project.model.DeviceType;

import project.model.User;

public class Main {
    public static void main(String[] args) {

    User[] sampleUsers = new User[] {
        new User(101, "Alice Johnson", "New York", DeviceType.IOS, true),
        new User(102, "Bob Smith", "San Francisco", DeviceType.ANDROID, false),
        new User(103, "Charlie Brown", "New York", DeviceType.WINDOWS, true),
        new User(104, "Diana Prince", "Los Angeles", DeviceType.IOS, false),
        new User(105, "Evan Wright", "Seattle", DeviceType.LINUX, true),
        new User(106, "Fiona Gallagher", "Chicago", DeviceType.ANDROID, false),
        new User(107, "George Clark", "Austin", DeviceType.WINDOWS, false),
        new User(108, "Hannah Abbott", "New York", DeviceType.IOS, true),
        new User(109, "Ian Malcolm", "Los Angeles", DeviceType.LINUX, false)
    };

    AudienceIndex index = new AudienceIndex();

    for(User u: sampleUsers){
        index.addUser(u);
    }

    //getting all premium users
    System.out.println("All premium users: "+index.getPremiumUsers());

    //getting all users in city New York 
    System.out.println("All users in New York: "+index.getUsersByCity("New York"));

    //getting all users using Androids 
    System.out.println("All users using Android: "+index.getUsersByDeviceType(DeviceType.ANDROID));

    //getting all Premium users using Androids 
    System.out.println("All Premium users using Android: "+index.getPremiumAndroidUsers());

    //getting Premium users in city Los Angeles 
    System.out.println("All Premium users in Los Angeles: "+index.getPremiumUsersByCity("Los Angeles"));

    //getting all users in city Seattle using Linux 
    System.out.println("All users in city Seattle using Linux: "+index.getUsersByCityAndDevice("Seattle", DeviceType.LINUX));

    //getting all Premium users in city New York using IOS  
    System.out.println("All Premium users in city New York using IOS: "+index.getPremiumUsersByCityAndDevice("New York", DeviceType.IOS));
    }
}
