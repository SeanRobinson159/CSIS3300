/**
 * This is the code that I will used to grade your MagicWand.java file
 *  
 * @author Dr. B.
 *
 */
public class MagicWandGrader {

	public static void main(String[] args) {
		int points = 100;
		int deduction = 100/8;
		

		//No argument constructor
		MagicWand one = new MagicWand();
		int magic = one.getCurrentMagicCharge();
		String name = one.getBrandName();
		String core = one.getMagicCore();
		if(magic != 2000){
			System.err.println("Creating a new MagicWand using the no argument constructor should have a current magic charge of 2000.  This error could also be caused by your getter methods not returning the correct values");
			points -= deduction;
		}
		else if(name.equals("Ollivander") == false){
			System.err.println("Creating a new MagicWand using the no argument constructor should have a brand name of 'Ollivander'.  This error could also be caused by your getter methods not returning the correct values");
			points -= deduction;
		}
		else if(core.equals("Dragon heartstring") == false){
			System.err.println("Creating a new MagicWand using the no argument constructor should have a magic core of 'Dragon heartstring'.  This error could also be caused by your getter methods not returning the correct values");
			points -= deduction;
		}

		//2 argument constructor
		int rand = (int)(Math.random()*5000);
		MagicWand two = new MagicWand(rand, "Knock Off Brand");
		magic = two.getCurrentMagicCharge();
		name = two.getBrandName();
		core = two.getMagicCore();
		if(magic != rand || name.equals("Knock Off Brand") == false || core.equals("Unicorn tail hair") == false){
			System.err.println("Creating a new MagicWand using the 2 argument constructor should have a current magic charge and a brand name of what was passed in and a magic core of 'Unicorn tail hair'.  This error could also be caused by your getter methods not returning the correct values");
			points -= deduction;
		}
		
		//2 argument constructor with bad value.
		rand = (int)(Math.random()*5000)*-1;
		MagicWand three = new MagicWand(rand, "Knock Off Brand 3");
		magic = three.getCurrentMagicCharge();
		name = three.getBrandName();
		core = three.getMagicCore();
		if(magic != 0){
			System.err.println("Creating a new MagicWand using the 2 argument constructor and negative magic value should have a current magic charge of 0.  This error could also be caused by your getter methods not returning the correct values");
			points -= deduction;
		}
		else if(name.equals("Knock Off Brand 3") == false ){
			System.err.println("Creating a new MagicWand using the 2 argument constructor and negative magic value should have a brand name of what was passed in.  This error could also be caused by your getter methods not returning the correct values");
			points -= deduction;
		}
		else if(core.equals("Unicorn tail hair") == false){
			System.err.println("Creating a new MagicWand using the 2 argument constructor and negative magic value should have a magic core of 'Unicorn tail hair'.  This error could also be caused by your getter methods not returning the correct values");
			points -= deduction;
		}

		//A few basic spells
		MagicWand smallPower = new MagicWand(99,"Found on the street");
		boolean deduct = false;
		for (int i = 0; i < 19; i++){
			if (one.castSpell(100) == false || smallPower.castSpell(1) == false){
				deduct = true;
				break;
			}
		}

		//make sure spells over the limit do not fail
		int p1 = smallPower.getCurrentMagicCharge();
		int p2 = one.getCurrentMagicCharge();
		if(smallPower.castSpell(p1+1) == true || one.castSpell(p2*10) == true){
			System.err.println("Casting spells over the current limit does not seem to work");
			points -= deduction;
		}

		//Test spells with negative numbers
		p1 = smallPower.getCurrentMagicCharge();
		p2 = one.getCurrentMagicCharge();
		if(smallPower.castSpell(-60) == true || one.castSpell(-1) == true || smallPower.getCurrentMagicCharge() != p1 || one.getCurrentMagicCharge() != p2){
			System.err.println("Casting spells using negative numbers does not seem to work, or it changes the current charge");
			points -= deduction;
		}

		//Make sure spells at the limit do not fail
		p1 = smallPower.getCurrentMagicCharge();
		p2 = one.getCurrentMagicCharge();
		if(smallPower.castSpell(p1) == false || one.castSpell(p2) == false || deduct){
			System.err.println("Casting spells with good values does not seem to work");
			points -= deduction;
		}
		
		one.rechargeWand();
		smallPower.rechargeWand();
		if(one.getCurrentMagicCharge() != 2000 || smallPower.getCurrentMagicCharge() != 99){
			System.err.println("It looks like your rechargeWand method is charging to an incorrect number");
			points -= deduction;
		}
		
		p1 = smallPower.getCurrentMagicCharge();
		smallPower.castSpell(p1);
		if(smallPower.hasCharge() == true || one.hasCharge() == false || three.hasCharge() == true || two.hasCharge() == false){
			System.err.println("It looks like your hasCharge method is not working correctly.  It could also be some of your other methods not doing their job properly");
			points -= deduction;
		}
		
		
		System.out.println("\n\nYour graded percentage for this assignment is: " + points);
	}

}
