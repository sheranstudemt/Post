package com.CodeQnA;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PostTest {
    private Post post;

    @BeforeEach
    public void setUp() {
        // Setting up a valid post
        post = new Post(1, "ValidTitle", "A".repeat(250), new String[]{"java", "programming"}, "Easy", "Ordinary");
    }

    // Test cases for addPost function

    @Test
    public void testAddPost_ValidPost() {
        post = new Post(1, "Valid Title", "A".repeat(250), new String[]{"java", "programming"}, "Easy", "Ordinary");
        assertTrue(post.addPost());
    }

    @Test
    public void testAddPost_TitleTooShort() {
        post = new Post(2, "Short", "A".repeat(250), new String[]{"java", "programming"}, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_TitleTooLong() {
        post = new Post(3, "A".repeat(251), "A".repeat(250), new String[]{"java", "programming"}, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_BodyTooShort() {
        post = new Post(4, "Valid Title", "Short body", new String[]{"java", "programming"}, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_InvalidTagsUppercase() {
        post = new Post(5, "Valid Title", "A".repeat(250), new String[]{"Java", "programming"}, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_TooManyTagsForEasy() {
        post = new Post(6, "Valid Title", "A".repeat(250), new String[]{"java", "programming", "questions", "answers"}, "Easy", "Ordinary");
        assertFalse(post.addPost());
    }

    // Test cases for addComment function

    @Test
    public void testAddComment_ValidComment() {
        assertTrue(post.addComment("This is valid comment."));
    }

    @Test
    public void testAddComment_TooShortComment() {
        assertFalse(post.addComment("Too short."));
    }

    @Test
    public void testAddComment_TooLongComment() {
        assertFalse(post.addComment("This comment has too many words and should not be valid."));
    }

    @Test
    public void testAddComment_CommentFirstWordNotUppercase() {
        assertFalse(post.addComment("this should be invalid."));
    }

    @Test
    public void testAddComment_ValidCommentOnPostWithThreeComments() {
        post = new Post(2, "Valid Title", "A".repeat(300), new String[]{"java", "programming"}, "Very Difficult", "Highly Needed");
        post.addComment("First valid comment.");
        post.addComment("Second valid comment.");
        post.addComment("Third valid comment.");
        assertTrue(post.addComment("Fourth valid comment."));
    }

    @Test
    public void testAddComment_InvalidCommentOnEasyPostWithThreeComments() {
        post = new Post(1, "Valid Title", "A".repeat(250), new String[]{"java", "programming"}, "Easy", "Ordinary");
        post.addComment("First valid comment.");
        post.addComment("Second valid comment.");
        post.addComment("Third valid comment.");
        assertFalse(post.addComment("Fourth valid comment."));
    }
}
