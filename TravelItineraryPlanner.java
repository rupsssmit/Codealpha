import java.util.*;
import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class TravelItineraryPlanner {

    private List<Destination> destinations;
    private double totalBudget;
    private String travelStartDate;
    private String travelEndDate;

    public TravelItineraryPlanner() {
        destinations = new ArrayList<>();
        totalBudget = 0.0;
    }

    
    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    
    public void setTravelDates(String startDate, String endDate) {
        this.travelStartDate = startDate;
        this.travelEndDate = endDate;
    }

    
    public void setTotalBudget(double budget) {
        this.totalBudget = budget;
    }

    
    public void displayItinerary() {
        double totalCost = 0;
        System.out.println("\n== Travel Itinerary =="); 
        System.out.println("Travel Dates: " + travelStartDate + " to " + travelEndDate);
        System.out.println("Total Budget: $" + totalBudget);
        System.out.println("\n== Destinations: ==");

        for (Destination destination : destinations) {
            System.out.println(destination);
            totalCost += destination.getEstimatedCost();
            System.out.println("Map link: " + destination.getMapLink());
        }

        System.out.println("\nTotal Estimated Cost: $" + totalCost);
        checkBudget(totalCost);
    }

    
    private void checkBudget(double totalCost) {
        if (totalCost > totalBudget) {
            System.out.println("Warning: Your trip exceeds the budget by $" + (totalCost - totalBudget));
        } else {
            System.out.println("Your trip is within budget.");
        }
    }

    
    static class Destination {
        private String name;
        private String weatherForecast;
        private double estimatedCost;

        public Destination(String name, String weatherForecast, double estimatedCost) {
            this.name = name;
            this.weatherForecast = weatherForecast;
            this.estimatedCost = estimatedCost;
        }

        public String getName() {
            return name;
        }

        public String getWeatherForecast() {
            return weatherForecast;
        }

        public double getEstimatedCost() {
            return estimatedCost;
        }

        @Override
        public String toString() {
            return name + " - Weather: " + weatherForecast + ", Estimated Cost: $" + estimatedCost;
        }

        
        public String getMapLink() {
            return "https://www.google.com/maps?q=" + name.replace(" ", "+");
        }
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TravelItineraryPlanner planner = new TravelItineraryPlanner();

        
        System.out.print("Enter travel start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter travel end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        planner.setTravelDates(startDate, endDate);

        System.out.print("Enter total trip budget: ");
        double budget = scanner.nextDouble();
        planner.setTotalBudget(budget);

        
        scanner.nextLine();  
        while (true) {
            System.out.print("Enter destination name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            
            String weather = WeatherAPI.getWeatherForecast(name);

            System.out.print("Enter estimated cost for " + name + ": ");
            double cost = scanner.nextDouble();
            scanner.nextLine();  

            Destination destination = new Destination(name, weather, cost);
            planner.addDestination(destination);
        }

        
        planner.displayItinerary();
    }

    
    static class WeatherAPI {
        public static String getWeatherForecast(String city) {
            String[] weatherOptions = {"Sunny", "Rainy", "Cloudy", "Windy", "Snowy"};
            Random rand = new Random();
            return weatherOptions[rand.nextInt(weatherOptions.length)] + " (24°C)";
        }
    }
}
