package security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
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
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		int i = 0;
		while(i<10) {
			String hashedPassword = encoder.encode(password);
			System.out.println(hashedPassword);
			i++;
		}
	}
	
	@Test
	public void testByteEncrypt() {
		try{
		String text = "young";
		String salt = KeyGenerators.string().generateKey();
		System.out.println("the salt is :"+salt);
		
		TextEncryptor textEncryptor = Encryptors.text(password, salt);
		String encryptText = textEncryptor.encrypt(text);
		System.out.println("encrypt text is : " + encryptText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
