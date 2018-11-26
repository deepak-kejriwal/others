

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestJsonStructure {
	public static void main2(String[] args) throws Exception {
		List<String> productTypes = new ArrayList<>();
		productTypes.add("equipment");
		productTypes.add("service");
		Stream<String> streamlist = productTypes.stream();

		// Stream<String> stream=Stream.of("equipment","activationFees");
		String result = streamlist.filter(s -> s.equalsIgnoreCase("equipment")).findAny().orElse(null);
		System.out.println(result);
		boolean flag = streamlist.anyMatch(s -> s.equalsIgnoreCase("equipment"));
		System.out.println(flag);

	}

	public static void main(String[] args) throws Exception {
		String filePath = "C:\\Users\\deepak\\Desktop\\response.json";
		String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
		//ResponseBean  jsonObject = JsonService.getObjectFromJson(jsonString,ResponseBean.class);
		
		//System.out.println(JsonService.getJsonFromObject(jsonObject));
	}

}
