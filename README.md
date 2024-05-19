# SI_2024_lab2_222052
Ана Петрула Цаиќ 222052

# 1. Код: 
 ```java
public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment){ // 1
        if (allItems == null){ // 2
            throw new RuntimeException("allItems list can't be null!"); // 3
        }

        float sum = 0; // 4

        for (int i = 0; i < allItems.size(); i++){ // 5.1 , 5.2 , 5.3 
            Item item = allItems.get(i); // 6
            if (item.getName() == null || item.getName().length() == 0){ // 7
                item.setName("unknown"); // 8
            }
            if (item.getBarcode() != null){ // 9
                String allowed = "0123456789"; // 10
                char chars[] = item.getBarcode().toCharArray(); // 11
                for (int j = 0; j < item.getBarcode().length(); j++){ // 12.1 , 12.2, 12.3
                    char c = item.getBarcode().charAt(j); // 13
                    if (allowed.indexOf(c) == -1){ // 14
                        throw new RuntimeException("Invalid character in item barcode!"); // 15
                    }
                }
                if (item.getDiscount() > 0){ // 16
                    sum += item.getPrice()*item.getDiscount(); // 17
                }
                else {
                    sum += item.getPrice(); // 18
                }
            }
            else {
                throw new RuntimeException("No barcode!"); // 19
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){ // 20
                sum -= 30; // 21
            }
        }
        if (sum <= payment){ // 22
            return true; // 23
        }
        else {
            return false; // 24
        }
    } // 25
}
```

# 2. Control Flow Graph 
![cfg](https://media.discordapp.net/attachments/1054498402592890990/1241776655119417504/Untitled_Diagram.drawio1.png?ex=664b6e21&is=664a1ca1&hm=75a65b4c05a423da0961df7a475f89a0cb80f932ec4c21742dab26b4f7407aa8&=&format=webp&quality=lossless&width=621&height=662)

# 3. Цикломатска комплексност
Пресметуваме според формулата V(G) = E - N + 2 (E - бројот на ребра и N - бројот на јазли). <br/>
Графот има 37 ребра и 29 јазли така што 37 - 29 + 2 = 10.<br/>
Цикломатската комплексност изнесува 10.

# 4. Тест случаи според Every Branch критериумот
Test case 1: allItems = null, payment = 0<br/>
За јазлите: 1, 2, 3 и 25 (null листа)<br/>

Test case 2: allItems = [Item(null, null, 100, 0.2)] payment = 40<br/>
За јазлите: 1, 2, 4, 5.1, 5.2, 6, 7, 8, 9, 19 и 25 (празен баркод и име)<br/>

Test case 3: allItems = [Item("item", "!", 300, 0.3)] payment = 100<br/>
За јазлите: 1, 2, 4, 5.1, 5.2, 6, 7, 9, 10, 11, 12.1, 12.2, 13, 14, 15, 25 (баркодот е невалиден)<br/>

Test case 4: allItems = [Item("item", "0", 350, 0.5)] payment = 160<br/>
За јазлите: 1, 2, 4, 5.1, 5.2, 6, 7, 9, 10, 11, 12.1, 12.2, 13, 14, 12.3, 16, 17, 20, 21, 5.3, 22, 23, 25 (цената е поголема од 300, попустот е поголем од 0, баркодот почнува на 0 и сумата е помала од наплатата)<br/>

Test case 5: allItems = [Item("item", "2", 150, 0)] payment = 140<br/>
За јазлите: јазлите 1, 2, 4, 5.1, 5.2, 6, 7, 9, 10, 11, 12.1, 12.2, 13, 14, 12.3, 16, 18, 20, 5.3, 22, 24, 25 (цената е помала од 300, попустот е еднаков на нула/нема попуст и сумата е поголема од наплатата)<br/>

# 5. Тест случаи според Multiple Branch критериумот
```java
if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')
```
Test case 1: Item("item", "0", 380, 0.5) - TTT<br/>

Test case 2: Item("item", "1", 500, 0.2) - TTF<br/>

Test case 3: Item("item", "0", 400, 0) - TFX<br/>

Test case 4: Item("item", "0", 250, 0.5) - FXX<br/> 
