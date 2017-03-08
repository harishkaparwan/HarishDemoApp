import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

public class GroceryBagger {
	/* Sample Item Type as provided in document*/
	 static String itemType_1[]={"DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE", "MEAT"};
 	 static String itemType_2[]={};
	 static String itemType_3[]={"CANNED", "CANNED", "PRODUCE", "DAIRY", "MEAT", "BREAD", "HOUSEHOLD","PRODUCE", "FROZEN", "PRODUCE", "DAIRY"};
	 static String itemType_4[]={"CANNED", "CANNED", "PRODUCE", "DAIRY", "MEAT", "BREAD", "HOUSEHOLD","PRODUCE", "FROZEN", "PRODUCE", "DAIRY"};
	 /* Case:- Item Type which doesn't fall under  A-Z]  */
	 static String itemType_5[]={"abc45"};
	 /* Case:- Item Type exceeded  more then 50   */
	 static String itemType_6[]={"DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE","DAIRY", "DAIRY", "PRODUCE", "PRODUCE", "PRODUCE" };
	 /* Case:- Item name exceeded  more then 50   */
	 static String itemType_7[]={"ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXY"};

	public int MinimumBags(int strength, String[] itemType) {
		 int minimumBagsRequired=0,numberOfItemType=0;
		 HashMap<String, Integer> itemTypeCounterMap= new HashMap<String, Integer> ();
		 Pattern pattern = Pattern.compile("^[A-Z]*$");
		 try {
			 if(strength < 1 || strength > 50 ){
				 throw new Exception("Validation Error:- Strength will be between 1 and 50.");
			 }
			 if(itemType.length < 0 || itemType.length > 50 ){
				 throw new Exception("Validation Error:- ItemType will contain between 0 and 50 elements.");

			 }
			for(int i=0; i<itemType.length;i++){
				numberOfItemType=0;
				String item=itemType[i];
				if(item.length() < 1 || item.length() >50){
					 throw new Exception("Validation Error:- Each element of itemType will contain between 1 and 50 characters.");
				}
				if(!pattern.matcher(item).matches()){
 					 throw new Exception("Validation Error:- Each element of itemType will contain only the characters 'A'-'Z'.");
				}
				if(null!=itemTypeCounterMap.get(item)){
					numberOfItemType=(int)itemTypeCounterMap.get(item);
					numberOfItemType++;
					itemTypeCounterMap.put(item, numberOfItemType);
					
				}else{
					numberOfItemType=numberOfItemType+1;
					itemTypeCounterMap.put(item, numberOfItemType);
				}
			}
			Set<String> hashSet=itemTypeCounterMap.keySet();
			Iterator<String> iterator=hashSet.iterator();
			while(iterator.hasNext()){
				String item=""+iterator.next();
				int value=(int) itemTypeCounterMap.get(item);
				int d = value%strength;
			    int r = value/strength;
			    if(r==0){
			    	minimumBagsRequired=minimumBagsRequired+1;
			    }else{
			    	minimumBagsRequired=minimumBagsRequired+(d)+ (r);
			    }

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
 		return minimumBagsRequired;
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GroceryBagger groceryBagger= new GroceryBagger();

		System.out.println("***Case***strength=2 and Outout will be 4---"+(groceryBagger.MinimumBags(2,itemType_1)));
		System.out.println("***Case***Casestrength=3 and Outout will be 3---"+(groceryBagger.MinimumBags(3,itemType_1)));
		System.out.println("***Case***strength=3.itemType=0 and Outout will 3---"+(groceryBagger.MinimumBags(10,itemType_2)));
		System.out.println("***Case***strength=5 and Outout will be 7----"+(groceryBagger.MinimumBags(5,itemType_3)));
		System.out.println("***Case***strength=2 and Outout will be 8----"+(groceryBagger.MinimumBags(2,itemType_4)));

		/*  Validation Error */
		System.out.println("***Case***Item Type which doesnt fall under  A-Z]---"+(groceryBagger.MinimumBags(2,itemType_5)));
		System.out.println("***Case***Item Type exceeded  more then 50 ---"+(groceryBagger.MinimumBags(3,itemType_6)));
		System.out.println("***Case***Item name exceeded  more then 50---"+(groceryBagger.MinimumBags(2,itemType_7)));
		System.out.println("***Case***Strength will be between 1 and 50.---"+(groceryBagger.MinimumBags(51,itemType_5)));
		
	}

}
