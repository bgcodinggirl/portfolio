
public class BronzeCard extends DiscountCard{

	BronzeCard(Client owner, float turnover) {
		super(owner, turnover);
	}

	float getDiscountRate() {
		if(turnover<100) return 0f;
		else if(turnover>=100 && turnover <=300) return 0.01f;
		else return 0.025f;
	}

	float calculateDiscount(float purchaseValue) {
		return (purchaseValue * getDiscountRate());
	}
	
}
