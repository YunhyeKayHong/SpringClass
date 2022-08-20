package net.softsociety.shoppinglist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orderlist {
	int order_num;
	String id;
	String name;
	String address;
	String p_name;
	int p_amount;
	int p_price;
	int p_num;
}
