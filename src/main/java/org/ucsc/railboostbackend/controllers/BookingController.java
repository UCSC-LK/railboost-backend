package org.ucsc.railboostbackend.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Claims;
import org.ucsc.railboostbackend.models.Booking;
import org.ucsc.railboostbackend.repositories.BookingRepo;
import org.ucsc.railboostbackend.services.CustomRequest;
import org.ucsc.railboostbackend.services.LocalDateDeserializer;
import org.ucsc.railboostbackend.services.LocalDateSerializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.*;

public class BookingController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpServletRequest wrappedReq = new CustomRequest(req);
        PrintWriter writer = resp.getWriter();
        Claims jwt = (Claims) req.getAttribute("jwt");
        Object id = jwt.get("userId");
        BookingRepo bookingRepo = new BookingRepo();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();


        Booking booking;
        booking = gson.fromJson(req.getReader(), Booking.class);
        bookingRepo.bookTicketAndSendEmail(booking,id);

        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter writer = resp.getWriter();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .setDateFormat("MM/dd/yyyy")
                .create();
        BookingRepo bookingRepo = new BookingRepo();

        String sid = req.getParameter("id");

        if (sid != null){
//            int id = Integer.parseInt(sid);
            Booking booking = bookingRepo.getTicketDetails(sid);
            if (booking != null){
                writer.write(gson.toJson(booking));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        writer.flush();
        writer.close();
    }
}
