/**
 * 
 * @author
 *
 */
public class Shelf {

	/**
	 * Create a shelf that can hold items
	 * 
	 * @param numberOfItemsWide
	 * @param numberOfItemsTall
	 * 
	 * GOTCHAS: Negative numbers, and 0, should set the appropriate dimension to 1 so that there is at least room for 1 item
	 */
	public Shelf(int numberOfItemsWide, int numberOfItemsTall){
		
	}

	/**
	 * @return the number of items wide the shelf it
	 */
	public int getNumberOfItemsWide(){
		return -1;
	}

	/**
	 * @return the number of items tall the shelf is
	 */
	public int getNumberOfItemsTall(){
		return -1;
	}
	
	
	/**
	 * This method should tell how many items are in the slot
	 * 
	 * @param slot
	 * @return the number of items in that slot
	 * 
	 * GOTCHAS:
	 * Don't crash on bad slot numbers, but return 0
	 */
	public int getNumberOfItemsAtSlot(int slot){
		return 0;
	}

	/**
	 * This method should tell how many empty locations are in the slot
	 * 
	 * @param slot
	 * @return the number of empty locations in that slot
	 * 
	 * GOTCHAS:
	 * Don't crash on bad slot numbers, but return 0
	 */
	public int getNumberOfEmptySpotsAtSlot(int slot){
		return 0;
	}

	/**
	 * This should add an item to the shelf.
	 * It will probably be a wand, but we are going to program in the general
	 * 
	 * @param o the Item to add
	 * @param slot the slot number at which to add it
	 * @return true if you can add the item, false otherwise
	 * 
	 * GOTCHAS:
	 * Don't add null items
	 * Don't crash on bad slot numbers
	 * Slot's can fill up, so don't let too many items onto a slot
	 * 
	 */
	public boolean addItemToTopAtSlot(Object o, int slot){
		return Math.random()<0.5;
	}
	
	
	/**
	 * This method should return the item that was previously added into that slot
	 * @param slot
	 * @param location
	 * @return the item in the correct location
	 * 
	 * GOTCHAS:
	 * Return null on invalid slot numbers, or invalid location, or empty locations
	 */
	public Object getButDontRemoveItemAtSlotAndLocation(int slot, int location){
		return new Object();
	}
	
	
	/**
	 * This method should return how many total items are on the entire shelf
	 * @return
	 */
	public int getTotalNumberOfItemsOnShelf(){
		return 0;
	}
	
	
	
}
