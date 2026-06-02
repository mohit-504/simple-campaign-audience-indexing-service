//Main.java
package project;

import java.util.Set;

import project.analytics.AudienceAnalytics;
import project.generator.UserGenerator;
import project.index.AudienceIndex;
import project.model.DeviceType;

import project.model.User;

public class Main {
    public static void main(String[] args) {

    // User[] sampleUsers = new User[] {
    //     new User(101, "Alice Johnson", "New York", DeviceType.IOS, true),
    //     new User(102, "Bob Smith", "San Francisco", DeviceType.ANDROID, false),
    //     new User(103, "Charlie Brown", "New York", DeviceType.WINDOWS, true),
    //     new User(104, "Diana Prince", "Los Angeles", DeviceType.IOS, false),
    //     new User(105, "Evan Wright", "Seattle", DeviceType.LINUX, true),
    //     new User(106, "Fiona Gallagher", "Chicago", DeviceType.ANDROID, false),
    //     new User(107, "George Clark", "Austin", DeviceType.WINDOWS, false),
    //     new User(108, "Hannah Abbott", "New York", DeviceType.IOS, true),
    //     new User(109, "Ian Malcolm", "Los Angeles", DeviceType.LINUX, false)
    // };

    UserGenerator generator = new UserGenerator();
    AudienceIndex index = new AudienceIndex();
    AudienceAnalytics analytics = new AudienceAnalytics();
    
    long start = System.nanoTime();

    int userCount = 100_000;
    for(int i=1;i<=userCount;i++){
        User user = generator.generateUser(i);
        index.addUser(user);
        analytics.recordUser(user);
    }

    long end = System.nanoTime();

    double buildTime = (end - start) / 1_000_000.00;

    //getting total build time
    System.out.println("Time taken to generate users, build indexes and record analytics: "+buildTime);

    //INDEX OPERATIONS
    //getting all premium users
    // System.out.println("All premium users: "+index.getPremiumUsers());

    // //getting all users in city New York 
    // System.out.println("All users in New York: "+index.getUsersByCity("New York"));

    // //getting all users using Androids 
    // System.out.println("All users using Android: "+index.getUsersByDeviceType(DeviceType.ANDROID));

    // //getting all Premium users using Androids 
    // System.out.println("All Premium users using Android: "+index.getPremiumAndroidUsers());

    // //getting Premium users in city Los Angeles 
    // System.out.println("All Premium users in Los Angeles: "+index.getPremiumUsersByCity("Los Angeles"));

    // //getting all users in city Mumbai using Linux 
    // System.out.println("All users in city Mumbai using Linux: "+index.getUsersByCityAndDevice("Mumbai", DeviceType.LINUX));

    // //getting all Premium users in city New York using IOS  
    // System.out.println("All Premium users in city New York using IOS: "+index.getPremiumUsersByCityAndDevice("New York", DeviceType.IOS));

    // ANALYTICS OPERATIONS
    //getting count of total users
    System.out.println("Number of users: "+analytics.getTotalUserCount());

    //getting count of premium users
    System.out.println("Number of premium users: "+analytics.getPremiumUserCount());

    //getting percentage of premium users
    System.out.println("Percentage of premium users: "+analytics.getPremiumUserPercentage()+"%");

    //getting city wise count of users
    System.out.println("City-wise users: "+analytics.getCityCounts());

    //getting device type wise count of users
    System.out.println("DeviceType-wise users: "+analytics.getDeviceTypeCounts());

    //BENCHMARKS
    //getUsersByCity benchmark
    start = System.nanoTime();
    Set<Long> mumbaiUsers = index.getUsersByCity("Mumbai");
    end = System.nanoTime();
    buildTime = (end - start) / 1_000_000.00;
    System.out.println("mumbai users segment size: "+mumbaiUsers.size());
    System.out.println("time taken: "+buildTime);

    //getPremiumUsersByCityAndDevice benchmark
    start = System.nanoTime();
    Set<Long> mumbaiAndroidUsers = index.getPremiumUsersByCityAndDevice("Mumbai",DeviceType.ANDROID);
    end = System.nanoTime();
    buildTime = (end - start) / 1_000_000.00;
    System.out.println("mumbai android users segment size: "+mumbaiAndroidUsers.size());
    System.out.println("time taken: "+buildTime);

    }
}
