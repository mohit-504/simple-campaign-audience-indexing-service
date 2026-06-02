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
    System.out.println("All premium users: "+index.getAllPremiumUsers());

    //getting all users in city New York 
    System.out.println("All users in New York: "+index.getUsersByCity("New York"));

    //getting all users using Androids 
    System.out.println("All users using Android: "+index.getUsersByDeviceType(DeviceType.ANDROID));
    }
}
