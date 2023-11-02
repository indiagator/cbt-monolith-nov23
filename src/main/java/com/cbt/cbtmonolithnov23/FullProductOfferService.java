package com.cbt.cbtmonolithnov23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FullProductOfferService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductofferRepository productofferRepository;

    public FullProductOffer composeFullOffer(String offerid)
    {
        FullProductOffer fullProductOffer = new FullProductOffer();
        Productoffer productOffer = productofferRepository.findById(offerid).get();

        fullProductOffer.setProductoffer(productOffer);
        fullProductOffer.setAmnt(productOffer.getUnitprice()* productOffer.getQty());
        fullProductOffer.setProduct(productRepository.findById(productOffer.getHscode()).get());

        return fullProductOffer;
    }
}
