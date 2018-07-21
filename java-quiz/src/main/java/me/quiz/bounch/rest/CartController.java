package me.quiz.bounch.rest;

import me.quiz.bounch.mongo.entity.Purchase;
import me.quiz.bounch.mongo.repo.PurchaseRepo;
import me.quiz.bounch.rest.req.PurchaseReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    PurchaseRepo purchaseRepo;

    @PostMapping("purchase")
    public void purchase(@RequestBody PurchaseReq purchaseReq) {
        purchaseRepo.save(
                new Purchase(
                        purchaseReq.items
                                .stream()
                                .map(selectedItem ->
                                        new Purchase.PurchasedItem(
                                                selectedItem.id,
                                                selectedItem.description,
                                                selectedItem.cost
                                        )
                                )
                                .collect(toList()),
                        purchaseReq.sum
                )
        );
    }
}
