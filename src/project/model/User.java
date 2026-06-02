package project.model;

public record User (
    long id,
    String name,
    String city,
    DeviceType deviceType,
    boolean isPremium
) {}
