// EG/2020/4307
// Yaparathna Y.M.S.K

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TravelCostCalculator {
    static Map<String, Double> hashMap_a = new HashMap<>();
    static Map<String, Double> hashMap_b = new HashMap<>();
    static Map<String, Double> hashMap_c = new HashMap<>();

    static void functionHashMap_a(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i; 
        while ((i = reader.readLine()) != null) {
            String[] point = i.split(",");
            hashMap_a.put(point[0].toUpperCase(), Double.parseDouble(point[1]));
        }
    }

    static void functionHashMap_b(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i;
        while ((i = reader.readLine()) != null) {
            String[] point = i.split(",");
            hashMap_b.put(point[0].toUpperCase(), Double.parseDouble(point[1]));
        }
    }

    static void functionHashMap_c(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i;
        while ((i = reader.readLine()) != null) {
            String[] point = i.split(",");
            hashMap_c.put(point[0].toUpperCase(), Double.parseDouble(point[1]));
        }
    }

    public static void main(String[] args) {
        try {
            functionHashMap_a("data/hotel_rates.csv");
            functionHashMap_b("data/exchange_rates.csv");
            functionHashMap_c("data/flight_costs.csv");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter your destination: ");
            String destination = reader.readLine().toUpperCase();

            double flight_cost = hashMap_c.getOrDefault(destination, 0.0);
            double hotel_cost = hashMap_a.getOrDefault(destination, 0.0);

            System.out.print("Enter your stay duration in days: ");
            int stay_duration = Integer.parseInt(reader.readLine());
            hotel_cost *= stay_duration;

            double total_cost_usd = flight_cost + hotel_cost;

            System.out.printf("Flight cost: USD %.2f\n", flight_cost);
            System.out.printf("Hotel cost (%d days): USD %.2f\n", stay_duration, hotel_cost);
            System.out.printf("Total: USD %.2f\n", total_cost_usd);

            String[] available_currencies = hashMap_b.keySet().toArray(new String[0]);
            System.out.print("Select your currency for final price estimation(" + String.join(", ", available_currencies) + "): ");
            String selected_currency = reader.readLine();

            double final_price_local_currency = total_cost_usd * hashMap_b.get(selected_currency);

            System.out.printf("Total in %s: %.2f\n", selected_currency, final_price_local_currency);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
