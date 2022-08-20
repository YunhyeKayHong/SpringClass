package net.softsociety.shoppinglist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Itemlist {
	int p_num;
	String p_name;
	int p_amount;
	int p_price;
	
}
