/*
MIT License

Copyright (c) 2024, Nuno Datia, Matilde Pato, ISEL

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package isel.sisinf.ui;

import isel.sisinf.jpa.JPAContext;
import isel.sisinf.model.Client;

import java.sql.Timestamp;
import java.util.Scanner;
import java.util.HashMap;

interface DbWorker
{
    void doWork();
}
class UI
{
    private enum Option
    {
        // DO NOT CHANGE ANYTHING!
        Unknown,
        Exit,
        createCostumer,
        listExistingBikes,
        checkBikeAvailability,
        obtainBookings,
        makeBooking,
        cancelBooking,
        about
    }
    private static UI __instance = null;
  
    private HashMap<Option,DbWorker> __dbMethods;

    private UI()
    {
        // DO NOT CHANGE ANYTHING!
        __dbMethods = new HashMap<Option,DbWorker>();
        __dbMethods.put(Option.createCostumer, () -> UI.this.createCostumer());
        __dbMethods.put(Option.listExistingBikes, () -> UI.this.listExistingBikes()); 
        __dbMethods.put(Option.checkBikeAvailability, () -> UI.this.checkBikeAvailability());
        __dbMethods.put(Option.obtainBookings, new DbWorker() {public void doWork() {UI.this.obtainBookings();}});
        __dbMethods.put(Option.makeBooking, new DbWorker() {public void doWork() {UI.this.makeBooking();}});
        __dbMethods.put(Option.cancelBooking, new DbWorker() {public void doWork() {UI.this.cancelBooking();}});
        __dbMethods.put(Option.about, new DbWorker() {public void doWork() {UI.this.about();}});

    }

    public static UI getInstance()
    {
        // DO NOT CHANGE ANYTHING!
        if(__instance == null)
        {
            __instance = new UI();
        }
        return __instance;
    }

    private Option DisplayMenu()
    {
        Option option = Option.Unknown;
        Scanner s = new Scanner(System.in); //Scanner closes System.in if you call close(). Don't do it
        try
        {
            // DO NOT CHANGE ANYTHING!
            System.out.println("Bicycle reservation");
            System.out.println();
            System.out.println("1. Exit");
            System.out.println("2. Create Costumer");
            System.out.println("3. List Existing Bikes");
            System.out.println("4. Check Bike Availability");
            System.out.println("5. Current Bookings");
            System.out.println("6. Make a booking");
            System.out.println("7. Cancel Booking");
            System.out.println("8. About");
            System.out.print(">");
            int result = s.nextInt();
            option = Option.values()[result];
        }
        catch(RuntimeException ex)
        {
            //nothing to do.
        }
        
        return option;

    }
    private static void clearConsole() throws Exception
    {
        // DO NOT CHANGE ANYTHING!
        for (int y = 0; y < 25; y++) //console is 80 columns and 25 lines
            System.out.println("\n");
    }

    public void Run() throws Exception
    {
        // DO NOT CHANGE ANYTHING!
        Option userInput;
        do
        {
            clearConsole();
            userInput = DisplayMenu();
            clearConsole();
            try
            {
                __dbMethods.get(userInput).doWork();
                System.in.read();
            }
            catch(NullPointerException ex)
            {
                //Nothing to do. The option was not a valid one. Read another.
            }

        }while(userInput!=Option.Exit);
    }

    /**
    To implement from this point forward. Do not need to change the code above.
    -------------------------------------------------------------------------------     
        IMPORTANT:
    --- DO NOT MOVE IN THE CODE ABOVE. JUST HAVE TO IMPLEMENT THE METHODS BELOW ---
    --- Other Methods and properties can be added to support implementation -------
    -------------------------------------------------------------------------------
    
    */

    private static final int TAB_SIZE = 24;
    JPAContext ctx = new JPAContext();
    private void createCostumer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter client name: ");
        String name = scanner.nextLine();

        System.out.print("Enter client email: ");
        String email = scanner.nextLine();

        System.out.print("Enter client address: ");
        String address = scanner.nextLine();

        System.out.print("Enter client phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Enter client identification document number: ");
        String identDoc = scanner.nextLine();

        System.out.print("Enter client atrdisc: ");
        String atrdisc = scanner.nextLine();

        System.out.print("Enter client citizenship: ");
        String citizenship = scanner.nextLine();
        ctx.beginTransaction();
        // get client with biggest id
        int lastId = ctx.getClients().findClientWithBiggestId() == null ? 0 : ctx.getClients().findClientWithBiggestId().getId();
        Client client = new Client();
        client.setId(lastId + 1);
        client.setName(name);
        client.setEmail(email);
        client.setAddress(address);
        client.setPhoneNumber(phoneNumber);
        client.setIdentDoc(identDoc);
        client.setAtrdisc(atrdisc);
        client.setCitizenship(citizenship);

        System.out.println("Client created: " + client.getName() + " " + client.getEmail() + " " + client.getAddress() + " " + client.getPhoneNumber() + " " + client.getIdentDoc() + " " + client.getAtrdisc() + " " + client.getCitizenship());


        ctx.getClients().create(client);
        ctx.commit();

        System.out.println("createCustomer()");
    }
  
    private void listExistingBikes()
    {
        ctx.getBycicles().find("SELECT b FROM bicicleta b").forEach(b -> {
            System.out.println("Type: " + b.getAtrdisc());
            System.out.println("Code: " + b.getId_bicicleta());
            System.out.println("Weight: " + b.getPeso());
            System.out.println("Model: " + b.getModelo());
            System.out.println("Brand: " + b.getMarca());
            System.out.println("Change System: " + b.getMudanca());
            System.out.println("State: " + b.getEstado());
            System.out.println("GPS: " + b.getGps());
            System.out.println();
        });
        System.out.println("listExistingBikes()");
    }

    private void checkBikeAvailability() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter month: ");
        int month = scanner.nextInt();
        System.out.print("Enter day: ");
        int day = scanner.nextInt();
        System.out.print("Enter hour: ");
        int hour = scanner.nextInt();
        System.out.print("Enter minute: ");
        int minute = scanner.nextInt();
        System.out.print("Enter second: ");
        int second = scanner.nextInt();
        String timestamp = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;

        System.out.print("Enter bike id: ");
        int bikeId = scanner.nextInt();

       System.out.println(ctx.getBycicles().checkAvailability(Timestamp.valueOf(timestamp), bikeId));
        System.out.println("checkBikeAvailability()");

    }

    private void obtainBookings() {
        if(ctx.getBookings().find("SELECT r FROM reserva r").isEmpty())
            System.out.println("No bookings available"  );
        ctx.getBookings().find("SELECT r FROM reserva r").forEach (r ->{
            System.out.println("Número de reserva: " + r.getId());
            System.out.println("Loja: " + r.getShop());
            System.out.println("Data de ínicio: " + r.getInitialDate());
            System.out.println("Data final: " + r.getFinalDate());
            System.out.println("Valor: " + r.getPrice());
            System.out.println("Bicicleta: " + r.getBicycleCode());
        });
        System.out.println("obtainBookings()");
    }

    private void makeBooking()
    {
        // TODO
        System.out.println("makeBooking()");
        
    }

    private void cancelBooking()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter booking id: ");
        int bookingId = scanner.nextInt();
        ctx.beginTransaction();
        ctx.getBookings().delete(ctx.getBookings().findByKey(bookingId));
        ctx.commit();
        System.out.println("cancelBooking");
        
    }
    private void about()
    {
        System.out.println("About");
        System.out.println("Developers:");
        System.out.println("Nuno Aguiar - 49512");
        System.out.println("Bruno Ferreira - 50499");
        System.out.println("Constança Costa - 50541");
        System.out.println("DAL version:"+ isel.sisinf.jpa.Dal.version());
        System.out.println("Core version:"+ isel.sisinf.model.Core.version());
        
    }
}

public class App{
    public static void main(String[] args) throws Exception{
        UI.getInstance().Run();
    }
}