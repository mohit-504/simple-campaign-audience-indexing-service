//Main.java
package project;

import java.util.ArrayList;
import java.util.List;
// import java.util.Set;
import java.util.Set;

import project.discovery.SortedAudienceDiscovery;
// import project.analytics.AudienceAnalytics;
import project.generator.UserGenerator;
import project.index.AudienceIndex;
import project.index.ConcurrentAudienceIndex;
// import project.model.DeviceType;
import project.model.DeviceType;
import project.model.User;
import project.query.AudienceQueryService;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        // runSingleThreadBenchmark();
        // runMultiThreadBenchmark();
        queryTesting();
    }

    public static void queryTesting(){
        UserGenerator generator = new UserGenerator();
        AudienceIndex index = new AudienceIndex();
        AudienceQueryService queryService = new AudienceQueryService(index);

        int userCount = 100_000;
        for(int i=1;i<=userCount;i++){
            User user = generator.generateUser(i);
            index.addUser(user);
            // analytics.recordUser(user);
            // discovery.recordUser(user);
        }

        Set<Long> users = queryService.execute("premium AND city=Mumbai");
        String query = queryService.buildQuery(true, "Mumbai", DeviceType.ANDROID);

        System.out.println(query);
        System.out.println(users.size());
    }

    public static void runSingleThreadBenchmark(){
        UserGenerator generator = new UserGenerator();
        AudienceIndex index = new AudienceIndex();
        // AudienceAnalytics analytics = new AudienceAnalytics();
        SortedAudienceDiscovery discovery = new SortedAudienceDiscovery();

        long start = System.nanoTime();
        int userCount = 100_000;
        for(int i=1;i<=userCount;i++){
            User user = generator.generateUser(i);
            index.addUser(user);
            // analytics.recordUser(user);
            discovery.recordUser(user);
        }
        long end = System.nanoTime();

        double buildTime = (end - start) / 1_000_000.00;
        System.out.println("Single Thread - Time taken to generate users, build indexes and record analytics: "+buildTime);

        System.out.println("First user: "+discovery.getFirstUserId());
        System.out.println("Last user: "+discovery.getLastUserId());
        System.out.println("Users 50000 to 50010: "+discovery.getUsersInRange(50000,50010));
        System.out.println("First 10 users: "+discovery.getFirstNUsers(10));


    } 

    public static void runMultiThreadBenchmark() throws InterruptedException{
        ConcurrentAudienceIndex index = new ConcurrentAudienceIndex();
        // AudienceAnalytics analytics = new AudienceAnalytics();

        List<Thread> workers = new ArrayList<>();
        int totalUsers = 100_000;
        int threadCount = 4;
        int usersPerThread = totalUsers / threadCount;

        for(int t=0;t<threadCount;t++){
            long startId = (long)t*usersPerThread + 1;
            long endId = (t == threadCount - 1) ? totalUsers : (long)(t + 1) * usersPerThread; 
            //if last thread then give all leftover users to it

            long from = startId;
            long to = endId;

            Thread worker = new Thread(() -> {
                UserGenerator generator = new UserGenerator();

                for(long id=from;id<=to;id++){
                    User user = generator.generateUser(id);
                    index.addUser(user);
                }
            },
            "ingestion-worker"+t);

            workers.add(worker);
        }
        
        long start = System.nanoTime();
        for(Thread w : workers){
            w.start();
        }
        for(Thread w : workers) {
            w.join();
        }
        long end = System.nanoTime();

        double buildTime = (end - start) / 1_000_000.00;
        System.out.println("Multi Thread - Time taken to generate users, build indexes and record analytics: "+buildTime);

        System.out.println("Indexed users: "+ index.getIndexedUsersCount());
    }
}
