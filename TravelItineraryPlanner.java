import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Destination {
    String name;
    String activity;
    
    public Destination(String name, String activity) {
        this.name = name;
        this.activity = activity;
    }
    
    @Override
    public String toString() {
        return "Destination: " + name + ", Activity: " + activity;
    }
}

class TravelPlan {
    List<Destination> destinations;
    String startDate;
    String endDate;

    public TravelPlan(String startDate, String endDate) {
        this.destinations = new ArrayList<>();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    @Override
    public String toString() {
        StringBuilder itinerary = new StringBuilder();
        itinerary.append("Travel Itinerary:\n");
        itinerary.append("From: ").append(startDate).append(" To: ").append(endDate).append("\n");
        for (Destination dest : destinations) {
            itinerary.append(dest).append("\n");
        }
        return itinerary.toString();
    }
}

class WeatherService {
    public String getWeather(String destination) {
        // Placeholder for actual weather fetching logic.
        return "Sunny, 75°F"; // Example weather
    }
}

class BudgetCalculator {
    public double calculateBudget(List<Destination> destinations) {
        // Placeholder for budget calculation logic
        return destinations.size() * 100; // Example budget calculation
    }
}

public class TravelItineraryPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Travel Itinerary Planner!");

        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        TravelPlan travelPlan = new TravelPlan(startDate, endDate);

        while (true) {
            System.out.print("Enter destination (or 'done' to finish): ");
            String destinationName = scanner.nextLine();
            if (destinationName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter activity for this destination: ");
            String activity = scanner.nextLine();

            travelPlan.addDestination(new Destination(destinationName, activity));
        }

        // Fetch weather information for each destination
        WeatherService weatherService = new WeatherService();
        for (Destination dest : travelPlan.destinations) {
            String weather = weatherService.getWeather(dest.name);
            System.out.println("Weather in " + dest.name + ": " + weather);
        }

        // Calculate budget
        BudgetCalculator budgetCalculator = new BudgetCalculator();
        double budget = budgetCalculator.calculateBudget(travelPlan.destinations);
        System.out.println("Estimated Budget: $" + budget);

        // Print the final itinerary
        System.out.println(travelPlan);
    }
}