import java.sql.*;

public class KFTest {   
   public static void main(String[] args) {
      try (
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/test", "root", "xxxx");   
         Statement stmt = conn.createStatement();
      ) {
        
         String selectClients = "SELECT * FROM Clients";
         ResultSet clientsResult = stmt.executeQuery(selectClients);
         System.out.println("Clients table:");
         while(clientsResult.next()) {   
            int clientId = clientsResult.getInt("client_id");
            String name = clientsResult.getString("name");
            String email = clientsResult.getString("email");
            String phone = clientsResult.getString("phone");
            String address = clientsResult.getString("address");
            System.out.println(clientId + ", " + name + ", " + email + ", " + phone + ", " + address);
         }
         System.out.println();

       
         String selectInvoices = "SELECT * FROM Invoices";
         ResultSet invoicesResult = stmt.executeQuery(selectInvoices);
         System.out.println("Invoices table:");
         while(invoicesResult.next()) {   
            int invoiceId = invoicesResult.getInt("invoice_id");
            int clientId = invoicesResult.getInt("client_id");
            Date invoiceDate = invoicesResult.getDate("invoice_date");
            double totalAmount = invoicesResult.getDouble("total_amount");
            System.out.println(invoiceId + ", " + clientId + ", " + invoiceDate + ", " + totalAmount);
         }
         System.out.println();

   
         String selectServiceBooking = "SELECT * FROM Service_Booking";
         ResultSet serviceBookingResult = stmt.executeQuery(selectServiceBooking);
         System.out.println("Service_Booking table:");
         while(serviceBookingResult.next()) {   
            int bookingId = serviceBookingResult.getInt("booking_id");
            int clientId = serviceBookingResult.getInt("client_id");
            Date bookingDate = serviceBookingResult.getDate("booking_date");
            String serviceDescription = serviceBookingResult.getString("service_description");
            System.out.println(bookingId + ", " + clientId + ", " + bookingDate + ", " + serviceDescription);
         }
         System.out.println();

        
         String selectServiceInquiry = "SELECT * FROM Service_Inquiry";
         ResultSet serviceInquiryResult = stmt.executeQuery(selectServiceInquiry);
         System.out.println("Service_Inquiry table:");
         while(serviceInquiryResult.next()) {   
            int inquiryId = serviceInquiryResult.getInt("inquiry_id");
            int clientId = serviceInquiryResult.getInt("client_id");
            Date inquiryDate = serviceInquiryResult.getDate("inquiry_date");
            String inquiryDetails = serviceInquiryResult.getString("inquiry_details");
            System.out.println(inquiryId + ", " + clientId + ", " + inquiryDate + ", " + inquiryDetails);
         }
         System.out.println();

         
         String selectPayment = "SELECT * FROM Payment";
         ResultSet paymentResult = stmt.executeQuery(selectPayment);
         System.out.println("Payment table:");
         while(paymentResult.next()) {   
            int paymentId = paymentResult.getInt("payment_id");
            int invoiceId = paymentResult.getInt("invoice_id");
            double amountPaid = paymentResult.getDouble("amount_paid");
            Date paymentDate = paymentResult.getDate("payment_date");
            System.out.println(paymentId + ", " + invoiceId + ", " + amountPaid + ", " + paymentDate);
         }
         System.out.println();

      } catch(SQLException ex) {
         ex.printStackTrace();
      }
   }
}