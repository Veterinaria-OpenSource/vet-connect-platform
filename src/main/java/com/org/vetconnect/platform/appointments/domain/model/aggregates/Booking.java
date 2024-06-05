package com.org.vetconnect.platform.appointments.domain.model.aggregates;

import com.org.vetconnect.platform.appointments.domain.model.valueobjects.BookingDetails;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // para usar created_at y updated_at
@Entity
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_owner_id", nullable = false)
    @Getter
    private PetOwner petOwner;

    @ManyToOne
    @JoinColumn(name = "vet_center_id", nullable = false)
    @Getter
    private VetCenter vetCenter;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ServiceType serviceType;

    @Embedded
    @Getter
    @Setter
    private BookingDetails bookingDetails;

    @Getter
    @Setter
    private Double price;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDateTime date;

    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String updatedAt;

    public Booking(PetOwner petOwner, VetCenter vetCenter, ServiceType serviceType, BookingDetails bookingDetails, Double price, LocalDateTime date) {
        this.petOwner = petOwner;
        this.vetCenter = vetCenter;
        this.serviceType = serviceType;
        this.bookingDetails = bookingDetails;
        this.price = price;
        this.date = date;
    }

    public Booking() {
    }

}
