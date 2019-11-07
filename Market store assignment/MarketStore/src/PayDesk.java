import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PayDesk {

	public static void printInfo( DiscountCard discountCard, float purchaseValue) {
		NumberFormat formatter = new DecimalFormat("#0.00");  
		float discountRate = discountCard.getDiscountRate() * 100;
		float discount = discountCard.calculateDiscount(purchaseValue);
		switch(discountCard.getClass().getName().toString()) {
			case "BronzeCard": {System.out.print("Bronze: "); break;}
			case "SilverCard": {System.out.print("Silver: "); break;}
			case "GoldCard": {System.out.print("Gold: "); break;}
			default: {System.out.print("Discount card: "); break;}
		}
		
		System.out.println("\na.Mock data: turnover $"+discountCard.turnover+", purchase value $"+purchaseValue);
		System.out.println("b.Output:\n\n* Purchase value: $"+ formatter.format(purchaseValue)+
				"\n* Discount rate: "+ discountRate +
				"%\n* Discount: $"+formatter.format(discount)+
				"\n* Total: $"+formatter.format(purchaseValue-discount)+"\n");
	}
	
	public static void main(String[] args) {
		Client firstOwner = new Client("Sanie Mehmet","+359899616894");
		Client secondOwner = new Client("Maria Daneva","0886524789");
		Client thirdOwner = new Client("Ivan Ivanov","0872341569");
		
		BronzeCard bronzeCard = new BronzeCard(firstOwner,0);
		SilverCard silverCard = new SilverCard(secondOwner,600);
		GoldCard goldCard = new GoldCard(thirdOwner,1500);
		
		printInfo(bronzeCard,150);
		printInfo(silverCard,850);
		printInfo(goldCard,1300);
	}

}
