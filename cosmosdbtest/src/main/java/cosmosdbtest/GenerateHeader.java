package cosmosdbtest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class GenerateHeader {

	public static String generate(String date)
	{
		 String authorization = null;
		 String verb="POST";
		 String resourceType="docs";
		 String resourceId = "dbs/tempdb/colls/testcontainer";
		 //String date=DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneId.of("GMT")));
		 System.out.println(date);
		 String key="5ZIwbKPzDCnW6J9btNDHVkBKJpVQ3Iwkw18s3PQPW2ASHkkfXghANmScK23JgEC7yk3igrdTpeTn2fktBE9GgQ==";
		 String keyType = "master";
		 String tokenVersion = "1.0";
		 String payload=verb.toLowerCase()+"\n"
		 +resourceType.toLowerCase()+"\n"
		 +resourceId+"\n"
		 +date.toLowerCase()+"\n"
		 +""+"\n";
		 Mac sha256_HMAC;
		try {
			sha256_HMAC = Mac.getInstance("HmacSHA256");
		
		 SecretKeySpec secret_key = new SecretKeySpec(Base64.getDecoder().decode(key), "HmacSHA256");
		 sha256_HMAC.init(secret_key);
		 String signature = Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(payload.getBytes("UTF-8")));
		 authorization=URLEncoder.encode("type="+keyType+"&ver="+tokenVersion+"&sig="+signature, "utf-8");
		 System.out.println(authorization);
		}catch (InvalidKeyException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return authorization;
	}
}
