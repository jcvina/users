package ar.com.users.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest
{
	private User user;

    @Test
    public void testInit() {
        this.user = UserFixture.withDefaults();
        assertNotNull(this.user);
        assertNotNull(this.user.getId());
        assertNotNull(this.user.getUsername());
        assertNotNull(this.user.getFirstName());
        assertNotNull(this.user.getLastName());
        assertNotNull(this.user.getEmail());
        assertNotNull(this.user.getPassword());
        assertNotNull(this.user.getCreatedAt());
        assertNotNull(this.user.getUpdatedAt());
		assertNotNull(this.user.getGender());
		assertNotNull(this.user.getBirthDate());
        assertFalse(this.user.equals(null));
        assertNotNull(this.user.toString());
    }
    
}