package com.org.vetconnect.platform.profiles.interfaces.rest.transform;

import com.org.vetconnect.platform.profiles.domain.model.entities.Review;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.ReviewResource;
import org.springframework.stereotype.Component;

@Component
public class ReviewResourceFromEntityAssembler {
    public ReviewResource toResourceFromEntity(Review review) {
        return new ReviewResource(
                review.getId(),
                review.getPetOwner().getId(),
                review.getVetCenter().getId(),
                review.getComments(),
                review.getRating()
        );
    }
}
