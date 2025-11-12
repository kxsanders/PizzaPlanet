package org.example;

import org.example.EnumClasses.CrustType;
import org.example.EnumClasses.Size;

import java.util.List;

public class SignaturePizza extends Pizza {
    private String signatureName;

    public SignaturePizza(String signatureName,
                          Size size,
                          double basePrice,
                          CrustType crustType,
                          boolean stuffedCrust,
                          List<Topping> defaultToppings) {
        super(signatureName, size, basePrice, crustType, stuffedCrust);
        this.signatureName = signatureName;

        //Apply default toppings to this pizza
        if (defaultToppings != null) {
            for (Topping topping : defaultToppings) {
                addTopping(topping);
            }
        }
    }

    public String getSignatureName() {
        return signatureName;
    }

    @Override
    public String toString() {
        return "(Signature) " + super.toString();
    }
}
