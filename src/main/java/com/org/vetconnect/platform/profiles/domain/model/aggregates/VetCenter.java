package com.org.vetconnect.platform.profiles.domain.model.aggregates;

import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterEmail;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterName;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterPhone;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterRUC;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
    @Setter
    private VetCenterName vetCenterName;

    @Embedded
    @Setter
    private VetCenterEmail vetCenterEmail;

    @Embedded
    @Setter
    private VetCenterRUC vetCenterRUC;

    @Embedded
    @Setter
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
        return this.vetCenterRUC.vetCenterRUC();
    }

    public Long getPhone(){
        return this.vetCenterPhone.getVetCenterPhone();
    }

    public void setName(String name){
        this.vetCenterName = new VetCenterName(name);
    }

    public void setEmail(String email){
        this.vetCenterEmail = new VetCenterEmail(email);
    }

    public void setPhone(Long phone){
        this.vetCenterPhone = new VetCenterPhone(phone);
    }

    public void setRUC(Long ruc){
        this.vetCenterRUC = new VetCenterRUC(ruc);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}