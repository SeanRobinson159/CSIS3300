public class ShelfGrader {

	public static void main(String[] args) {
		int points = 100;
		int deduction = 100 / 11;

		try {
			// Create a shelf that can hold items
			// GOTCHAS: Negative numbers should set the dimension to 0
			// public Shelf(int numberOfItemsWide, int numberOfItemsTall)
			boolean widthAndHeight = true;
			boolean emptySlots = true;
			for (int i = 1; i < 100; i++) {
				for (int j = 1; j < 100; j++) {
					Shelf s = new Shelf(i, j);
					if (widthAndHeight == true && (s.getNumberOfItemsWide() != i || s.getNumberOfItemsTall() != j)) {
						System.err.println("A new shelf should have the correct width and height");
						points -= deduction;
						widthAndHeight = false;
					}

					for (int k = 0; k < i; k++) {
						if (emptySlots == true && (s.getNumberOfEmptySpotsAtSlot(k) != j || s.getNumberOfItemsAtSlot(k) != 0)) {
							System.err.println("A new shelf should have all slots empty, and no items in any slot");
							points -= deduction;
							emptySlots = false;
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("I didn't have enough time to correctly implement this, so I am docking you for all");
			System.err.println("A new shelf should have the correct width and height");
			System.err.println("A new shelf should have all slots empty, and no items in any slot");
			points -= deduction;
			points -= deduction;
		}

		try {
			// Bad values in the constructor
			boolean wideAndTallWorks = true;
			boolean emptyWorks = true;
			for (int i = -100; i < 100; i++) {
				for (int j = -100; j < 100; j++) {
					if (i < 1 || j < 1) {
						Shelf s = new Shelf(i, j);
						if (wideAndTallWorks == true && ((i <= 0 && s.getNumberOfItemsWide() != 1) || (j <= 0 && s.getNumberOfItemsTall() != 1))){
							System.err.println("Bad values for the constructor should set the appropriate dimention to 1");
							points -= deduction;
							wideAndTallWorks = false;
						}
						for (int k = 0; k < i; k++) {
							int items = j;
							if (j < 1){
								items = 1;
							}
							if (emptyWorks == true && (s.getNumberOfEmptySpotsAtSlot(k) != items || s.getNumberOfItemsAtSlot(k) != 0)){
								System.err.println("Bad values for the constructor should have all empty slots and no items in any slot");
								points -= deduction;
								emptyWorks = false;
							}
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("I didn't have enough time to correctly implement this, so I am docking you for all");
			System.err.println("Bad values for the constructor should have all empty slots and no items in any slot");
			points -= deduction;
			points -= deduction;
		}

		// This should add an item to the shelf.
		// It will probably be a wand, but we are going to program in the
		// general
		// GOTCHAS:
		// Don't add null items
		// Don't crash on bad slot numbers
		// Slot's can fill up, so don't let too many items onto a slot
		// public boolean addItemToTopAtSlot(Object i, int slot){
		try{
			boolean returnWorks = true;
			boolean valuesWork = true;
			boolean totalItemsOnShelfWorks = true;
			int totalItems = 0;

			Shelf s = new Shelf(100,200);
			Object[][] wands = new Object[100][200];
			for (int i = 0; i < 100; i++){
				for (int j = 0; j < 200; j++){
					MagicWand m = new MagicWand();
					wands[i][j] = m;
					boolean b = s.addItemToTopAtSlot(m, i);
					totalItems++;
					if(returnWorks && b != true){
						System.err.println("Objects should be able to be added to the correct location");
						points -= deduction;
						returnWorks = false;
					}
					if(valuesWork == true && (s.getNumberOfItemsAtSlot(i) != j+1 || s.getNumberOfEmptySpotsAtSlot(i) != 200-j-1)){
						System.err.println("The number of items (and empty items) in each slot should fill up correctly");
						points -= deduction;
						valuesWork = false;
					}
					if(totalItemsOnShelfWorks == true && s.getTotalNumberOfItemsOnShelf() != totalItems){
						System.err.println("The total items on your shelf doesn't work correctly");
						points -= deduction;
						totalItemsOnShelfWorks = false;
					}
				}
			}
			for (int i = 0; i < 100; i++){
				for (int j = 0; j < 200; j++){
					if(s.getButDontRemoveItemAtSlotAndLocation(i, j) != wands[i][j]){
						System.err.println("The correct item should be returned when asked for");
						points -= deduction;
						i = 100;
						break;
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("I didn't have enough time to correctly implement this, so I am docking you for all");
			System.err.println("Objects should be able to be added to the correct location");
			System.err.println("The number of items (and empty items) in each slot should fill up correctly");
			System.err.println("The correct item should be returned when asked for");
			System.err.println("The total items on your shelf doesn't work correctly");
			points -= deduction;
			points -= deduction;
			points -= deduction;
			points -= deduction;

		}
		// GOTCHAS:
		// Don't add null items
		// Don't crash on bad slot numbers
		// Slot's can fill up, so don't let too many items onto a slot
		// public boolean addItemToTopAtSlot(Object i, int slot){
		try{
			boolean returnWorks = true;
			boolean overTheTopWorks = true;

			Shelf s = new Shelf(100,200);
			Object[][] wands = new Object[100][200];
			for (int i = 0; i < 100; i++){
				for (int j = 0; j < 200; j++){
					MagicWand m = new MagicWand();
					wands[i][j] = m;
					boolean b = s.addItemToTopAtSlot(m, i);
					b = s.addItemToTopAtSlot(null, i);
					if(returnWorks == true && (b == true || (s.getNumberOfItemsAtSlot(i) != j+1 || s.getNumberOfEmptySpotsAtSlot(i) != 200-j-1))){
						System.err.println("When adding null, you either return true or have the incorrect number of items in your slots");
						points -= deduction;
						returnWorks = false;
					}
				}
				for (int j = 0; j < 10; j++){
					MagicWand m = new MagicWand();
					boolean b = s.addItemToTopAtSlot(m, i);
					if(overTheTopWorks == true && b == true){
						System.err.println("When adding too many items to a slot, you return true, and maybe add it");
						points -= deduction;
						overTheTopWorks = false;
					}
				}				
			}
			for (int i = -100; i < 200; i++){
				for (int j = -100; j < 300; j++){
					if(i < 0 || j < 0 || i > 100 || j > 200){
						if(s.getButDontRemoveItemAtSlotAndLocation(i, j) != null){
							System.err.println("Bad locations for the getButDontRemoveItemAtSlotAndLocation should return null");
							points -= deduction;
							i = 200;
							break;
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("When adding null, you either return true or have the incorrect number of items in your slots");
			System.err.println("When adding too many items to a slot, you return true, and maybe add it");
			System.err.println("Bad locations for the getButDontRemoveItemAtSlotAndLocation should return null");
			points -= deduction;
			points -= deduction;
			points -= deduction;

		}

		
		System.out.println("\n\nYour graded percentage for this assignment is: " + points);

	}

}
