package business.ultil.enumList;

import java.io.*;
import java.util.List;

public class IOFile {
	
	public static final String PATH_CATEGORY = "/Users/phamlinh/Desktop/HN_JV240617_MD02_PRJ_Pham_Thi_Thuy_Linh/src/data/categoryList.txt";
	public static final String PATH_PRODUCT = "/Users/phamlinh/Desktop/HN_JV240617_MD02_PRJ_Pham_Thi_Thuy_Linh/src/data/productList.txt";
	public static final String PATH_USER = "/Users/phamlinh/Desktop/HN_JV240617_MD02_PRJ_Pham_Thi_Thuy_Linh/src/data/userList.txt";
	public static final String PATH_USERCURRENT = "/Users/phamlinh/Desktop/HN_JV240617_MD02_PRJ_Pham_Thi_Thuy_Linh/src/data/currentUser.txt";
	public static final String PATH_ORDER = "/Users/phamlinh/Desktop/HN_JV240617_MD02_PRJ_Pham_Thi_Thuy_Linh/src/data/orderList.txt";

//	public static final String PATH_CART = "";

	public static <T> void writeObjectToFile(List<T> list, String path) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(list);
//			System.out.println("List written to file: " + path);
		} catch (IOException e) {
//			System.err.println("Error writing object to file: " + path);
			e.printStackTrace();
		}
	}


	public static <T> List<T> readObjectFromFile(String path) {
		List<T> listOut = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			listOut = (List<T>) ois.readObject();
		} catch (IOException e) {
//			System.err.println("Error reading object from file: " + path);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
//			System.err.println("Class not found during object deserialization: " + e.getMessage());
			e.printStackTrace();
		}
		return listOut;
	}

}
