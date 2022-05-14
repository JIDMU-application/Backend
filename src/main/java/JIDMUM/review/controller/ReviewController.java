package JIDMUM.review.controller;

import JIDMUM.review.dto.ReviewRequest;
import JIDMUM.review.dto.ReviewResponse;
import JIDMUM.review.model.Review;
import JIDMUM.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping
    public List<Review> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public List<ReviewResponse> getAllReviewsForProduct(@PathVariable UUID id) {
        return service.getAllReviewsForProduct(id);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ReviewRequest review,
                                 BindingResult result) {

        if (result.hasErrors())
            return new ResponseEntity<String>("Invalid request format", HttpStatus.UNPROCESSABLE_ENTITY);

        service.addReview(review);
        return new ResponseEntity<ReviewRequest>(review, HttpStatus.OK);
    }
}
