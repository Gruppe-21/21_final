package com.gruppe21;

import com.gruppe21.game.Die;
import org.junit.Test;

public class DieTest {

    @Test
    public void normalDistributionTest() {
        Die die1 = new Die();
        Die die2 = new Die();

        int i = 1;
        double two = 0;
        double three = 0;
        double four = 0;
        double five = 0;
        double six = 0;
        double seven = 0;
        double eight = 0;
        double nine = 0;
        double ten = 0;
        double eleven = 0;
        double twelve = 0;
        while (i <= 1000000) { //Throws the die 1000000 times
            die1.rollDie();
            die2.rollDie();

            switch (die1.getValue() + die2.getValue()) {
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
                case 5:
                    five++;
                    break;
                case 6:
                    six++;
                    break;
                case 7:
                    seven++;
                    break;
                case 8:
                    eight++;
                    break;
                case 9:
                    nine++;
                    break;
                case 10:
                    ten++;
                    break;
                case 11:
                    eleven++;
                    break;
                case 12:
                    twelve++;
                    break;
                default:
                    System.out.println("Error");
                    break;

            }
            i++;
        }

        System.out.println("Total times getting 2: " + two);
        System.out.println("Total times getting 3: " + three);
        System.out.println("Total times getting 4: " + four);
        System.out.println("Total times getting 5:" + five);
        System.out.println("Total times getting 6: " + six);
        System.out.println("Total times getting 7: " + seven);
        System.out.println("Total times getting 8: " + eight);
        System.out.println("Total times getting 9: " + nine);
        System.out.println("Total times getting 10:" + ten);
        System.out.println("Total times getting 11:" + eleven);
        System.out.println("Total times getting 12: " + twelve);

    }
}