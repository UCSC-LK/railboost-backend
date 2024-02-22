package org.ucsc.railboostbackend.controllers;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.ucsc.railboostbackend.models.ParcelBooking;
import org.ucsc.railboostbackend.repositories.ParcelBookingRepo;
import org.ucsc.railboostbackend.services.MyCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class ParcelBookingController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException {
        Claims jwt = (Claims) req.getAttribute("jwt");
        Gson gson = new Gson();
        ParcelBookingRepo parcelBookingRepo = new ParcelBookingRepo();

        ParcelBooking parcelBooking = gson.fromJson(req.getReader(), ParcelBooking.class);
        parcelBooking.setUserId(jwt.get("userId", Integer.class));
        parcelBooking.setTrackingId(new MyCache().createTempUID(new Date().toString()));

        try {
            parcelBookingRepo.addParcel(parcelBooking);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
