package com.example.project;

public class UserProfile {
    private String id;
    private String userId;
    private String username;
    private String profilePictureUrl;

    // Constructor
    public UserProfile(String id, String username, String profilePictureUrl) {
        this.id = id;
        this.username = username;
        this.profilePictureUrl = (profilePictureUrl != null) ? profilePictureUrl : "";
    }

    // --- Getters & Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
}