package Management;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HotelMenuSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        // ===== MENU =====
        String[] items = {
        	    "Chicken Biryani",
        	    "Veg Fried Rice",
        	    "Paneer Butter Masala",
        	    "Schezwan Noodles",
        	    "Mutton Biryani",
        	    "Egg Fried Rice",
        	    "Chicken 65",
        	    "Gobi Manchurian",
        	    "Veg Noodles",
        	    "Butter Naan"
        	};

        	double[] prices = {
        	    180.0, 150.0, 160.0, 140.0, 220.0, 160.0, 190.0, 130.0, 140.0, 40.0
        	};


        // ===== RANDOM THANK-YOU QUOTES =====
        String[] quotes = {
            "Good food is the foundation of genuine happiness.",
            "Food tastes better when you eat it with your family.",
            "One cannot think well, love well, sleep well if one has not dined well.",
            "Life is uncertain. Eat dessert first!",
            "There is no sincere love than the love of food."
        };

        // ===== CUSTOMER DETAILS =====
        System.out.println("Enter your name: ");
        String customerName = sc.nextLine();
        System.out.println("Enter your table number: ");
        String tableNumber = sc.nextLine();

        // ===== ORDER DETAILS =====
        int[] quantities = new int[items.length];
        double totalCost = 0.0;

        while (running) {
            System.out.println("\n===== HOTEL MENU MANAGEMENT SYSTEM =====");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View Current Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n----- MENU -----");
                    for (int i = 0; i < items.length; i++) {
                        System.out.printf("%d. %-25s ₹%.2f%n", i + 1, items[i], prices[i]);
                    }
                }

                case 2 -> {
                    System.out.println("\nEnter item number to order (1-" + items.length + "): ");
                    int itemNo = sc.nextInt();
                    if (itemNo < 1 || itemNo > items.length) {
                        System.out.println("Invalid item number!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    if (qty <= 0) {
                        System.out.println("Invalid quantity!");
                        break;
                    }
                    quantities[itemNo - 1] += qty;
                    System.out.println(qty + " x " + items[itemNo - 1] + " added to your order.");
                }

                case 3 -> {
                    System.out.println("\n----- CURRENT ORDER -----");
                    totalCost = 0;
                    for (int i = 0; i < items.length; i++) {
                        if (quantities[i] > 0) {
                            double cost = quantities[i] * prices[i];
                            totalCost += cost;
                            System.out.printf("%-25s Qty: %d  Cost: ₹%.2f%n", items[i], quantities[i], cost);
                        }
                    }
                    System.out.printf("Total so far: ₹%.2f%n", totalCost);
                }

                case 4 -> {
                    System.out.print("Are you sure you want to cancel your order? (yes/no): ");
                    sc.nextLine(); // consume newline
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("yes")) {
                        Arrays.fill(quantities, 0);
                        totalCost = 0;
                        System.out.println("✅ Your order has been cancelled.");
                    } else {
                        System.out.println("❌ Order not cancelled.");
                    }
                }

                case 5 -> {
                    System.out.println("\n===== ORDER SUMMARY =====");
                    double subtotal = 0;
                    int totalQty = 0;
                    for (int i = 0; i < items.length; i++) {
                        if (quantities[i] > 0) {
                            double cost = quantities[i] * prices[i];
                            subtotal += cost;
                            totalQty += quantities[i];
                            System.out.printf("%-25s x %d = ₹%.2f%n", items[i], quantities[i], cost);
                        }
                    }

                    // Add tip option
                    System.out.print("Enter tip amount (or 0 for none): ₹");
                    double tip = sc.nextDouble();

                    double gst = subtotal * 0.05;
                    double discount = (subtotal > 500) ? subtotal * 0.10 : 0;
                    double total = subtotal + gst + tip - discount;

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String dateTime = LocalDateTime.now().format(dtf);

                    System.out.println("\n------------------------------------");
                    System.out.println("Customer: " + customerName + "  |  Table: " + tableNumber);
                    System.out.println("Order Time: " + dateTime);
                    System.out.println("------------------------------------");
                    System.out.printf("Subtotal: ₹%.2f%n", subtotal);
                    System.out.printf("GST (5%%): ₹%.2f%n", gst);
                    if (discount > 0)
                        System.out.printf("Discount (10%%): -₹%.2f%n", discount);
                    System.out.printf("Tip: ₹%.2f%n", tip);
                    System.out.println("------------------------------------");
                    System.out.printf("Total Payable: ₹%.2f%n", total);
                    System.out.println("------------------------------------");

                    int prepTime = totalQty * 5; // 5 minutes per item
                    System.out.println("Estimated preparation time: " + prepTime + " minutes.");

                    // Random thank-you quote
                    Random rand = new Random();
                    System.out.println("\n💬 \"" + quotes[rand.nextInt(quotes.length)] + "\"");
                    System.out.println("✅ Thank you for ordering with us, " + customerName + "!");

                    // Reset order after checkout
                    Arrays.fill(quantities, 0);
                    totalCost = 0;
                }

                case 6 -> {
                    System.out.println("\n==========================================");
                    System.out.println("   🙏 THANK YOU FOR VISITING OUR RESTAURANT 🙏");
                    System.out.println("   We hope you enjoyed your meal! 🍽️");
                    System.out.println("==========================================\n");

                    System.out.print("Please rate your experience (1–5): ");
                    int rating = sc.nextInt();
                    System.out.println("⭐".repeat(rating) + "  Thank you for your feedback!");

                    running = false;
                }

                default -> System.out.println("Invalid choice! Please try again.");
            }
        }

        sc.close();
    }
}
