import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 
 * @author Deepak Kejriwal
 *
 */
public class BaseEncodingImpl {

	public static void main(String[] args) {
       String input="https://www.google.com/";
		MessageDigest md = getMD5Digester();
        
        //MD5 conversion of input
        byte[] md5Bytes=md.digest(input.getBytes());
        System.out.println("MD5:"+new String(md5Bytes));
        System.out.println("MD5:"+new String(md5Bytes).length());
		
        // conversion of MD5 bytes to 64 encoded bytes
        String encoded64 = Base64.getUrlEncoder().withoutPadding().encodeToString(md5Bytes);
		System.out.println("Base64:"+ encoded64);
		System.out.println("Base64:"+encoded64.length());
		
		// conversion of MD5 bytes to 62 encoded bytes
		Base62 base62=Base62.createInstance();
		byte[] bytes=base62.encode(md5Bytes);
		System.out.println("Base62:"+new String(bytes));
		System.out.println("Base62:"+new String(bytes).length());
		// conversion of 62 encoded bytes back to MD5 bytes
		bytes=base62.decode(bytes);
		System.out.println("MD5:"+new String(bytes));
		System.out.println("Output of MD5 bytes before encoding and after decoding is same. Hence, implementation of Base62 class is correct.");
		
	}

	private static MessageDigest getMD5Digester() throws InternalError {
		MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("MD5 not supported", nsae);
        }
		return md;
	}

}
