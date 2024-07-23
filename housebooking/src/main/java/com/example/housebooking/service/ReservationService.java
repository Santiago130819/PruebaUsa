package com.example.housebooking.service;

import com.example.housebooking.dto.ReservationRequest;
import com.example.housebooking.model.Reservation;
import com.example.housebooking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    // Uso del logger
    public Reservation createReservation(ReservationRequest request) {
        // Validar el descuento usando la API externa
        validateDiscount(request);

        // Crear la entidad de reserva
        Reservation reservation = new Reservation();
        reservation.setUserId(request.getUserId());
        reservation.setHouseId(request.getHouseId());
        reservation.setDiscountCode(request.getDiscountCode());

        // Guardar la reserva en la base de datos
        return reservationRepository.save(reservation);
    }
    private void validateDiscount(ReservationRequest request) {
        // Lógica para validar el descuento
        String url = "https://sbv2bumkomidlxwffpgbh4k6jm0ydskh.lambda-url.us-east-1.on.aws/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, request, Void.class);
    }
}
