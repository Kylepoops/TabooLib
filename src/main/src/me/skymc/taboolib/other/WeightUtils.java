package me.skymc.taboolib.other;

import java.util.List;
import java.util.Random;

import me.skymc.taboolib.Main;
import me.skymc.taboolib.object.WeightCategory;

public class WeightUtils {  
	
    public static String getStringByWeight(List<WeightCategory> categorys) {
    	
    	int weightSum = 0;    
        for (WeightCategory wc : categorys) {    
            weightSum += wc.getWeight();    
        }    
          
        if (weightSum <= 0) {    
           return null;    
        }    
        
        Integer n = NumberUtils.getRand().nextInt(weightSum);
        Integer m = 0;    
        
        for (WeightCategory wc : categorys) {    
        	if (m <= n && n < m + wc.getWeight()) {    
            	return wc.getCategory();
        	}    
            m += wc.getWeight();    
        }    
        return null;
    }
 
}    