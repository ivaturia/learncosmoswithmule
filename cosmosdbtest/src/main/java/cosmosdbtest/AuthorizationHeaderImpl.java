package cosmosdbtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class AuthorizationHeaderImpl {

	private static final Logger log = LoggerFactory.getLogger(AuthorizationHeaderImpl.class);

	public static String generateHeader(String date) {

		AuthorizationHeader cah = new AuthorizationHeader();

		String verb = "POST";
		String resourceType = "docs";
		String resourceId = "dbs/tempdb/colls/testcontainer";
		String masterKey = "5ZIwbKPzDCnW6J9btNDHVkBKJpVQ3Iwkw18s3PQPW2ASHkkfXghANmScK23JgEC7yk3igrdTpeTn2fktBE9GgQ==";
		String header = "";

		try {
			header = cah.getHeader(verb, resourceType, resourceId, masterKey,date);
			log.info("Generating Header: {}",header);
		} catch (UnsupportedEncodingException uee) {
			log.error(uee.getMessage());
		}
		return header;
	}

	public static String getTime() {
		AuthorizationHeader cah = new AuthorizationHeader();
		String serverTime = "";
		serverTime =  cah.getServerTime();
		log.info("ServerTime: {}", serverTime);
		return serverTime;
	}

}
