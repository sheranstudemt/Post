package com.CodeQnA;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postType;
    private String postEmergency;
    private ArrayList<String> postComments = new ArrayList<>();

    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

    public boolean addPost() {
        // Condition 1: Title length and characters check
        if (postTitle == null || postTitle.length() < 10 || postTitle.length() > 250) {
            return false;
        }
        if (!postTitle.substring(0, 5).matches("[a-zA-Z]{5}")) {
            return false;
        }

        // Condition 2: Body length check
        if (postBody == null || postBody.length() < 250) {
            return false;
        }

        // Condition 3: Tags length and characters check
        if (postTags == null || postTags.length < 2 || postTags.length > 5) {
            return false;
        }
        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                return false;
            }
        }

        // Condition 4: Post type check
        if (postType.equals("Easy") && postTags.length > 3) {
            return false;
        }
        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postBody.length() < 300) {
            return false;
        }

        // Condition 5: Emergency status check
        if (postType.equals("Easy") && (postEmergency.equals("Immediately Needed") || postEmergency.equals("Highly Needed"))) {
            return false;
        }
        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postEmergency.equals("Ordinary")) {
            return false;
        }

        // If all conditions are met, write the post information to the file
        try (FileWriter writer = new FileWriter("post.txt", true)) {
            writer.write("Post ID: " + postID + "\n");
            writer.write("Title: " + postTitle + "\n");
            writer.write("Body: " + postBody + "\n");
            writer.write("Tags: " + String.join(", ", postTags) + "\n");
            writer.write("Type: " + postType + "\n");
            writer.write("Emergency: " + postEmergency + "\n");
            writer.write("Comments: " + String.join(", ", postComments) + "\n\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addComment(String comment) {
        // Condition 1: Comment text length and format check
        if (comment == null || comment.trim().isEmpty()) {
            return false;
        }
        String[] words = comment.trim().split("\\s+");
        if (words.length < 4 || words.length > 10) {
            return false;
        }
        if (!Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // Condition 2: Comment limit per post check
        if (postComments.size() >= 5) {
            return false;
        }
        if ((postType.equals("Easy") || postEmergency.equals("Ordinary")) && postComments.size() >= 3) {
            return false;
        }

        // If all conditions are met, add the comment to the list and write to the file
        postComments.add(comment);
        try (FileWriter writer = new FileWriter("comment.txt", true)) {
            writer.write("Post ID: " + postID + "\n");
            writer.write("Comment: " + comment + "\n\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
