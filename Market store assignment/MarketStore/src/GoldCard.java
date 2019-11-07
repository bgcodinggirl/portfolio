
public class GoldCard extends DiscountCard{

	GoldCard(Client owner, float turnover) {
		super(owner, turnover);
	}
	
	int value=(((int)turnover/100)+2);
	
	float getDiscountRate() {
		if(turnover<100) return 0.02f;
		else if(turnover>=800) return 0.1f;
		else return (float)value/100;
	}

	float calculateDiscount(float purchaseValue) {
		return (purchaseValue * getDiscountRate());
	}
	
}
