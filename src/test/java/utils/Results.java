package utils;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Results {
  public static List<Result> results = new ArrayList<>();

  public static String resultsToString() {
    StringBuilder text = new StringBuilder("Nombre,Precio,Link\n");
    for (Result r : results) {
      text.append(r.toString());
    }
    return text.toString();
  }

  public static void printToFile() throws Exception {
    try {
      FileWriter myWriter = new FileWriter("ML_pricelist_" + randomNumericString(10) + ".csv");
      myWriter.write(resultsToString());
      myWriter.close();
    } catch (Exception e) {
      throw new Exception("The file cannot be created nor written");
    }
  }

  private static String randomNumericString(int amount) {
    String data = "0123456789";
    Random r = new Random();
    StringBuilder number = new StringBuilder();

    for (int i = 0; i < amount; i++) {
      number.append(data.charAt(r.nextInt(data.length())));
    }
    return number.toString();
  }
}
