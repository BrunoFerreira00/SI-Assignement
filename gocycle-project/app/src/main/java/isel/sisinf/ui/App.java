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
import isel.sisinf.model.ClientReservationId;
import isel.sisinf.model.Reservation;
import isel.sisinf.model.Shop;

import java.math.BigDecimal;
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
        cancelBookingOptimistic,
        testOptimisticLocking,
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
        __dbMethods.put(Option.cancelBookingOptimistic, new DbWorker() {public void doWork() {UI.this.cancelBookingOptimistic();}});
        __dbMethods.put(Option.testOptimisticLocking, new DbWorker() {public void doWork() {UI.this.testOptimisticLocking();}});

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
            System.out.println("8. Cancel Booking Optimistic");
            System.out.println("9. Test optimistic Lock");
            System.out.println("10. About");
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
            System.out.println("GPS: " + b.getGps().getNoserie());
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
            System.out.println("Número de reserva: " + r.getNoreserva());
            System.out.println("Loja: " + r.getLoja().getCodigo());
            System.out.println("Data de ínicio: " + r.getDtinicio());
            System.out.println("Data final: " + r.getDtfim());
            System.out.println("Valor: " + r.getValor());
            System.out.println("Bicicleta: " + r.getBicicleta().getId_bicicleta());
        });
        System.out.println("obtainBookings()");
    }

    private void makeBooking() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter shop ID: ");
        int shopId = scanner.nextInt();

        System.out.print("Enter year for initial date: ");
        int initYear = scanner.nextInt();
        System.out.print("Enter month for initial date: ");
        int initMonth = scanner.nextInt();
        System.out.print("Enter day for initial date: ");
        int initDay = scanner.nextInt();
        System.out.print("Enter hour for initial date: ");
        int initHour = scanner.nextInt();
        System.out.print("Enter minute for initial date: ");
        int initMinute = scanner.nextInt();
        System.out.print("Enter second for initial date: ");
        int initSecond = scanner.nextInt();
        String initialDateStr = initYear + "-" + initMonth + "-" + initDay + " " + initHour + ":" + initMinute + ":" + initSecond;
        Timestamp initialDate = Timestamp.valueOf(initialDateStr);

        System.out.print("Enter year for final date: ");
        int finalYear = scanner.nextInt();
        System.out.print("Enter month for final date: ");
        int finalMonth = scanner.nextInt();
        System.out.print("Enter day for final date: ");
        int finalDay = scanner.nextInt();
        System.out.print("Enter hour for final date: ");
        int finalHour = scanner.nextInt();
        System.out.print("Enter minute for final date: ");
        int finalMinute = scanner.nextInt();
        System.out.print("Enter second for final date: ");
        int finalSecond = scanner.nextInt();
        String finalDateStr = finalYear + "-" + finalMonth + "-" + finalDay + " " + finalHour + ":" + finalMinute + ":" + finalSecond;
        Timestamp finalDate = Timestamp.valueOf(finalDateStr);

        System.out.print("Enter reservation value: ");
        double value = scanner.nextDouble();

        System.out.print("Enter bicycle ID: ");
        int bicycleId = scanner.nextInt();

        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();

        // Ensure that the final date is after the initial date
        if (finalDate.before(initialDate)) {
            System.out.println("Final date must be after initial date.");
            return;
        }




        Reservation reservation = new Reservation();
        Shop shop = ctx.getShops().findShopByKey(shopId);

        reservation.setLoja(shop); // shopId
        reservation.setDtinicio(initialDate);
        reservation.setDtfim(finalDate);
        reservation.setValor(BigDecimal.valueOf(value));
        reservation.setNoreserva(bicycleId);

        ctx.getBookings().createReservation(reservation,clientId);

        // Create the client-reservation relationship


        System.out.println("Reservation created successfully!");
        System.out.println("makeBooking()");
    }

    private void cancelBooking()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter booking id: ");
        int bookingId = scanner.nextInt();

        System.out.print("Enter client id: ");
        int clientId = scanner.nextInt();

        System.out.print("Enter shop id: ");
        int shopId = scanner.nextInt();

        ClientReservationId clientBookingId = new ClientReservationId();
        clientBookingId.setCliente(clientId);
        clientBookingId.setReserva(bookingId);
        clientBookingId.setLoja(shopId);

        ctx.beginTransaction();
        ctx.getClientBookings().delete(ctx.getClientBookings().findByEmbeddedKey(clientBookingId));
        ctx.commit();



        ctx.beginTransaction();
        ctx.getBookings().delete(ctx.getBookings().findByKey(bookingId));
        ctx.commit();
        System.out.println("cancelBooking");
        
    }

    private void cancelBookingOptimistic()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter booking id: ");
        int bookingId = scanner.nextInt();

        System.out.print("Enter client id: ");
        int clientId = scanner.nextInt();

        System.out.print("Enter shop id: ");
        int shopId = scanner.nextInt();

        ClientReservationId clientBookingId = new ClientReservationId();
        clientBookingId.setCliente(clientId);
        clientBookingId.setReserva(bookingId);
        clientBookingId.setLoja(shopId);

        ctx.beginTransaction();
        ctx.getClientBookings().deleteOptmisticLocking(ctx.getClientBookings().findByEmbeddedKey(clientBookingId));
        ctx.getBookings().deleteOptmisticLocking(ctx.getBookings().findByKey(bookingId));
        ctx.commit();
        System.out.println("cancelBooking");

    }

    private void testOptimisticLocking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter booking id to test optimisticLock");
        int bookingID = scanner.nextInt();
        System.out.println("Enter client id to test optimisticLock");
        int clientID = scanner.nextInt();
        System.out.println("Enter shop id to test optimisticLock");
        int shopID = scanner.nextInt();
        ClientReservationId clientBookingId = new ClientReservationId();
        clientBookingId.setCliente(clientID);
        clientBookingId.setReserva(bookingID);
        clientBookingId.setLoja(shopID);

        try {
            ctx.beginTransaction();
            Reservation booking1 = ctx.getBookings().findByKey(bookingID);
            booking1.setValor(BigDecimal.valueOf(1000));
            ctx.commit();

            ctx.beginTransaction();
            Reservation booking2 = ctx.getBookings().findByKey(bookingID);

            ctx.getClientBookings().deleteOptmisticLocking(ctx.getClientBookings().findByEmbeddedKey(clientBookingId));
            ctx.commit();

            ctx.beginTransaction();
            ctx.getBookings().deleteOptmisticLocking(booking2);
            ctx.commit();

            System.out.println("Optimistic lock success");
        } catch (Exception e) {
            ctx.rollback();
            System.out.println("Optimistic lock failed");
        }
        System.out.println("testOptimisticLocking()");
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