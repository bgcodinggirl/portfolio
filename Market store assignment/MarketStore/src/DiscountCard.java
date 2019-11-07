
public abstract class DiscountCard {
		Client owner;
		float turnover;
		float discountRate;
		
		DiscountCard(Client owner, float turnover, float discountRate){
			this.owner = owner;
			this.turnover = turnover;
			this.discountRate = discountRate;
		}
		
		DiscountCard(Client owner, float turnover){
			this.owner = owner;
			this.turnover = turnover;
		}
		
		abstract float getDiscountRate();
		abstract float calculateDiscount(float purchaseValue);
	
}
