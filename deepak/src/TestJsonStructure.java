
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import com.att.aft.dme2.internal.jettison.json.JSONObject;
import com.att.idp.catalog.common.constants.util.JsonService;
import com.att.idp.catalog.wireless.model.mds.device.DeviceListBean;


public class TestJsonStructure {

	public static void main4(String[] args) throws Exception {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(5);
		pq.add(6);
		pq.add(1);
		System.out.println(pq.peek());
	}

	public static void main2(String[] args) throws Exception {
		List<String> productTypes = new ArrayList<>();
		productTypes.add("equipment");
		productTypes.add("activationFees");
		Stream<String> streamlist = productTypes.stream();

		// Stream<String> stream=Stream.of("equipment","activationFees");
		String result = streamlist.filter(s -> s.equalsIgnoreCase("equipment")).findAny().orElse(null);
		System.out.println(result);
		boolean flag = streamlist.anyMatch(s -> s.equalsIgnoreCase("equipment"));
		System.out.println(flag);

	}

	public static void main(String[] args) throws Exception {
		//String filePath = "C:\\Users\\dk882q\\Desktop\\test.txt";
		//String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
		DeviceListBean dlb = JsonService.getObjectFromJson("",
				DeviceListBean.class);
		System.out.println(dlb);
	}

}
