package security;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
public class SpringEncrypt {
	
	private final String password = "123456";
	
	@Test
	public void testPasswordEncrypt() {
		
		List<String> encryptPasswords = new ArrayList<String>();
		int i = 0;
		while(i<10) {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String hashedPassword = encoder.encode(password);
			encryptPasswords.add(hashedPassword);
			i++;
		}
		for(String text : encryptPasswords) {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			System.out.println(text);
			assertTrue(encoder.matches(password, text));
		}
	}
	
	@Test
	public void testTextEncrypt() {
		String text = "young";
		System.out.println("original text is:" +text);
		String salt = KeyGenerators.string().generateKey();
		System.out.println("the salt is :"+salt);
		
		TextEncryptor textEncryptor = Encryptors.text(password, salt);
		
		for(int i = 0 ; i < 10 ; i++) {
			String encryptText = textEncryptor.encrypt(text);
			System.out.println("encrypt text is : " + encryptText);
			System.out.println("decrypt test is " + textEncryptor.decrypt(encryptText));
			System.out.println("--------------------------------------------------");
		}
	}
		
		@Test
		public void testQueryableEncrypt() {
			String text = "young";
			System.out.println("original text is:" +text);
			String salt = KeyGenerators.string().generateKey();
			System.out.println("the salt is :"+salt);
			
			TextEncryptor textEncryptor = Encryptors.queryableText(password, salt);
			
			for(int i = 0 ; i < 10 ; i++) {
				String encryptText = textEncryptor.encrypt(text);
				System.out.println("encrypt text is : " + encryptText);
				System.out.println("decrypt test is " + textEncryptor.decrypt(encryptText));
				System.out.println("--------------------------------------------------");
			}
	}

}
