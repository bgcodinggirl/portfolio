
public class SilverCard extends DiscountCard{

	SilverCard(Client owner, float turnover) {
		super(owner, turnover);
	}
	
	float getDiscountRate() {
		if(turnover>300) return 0.035f;
		else return 0.02f;
	}

	float calculateDiscount(float purchaseValue) {
		return (purchaseValue * getDiscountRate());
	}

}
