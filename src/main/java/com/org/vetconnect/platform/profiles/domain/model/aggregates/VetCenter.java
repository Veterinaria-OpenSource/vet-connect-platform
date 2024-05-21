package com.org.vetconnect.platform.profiles.domain.model.aggregates;

import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterEmail;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterName;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterPhone;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterRUC;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class VetCenter extends AbstractAggregateRoot<VetCenter> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private VetCenterName vetCenterName;

    @Embedded
    private VetCenterEmail vetCenterEmail;

    @Embedded
    private VetCenterRUC vetCenterRUC;

    @Embedded
    private VetCenterPhone vetCenterPhone;


    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String updatedAt;

    public VetCenter(String name, String email, Long ruc, Long phone){
        this.vetCenterName = new VetCenterName(name);
        this.vetCenterEmail = new VetCenterEmail(email);
        this.vetCenterRUC = new VetCenterRUC(ruc);
        this.vetCenterPhone = new VetCenterPhone(phone);
    }

    public VetCenter(){

    }

    public String getName(){
        return this.vetCenterName.getFullName();
    }

    public String getEmail(){
        return this.vetCenterEmail.email();
    }

    public Long getRUC(){
        return this.vetCenterRUC.getVetCenterRUC();
    }

    public Long getPhone(){
        return this.vetCenterPhone.getVetCenterPhone();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
